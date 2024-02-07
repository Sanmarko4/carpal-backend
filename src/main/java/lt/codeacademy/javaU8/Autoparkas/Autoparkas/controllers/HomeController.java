package lt.codeacademy.javaU8.Autoparkas.Autoparkas.controllers;

import lt.codeacademy.javaU8.Autoparkas.Autoparkas.entities.User;
import lt.codeacademy.javaU8.Autoparkas.Autoparkas.entities.Driver;
import lt.codeacademy.javaU8.Autoparkas.Autoparkas.entities.Vehicle;
import lt.codeacademy.javaU8.Autoparkas.Autoparkas.services.DriverService;
import lt.codeacademy.javaU8.Autoparkas.Autoparkas.services.UserService;
import lt.codeacademy.javaU8.Autoparkas.Autoparkas.services.VehicleMakeService;
import lt.codeacademy.javaU8.Autoparkas.Autoparkas.services.VehicleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController
public class HomeController {
    DriverService driverService;
    UserService userService;
    VehicleService vehicleService;

    VehicleMakeService vehicleMakeService;

    public HomeController(DriverService driverService, UserService userService, VehicleService vehicleService, VehicleMakeService vehicleMakeService){
        this.driverService = driverService;
        this.userService = userService;
        this.vehicleService = vehicleService;
        this.vehicleMakeService = vehicleMakeService;
    }

    //===========================
    //===== VEHICLE METHODS =====
    //===========================

    @GetMapping("/vehicles")
    public List<Vehicle> showVehicles() {
        return vehicleService.findAllVehicles();
    }
    @GetMapping("/vehiclemakes")
    public List<String> showAllMakes() {
        return vehicleMakeService.getVehicleMakes();
    }
    @GetMapping("/vehiclemodels/{make}")
    public List<String> showAvailableModels(@PathVariable("make") String make) {
        return vehicleMakeService.getModelsForMake(make);
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

    //===========================
    //======== SECURITY =========
    //===========================


}
