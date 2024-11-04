package hu.icellmobilsoft.onboarding.java.sample.rest;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.equalTo;

public class InvoiceDataRestTest {
    private final String ID = "12345";

    @Test
    void testGetInvoice() {
        given()
            .pathParam("id", ID)
        .when()
            .get(Path.INVOICE_REST + "/{id}")
        .then()
            .statusCode(200)
            .body("invoice.id", equalTo(ID))
            .log().all();
    }

    @Test
    void testInvoiceQuery() {
        String requestBody = "{}";

        given()
            .contentType(ContentType.JSON)
            .body(requestBody)
        .when()
            .post(Path.INVOICE_REST + "/query")
        .then()
            .statusCode(200)
            .body("invoiceData", hasSize(greaterThan(0)))
            .log().all();
    }

    @Test
    void testPostInvoice() {
        postInvoice();
    }

    @Test
    void testPutInvoice() {
        String requestBody = "{" +
            "\"invoice\": {" +
                "\"invoiceNumber\": \"ICELL-2024-11\"," +
                "\"invoiceType\": \"SIMPLIFIED\"," +
                "\"supplierTaxNumber\": \"31415926\"," +
                "\"customerTaxNumber\": \"32468972\"," +
                "\"sumPrice\": 1298000" +
            "}" +
        "}";

        given()
            .pathParam("id", ID)
            .contentType(ContentType.JSON)
            .body(requestBody)
        .when()
            .put(Path.INVOICE_REST + "/{id}")
        .then()
            .statusCode(200)
            .body("invoice.id", equalTo(ID))
            .log().all();
    }

    @Test
    void testDeleteInvoice() {
        String testInvoiceId = postInvoice();

        given()
            .pathParam("id", testInvoiceId)
        .when()
            .delete(Path.INVOICE_REST + "/{id}")
        .then()
            .statusCode(200)
            .body("invoice.id", equalTo(testInvoiceId))
            .log().all();
    }

    private String postInvoice() {
        String requestBody = "{" +
            "\"invoice\": {" +
                "\"invoiceNumber\": \"ICELL-2024-11\"," +
                "\"invoiceType\": \"NORMAL\"," +
                "\"supplierTaxNumber\": \"31415926\"," +
                "\"customerTaxNumber\": \"32468972\"," +
                "\"sumPrice\": 1298000" +
            "}," +
            "\"lines\": {" +
                "\"line\": [" +
                    "{" +
                        "\"name\": \"Monitor\"," +
                        "\"quantity\": 20," +
                        "\"unitOfMeasure\": \"PIECE\"," +
                        "\"customUnitOfMeasure\": null," +
                        "\"unitPrice\": 54000" +
                    "}," +
                    "{" +
                        "\"name\": \"Billentyűzet + egér\"," +
                        "\"quantity\": 20," +
                        "\"unitOfMeasure\": \"CUSTOM\"," +
                        "\"customUnitOfMeasure\": \"csomag\"," +
                        "\"unitPrice\": 10900" +
                    "}" +
                "]" +
            "}" +
        "}";

        return given()
            .contentType(ContentType.JSON)
            .body(requestBody)
        .when()
            .post(Path.INVOICE_REST)
        .then()
            .statusCode(200)
            .body("invoice.invoiceNumber", equalTo("ICELL-2024-11"))
            .log().all()
            .extract()
            .path("invoice.id");
    }
}
