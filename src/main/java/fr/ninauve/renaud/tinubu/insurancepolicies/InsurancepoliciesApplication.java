package fr.ninauve.renaud.tinubu.insurancepolicies;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.TimeZone;

@SpringBootApplication
public class InsurancepoliciesApplication {

	public static void main(String[] args) {
		SpringApplication.run(InsurancepoliciesApplication.class, args);
	}

//	@Bean
//	public ObjectMapper objectMapper() {
//		ObjectMapper objectMapper = new ObjectMapper();
//		return objectMapper
//				.setDateFormat(StdDateFormat.getDateTimeInstance(StdDateFormat.DATE_FORMAT_STR_ISO8601))
//				.enable(DeserializationFeature.READ_DATE_TIMESTAMPS_AS_NANOSECONDS);
//	}
}
