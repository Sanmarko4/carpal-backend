package lt.codeacademy.javaU8.Autoparkas.Autoparkas.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class VehicleMakeService {

    private static final Logger log = LoggerFactory.getLogger(VehicleMakeService.class);
    private static final String JSON_FILE_PATH = "vehicleMakesModels.json";

    public List<String> getVehicleMakes() {
        ObjectMapper objectMapper = new ObjectMapper();
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(JSON_FILE_PATH)) {
            List<Map<String, Object>> makesList = objectMapper.readValue(inputStream, new TypeReference<List<Map<String, Object>>>() {});
            List<String> makes = new ArrayList<>();
            for (Map<String, Object> makeMap : makesList) {
                makes.add((String) makeMap.get("make"));
            }
            return makes;
        } catch (IOException e) {
            log.error("An error occurred while reading the JSON file: {}", e.getMessage());
            return null;
        }
    }

    public List<String> getModelsForMake(String make) {
        ObjectMapper objectMapper = new ObjectMapper();
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(JSON_FILE_PATH)) {
            List<Map<String, Object>> makesList = objectMapper.readValue(inputStream, new TypeReference<List<Map<String, Object>>>() {});
            for (Map<String, Object> makeMap : makesList) {
                if (makeMap.get("make").equals(make)) {
                    return (List<String>) makeMap.get("models");
                }
            }
            return null;
        } catch (IOException e) {
            log.error("An error occurred while reading the JSON file: {}", e.getMessage());
            return null;
        }
    }
}
