package lt.codeacademy.javaU8.Autoparkas.Autoparkas.services;

import lt.codeacademy.javaU8.Autoparkas.Autoparkas.entities.Driver;
import lt.codeacademy.javaU8.Autoparkas.Autoparkas.repositories.DriverRepository;
import lt.codeacademy.javaU8.Autoparkas.Autoparkas.repositories.VehicleRepository;
import lt.codeacademy.javaU8.Autoparkas.Autoparkas.services.DriverService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class DriverServiceTest {

    @Mock
    private DriverRepository driverRepository;

    @Mock
    private VehicleRepository vehicleRepository;

    private DriverService driverService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        driverService = new DriverService(driverRepository, vehicleRepository);
    }

    @Test
    void testFindAllDrivers() {
        List<Driver> drivers = new ArrayList<>();
        drivers.add(new Driver("John", "Doe", "12345", null));
        drivers.add(new Driver("Jane", "Doe", "54321", null));
        when(driverRepository.findAll()).thenReturn(drivers);

        List<Driver> allDrivers = driverService.findAllDrivers();

        assertEquals(2, allDrivers.size());
    }

    @Test
    void testFindAvailableDrivers() {
        List<Driver> drivers = new ArrayList<>();
        drivers.add(new Driver("John", "Doe", "12345", null));
        drivers.add(new Driver("Jane", "Doe", "54321", null));
        when(driverRepository.findAll()).thenReturn(drivers);
        when(vehicleRepository.existsByDriver(any())).thenReturn(false);

        List<Driver> availableDrivers = driverService.findAvailableDrivers();

        assertEquals(2, availableDrivers.size());
    }

    @Test
    void testAddDriver() {
        Driver newDriver = new Driver("John", "Doe", "12345", null);
        when(driverRepository.save(newDriver)).thenReturn(newDriver);

        Driver savedDriver = driverService.addDriver(newDriver);

        assertEquals(newDriver.getFirstName(), savedDriver.getFirstName());
        assertEquals(newDriver.getSecondName(), savedDriver.getSecondName());
        assertEquals(newDriver.getDriverLicenseNumber(), savedDriver.getDriverLicenseNumber());
        assertEquals(newDriver.getLicenseExpiryDate(), savedDriver.getLicenseExpiryDate());
    }

    @Test
    void testUpdateDriver() {
        Driver existingDriver = new Driver("John", "Doe", "12345", null);
        Driver updatedDriver = new Driver("Updated", "Driver", "54321", null);
        when(driverRepository.findById(anyLong())).thenReturn(Optional.of(existingDriver));
        when(driverRepository.save(existingDriver)).thenReturn(updatedDriver);

        driverService.updateDriver(updatedDriver);

        verify(driverRepository).save(existingDriver);
        assertEquals("Updated", existingDriver.getFirstName());
        assertEquals("Driver", existingDriver.getSecondName());
        assertEquals("54321", existingDriver.getDriverLicenseNumber());
    }

    @Test
    void testDeleteDriver() {
        Long idToDelete = 1L;
        Driver driverToDelete = new Driver("John", "Doe", "12345", null);
        when(driverRepository.findById(idToDelete)).thenReturn(Optional.of(driverToDelete));

        driverService.deleteDriver(idToDelete);

        verify(driverRepository).delete(driverToDelete);
    }

    @Test
    void testDeleteDrivers() {
        driverService.deleteDrivers();

        verify(driverRepository).deleteAll();
    }
}
