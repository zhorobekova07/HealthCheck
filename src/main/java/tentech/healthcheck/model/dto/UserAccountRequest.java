package tentech.healthcheck.model.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;
import tentech.healthcheck.model.enums.Role;


@Setter
@Getter
public class UserAccountRequest {

    private String email;
    private String password;
    private String confirmPassword;
    private Long phoneNumber;
    @Enumerated(value = EnumType.STRING)
    private Role role;
    private Long userId;
}

