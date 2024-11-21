package fr.ninauve.renaud.tinubu.insurancepolicies.usecases.extension;

import lombok.*;
import org.apache.hc.core5.http.HttpStatus;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static java.net.http.HttpClient.newHttpClient;
import static org.assertj.core.api.Assertions.assertThat;

@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UseCaseDSL {
    private final String baseUrl;

    private Integer responseStatus;
    private String responseBody;

    public void reset() {
        responseStatus = null;
        responseBody = null;
    }

    @SneakyThrows
    public UseCaseDSL whenGet(String path) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(baseUrl + path))
                .GET()
                .build();
        try (HttpClient httpClient = newHttpClient()) {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            this.responseStatus = response.statusCode();
            this.responseBody = response.body();
        }
        return this;
    }

    public UseCaseDSL thenStatusIsOK() {
        assertThat(responseStatus).isEqualTo(HttpStatus.SC_OK);
        return this;
    }
}
