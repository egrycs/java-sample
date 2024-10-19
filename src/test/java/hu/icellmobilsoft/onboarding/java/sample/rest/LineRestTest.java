package hu.icellmobilsoft.onboarding.java.sample.rest;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

public class LineRestTest {

    @Test
    void testGetHello() {
        given()
                .when().get("/rest/sampleService/line/hello")
                .then()
                .statusCode(200)
                .body(is("Hello world!"));
    }
}
