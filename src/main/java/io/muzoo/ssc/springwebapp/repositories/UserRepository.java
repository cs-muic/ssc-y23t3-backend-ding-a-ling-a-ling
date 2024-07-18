package io.muzoo.ssc.springwebapp.repositories;

import io.muzoo.ssc.springwebapp.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;


public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Boolean existsByUsername(String username);
    List<User>findUsersByDislikesAndPreferences(Set<String> dislikes, Set<String> preferences);
    Boolean existsByEmail(String email);
}
