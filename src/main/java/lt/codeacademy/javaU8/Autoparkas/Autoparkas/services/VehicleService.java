package lt.codeacademy.javaU8.Autoparkas.Autoparkas.services;

import lt.codeacademy.javaU8.Autoparkas.Autoparkas.entities.Make;
import lt.codeacademy.javaU8.Autoparkas.Autoparkas.entities.Model;
import lt.codeacademy.javaU8.Autoparkas.Autoparkas.entities.Vehicle;
import lt.codeacademy.javaU8.Autoparkas.Autoparkas.repositories.VehicleMakeRepository;
import lt.codeacademy.javaU8.Autoparkas.Autoparkas.repositories.VehicleModelRepository;
import lt.codeacademy.javaU8.Autoparkas.Autoparkas.repositories.VehicleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleService {
    VehicleRepository vehicleRepository;
    VehicleMakeRepository vehicleMakeRepository;
    VehicleModelRepository vehicleModelRepository;

    public VehicleService(VehicleRepository vehicleRepository, VehicleMakeRepository vehicleMakeRepository, VehicleModelRepository vehicleModelRepository) {
        this.vehicleRepository = vehicleRepository;
        this.vehicleMakeRepository = vehicleMakeRepository;
        this.vehicleModelRepository = vehicleModelRepository;
    }

    public List<Vehicle> findAllVehicles() {
        return vehicleRepository.findAll();
    }

    public Vehicle addVehicle(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    public void updateVehicle(Vehicle newV) {
        vehicleRepository.findById(newV.getId()).ifPresent(oldV -> {
            oldV.setMake(newV.getMake());
            oldV.setModel(newV.getModel());
            oldV.setYear(newV.getYear());
            oldV.setColor(newV.getColor());
            oldV.setPlateNumber(newV.getPlateNumber());
            oldV.setVin(newV.getVin());
            oldV.setInsurenceExpiryDate(newV.getInsurenceExpiryDate());
            oldV.setInspectionExpiryDate(newV.getInspectionExpiryDate());
            oldV.setNextServiceDate(newV.getNextServiceDate());
            oldV.setDriver(newV.getDriver());
            vehicleRepository.save(oldV);
        });
    }

    public void deleteVehicle(Long id) {
        vehicleRepository.findById(id).ifPresent(vehicleToDelete -> vehicleRepository.delete(vehicleToDelete));
    }

    public void deleteVehicles() {
        vehicleRepository.deleteAll();
    }

    public void deleteVehiclesData(){
        vehicleMakeRepository.deleteAll();
        vehicleModelRepository.deleteAll();
    }
    public void separateVehicle(Vehicle newV) {
        vehicleRepository.findById(newV.getId()).ifPresent(oldV -> {
            oldV.setDriver(null);
            vehicleRepository.save(oldV);
        });
    }

    //========================================
    //===== VEHICLE MAKE & MODEL METHODS =====
    //========================================

    public void saveMakeAndModel(String makeName, String modelName) {
        Make make = vehicleMakeRepository.findByName(makeName);
        if (make == null) {
            make = new Make();
            make.setName(makeName);
            make = vehicleMakeRepository.save(make);
        }

        Model existingModel = vehicleModelRepository.findByMakeAndName(make, modelName);
        if (existingModel == null) {
            Model model = new Model();
            model.setName(modelName);
            model.setMake(make);
            vehicleModelRepository.save(model);
        }
    }

    public List<String> getAllMakes() {
        return vehicleMakeRepository.findAllMakes();
    }

    public List<String> getModelsForMake(String make) {
        return vehicleModelRepository.findModelsByMake(make);
    }

}
