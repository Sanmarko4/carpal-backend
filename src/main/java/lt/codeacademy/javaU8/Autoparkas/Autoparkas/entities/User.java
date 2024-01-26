package lt.codeacademy.javaU8.Autoparkas.Autoparkas.entities;


import jakarta.validation.constraints.NotBlank;

import java.util.List;

public class User {

    private long id;
    @NotBlank(message = "All fields are mandatory!")
    private String firstName, secondName, licenseNumber, licenseExpiryDate;

    private List<Vehicle> vehicles;

    private List<Driver> drivers;

    public User() {
    }

    public User(String firstName, String secondName, String licenseNumber, String licenseExpiryDate) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.licenseNumber = licenseNumber;
        this.licenseExpiryDate = licenseExpiryDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public String getLicenseExpiryDate() {
        return licenseExpiryDate;
    }

    public void setLicenseExpiryDate(String licenseExpiryDate) {
        this.licenseExpiryDate = licenseExpiryDate;
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    public List<Driver> getDrivers() {
        return drivers;
    }

    public void setDrivers(List<Driver> drivers) {
        this.drivers = drivers;
    }
}
