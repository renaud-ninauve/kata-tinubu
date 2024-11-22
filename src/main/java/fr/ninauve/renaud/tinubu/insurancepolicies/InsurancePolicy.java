package fr.ninauve.renaud.tinubu.insurancepolicies;

import jakarta.persistence.*;

@Entity
@Table(name = "insurance_policies")
public class InsurancePolicy {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "insurance_policies_seq")
    @SequenceGenerator(
            name = "insurance_policies_seq",
            allocationSize = 5
    )
    private Long id;
}
