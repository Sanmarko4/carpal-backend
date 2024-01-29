package lt.codeacademy.javaU8.Autoparkas.Autoparkas.services;

import lt.codeacademy.javaU8.Autoparkas.Autoparkas.entities.Vehicle;
import lt.codeacademy.javaU8.Autoparkas.Autoparkas.repositories.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VehicleService {
    VehicleRepository vehicleRepository;

    public VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    /*List<Vehicle> vehicles;

    public VehicleService() {
        vehicles = new ArrayList<>();
        addVehicle(new Vehicle("Audi", "A1", "2011", "black", "LMR242", "WAUZZ4654832", "2024-12-14", "2025-12-20", "2024-05-17"));
        addVehicle(new Vehicle("BMW", "525i", "2007", "blue", "MCO842", "WBA86y386955", "2024-12-20", "2025-11-15", "2024-08-19"));
        addVehicle(new Vehicle("BMW", "328i", "1995", "silver", "EBB752", "WBA7579032034", "2023-09-14", "2025-08-20", "2024-08-14"));
        addVehicle(new Vehicle("Mercedes Benz", "CL500", "2001", "blue", "NGR666", "WAUZZ436732", "2024-11-14", "2022-01-20", "2022-03-10"));
    }*/
    public List<Vehicle> findAllVehicles() {
        return vehicleRepository.findAll();
    }
   /*public List<Vehicle> findAllVehicles() {
        return vehicles;
    }*/

    public Vehicle addVehicle(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }
    /*public Vehicle addVehicle(Vehicle vehicle) {
        vehicle.setId(getAvailableId());
        vehicles.add(vehicle);
        return vehicle;
    }*/

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
            vehicleRepository.save(oldV);
        });
    }
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

    public void deleteVehicle(Long id) {
        vehicleRepository.findById(id).ifPresent(vehicleToDelete -> vehicleRepository.delete(vehicleToDelete));
    }
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
