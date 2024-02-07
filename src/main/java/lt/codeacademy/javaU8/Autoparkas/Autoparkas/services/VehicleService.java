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
    public void seperateVehicle(Vehicle newV) {
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

    /*List<Vehicle> vehicles;

    public VehicleService() {
        vehicles = new ArrayList<>();
        addVehicle(new Vehicle("Audi", "A1", "2011", "black", "LMR242", "WAUZZ4654832", "2024-12-14", "2025-12-20", "2024-05-17"));
        addVehicle(new Vehicle("BMW", "525i", "2007", "blue", "MCO842", "WBA86y386955", "2024-12-20", "2025-11-15", "2024-08-19"));
        addVehicle(new Vehicle("BMW", "328i", "1995", "silver", "EBB752", "WBA7579032034", "2023-09-14", "2025-08-20", "2024-08-14"));
        addVehicle(new Vehicle("Mercedes Benz", "CL500", "2001", "blue", "NGR666", "WAUZZ436732", "2024-11-14", "2022-01-20", "2022-03-10"));
    }*/

    /*public List<Vehicle> findAllVehicles() {
        return vehicles;
    }*/

    /*public Vehicle addVehicle(Vehicle vehicle) {
        vehicle.setId(getAvailableId());
        vehicles.add(vehicle);
        return vehicle;
    }*/

    /*public void updateVehicle(Vehicle newV) {
        for (Vehicle oldV : vehicles) {
            if (oldV.getId() == (newV.getId())) {
                oldV.setMake(newV.getMake());
                oldV.setModel(newV.getModel());
                oldV.setYear(newV.getYear());
                oldV.setColor(newV.getColor());
                oldV.setPlateNumber(newV.getPlateNumber());
                oldV.setVin(newV.getVin());
                oldV.setInsurenceExpiryDate(newV.getInsurenceExpiryDate());
                oldV.setInspectionExpiryDate(newV.getInspectionExpiryDate());
                oldV.setNextServiceDate(newV.getNextServiceDate());
                break;
            }
        }
    }*/

    /*public void deleteVehicle(Long id) {
        getByID(id).ifPresent(vehicleToDelete -> vehicles.remove(vehicleToDelete));
    }*/
    /*public void deleteVehicle(Long id) { //ALTERNATYVUS DESTYTOJO METODAS
        Optional<Vehicle> box = getByID(id);
        if (box.isEmpty()) {
            return;
        }
        Vehicle vehicle = box.get();
        vehicles.remove(vehicle);
    }*/
    /*private Long getAvailableId() {
        if (vehicles.isEmpty()) {
            return 0L;
        }
        return vehicles.getLast().getId() + 1;
    }*/

    /*public Optional<Vehicle> getByID(Long id){
        for (Vehicle oldV : vehicles) {
            if (oldV.getId() == (id)) {
                return Optional.of(oldV);
            }
        }
        return Optional.empty();
    }*/
}
