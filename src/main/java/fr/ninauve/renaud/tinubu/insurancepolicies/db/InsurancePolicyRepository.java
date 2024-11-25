package fr.ninauve.renaud.tinubu.insurancepolicies.db;

import fr.ninauve.renaud.tinubu.insurancepolicies.InsurancePolicy;
import org.springframework.data.repository.CrudRepository;

public interface InsurancePolicyRepository extends CrudRepository<InsurancePolicy, Long> {
}
