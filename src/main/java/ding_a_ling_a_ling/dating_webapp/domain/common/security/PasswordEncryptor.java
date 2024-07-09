package ding_a_ling_a_ling.dating_webapp.domain.common.security;

public interface PasswordEncryptor {

  /**
   * Encrypt a raw password
   */
  String encrypt(String rawPassword);
}
