package fr.ninauve.renaud.tinubu.insurancepolicies;

import fr.ninauve.renaud.tinubu.insurancepolicies.InsurancePolicy.Status;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.json.JsonContent;
import org.springframework.boot.test.json.ObjectContent;

import java.time.Instant;
import java.time.format.DateTimeFormatter;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
class InsurancePolicyTest {
    private static final long ID = 12345678901L;
    private static final String NAME = "my insurance policy";
    private static final Status STATUS = Status.ACTIVE;
    private static final String STATUS_STRING = "ACTIVE";
    private static final String START_DATE_STRING = "2024-11-24T14:41:52.123456Z";
    private static final Instant START_DATE = Instant.from(DateTimeFormatter.ISO_OFFSET_DATE_TIME.parse(START_DATE_STRING));
    private static final String END_DATE_STRING = "2025-04-01T07:33:24.123456Z";
    private static final Instant END_DATE = Instant.from(DateTimeFormatter.ISO_OFFSET_DATE_TIME.parse(END_DATE_STRING));

    private static final InsurancePolicy INSURANCE_POLICY = InsurancePolicy.builder()
            .id(ID)
            .name(NAME)
            .status(STATUS)
            .startDate(START_DATE)
            .endDate(END_DATE)
            .build();

    private static final String INSURANCE_POLICY_JSON = """
            {
                "id": ${id},
                "name": "${name}",
                "status": "${status}",
                "startDate": "${startDate}",
                "endDate": "${endDate}"
            }
            """.replace("${id}", "" + ID)
            .replace("${name}", NAME)
            .replace("${status}", STATUS_STRING)
            .replace("${startDate}", START_DATE_STRING)
            .replace("${endDate}", END_DATE_STRING);

    @Autowired
    private JacksonTester<InsurancePolicy> json;

    @Test
    void toJson() throws Exception {
        JsonContent<InsurancePolicy> actual = json.write(INSURANCE_POLICY);

        assertThat(actual).extractingJsonPathNumberValue("@.id").isEqualTo(ID);
        assertThat(actual).extractingJsonPathStringValue("@.name").isEqualTo(NAME);
        assertThat(actual).extractingJsonPathStringValue("@.status").isEqualTo(STATUS_STRING);
        assertThat(actual).extractingJsonPathStringValue("@.startDate").isEqualTo(START_DATE_STRING);
        assertThat(actual).extractingJsonPathStringValue("@.endDate").isEqualTo(END_DATE_STRING);
    }

    @Test
    void fromJson() throws Exception {
        ObjectContent<InsurancePolicy> actual = json.parse(INSURANCE_POLICY_JSON);

        assertThat(actual).isEqualTo(INSURANCE_POLICY);
    }
}