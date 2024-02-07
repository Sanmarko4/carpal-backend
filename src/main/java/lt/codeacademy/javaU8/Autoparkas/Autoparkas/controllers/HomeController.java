package lt.codeacademy.javaU8.Autoparkas.Autoparkas.controllers;

import lt.codeacademy.javaU8.Autoparkas.Autoparkas.entities.User;
import lt.codeacademy.javaU8.Autoparkas.Autoparkas.entities.Driver;
import lt.codeacademy.javaU8.Autoparkas.Autoparkas.entities.Vehicle;
import lt.codeacademy.javaU8.Autoparkas.Autoparkas.services.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class HomeController {

    //Address to test with swagger: http://localhost:8080/swagger-ui/index.html
    DriverService driverService;
    UserService userService;
    VehicleService vehicleService;

    public HomeController(DriverService driverService, UserService userService, VehicleService vehicleService){
        this.driverService = driverService;
        this.userService = userService;
        this.vehicleService = vehicleService;
    }

    //===========================
    //===== VEHICLE METHODS =====
    //===========================

    @GetMapping("/vehicles")
    public List<Vehicle> showVehicles() {
        return vehicleService.findAllVehicles();
    }
    @PostMapping("/addvehicle")
    public Vehicle addVehicle(@RequestBody Vehicle vehicle){
        return vehicleService.addVehicle(vehicle);
    }
    @PutMapping("/updatevehicle")
    public void updateVehicle(@RequestBody Vehicle vehicle){
        vehicleService.updateVehicle(vehicle);
    }
    @PutMapping("/seperatevehiclefromdriver")
    public void seperateVehicle(@RequestBody Vehicle vehicle){
        vehicleService.seperateVehicle(vehicle);
    }
    @DeleteMapping("/deletevehicle/{id}")
    public void deleteVehicle(@PathVariable Long id) {
        vehicleService.deleteVehicle(id);
    }

    //========================================
    //===== VEHICLE MAKE & MODEL METHODS =====
    //========================================

    @PostMapping("/savemakemodel")
    public void saveMakeAndModel(@RequestParam String makeName, @RequestBody String modelName) {
        vehicleService.saveMakeAndModel(makeName, modelName);
    }
    @GetMapping("/vehiclemakes")
    public List<String> getAllMakes() {
        return vehicleService.getAllMakes();
    }
    @GetMapping("/vehiclemodels/{make}")
    public List<String> getModelsForMake(@PathVariable("make") String make) {
        return vehicleService.getModelsForMake(make);
    }

    //===========================
    //====== USER METHODS =======
    //===========================

    @GetMapping("/users")
    public List<User> showUsers() {
        return userService.findAllUsers();
    }
    @PostMapping("/adduser")
    public void addUser(@RequestBody User user){
        userService.addUser(user);
    }
    @PutMapping("/updateuser")
    public void updateUser(@RequestBody User user){
        userService.updateUser(user);
    }
    @PutMapping("/seperatedriverfromuser")
    public void seperateDriver(@RequestBody User user){
        userService.seperateDriver(user);
    }
    @DeleteMapping("/deleteuser/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    //===========================
    //===== DRIVER METHODS ======
    //===========================

    @GetMapping("/drivers")
    public List<Driver> showDrivers() {
        return driverService.findAllDrivers();
    }
    @GetMapping("/availabledrivers")
    public List<Driver> showAvailableDrivers() {
        return driverService.findAvailableDrivers();
    }
    @PostMapping("/adddriver")
    public void addDriver(@RequestBody Driver d){
        driverService.addDriver(d);
    }
    @PutMapping("/updatedriver")
    public void updateDriver(@RequestBody Driver driver){
        driverService.updateDriver(driver);
    }
    @DeleteMapping("/deletedriver/{id}")
    public void deleteDriver(@PathVariable Long id) {
        driverService.deleteDriver(id);
    }

    //===========================
    //==== DATABASE METHODS =====
    //===========================

    @DeleteMapping("/deleteall")
    public void deleteAllData() {
        userService.deleteUsers();
        vehicleService.deleteVehicles();
        driverService.deleteDrivers();
    }

    @DeleteMapping("/deleteVehicleMakesAndModels")
    public void deleteAllVehicleData() {
        vehicleService.deleteVehiclesData();
    }

    //===========================
    //======== SECURITY =========
    //===========================


}
