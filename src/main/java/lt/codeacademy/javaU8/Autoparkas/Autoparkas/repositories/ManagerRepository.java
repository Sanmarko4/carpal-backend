package lt.codeacademy.javaU8.Autoparkas.Autoparkas.repositories;

import lt.codeacademy.javaU8.Autoparkas.Autoparkas.entities.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManagerRepository extends JpaRepository<Manager, Long> {
}
