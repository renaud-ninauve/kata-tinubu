package fr.ninauve.renaud.tinubu.insurancepolicies;

import jakarta.persistence.*;
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

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "insurance_policies_seq")
    @SequenceGenerator(
            name = "insurance_policies_seq",
            allocationSize = 5
    )
    private Long id;

    @Column
    private String name;
    @Column
    private String status;

    @Column()
    //@Temporal(TemporalType.TIMESTAMP)
    private Instant startDate;

    @Column
    //@Temporal(TemporalType.TIMESTAMP)
    private Instant endDate;

    @CreatedDate
    private Instant createdDate;
    @LastModifiedDate
    private Instant lastModifiedDate;
}
