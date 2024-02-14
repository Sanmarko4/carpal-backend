package lt.codeacademy.javaU8.Autoparkas.Autoparkas.repositories;

import lt.codeacademy.javaU8.Autoparkas.Autoparkas.models.EnumRoles;
import lt.codeacademy.javaU8.Autoparkas.Autoparkas.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(EnumRoles name);
}
