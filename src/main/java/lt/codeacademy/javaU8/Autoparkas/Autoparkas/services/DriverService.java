package lt.codeacademy.javaU8.Autoparkas.Autoparkas.services;

import lt.codeacademy.javaU8.Autoparkas.Autoparkas.entities.Driver;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DriverService {
    List<Driver> drivers;

    public DriverService() {
        drivers = new ArrayList<>();
        drivers.add(new Driver("Jonas", "Jonaitis", "6235495", "2024-05-20"));
        drivers.add(new Driver("Petras", "Petraitis", "79945352", "2026-05-20"));
        drivers.add(new Driver("Kazys", "Kaziukaitis", "14568769", "2024-07-20"));
        drivers.add(new Driver("Marius", "Mariukas", "08734521", "2030-02-10"));
    }

    public List<Driver> findAll() {
        return drivers;
    }

    public List<Driver> findAllDrivers() {
        return drivers;
    }
}

