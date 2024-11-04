package hu.icellmobilsoft.onboarding.java.sample.rest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.*;

import org.junit.jupiter.api.Test;

import io.restassured.http.ContentType;

public class LineRestTest {

    private final String id = "000001";

    @Test
    void testGetHello() {
        given()
        .when()
            .get(Path.LINE_REST + "/hello")
        .then()
            .statusCode(200)
            .body(is("Hello world!"));
    }

    @Test
    void testGetLine() {
        given()
            .pathParam("id", id)
        .when()
            .get(Path.LINE_REST + "/{id}")
        .then()
            .statusCode(200)
            .body("name", equalTo("Kristály cukor"))
            .log().all();
    }

    @Test
    void testLineQuery() {
        String requestBody = "{}";

        given()
            .contentType(ContentType.JSON)
            .body(requestBody)
        .when()
            .post(Path.LINE_REST + "/query")
        .then()
            .statusCode(200)
            .body("line", hasSize(greaterThan(0)))
            .log().all();
    }

    @Test
    void testPostLine() {
        String requestBody = "{" +
                "\"name\":\"Irodai asztal\"," +
                "\"quantity\":10," +
                "\"unitOfMeasure\":\"PIECE\"," +
                "\"unitPrice\":147000" +
                "}";

        given()
            .contentType(ContentType.JSON)
            .body(requestBody)
        .when()
            .post(Path.LINE_REST)
        .then()
            .statusCode(200)
            .body("name", equalTo("Irodai asztal"))
            .log().all();
    }

    @Test
    void testPutLine() {
        String requestBody = "{" +
                "\"name\":\"Kristály cukor\"," +
                "\"quantity\":10," +
                "\"unitOfMeasure\":\"KG\"," +
                "\"unitPrice\":359" +
                "}";

        given()
            .pathParam("id", id)
            .contentType(ContentType.JSON)
            .body(requestBody)
        .when()
            .put(Path.LINE_REST + "/{id}")
        .then()
            .statusCode(200)
            .body("quantity", equalTo(10))
            .log().all();
    }

    @Test
    void testDeleteLine() {
        String orphanedId = "000004";

        given()
            .pathParam("id", orphanedId)
        .when()
            .delete(Path.LINE_REST + "/{id}")
        .then()
            .statusCode(200)
            .body("id", equalTo(orphanedId))
            .log().all();
    }
}
