package hu.icellmobilsoft.onboarding.java.sample.rest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

import org.junit.jupiter.api.Test;

public class LineRestTest {

    @Test
    void testGetHello() {
        given().when()
                .get("http://127.0.0.1:8080/java-sample-0.1.0-SNAPSHOT/rest/sampleService/line/hello")
                .then()
                .statusCode(200)
                .body(is("Hello world!"));
    }
}
