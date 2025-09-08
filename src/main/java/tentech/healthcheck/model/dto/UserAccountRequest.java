package tentech.healthcheck.model.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;
import tentech.healthcheck.model.entity.User;
import tentech.healthcheck.model.enums.Role;

import java.time.LocalDate;

@Setter
@Getter
public class UserAccountRequest {

    private String email;
    private String password;
    private String confirmPassword;
    private LocalDate localDate;
    private Role role;
    private User user;

}

