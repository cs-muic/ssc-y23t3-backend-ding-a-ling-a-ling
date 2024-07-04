package io.muzoo.ssc.springwebapp.repositories;

import io.muzoo.ssc.springwebapp.models.User;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Set<User> findByDislikes(Set<String> username);
    Optional<User> findByEmail(String email);
}
