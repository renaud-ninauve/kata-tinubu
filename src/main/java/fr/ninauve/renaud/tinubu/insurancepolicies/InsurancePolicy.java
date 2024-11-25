package fr.ninauve.renaud.tinubu.insurancepolicies;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;

@Entity
@Table(name = "insurance_policies")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
public class InsurancePolicy {

    public enum Status {
        ACTIVE, INACTIVE
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "insurance_policies_seq")
    @SequenceGenerator(
            name = "insurance_policies_seq",
            allocationSize = 5
    )
    private Long id;

    @Column
//    @NotBlank(message = "name is mandatory and should not be blank")
    private String name;

    @Column
//    @NotNull(message = "status is mandatory and should not be one of ACTIVE, INACTIVE")
    private Status status;

    @Column
//    @NotNull(message = "startDate is mandatory")
    private Instant startDate;

    @Column
//    @NotNull(message = "endDate is mandatory")
    private Instant endDate;

    @CreatedDate
    private Instant createdDate;
    @LastModifiedDate
    private Instant lastModifiedDate;
}
