import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;

public class ReqresTests {
    @BeforeAll
    static void SetUp() {
        RestAssured.baseURI = "https://reqres.in/";
    }

    @Test
    void successfulLogin() {

        String data = "{ \"email\": \"eve.holt@reqres.in\", \"password\": \"cityslicka\" }";

        given()
                .contentType(JSON)
                .body(data)
                .when()
                .post("/api/login")
                .then()
                .statusCode(200)
                .body("token", is("QpwL5tke4Pnpja7X4"));

    }

    @Test
    void negativeLogin() {
        String data = "{ \"email\": \"eve.holt@reqres.in\"}";

        given()
                .contentType(JSON)
                .body(data)
                .when()
                .post("/api/login")
                .then()
                .statusCode(400)
                .body("error", is("Missing password"));
    }

    @Test
    void userNotFound() {
        given()
                .when()
                .get("/api/users/23")
                .then()
                .statusCode(404);

    }
}
