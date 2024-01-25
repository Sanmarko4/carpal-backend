package lt.codeacademy.javaU8.Autoparkas.Autoparkas.controllers;

import lt.codeacademy.javaU8.Autoparkas.Autoparkas.entities.Admin;
import lt.codeacademy.javaU8.Autoparkas.Autoparkas.entities.Driver;
import lt.codeacademy.javaU8.Autoparkas.Autoparkas.entities.Vehicle;
import lt.codeacademy.javaU8.Autoparkas.Autoparkas.services.AdminService;
import lt.codeacademy.javaU8.Autoparkas.Autoparkas.services.DriverService;
import lt.codeacademy.javaU8.Autoparkas.Autoparkas.services.VehicleService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/vehicles")
    public List<Vehicle> showVehicles() {
        return vehicleService.findAllVehicles();
    }

    @GetMapping("/admins")
    public List<Admin> showAdmins() {
        return adminService.findAllVehicles();
    }

    @GetMapping("/drivers")
    public List<Driver> showDrivers() {
        return driverService.findAllDrivers();
    }

    /*@PostMapping("/create")
    public Vehicle createVehicle(@RequestBody Vehicle v){
        return vehicleService.addVehicle(v);
    }*/
}
