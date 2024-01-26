package lt.codeacademy.javaU8.Autoparkas.Autoparkas.services;

import lt.codeacademy.javaU8.Autoparkas.Autoparkas.entities.Admin;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AdminService {
    List<Admin> admins;

    public AdminService() {
        admins = new ArrayList<>();
        addAdmin(new Admin("Tomas", "Razvanovičius", "68596532", "2024-05-20"));
        addAdmin(new Admin("Agnė", "Sidorovaitė", "1345768", "2026-05-20"));
        addAdmin(new Admin("Indrė", "Razvanovičiutė", "13465276", "2024-07-20"));
        addAdmin(new Admin("Ilona", "Čeponė", "1324565", "2030-02-10"));
    }

    public List<Admin> findAllAdmins() {
        return admins;
    }

    public Admin addAdmin(Admin admin) {
        admin.setId(getAvailableId());
        admins.add(admin);
        return admin;
    }

    public void updateAdmin(Admin newAdmin) {
        for (Admin oldAdmin : admins) {
            if (oldAdmin.getId() == (newAdmin.getId())) {
                oldAdmin.setFirstName(newAdmin.getFirstName());
                oldAdmin.setSecondName(newAdmin.getSecondName());
                oldAdmin.setLicenseNumber(newAdmin.getLicenseNumber());
                oldAdmin.setLicenseExpiryDate(newAdmin.getLicenseExpiryDate());
                break;
            }
        }
    }
    public void deleteAdmin(Long id) {
        getByID(id).ifPresent(admin -> admins.remove(admin));
    }

    private Long getAvailableId() {
        if (admins.isEmpty()) {
            return 0L;
        }
        return admins.getLast().getId() + 1;
    }

    public Optional<Admin> getByID(Long id){
        for (Admin oldAdmin : admins) {
            if (oldAdmin.getId() == (id)) {
                return Optional.of(oldAdmin);
            }
        }
        return Optional.empty();
    }
}
