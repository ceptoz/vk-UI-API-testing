package utils;

import aquality.selenium.core.logging.Logger;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static configuration.Configuration.getApiUrl;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.HTML;
import static utils.Constants.API_VERSION;
import static utils.Constants.TOKEN;

public class ReqResUtils {

    public static Map<String, String> getMapWithBasicParams() {
        Map<String, String> basicParams = new HashMap<>();
        basicParams.put("access_token", TOKEN);
        basicParams.put("v", API_VERSION);
        return basicParams;
    }

    public static Response getResponseAfterGet(String method, Map<String, String> parameters) {
        return given()
                .queryParams(parameters)
                .when()
                .get(getApiUrl() + method)
                .then().log().all().extract().response();
    }

    public static Response getResponseAfterPost(String method, Map<String, String> parameters) {
        return given()
                .queryParams(parameters)
                .when()
                .post(getApiUrl() + method)
                .then().log().all().extract().response();
    }

    public static <T> T getInnerObjectFromJSONResponse(Response response, String pathParamName, Class<T> modelClass) {
        return response
                .then()
                .extract().body().jsonPath().getObject(pathParamName, modelClass);
    }

    public static <T> List<T> getListOfInnerJSONObjects(Response response, String pathParamName, Class<T> modelClass) {
        return response
                .then()
                .extract().body().jsonPath().getList(pathParamName, modelClass);
    }

    public static Response sendMultipartFormat(String server, String reqField, String filePath) {
        return given()
                .contentType(ContentType.MULTIPART)
                .multiPart(reqField, new File(filePath))
                .when()
                .post(server)
                .then().log().all().extract().response();
    }

    public static String extractDataFromHTMLResponse(Response response) {
        return response
                .then()
                .contentType(HTML)
                .extract().body().htmlPath().getString("html.body");
    }

    public static <T> T getObjectFromStringJSON(String stringJSON, Class<T> modelClass) {
        T object;
        try {
            object = JSONJacksonProcessorUtils.MAPPER.readValue(stringJSON, modelClass);
        } catch (IOException e) {
            Logger.getInstance().error("Data format can't be processed!");
            throw new RuntimeException(e);
        }
        return object;
    }
}
