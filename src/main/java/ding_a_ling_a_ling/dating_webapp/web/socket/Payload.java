package ding_a_ling_a_ling.dating_webapp.web.socket;

import java.lang.annotation.*;

/**
 * Mark a parameter as the payload in an action method of channel handler.
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Payload {
}
