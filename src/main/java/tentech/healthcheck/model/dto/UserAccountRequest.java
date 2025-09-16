package tentech.healthcheck.model.dto;

import lombok.Getter;
import lombok.Setter;
import tentech.healthcheck.model.entity.User;
import tentech.healthcheck.model.enums.Role;

import java.time.LocalDate;

@Getter
@Setter
public class UserAccountRequest {

    private String email;
    private Long phoneNumber;
    private String password;
    private String confirmPassword;
    private LocalDate date;
    private Role role;
    private User user;
}
