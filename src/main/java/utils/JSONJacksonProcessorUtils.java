package utils;

import aquality.selenium.core.logging.Logger;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;

public class JSONJacksonProcessorUtils {

    public static final ObjectMapper MAPPER = new ObjectMapper().disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

    public JSONJacksonProcessorUtils() {}

    public static String readJSONFileToString(Path path) {
        try {
            return Files.readString(path);
        } catch (IOException e) {
            Logger.getInstance().error("File not found by specified path!");
            throw new RuntimeException(e);
        }
    }

    public static HashMap<String, String> parseJSONToMap(String data) {
        TypeReference<HashMap<String, String>> typeReference = new TypeReference<>() {};
        try {
            return MAPPER.readValue(data, typeReference);
        } catch (JsonProcessingException e) {
            Logger.getInstance().error("Incorrect data format!");
            throw new RuntimeException(e);
        }
    }

    public static HashMap<String, String> getMapJSON(Path path) {
        return parseJSONToMap(readJSONFileToString(path));
    }

    public static String getValueFromJSONMap(Path path, String key) {
        HashMap<String, String> mapJSON = getMapJSON(path);
        return mapJSON.get(key);
    }
}
