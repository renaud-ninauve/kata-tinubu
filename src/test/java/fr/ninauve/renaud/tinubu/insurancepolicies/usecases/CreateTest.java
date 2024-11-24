package fr.ninauve.renaud.tinubu.insurancepolicies.usecases;

import fr.ninauve.renaud.tinubu.insurancepolicies.usecases.extension.UseCase;
import fr.ninauve.renaud.tinubu.insurancepolicies.usecases.extension.UseCasesExtension;
import io.restassured.http.ContentType;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.notNull;

@ExtendWith(UseCasesExtension.class)
public class CreateTest implements UseCase {
    private String applicationBaseUri;

    @Test
    void create() {
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

        ExtractableResponse<Response> getResponse = given()
                .baseUri(createdInsurancePolicyUri)
                .when()
                .get()
                .then()
                .statusCode(200)
                .body("name", is(equalTo("my-policy")))
                .body("status", is(equalTo("ACTIVE")))
                .body("startDate", is(equalTo("2024-11-24T14:41:52.123456Z")))
                .body("endDate", is(equalTo("2025-11-24T14:41:52.123456Z")))
                .body("createdDate", is(notNull()))
                .body("lastModifiedDate", is(notNull()))
                .extract();

        System.out.println(getResponse.body().asPrettyString());
    }

    @Override
    public void setApplicationBaseUri(String applicationBaseUri) {
        this.applicationBaseUri = applicationBaseUri;
    }
}
