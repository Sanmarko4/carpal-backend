package lt.codeacademy.javaU8.Autoparkas.Autoparkas.services;

import lt.codeacademy.javaU8.Autoparkas.Autoparkas.entities.Manager;
import lt.codeacademy.javaU8.Autoparkas.Autoparkas.repositories.ManagerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManagerService {
    ManagerRepository managerRepository;

    public ManagerService(ManagerRepository managerRepository) {
        this.managerRepository = managerRepository;
    }
    public List<Manager> findAllManagers() {
        return managerRepository.findAll();
    }

    public Manager addManager(Manager manager) {
        return managerRepository.save(manager);
    }

    public void updateManager(Manager newManager) {
        managerRepository.findById(newManager.getId()).ifPresent(oldManager -> {
            oldManager.setFirstName(newManager.getFirstName());
            oldManager.setSecondName(newManager.getSecondName());
            oldManager.setLicenseNumber(newManager.getLicenseNumber());
            oldManager.setLicenseExpiryDate(newManager.getLicenseExpiryDate());
            managerRepository.save(oldManager);
        });
    }

    public void deleteManager(Long id) {
        managerRepository.findById(id).ifPresent(managerToDelete -> managerRepository.delete(managerToDelete));
    }

    public void deleteManagers() {
        managerRepository.deleteAll();
    }

    public void seperateDriver(Manager newManager) {
        managerRepository.findById(newManager.getId()).ifPresent(oldManager -> {
            oldManager.setDrivers(null);
            managerRepository.save(oldManager);
        });
    }
}
