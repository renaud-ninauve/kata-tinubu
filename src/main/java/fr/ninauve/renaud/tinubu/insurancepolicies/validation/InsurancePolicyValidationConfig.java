package fr.ninauve.renaud.tinubu.insurancepolicies.validation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.Validator;

@Configuration
public class InsurancePolicyValidationConfig {

    @Bean
    Validator beforeCreateInsurancePolicyValidator(InsurancePolicyValidator insurancePolicyValidator) {
        return insurancePolicyValidator;
    }

    @Bean
    Validator beforeSaveInsurancePolicyValidator(InsurancePolicyValidator insurancePolicyValidator) {
        return insurancePolicyValidator;
    }
}
