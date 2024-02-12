package lt.codeacademy.javaU8.Autoparkas.Autoparkas;

import lt.codeacademy.javaU8.Autoparkas.Autoparkas.entities.Driver;
import lt.codeacademy.javaU8.Autoparkas.Autoparkas.entities.Manager;
import lt.codeacademy.javaU8.Autoparkas.Autoparkas.entities.Vehicle;
import lt.codeacademy.javaU8.Autoparkas.Autoparkas.repositories.DriverRepository;
import lt.codeacademy.javaU8.Autoparkas.Autoparkas.repositories.ManagerRepository;
import lt.codeacademy.javaU8.Autoparkas.Autoparkas.repositories.VehicleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    DriverRepository driverRepository;

    @Autowired
    ManagerRepository managerRepository;

    @Autowired
    VehicleRepository vehicleRepository;

    private static final Logger log = LoggerFactory.getLogger(DataLoader.class);

    @Override
    public void run(String... args) throws Exception {
        log.debug("Data loader ir using DEBUG");
        log.trace("Data loader ir using TRACE");
        log.info("Data loader ir using INFO");
        log.warn("Data loader ir using WARN");
        log.error("Data loader ir using ERROR");

        Vehicle audi = new Vehicle("Audi", "A1", "2011", "black", "LMR242", "WAUZZ4654832", "2024-12-14", "2025-12-20", "2024-05-17");
        Vehicle bmw5 = new Vehicle("BMW", "525i", "2007", "blue", "MCO842", "WBA86y386955", "2024-12-20", "2025-11-15", "2024-08-19");
        Vehicle bmw3 = new Vehicle("BMW", "328i", "1995", "silver", "EBB752", "WBA7579032034", "2023-09-14", "2025-08-20", "2024-08-14");
        Vehicle mercedes = new Vehicle("Mercedes Benz", "CL500", "2001", "blue", "NGR666", "WAUZZ436732", "2024-11-14", "2022-01-20", "2022-03-10");

        Driver jonas = new Driver("Jonas", "Jonaitis", "6235495", "2024-05-20");
        Driver petras = new Driver("Petras", "Petraitis", "79945352", "2026-05-20");
        Driver kazys = new Driver("Kazys", "Kaziukaitis", "14568769", "2024-07-20");
        Driver marius = new Driver("Marius", "Mariukas", "08734521", "2030-02-10");

        Manager tomas = new Manager("Tomas", "Razvanovičius", "68596532", "2024-05-20");
        Manager agne = new Manager("Agnė", "Sidorovaitė", "1345768", "2026-05-20");
        Manager indre = new Manager("Indrė", "Razvanovičiutė", "13465276", "2024-07-20");
        Manager ilona = new Manager("Ilona", "Čeponė", "1324565", "2030-02-10");

        audi.setDriver(jonas);
        bmw5.setDriver(petras);
        bmw3.setDriver(kazys);
        mercedes.setDriver(marius);

        List<Driver> tomoD = new ArrayList<>();
        tomoD.add(jonas);
        tomoD.add(petras);
        List<Driver> agnesD = new ArrayList<>();
        agnesD.add(kazys);
        List<Driver> indresD = new ArrayList<>();
        indresD.add(marius);

        tomas.setDrivers(tomoD);
        agne.setDrivers(agnesD);
        indre.setDrivers(indresD);

        driverRepository.saveAll(Arrays.asList(jonas, petras, kazys, marius));
        vehicleRepository.saveAll(Arrays.asList(audi, bmw3, bmw5, mercedes));
        managerRepository.saveAll(Arrays.asList(tomas, agne, indre, ilona));

    }
}
