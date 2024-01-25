package lt.codeacademy.javaU8.Autoparkas.Autoparkas.services;

import lt.codeacademy.javaU8.Autoparkas.Autoparkas.entities.Admin;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminService {
    List<Admin> admins;

    public AdminService() {
        admins = new ArrayList<>();
        admins.add(new Admin("Tomas", "Razvanovičius", "68596532", "2024-05-20"));
        admins.add(new Admin("Agnė", "Sidorovaitė", "1345768", "2026-05-20"));
        admins.add(new Admin("Indrė", "Razvanovičiutė", "13465276", "2024-07-20"));
        admins.add(new Admin("Ilona", "Čeponė", "1324565", "2030-02-10"));
    }

    public List<Admin> findAllVehicles() {
        return admins;
    }

    public void addAdmin(Admin a) {
        admins.add(a);
    }
}
