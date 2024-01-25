package lt.codeacademy.javaU8.Autoparkas.Autoparkas.entities;


import jakarta.validation.constraints.NotBlank;


public class Vehicle {

    private long id;
    @NotBlank(message = "All fields are mandatory!")
    private String make, model, year, color, registrationLicenseNumber, vinCode, insurenceExpiryDate, inspectionExpiryDate, nextServiceDate;

    private Driver driver;

    public Vehicle() {
    }

    public Vehicle(String make, String model, String year, String color, String registrationLicenseNumber, String vinCode, String insurenceExpiryDate, String inspectionExpiryDate, String nextServiceDate) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.color = color;
        this.registrationLicenseNumber = registrationLicenseNumber;
        this.vinCode = vinCode;
        this.insurenceExpiryDate = insurenceExpiryDate;
        this.inspectionExpiryDate = inspectionExpiryDate;
        this.nextServiceDate = nextServiceDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getRegistrationLicenseNumber() {
        return registrationLicenseNumber;
    }

    public void setRegistrationLicenseNumber(String registrationLicenseNumber) {
        this.registrationLicenseNumber = registrationLicenseNumber;
    }

    public String getVinCode() {
        return vinCode;
    }

    public void setVinCode(String vinCode) {
        this.vinCode = vinCode;
    }

    public String getInsurenceExpiryDate() {
        return insurenceExpiryDate;
    }

    public void setInsurenceExpiryDate(String insurenceExpiryDate) {
        this.insurenceExpiryDate = insurenceExpiryDate;
    }

    public String getInspectionExpiryDate() {
        return inspectionExpiryDate;
    }

    public void setInspectionExpiryDate(String inspectionExpiryDate) {
        this.inspectionExpiryDate = inspectionExpiryDate;
    }

    public String getNextServiceDate() {
        return nextServiceDate;
    }

    public void setNextServiceDate(String nextServiceDate) {
        this.nextServiceDate = nextServiceDate;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }
}
