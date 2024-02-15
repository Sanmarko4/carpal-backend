package lt.codeacademy.javaU8.Autoparkas.Autoparkas.services;

import lt.codeacademy.javaU8.Autoparkas.Autoparkas.entities.Driver;
import lt.codeacademy.javaU8.Autoparkas.Autoparkas.services.VehicleService;
import lt.codeacademy.javaU8.Autoparkas.Autoparkas.entities.Vehicle;
import lt.codeacademy.javaU8.Autoparkas.Autoparkas.repositories.VehicleMakeRepository;
import lt.codeacademy.javaU8.Autoparkas.Autoparkas.repositories.VehicleModelRepository;
import lt.codeacademy.javaU8.Autoparkas.Autoparkas.repositories.VehicleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class VehicleServiceTest {

    @Mock
    private VehicleRepository vehicleRepository;

    @Mock
    private VehicleMakeRepository vehicleMakeRepository;

    @Mock
    private VehicleModelRepository vehicleModelRepository;

    private VehicleService vehicleService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        vehicleService = new VehicleService(vehicleRepository, vehicleMakeRepository, vehicleModelRepository);
    }

    @Test
    void testFindAllVehicles() {
        List<Vehicle> vehicles = new ArrayList<>();
        vehicles.add(new Vehicle());
        vehicles.add(new Vehicle());
        when(vehicleRepository.findAll()).thenReturn(vehicles);

        List<Vehicle> allVehicles = vehicleService.findAllVehicles();

        assertEquals(2, allVehicles.size());
    }

    @Test
    void testAddVehicle() {
        Vehicle newVehicle = new Vehicle();
        when(vehicleRepository.save(newVehicle)).thenReturn(newVehicle);

        Vehicle savedVehicle = vehicleService.addVehicle(newVehicle);

        assertEquals(newVehicle, savedVehicle);
    }

    @Test
    void testUpdateVehicle() {

        Vehicle existingVehicle = new Vehicle();
        Vehicle updatedVehicle = new Vehicle();
        when(vehicleRepository.findById(anyLong())).thenReturn(Optional.of(existingVehicle));
        when(vehicleRepository.save(existingVehicle)).thenReturn(updatedVehicle);

        vehicleService.updateVehicle(updatedVehicle);

        verify(vehicleRepository).save(existingVehicle);
    }

    @Test
    void testDeleteVehicle() {
        Long idToDelete = 1L;
        Vehicle vehicleToDelete = new Vehicle();
        when(vehicleRepository.findById(idToDelete)).thenReturn(Optional.of(vehicleToDelete));

        vehicleService.deleteVehicle(idToDelete);

        verify(vehicleRepository).delete(vehicleToDelete);
    }

    @Test
    void testDeleteVehicles() {
        vehicleService.deleteVehicles();

        verify(vehicleRepository).deleteAll();
    }

    @Test
    void testDeleteVehiclesData() {
        vehicleService.deleteVehiclesData();

        verify(vehicleMakeRepository).deleteAll();
        verify(vehicleModelRepository).deleteAll();
    }

    @Test
    void testSeparateVehicle() {
        Vehicle existingVehicle = new Vehicle();
        existingVehicle.setId(1L);
        existingVehicle.setDriver(new Driver());

        when(vehicleRepository.findById(existingVehicle.getId())).thenReturn(Optional.of(existingVehicle));

        vehicleService.separateVehicle(existingVehicle);

        verify(vehicleRepository).save(existingVehicle);
        assertEquals(null, existingVehicle.getDriver());
    }

    @Test
    void testSaveMakeAndModel() {
        String makeName = "Audi";
        String modelName = "A6";
        when(vehicleMakeRepository.findByName(makeName)).thenReturn(null);
        when(vehicleModelRepository.findByMakeAndName(any(), any())).thenReturn(null);

        vehicleService.saveMakeAndModel(makeName, modelName);

        verify(vehicleMakeRepository).save(any());
        verify(vehicleModelRepository).save(any());
    }

    @Test
    void testGetAllMakes() {
        List<String> makes = new ArrayList<>();
        makes.add("Audi");
        makes.add("BMW");
        when(vehicleMakeRepository.findAllMakes()).thenReturn(makes);

        List<String> allMakes = vehicleService.getAllMakes();

        assertEquals(makes, allMakes);
    }

    @Test
    void testGetModelsForMake() {
        String make = "Audi";
        List<String> models = new ArrayList<>();
        models.add("A6");
        models.add("Q5");
        when(vehicleModelRepository.findModelsByMake(make)).thenReturn(models);

        List<String> modelsForMake = vehicleService.getModelsForMake(make);

        assertEquals(models, modelsForMake);
    }
}
