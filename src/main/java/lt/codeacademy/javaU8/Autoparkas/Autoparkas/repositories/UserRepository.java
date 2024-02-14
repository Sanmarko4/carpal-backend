package lt.codeacademy.javaU8.Autoparkas.Autoparkas.repositories;


import lt.codeacademy.javaU8.Autoparkas.Autoparkas.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
}
