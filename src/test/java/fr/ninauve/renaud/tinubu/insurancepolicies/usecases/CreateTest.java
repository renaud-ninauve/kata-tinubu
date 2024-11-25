package fr.ninauve.renaud.tinubu.insurancepolicies.usecases;

import fr.ninauve.renaud.tinubu.insurancepolicies.usecases.extension.UseCase;
import fr.ninauve.renaud.tinubu.insurancepolicies.usecases.extension.UseCasesExtension;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.core.Is.is;

@ExtendWith(UseCasesExtension.class)
public class CreateTest implements UseCase {
    private static final String DATE_PATTERN = "\\d\\d\\d\\d-\\d\\d-\\d\\d";
    private static final String TIME_PATTERN = "\\d\\d:\\d\\d:\\d\\d\\.\\d*";
    private static final String DATE_TIME_PATTERN = DATE_PATTERN + "T" + TIME_PATTERN + "Z";

    private String applicationBaseUri;

    @Test
    void create_when_valid() {
        final String createdInsurancePolicyUri = given()
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

        given()
                .baseUri(createdInsurancePolicyUri)
                .when()
                .get()
                .then()
                .statusCode(200)
                .body("name", is(equalTo("my-policy")))
                .body("status", is(equalTo("ACTIVE")))
                .body("startDate", is(equalTo("2024-11-24T14:41:52.123456Z")))
                .body("endDate", is(equalTo("2025-11-24T14:41:52.123456Z")))
                .body("createdDate", is(Matchers.matchesPattern(DATE_TIME_PATTERN)))
                .body("lastModifiedDate", is(Matchers.matchesPattern(DATE_TIME_PATTERN)));
    }

    @Test
    void fail_when_name_is_missing() {
        given()
                .baseUri(applicationBaseUri)
                .basePath("/insurancePolicies")
                .contentType(ContentType.JSON)
                .body("""
                        {
                            "status": "ACTIVE",
                            "startDate": "2024-11-24T14:41:52.123456Z",
                            "endDate": "2025-11-24T14:41:52.123456Z"
                        }
                        """)
                .when()
                .post()
                .then()
                .statusCode(400)
                .body("errors.property", hasItem("name"));
    }

    @Test
    void fail_when_name_is_blank() {
        given()
                .baseUri(applicationBaseUri)
                .basePath("/insurancePolicies")
                .contentType(ContentType.JSON)
                .body("""
                        {
                            "name": "",
                            "status": "ACTIVE",
                            "startDate": "2024-11-24T14:41:52.123456Z",
                            "endDate": "2025-11-24T14:41:52.123456Z"
                        }
                        """)
                .when()
                .post()
                .then()
                .statusCode(400)
                .body("errors.property", hasItem("name"));
    }

    @Test
    void create_should_ignore_id_field() {
        final long idToIgnore = 666;

        final String createdInsurancePolicyUri = given()
                .baseUri(applicationBaseUri)
                .basePath("/insurancePolicies")
                .contentType(ContentType.JSON)
                .body("""
                        {
                            "id": ${id},
                            "name": "my-policy",
                            "status": "ACTIVE",
                            "startDate": "2024-11-24T14:41:52.123456Z",
                            "endDate": "2025-11-24T14:41:52.123456Z"
                        }
                        """.replace("${id}", "" + idToIgnore))
                .when()
                .post()
                .then()
                .statusCode(201)
                .extract()
                .path("_links.self.href");

        given()
                .baseUri(createdInsurancePolicyUri)
                .when()
                .get()
                .then()
                .statusCode(200)
                .body("id", is(not(equalTo(idToIgnore))));
    }

    @Override
    public void setApplicationBaseUri(String applicationBaseUri) {
        this.applicationBaseUri = applicationBaseUri;
    }
}
