package tentech.healthcheck.model.entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import tentech.healthcheck.enums.Role;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "application_request")
@Setter
@Getter
public class UserAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    @Column(name = "phone_name")
    private Long phoneNumber;
    private String password;
    @Column(name = "new_password")
    private String newPassword;
    @Column(name = "confirm_password")
    private String confirmPassword;
    private LocalDate date;
    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.EAGER,mappedBy = "userAccount")
    private User user;
}
