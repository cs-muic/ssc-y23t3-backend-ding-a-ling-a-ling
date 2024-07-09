package ding_a_ling_a_ling.dating_webapp.domain.common.security;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.util.ThrowableAnalyzer;
import org.springframework.web.filter.GenericFilterBean;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ApiRequestAccessDeniedExceptionTranslationFilter extends GenericFilterBean {

  private ThrowableAnalyzer throwableAnalyzer = new DefaultThrowableAnalyzer();

  @Override
  public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
    throws IOException, ServletException {

    HttpServletRequest request = (HttpServletRequest) req;
    HttpServletResponse response = (HttpServletResponse) res;

    try {
      chain.doFilter(request, response);
    } catch (IOException ex) {
      throw ex;
    } catch (Exception ex) {
      // Rethrow the exception when the request is not an API request
      if (!request.getRequestURI().startsWith("/api/") && !request.getRequestURI().startsWith("/rt/")) {
        throw ex;
      }

      // Try to extract a SpringSecurityException from the stacktrace
      Throwable[] causeChain = throwableAnalyzer.determineCauseChain(ex);
      RuntimeException ase = (AccessDeniedException) throwableAnalyzer.getFirstThrowableOfType(
        AccessDeniedException.class, causeChain);

      // This is not a Spring Security's AccessDeniedException. We do not need to process it here
      if (ase == null) {
        throw ex;
      }

      if (response.isCommitted()) {
        throw new ServletException("Unable to translate AccessDeniedException because the response" +
          " of this API request is already committed.", ex);
      }

      // The user is not authenticated. Instead of showing a 403 error, we should
      // send a 401 error to the client, indicating that accessing the requested
      // resources requires authentication and the client hasn't been authenticated.
      //
      // Reference: https://httpstatuses.com/
      if (request.getUserPrincipal() == null) {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
      } else {
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
      }
    }
  }

  /**
   * Default implementation of <code>ThrowableAnalyzer</code> which is capable of also
   * unwrapping <code>ServletException</code>s.
   */
  private static final class DefaultThrowableAnalyzer extends ThrowableAnalyzer {
    /**
     * @see org.springframework.security.web.util.ThrowableAnalyzer#initExtractorMap()
     */
    protected void initExtractorMap() {
      super.initExtractorMap();

      registerExtractor(ServletException.class, throwable -> {
        ThrowableAnalyzer.verifyThrowableHierarchy(throwable,
          ServletException.class);
        return ((ServletException) throwable).getRootCause();
      });
    }
  }
}
