package fr.ninauve.renaud.tinubu.insurancepolicies.usecases;

import fr.ninauve.renaud.tinubu.insurancepolicies.usecases.extension.UseCase;
import fr.ninauve.renaud.tinubu.insurancepolicies.usecases.extension.UseCasesExtension;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.core.Is.is;

@ExtendWith(UseCasesExtension.class)
public class UpdateTest implements UseCase {
    private String applicationBaseUri;

    @Test
    void update() {
        String insurancePolicyUri = createInsurancePolicy();
        String insurancePolicy = getInsurancePolicy(insurancePolicyUri);
        String updatedInsurancePolicy = insurancePolicy.replace("my-policy", "updated-policy");

        given()
                .baseUri(insurancePolicyUri)
                .contentType(ContentType.JSON)
                .body(updatedInsurancePolicy)
                .when()
                .put()
                .then()
                .statusCode(200);

        given()
                .baseUri(insurancePolicyUri)
                .when()
                .get()
                .then()
                .statusCode(200)
                .body("name", is(equalTo("updated-policy")));
    }

    @Test
    void fail_when_name_is_blank() {
        String insurancePolicyUri = createInsurancePolicy();
        String insurancePolicy = getInsurancePolicy(insurancePolicyUri);
        String updatedInsurancePolicy = insurancePolicy.replace("my-policy", "");

        given()
                .baseUri(insurancePolicyUri)
                .contentType(ContentType.JSON)
                .body(updatedInsurancePolicy)
                .when()
                .put()
                .then()
                .statusCode(400);
    }

    private String createInsurancePolicy() {
        return given()
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
                .statusCode(201)
                .extract()
                .path("_links.self.href");
    }

    private String getInsurancePolicy(String insurancePolicyUri) {
        return given()
                .baseUri(insurancePolicyUri)
                .when()
                .get()
                .then()
                .statusCode(200)
                .extract()
                .body()
                .asString();
    }

    @Override
    public void setApplicationBaseUri(String applicationBaseUri) {
        this.applicationBaseUri = applicationBaseUri;
    }
}
