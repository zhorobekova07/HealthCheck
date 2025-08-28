package tentech.healthcheck.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import tentech.healthcheck.enums.Services;

import java.util.List;

@Entity
@Table(name = "departments")
@Setter
@Getter
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Services serviceName;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH}, mappedBy = "department")
    private List<Doctor> doctors;
//    @ManyToOne
//    @JoinColumn(name = "department_id")
//    private Department department;
}
