package lt.codeacademy.javaU8.Autoparkas.Autoparkas.services;

import lt.codeacademy.javaU8.Autoparkas.Autoparkas.entities.Driver;
import lt.codeacademy.javaU8.Autoparkas.Autoparkas.entities.Manager;
import lt.codeacademy.javaU8.Autoparkas.Autoparkas.repositories.ManagerRepository;
import lt.codeacademy.javaU8.Autoparkas.Autoparkas.services.ManagerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ManagerServiceTest {

    @Mock
    private ManagerRepository managerRepository;

    private ManagerService managerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        managerService = new ManagerService(managerRepository);
    }

    @Test
    void testFindAllManagers() {
        List<Manager> managers = new ArrayList<>();
        managers.add(new Manager());
        managers.add(new Manager());
        when(managerRepository.findAll()).thenReturn(managers);

        List<Manager> allManagers = managerService.findAllManagers();

        assertEquals(2, allManagers.size());
    }

    @Test
    void testAddManager() {
        Manager newManager = new Manager();
        when(managerRepository.save(newManager)).thenReturn(newManager);

        Manager savedManager = managerService.addManager(newManager);

        assertEquals(newManager, savedManager);
    }

    @Test
    void testUpdateManager() {
        Manager existingManager = new Manager();
        Manager updatedManager = new Manager();
        when(managerRepository.findById(anyLong())).thenReturn(Optional.of(existingManager));
        when(managerRepository.save(existingManager)).thenReturn(updatedManager);

        managerService.updateManager(updatedManager);

        verify(managerRepository).save(existingManager);
    }

    @Test
    void testDeleteManager() {
        Long idToDelete = 1L;
        Manager managerToDelete = new Manager();
        when(managerRepository.findById(idToDelete)).thenReturn(Optional.of(managerToDelete));

        managerService.deleteManager(idToDelete);

        verify(managerRepository).delete(managerToDelete);
    }

    @Test
    void testDeleteManagers() {
        managerService.deleteManagers();

        verify(managerRepository).deleteAll();
    }

    @Test
    public void testSeparateDriver() {
        Manager oldManager = new Manager();
        oldManager.setId(1L);
        List<Driver> drivers = new ArrayList<>();
        drivers.add(new Driver());
        oldManager.setDrivers(drivers);

        when(managerRepository.findById(1L)).thenReturn(Optional.of(oldManager));

        managerService.separateDriver(oldManager);

        verify(managerRepository).save(oldManager);
    }
}
