package lt.codeacademy.javaU8.Autoparkas.Autoparkas.repositories;


import lt.codeacademy.javaU8.Autoparkas.Autoparkas.entities.Make;
import lt.codeacademy.javaU8.Autoparkas.Autoparkas.entities.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleModelRepository extends JpaRepository<Model, Long> {
    @Query("SELECT m.name FROM Model m WHERE m.make.name = :make")
    List<String> findModelsByMake(String make);

    @Query("SELECT m FROM Model m WHERE m.make = :make AND m.name = :modelName")
    public Model findByMakeAndName(Make make, String modelName);

}
