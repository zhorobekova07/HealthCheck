package tentech.healthcheck.model.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
    @Enumerated(value = EnumType.STRING)
    private Role role;
    private User user;

}

