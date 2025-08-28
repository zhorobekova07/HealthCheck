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

    @Column(name = "first_name")
    private String firstName;

    private String lastName;
    private String email;
    private Long phoneNumber;
    private String password;
    private String newPassword;
    private String confirmPassword;
    private LocalDate date;
    @Enumerated(EnumType.STRING)
    private Role role;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinTable(name = "userAccounts_users",
            joinColumns = @JoinColumn(name = "userAccount_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> users;
}
