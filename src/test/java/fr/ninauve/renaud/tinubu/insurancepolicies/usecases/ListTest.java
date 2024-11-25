package fr.ninauve.renaud.tinubu.insurancepolicies.usecases;

import fr.ninauve.renaud.tinubu.insurancepolicies.usecases.extension.UseCase;
import fr.ninauve.renaud.tinubu.insurancepolicies.usecases.extension.UseCasesExtension;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static io.restassured.RestAssured.given;

@ExtendWith(UseCasesExtension.class)
public class ListTest implements UseCase {
    private String applicationBaseUri;

    @Test
    void list() {
        createInsurancePolicy();
        createInsurancePolicy();
        createInsurancePolicy();

        given()
                .baseUri(applicationBaseUri)
                .basePath("/insurancePolicies")
                .when()
                .get()
                .then()
                .statusCode(200)
                .body("_embedded.insurancePolicies", Matchers.is(Matchers.hasSize(3)));
    }

    private void createInsurancePolicy() {
        given()
                .baseUri(applicationBaseUri)
                .basePath("/insurancePolicies")
                .contentType(ContentType.JSON)
                .body("""
                        {
                            "name": "my-policy",
                            "status": "ACTIVE",
                            "startDate": "2024-11-24T14:41:52.123456Z",
                            "endDate": "2025-11-24T14:41:52.123456Z"
                        }
                        """)
                .when()
                .post()
                .then()
                .statusCode(201);
    }

    @Override
    public void setApplicationBaseUri(String applicationBaseUri) {
        this.applicationBaseUri = applicationBaseUri;
    }
}
