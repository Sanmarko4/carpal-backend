package lt.codeacademy.javaU8.Autoparkas.Autoparkas.controllers;

import lt.codeacademy.javaU8.Autoparkas.Autoparkas.entities.Admin;
import lt.codeacademy.javaU8.Autoparkas.Autoparkas.entities.Driver;
import lt.codeacademy.javaU8.Autoparkas.Autoparkas.entities.Vehicle;
import lt.codeacademy.javaU8.Autoparkas.Autoparkas.services.AdminService;
import lt.codeacademy.javaU8.Autoparkas.Autoparkas.services.DriverService;
import lt.codeacademy.javaU8.Autoparkas.Autoparkas.services.VehicleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
public class HomeController {
    DriverService driverService;
    AdminService adminService;
    VehicleService vehicleService;

    public HomeController(DriverService driverService, AdminService adminService, VehicleService vehicleService){
        this.driverService = driverService;
        this.adminService = adminService;
        this.vehicleService = vehicleService;
    }

    //===========================
    //===== VEHICLE METHODS =====
    //===========================

    @GetMapping("/vehicles")
    public List<Vehicle> showVehicles() {
        return vehicleService.findAllVehicles();
    }
    @PostMapping("/addVehicle")
    public Vehicle addVehicle(@RequestBody Vehicle vehicle){
        return vehicleService.addVehicle(vehicle);
    }
    @PutMapping("/updateVehicle")
    public void updateVehicle(@RequestBody Vehicle vehicle){
        vehicleService.updateVehicle(vehicle);
    }
    @DeleteMapping("/deleteVehicle/{id}")
    public void deleteVehicle(@PathVariable Long id) {
        vehicleService.deleteVehicle(id);
    }

    //===========================
    //===== ADMIN METHODS =======
    //===========================

    @GetMapping("/admins")
    public List<Admin> showAdmins() {
        return adminService.findAllAdmins();
    }
    @PostMapping("/addAdmin")
    public void addAdmin(@RequestBody Admin admin){
        adminService.addAdmin(admin);
    }
    @PutMapping("/updateAdmin")
    public void updateAdmin(@RequestBody Admin admin){
        adminService.updateAdmin(admin);
    }
    @DeleteMapping("/deleteAdmin/{id}")
    public void deleteAdmin(@PathVariable Long id) {
        adminService.deleteAdmin(id);
    }

    //===========================
    //===== DRIVER METHODS ======
    //===========================

    @GetMapping("/drivers")
    public List<Driver> showDrivers() {
        return driverService.findAllDrivers();
    }
    @PostMapping("/addDriver")
    public void addDriver(@RequestBody Driver d){
        driverService.addDriver(d);
    }
    @PutMapping("/updateDriver")
    public void updateDriver(@RequestBody Driver driver){
        driverService.updateDriver(driver);
    }
    @DeleteMapping("/deleteDriver/{id}")
    public void deleteDriver(@PathVariable Long id) {
        driverService.deleteDriver(id);
    }


}
