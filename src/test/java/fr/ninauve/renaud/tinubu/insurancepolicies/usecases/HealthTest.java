package fr.ninauve.renaud.tinubu.insurancepolicies.usecases;

import fr.ninauve.renaud.tinubu.insurancepolicies.usecases.client.ApplicationHttpClient;
import fr.ninauve.renaud.tinubu.insurancepolicies.usecases.client.ApplicationHttpRequest;
import fr.ninauve.renaud.tinubu.insurancepolicies.usecases.client.ApplicationHttpResponse;
import fr.ninauve.renaud.tinubu.insurancepolicies.usecases.extension.UseCase;
import fr.ninauve.renaud.tinubu.insurancepolicies.usecases.extension.UseCasesExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.http.HttpMethod;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(UseCasesExtension.class)
public class HealthTest implements UseCase {
    private ApplicationHttpClient insurancePoliciesClient;

    @Test
    void health() {

        ApplicationHttpRequest request = ApplicationHttpRequest.builder()
                .method(HttpMethod.GET)
                .path("/actuator/health")
                .build();

        ApplicationHttpResponse actual = insurancePoliciesClient.send(request);

        assertThat(actual.getStatus()).isEqualTo(200);
    }

    @Override
    public void setInsurancePoliciesClient(ApplicationHttpClient insurancePoliciesClient) {
        this.insurancePoliciesClient = insurancePoliciesClient;
    }
}
