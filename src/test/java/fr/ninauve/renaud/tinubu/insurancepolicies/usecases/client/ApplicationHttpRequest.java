package fr.ninauve.renaud.tinubu.insurancepolicies.usecases.client;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpMethod;

@Builder
@Data
public class ApplicationHttpRequest {
    private final String path;
    @Builder.Default
    private final String body = "";
    private final HttpMethod method;
}
