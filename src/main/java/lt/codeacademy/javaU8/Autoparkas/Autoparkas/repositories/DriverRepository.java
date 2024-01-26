package lt.codeacademy.javaU8.Autoparkas.Autoparkas.repositories;

import lt.codeacademy.javaU8.Autoparkas.Autoparkas.entities.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {
}
