package utils;

import java.nio.file.Path;
import java.nio.file.Paths;

import static utils.JSONJacksonProcessorUtils.getValueFromJSONMap;

public class Constants {
    private static final String RESOURCES_PATH = "src/main/resources";
    public static final Path PATH_TO_CREDENTIALS = Paths.get(String.format("%s/user_credentials.json", RESOURCES_PATH));
    public static final Path PATH_TO_TEST_DATA = Paths.get(String.format("%s/test_data.json", RESOURCES_PATH));
    public static final Path PATH_TO_VK_METHODS = Paths.get(String.format("%s/vk_methods.json", RESOURCES_PATH));
    public static final String TOKEN = getValueFromJSONMap(PATH_TO_CREDENTIALS, "token");
    public static final String API_VERSION = getValueFromJSONMap(PATH_TO_TEST_DATA, "apiVersion");
    public static final String WRAPPER = getValueFromJSONMap(PATH_TO_TEST_DATA, "wrapper");
    public static final String PATH_TO_PICTURE = Paths
            .get(String.format("%s/picToUpload.jpg", RESOURCES_PATH)).toAbsolutePath().toString();
    public static final String PATH_FOR_DOWNLOADED_PIC = Paths
            .get(String.format("%s/downloadedPic.jpg", RESOURCES_PATH)).toAbsolutePath().toString();
    public static final String PATH_TO_DIFFERENCE_DESTINATION = Paths
            .get(String.format("%s/difference.jpg", RESOURCES_PATH)).toAbsolutePath().toString();
    public static final String USER_ID_ATTRIBUTE = "data-from-oid";
    public static final double PIXEL_TOLERANCE = 0.13;
}
