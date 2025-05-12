import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PostmanEchoTests {
    private static final String BASE_URL = "https://postman-echo.com";

    @BeforeAll
    static void setup() {
        RestAssured.baseURI = BASE_URL;
    }

    @Test
    void getMethodTest() {
        Response response = RestAssured.given()
                .queryParam("foo1", "bar1")
                .queryParam("foo2", "bar2")
                .get("/get")
                .then()
                .extract()
                .response();

        assertEquals(200, response.getStatusCode());

        Map<String, Object> expectedQueries = new HashMap<>();
        expectedQueries.put("foo1", "bar1");
        expectedQueries.put("foo2", "bar2");

        assertEquals(expectedQueries, response.jsonPath().getMap("args"));
    }

    @Test
    void postMethodTest() {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("foo1", "bar1");
        requestBody.put("foo2", "bar2");

        Response response = RestAssured.given()
                .contentType("application/json")
                .body(requestBody)
                .post("/post")
                .then()
                .extract()
                .response();

        assertEquals(200, response.getStatusCode());

        Map<String, Object> expectedData = new HashMap<>();
        expectedData.put("foo1", "bar1");
        expectedData.put("foo2", "bar2");

        assertEquals(expectedData, response.jsonPath().getMap("json"));
    }

    @Test
    void putMethodTest() {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("foo1", "bar1");
        requestBody.put("foo2", "bar2");

        Response response = RestAssured.given()
                .contentType("application/json")
                .body(requestBody)
                .put("/put")
                .then()
                .extract()
                .response();

        assertEquals(200, response.getStatusCode());

        Map<String, Object> expectedData = new HashMap<>();
        expectedData.put("foo1", "bar1");
        expectedData.put("foo2", "bar2");

        assertEquals(expectedData, response.jsonPath().getMap("json"));
    }

    @Test
    void patchMethodTest() {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("foo1", "bar1");
        requestBody.put("foo2", "bar2");

        Response response = RestAssured.given()
                .contentType("application/json")
                .body(requestBody)
                .patch("/patch")
                .then()
                .extract()
                .response();

        assertEquals(200, response.getStatusCode());

        Map<String, Object> expectedData = new HashMap<>();
        expectedData.put("foo1", "bar1");
        expectedData.put("foo2", "bar2");

        assertEquals(expectedData, response.jsonPath().getMap("json"));
    }

    @Test
    void deleteMethodTest() {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("foo1", "bar1");
        requestBody.put("foo2", "bar2");

        Response response = RestAssured.given()
                .contentType("application/json")
                .body(requestBody)
                .delete("/delete")
                .then()
                .extract()
                .response();

        assertEquals(200, response.getStatusCode());

        Map<String, Object> expectedData = new HashMap<>();
        expectedData.put("foo1", "bar1");
        expectedData.put("foo2", "bar2");

        assertEquals(expectedData, response.jsonPath().getMap("json"));
    }

    @Test
    void headMethodTest() {
        Response response = RestAssured.given()
                .head("/headers")
                .then()
                .extract()
                .response();

        assertEquals(200, response.getStatusCode());
    }

    @Test
    void optionsMethodTest() {
        Response response = RestAssured.given()
                .options("/headers")
                .then()
                .extract()
                .response();

        assertEquals(200, response.getStatusCode());
    }
}
