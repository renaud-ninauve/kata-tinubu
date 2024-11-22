package fr.ninauve.renaud.tinubu.insurancepolicies.usecases.extension;

import fr.ninauve.renaud.tinubu.insurancepolicies.usecases.client.ApplicationHttpClient;

public interface UseCase {

    void setInsurancePoliciesClient(ApplicationHttpClient insurancePoliciesClient);
}
