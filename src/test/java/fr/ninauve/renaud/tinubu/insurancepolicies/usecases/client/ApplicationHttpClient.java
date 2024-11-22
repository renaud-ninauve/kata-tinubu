package fr.ninauve.renaud.tinubu.insurancepolicies.usecases.client;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static java.net.http.HttpClient.newHttpClient;

@AllArgsConstructor
public class ApplicationHttpClient {
    private final String baseUrl;

    @SneakyThrows
    public ApplicationHttpResponse send(ApplicationHttpRequest applicationRequest) {
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .method(applicationRequest.getMethod().name(), HttpRequest.BodyPublishers.ofString(applicationRequest.getBody()))
                .uri(new URI(baseUrl + applicationRequest.getPath()))
                .GET()
                .build();

        try (HttpClient httpClient = newHttpClient()) {
            HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            return ApplicationHttpResponse.builder()
                    .status(response.statusCode())
                    .body(response.body())
                    .build();
        }
    }
}
