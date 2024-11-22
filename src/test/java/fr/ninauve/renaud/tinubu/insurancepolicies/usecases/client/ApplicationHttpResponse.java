package fr.ninauve.renaud.tinubu.insurancepolicies.usecases.client;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ApplicationHttpResponse {
    private final int status;
    private final String body;
}
