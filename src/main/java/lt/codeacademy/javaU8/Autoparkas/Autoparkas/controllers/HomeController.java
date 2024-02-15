package lt.codeacademy.javaU8.Autoparkas.Autoparkas.controllers;

import lt.codeacademy.javaU8.Autoparkas.Autoparkas.entities.Manager;
import lt.codeacademy.javaU8.Autoparkas.Autoparkas.entities.Driver;
import lt.codeacademy.javaU8.Autoparkas.Autoparkas.entities.Vehicle;
import lt.codeacademy.javaU8.Autoparkas.Autoparkas.services.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class HomeController {

    //Address to test with swagger: http://localhost:8080/swagger-ui/index.html
    DriverService driverService;
    ManagerService managerService;
    VehicleService vehicleService;

    public HomeController(DriverService driverService, ManagerService managerService, VehicleService vehicleService){
        this.driverService = driverService;
        this.managerService = managerService;
        this.vehicleService = vehicleService;
    }

    //===========================
    //===== VEHICLE METHODS =====
    //===========================

    @GetMapping("/vehicles")
    @PreAuthorize("hasRole('MANAGER') or hasRole('ADMIN') or hasRole('DRIVER')")
    public List<Vehicle> showVehicles() {
        return vehicleService.findAllVehicles();
    }
    @PostMapping("/addvehicle")
    @PreAuthorize("hasRole('MANAGER') or hasRole('ADMIN')")
    public Vehicle addVehicle(@RequestBody Vehicle vehicle){
        return vehicleService.addVehicle(vehicle);
    }
    @PutMapping("/updatevehicle")
    @PreAuthorize("hasRole('MANAGER') or hasRole('ADMIN')")
    public void updateVehicle(@RequestBody Vehicle vehicle){
        vehicleService.updateVehicle(vehicle);
    }
    @PutMapping("/separatevehiclefromdriver")
    @PreAuthorize("hasRole('MANAGER') or hasRole('ADMIN')")
    public void separateVehicle(@RequestBody Vehicle vehicle){
        vehicleService.separateVehicle(vehicle);
    }
    @DeleteMapping("/deletevehicle/{id}")
    @PreAuthorize("hasRole('MANAGER') or hasRole('ADMIN')")
    public void deleteVehicle(@PathVariable Long id) {
        vehicleService.deleteVehicle(id);
    }

    //========================================
    //===== VEHICLE MAKE & MODEL METHODS =====
    //========================================

    @PostMapping("/savemakemodel")
    @PreAuthorize("hasRole('ADMIN')")
    public void saveMakeAndModel(@RequestParam String makeName, @RequestBody String modelName) {
        vehicleService.saveMakeAndModel(makeName, modelName);
    }
    @GetMapping("/vehiclemakes")
    @PreAuthorize("hasRole('MANAGER') or hasRole('ADMIN') or hasRole('DRIVER')")
    public List<String> getAllMakes() {
        return vehicleService.getAllMakes();
    }
    @GetMapping("/vehiclemodels/{make}")
    @PreAuthorize("hasRole('MANAGER') or hasRole('ADMIN') or hasRole('DRIVER')")
    public List<String> getModelsForMake(@PathVariable("make") String make) {
        return vehicleService.getModelsForMake(make);
    }

    //===========================
    //==== MANAGER METHODS ======
    //===========================

    @GetMapping("/managers")
    @PreAuthorize("hasRole('ADMIN')")
    public List<Manager> showManagers() {
        return managerService.findAllManagers();
    }
    @PostMapping("/addmanager")
    @PreAuthorize("hasRole('ADMIN')")
    public void addmanager(@RequestBody Manager manager){
        managerService.addManager(manager);
    }
    @PutMapping("/updatemanager")
    @PreAuthorize("hasRole('ADMIN')")
    public void updateManager(@RequestBody Manager manager){
        managerService.updateManager(manager);
    }
    @PutMapping("/separatedriverfrommanager")
    @PreAuthorize("hasRole('ADMIN')")
    public void separateDriver(@RequestBody Manager manager){
        managerService.separateDriver(manager);
    }
    @DeleteMapping("/deletemanager/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteManager(@PathVariable Long id) {
        managerService.deleteManager(id);
    }

    //===========================
    //===== DRIVER METHODS ======
    //===========================

    @GetMapping("/drivers")
    @PreAuthorize("hasRole('MANAGER') or hasRole('ADMIN') or hasRole('DRIVER')")
    public List<Driver> showDrivers() {
        return driverService.findAllDrivers();
    }
    @GetMapping("/availabledrivers")
    @PreAuthorize("hasRole('MANAGER') or hasRole('ADMIN') or hasRole('DRIVER')")
    public List<Driver> showAvailableDrivers() {
        return driverService.findAvailableDrivers();
    }
    @PostMapping("/adddriver")
    @PreAuthorize("hasRole('MANAGER') or hasRole('ADMIN')")
    public void addDriver(@RequestBody Driver d){
        driverService.addDriver(d);
    }
    @PutMapping("/updatedriver")
    @PreAuthorize("hasRole('MANAGER') or hasRole('ADMIN')")
    public void updateDriver(@RequestBody Driver driver){
        driverService.updateDriver(driver);
    }
    @DeleteMapping("/deletedriver/{id}")
    @PreAuthorize("hasRole('MANAGER') or hasRole('ADMIN')")
    public void deleteDriver(@PathVariable Long id) {
        driverService.deleteDriver(id);
    }

    //===========================
    //==== DATABASE METHODS =====
    //===========================

    @DeleteMapping("/deleteall")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteAllData() {
        managerService.deleteManagers();
        vehicleService.deleteVehicles();
        driverService.deleteDrivers();
    }

    @DeleteMapping("/deleteVehicleMakesAndModels")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteAllVehicleData() {
        vehicleService.deleteVehiclesData();
    }


}
