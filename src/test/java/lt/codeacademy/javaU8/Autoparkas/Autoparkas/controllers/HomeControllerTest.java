package lt.codeacademy.javaU8.Autoparkas.Autoparkas.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import lt.codeacademy.javaU8.Autoparkas.Autoparkas.controllers.HomeController;
import lt.codeacademy.javaU8.Autoparkas.Autoparkas.entities.Driver;
import lt.codeacademy.javaU8.Autoparkas.Autoparkas.entities.Manager;
import lt.codeacademy.javaU8.Autoparkas.Autoparkas.entities.Vehicle;
import lt.codeacademy.javaU8.Autoparkas.Autoparkas.services.DriverService;
import lt.codeacademy.javaU8.Autoparkas.Autoparkas.services.ManagerService;
import lt.codeacademy.javaU8.Autoparkas.Autoparkas.services.VehicleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.security.test.context.support.WithMockUser;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(HomeController.class)
public class HomeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DriverService driverService;

    @MockBean
    private ManagerService managerService;

    @MockBean
    private VehicleService vehicleService;

    @InjectMocks
    private HomeController homeController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @WithMockUser(roles = {"MANAGER", "ADMIN", "DRIVER"})
    public void testShowVehicles() throws Exception {
        List<Vehicle> vehicles = new ArrayList<>();
        vehicles.add(new Vehicle());

        when(vehicleService.findAllVehicles()).thenReturn(vehicles);

        mockMvc.perform(MockMvcRequestBuilders.get("/vehicles"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @WithMockUser(roles = {"MANAGER", "ADMIN", "DRIVER"})
    public void testShowDrivers() throws Exception {
        List<Driver> drivers = new ArrayList<>();
        drivers.add(new Driver());

        when(driverService.findAllDrivers()).thenReturn(drivers);

        mockMvc.perform(MockMvcRequestBuilders.get("/drivers"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @WithMockUser(roles = {"MANAGER", "ADMIN", "DRIVER"})
    public void testGetAllMakes() throws Exception {
        List<String> makes = new ArrayList<>();
        makes.add("Toyota");
        makes.add("Honda");

        when(vehicleService.getAllMakes()).thenReturn(makes);

        mockMvc.perform(MockMvcRequestBuilders.get("/vehiclemakes"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(makes.size()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0]").value("Toyota"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1]").value("Honda"));
    }

    @Test
    @WithMockUser(roles = {"MANAGER", "ADMIN", "DRIVER"})
    public void testGetModelsForMake() throws Exception {
        List<String> models = new ArrayList<>();
        models.add("Corolla");
        models.add("Camry");

        when(vehicleService.getModelsForMake("Toyota")).thenReturn(models);

        mockMvc.perform(MockMvcRequestBuilders.get("/vehiclemodels/Toyota"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(models.size()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0]").value("Corolla"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1]").value("Camry"));
    }

    @Test
    @WithMockUser(roles = {"MANAGER", "ADMIN", "DRIVER"})
    public void testShowAvailableDrivers() throws Exception {
        List<Driver> availableDrivers = new ArrayList<>();
        availableDrivers.add(new Driver("John", "Doe", "DL12345", "2024-02-15"));
        availableDrivers.add(new Driver("Alice", "Smith", "DL67890", "2023-12-31"));

        when(driverService.findAvailableDrivers()).thenReturn(availableDrivers);

        mockMvc.perform(MockMvcRequestBuilders.get("/availabledrivers"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(availableDrivers.size()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].firstName").value("John"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].secondName").value("Doe"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].driverLicenseNumber").value("DL12345"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].licenseExpiryDate").value("2024-02-15"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].firstName").value("Alice"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].secondName").value("Smith"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].driverLicenseNumber").value("DL67890"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].licenseExpiryDate").value("2023-12-31"));
    }
}
