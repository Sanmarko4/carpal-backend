package lt.codeacademy.javaU8.Autoparkas.Autoparkas.services;

import lt.codeacademy.javaU8.Autoparkas.Autoparkas.entities.Driver;
import lt.codeacademy.javaU8.Autoparkas.Autoparkas.repositories.DriverRepository;
import lt.codeacademy.javaU8.Autoparkas.Autoparkas.repositories.ManagerRepository;
import lt.codeacademy.javaU8.Autoparkas.Autoparkas.repositories.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DriverService {
    DriverRepository driverRepository;
    VehicleRepository vehicleRepository;

    public DriverService(DriverRepository driverRepository, VehicleRepository vehicleRepository) {
        this.driverRepository = driverRepository;
        this.vehicleRepository = vehicleRepository;
    }

    public List<Driver> findAllDrivers() {
        return driverRepository.findAll();
    }

    public List<Driver> findAvailableDrivers() {
        List<Driver> allDrivers = findAllDrivers();
        List<Driver> availableDrivers = new ArrayList<>();
        for (Driver driver : allDrivers) {
            boolean isAssignedToVehicle = vehicleRepository.existsByDriver(driver);
            if (!isAssignedToVehicle) {
                availableDrivers.add(driver);
            }
        }
        return availableDrivers;
    }

    public Driver addDriver(Driver driver) {
        return driverRepository.save(driver);
    }

    public void updateDriver(Driver newDriver) {
        driverRepository.findById(newDriver.getId()).ifPresent(oldDriver -> {
            oldDriver.setFirstName(newDriver.getFirstName());
            oldDriver.setSecondName(newDriver.getSecondName());
            oldDriver.setDriverLicenseNumber(newDriver.getDriverLicenseNumber());
            oldDriver.setLicenseExpiryDate(newDriver.getLicenseExpiryDate());
            driverRepository.save(oldDriver);
        });
    }

    public void deleteDriver(Long id) {
        driverRepository.findById(id).ifPresent(driverToDelete -> driverRepository.delete(driverToDelete));
    }

    public void deleteDrivers() {
        driverRepository.deleteAll();
    }

}

