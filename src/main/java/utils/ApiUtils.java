package utils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.Map;

public class ApiUtils {

    public static Response getRequest(String baseUrl, String path) {
        RestAssured.baseURI = baseUrl;
        return RestAssured.given()
                .when()
                .get(path);
    }

    public static Response postRequest(String baseUrl, String path, Map<String, String> map) {
        RestAssured.baseURI = baseUrl;
        return RestAssured.given()
                .contentType(ContentType.JSON)
                .when()
                .body(map)
                .post(path);
    }
}