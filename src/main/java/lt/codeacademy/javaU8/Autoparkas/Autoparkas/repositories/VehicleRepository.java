package lt.codeacademy.javaU8.Autoparkas.Autoparkas.repositories;

import lt.codeacademy.javaU8.Autoparkas.Autoparkas.entities.Driver;
import lt.codeacademy.javaU8.Autoparkas.Autoparkas.entities.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    @Query("SELECT CASE WHEN COUNT(v) > 0 THEN true ELSE false END FROM Vehicle v WHERE v.driver = :driver")
    boolean existsByDriver(@Param("driver") Driver driver);

}
