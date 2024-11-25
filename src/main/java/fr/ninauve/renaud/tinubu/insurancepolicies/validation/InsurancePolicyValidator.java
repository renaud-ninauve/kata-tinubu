package fr.ninauve.renaud.tinubu.insurancepolicies.validation;

import fr.ninauve.renaud.tinubu.insurancepolicies.InsurancePolicy;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class InsurancePolicyValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return InsurancePolicy.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "name.notBlank", "name is mandatory");

        InsurancePolicy insurancePolicy = (InsurancePolicy) target;
        if (insurancePolicy.getStatus() == null) {
            errors.rejectValue("status", "status.notNull", "status is mandatory and should be one of ACTIVE, INACTIVE");
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "startDate", "startDate.notNull", "startDate is mandatory");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "endDate", "endDate.notNull", "endDate is mandatory");
    }
}
