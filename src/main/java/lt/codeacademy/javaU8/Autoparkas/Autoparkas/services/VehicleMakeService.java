package lt.codeacademy.javaU8.Autoparkas.Autoparkas.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import java.io.File;
import java.util.Collections;
import java.util.List;
@Service
public class VehicleMakeService {

    private List<String> vehicleMakes;

    public VehicleMakeService() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            File file = ResourceUtils.getFile("classpath:vehicleMakes.json");
            vehicleMakes = mapper.readValue(file, new TypeReference<List<String>>() {});
        } catch (Exception e) {
            e.printStackTrace();
            vehicleMakes = Collections.emptyList();
        }
    }

    public List<String> getVehicleMakes() {
        return vehicleMakes;
    }
}
