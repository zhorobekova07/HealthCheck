package tentech.healthcheck.model.entity;

import ch.qos.logback.core.status.Status;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cache;
import tentech.healthcheck.enums.ApplicationStatus;

import java.time.LocalDate;

@Entity
@Table(name = "applications")
@Setter
@Getter
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(name = "phone_number")
    private String phoneNumber;
    private LocalDate creatDate;

    @Enumerated(EnumType.STRING)
    private ApplicationStatus status;
}
