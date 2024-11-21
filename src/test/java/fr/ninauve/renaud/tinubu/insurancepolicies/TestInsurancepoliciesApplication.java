package fr.ninauve.renaud.tinubu.insurancepolicies;

import org.springframework.boot.SpringApplication;

public class TestInsurancepoliciesApplication {

	public static void main(String[] args) {
		SpringApplication.from(InsurancepoliciesApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
