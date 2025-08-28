package tentech.healthcheck.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import tentech.healthcheck.enums.Days;
import tentech.healthcheck.enums.Services;
import tentech.healthcheck.enums.Status;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "appointments")
@Setter
@Getter
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Services services;

    private LocalDate dateOfVisit;

    private LocalTime timeOfVisit;

    @Enumerated(EnumType.STRING)
    private Days days;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToMany(mappedBy = "appointments", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    private List<Doctor> doctors;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinTable(name = "appointments_users",
            joinColumns = @JoinColumn(name = "appointment_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> users;
}
