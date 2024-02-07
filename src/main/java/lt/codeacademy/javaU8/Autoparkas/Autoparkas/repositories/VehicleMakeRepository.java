package lt.codeacademy.javaU8.Autoparkas.Autoparkas.repositories;


import lt.codeacademy.javaU8.Autoparkas.Autoparkas.entities.Make;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleMakeRepository extends JpaRepository<Make, Long> {

    Make findByName(String name);
    @Query("SELECT DISTINCT m.name FROM Make m")
    List<String> findAllMakes();
}
