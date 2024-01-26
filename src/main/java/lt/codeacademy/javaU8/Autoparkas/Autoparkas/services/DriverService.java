package lt.codeacademy.javaU8.Autoparkas.Autoparkas.services;

import lt.codeacademy.javaU8.Autoparkas.Autoparkas.entities.Driver;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DriverService {
    List<Driver> drivers;

    public DriverService() {
        drivers = new ArrayList<>();
        addDriver(new Driver("Jonas", "Jonaitis", "6235495", "2024-05-20"));
        addDriver(new Driver("Petras", "Petraitis", "79945352", "2026-05-20"));
        addDriver(new Driver("Kazys", "Kaziukaitis", "14568769", "2024-07-20"));
        addDriver(new Driver("Marius", "Mariukas", "08734521", "2030-02-10"));
    }

    public List<Driver> findAllDrivers() {
        return drivers;
    }

    public Driver addDriver(Driver driver) {
        driver.setId(getAvailableId());
        drivers.add(driver);
        return driver;
    }

    public void updateDriver(Driver newDriver) {
        for (Driver oldDriver : drivers) {
            if (oldDriver.getId() == (newDriver.getId())) {
                oldDriver.setFirstName(newDriver.getFirstName());
                oldDriver.setSecondName(newDriver.getSecondName());
                oldDriver.setDriverLicenseNumber(newDriver.getDriverLicenseNumber());
                oldDriver.setLicenseExpiryDate(newDriver.getLicenseExpiryDate());
                break;
            }
        }
    }
    public void deleteDriver(Long id) {
        getByID(id).ifPresent(driverToDelete -> drivers.remove(driverToDelete));
    }

    private Long getAvailableId() {
        if (drivers.isEmpty()) {
            return 0L;
        }
        return drivers.getLast().getId() + 1;
    }

    public Optional<Driver> getByID(Long id){
        for (Driver oldDriver : drivers) {
            if (oldDriver.getId() == (id)) {
                return Optional.of(oldDriver);
            }
        }
        return Optional.empty();
    }
}

