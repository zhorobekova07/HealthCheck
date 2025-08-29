package tentech.healthcheck.model.entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import tentech.healthcheck.enums.Role;
import java.time.LocalDate;

@Entity
@Table(name = "user_accounts")
@Setter
@Getter
public class UserAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    @Column(name = "phone_number")
    private Long phoneNumber;

    private String password;

    @Column(name = "new_password")
    private String newPassword;

    @Column(name = "confirm_password")
    private String confirmPassword;

    private LocalDate date;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;
}
