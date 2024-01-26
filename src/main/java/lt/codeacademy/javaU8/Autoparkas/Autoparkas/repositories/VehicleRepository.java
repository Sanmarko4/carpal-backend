package lt.codeacademy.javaU8.Autoparkas.Autoparkas.repositories;

import lt.codeacademy.javaU8.Autoparkas.Autoparkas.entities.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
}
