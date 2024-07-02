package io.muzoo.ssc.springwebapp.repositories;

import io.muzoo.ssc.springwebapp.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    Set<User> findByDislikes(Set<String> username);
}
