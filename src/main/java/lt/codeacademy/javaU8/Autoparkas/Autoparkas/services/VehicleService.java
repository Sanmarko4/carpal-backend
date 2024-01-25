package lt.codeacademy.javaU8.Autoparkas.Autoparkas.services;

import lt.codeacademy.javaU8.Autoparkas.Autoparkas.entities.Admin;
import lt.codeacademy.javaU8.Autoparkas.Autoparkas.entities.Vehicle;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VehicleService {
    List<Vehicle> vehicles;

    public VehicleService() {
        vehicles = new ArrayList<>();
        vehicles.add(new Vehicle("Audi", "A1", "2011", "black", "LMR242", "WAUZZ4654832", "2024-12-14", "2025-12-20", "2024-05-17"));
        vehicles.add(new Vehicle("BMW", "525i", "2007", "blue", "MCO842", "WBA86y386955", "2024-12-20", "2025-11-15", "2024-08-19"));
        vehicles.add(new Vehicle("BMW", "328i", "1995", "silver", "EBB752", "WBA7579032034", "2023-09-14", "2025-08-20", "2024-08-14"));
        vehicles.add(new Vehicle("Mercedes Benz", "CL500", "2001", "blue", "NGR666", "WAUZZ436732", "2024-11-14", "2022-01-20", "2022-03-10"));
    }

    public List<Vehicle> findAllVehicles() {
        return vehicles;
    }

    /*public Vehicle addVehicle(Vehicle v) {
        return vehicles.add(v);
    }*/
}
