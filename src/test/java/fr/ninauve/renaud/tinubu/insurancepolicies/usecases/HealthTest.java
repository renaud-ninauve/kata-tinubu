package fr.ninauve.renaud.tinubu.insurancepolicies.usecases;

import fr.ninauve.renaud.tinubu.insurancepolicies.usecases.extension.UseCase;
import fr.ninauve.renaud.tinubu.insurancepolicies.usecases.extension.UseCaseDSL;
import fr.ninauve.renaud.tinubu.insurancepolicies.usecases.extension.UseCasesExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(UseCasesExtension.class)
public class HealthTest implements UseCase {
    private UseCaseDSL dsl;

    @Test
    void health() {
        dsl.whenGet("/actuator/health")
                .thenStatusIsOK();
    }

    @Override
    public void setUseCaseDSL(UseCaseDSL dsl) {
        this.dsl = dsl;
    }
}
