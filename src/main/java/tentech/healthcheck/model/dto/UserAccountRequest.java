package tentech.healthcheck.model.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import tentech.healthcheck.model.enums.Role;

import java.time.LocalDate;


@Data
@Builder
public class UserAccountRequest {
    private String email;
    private String password;
    private String newPassword;
    private String confirmPassword;
    private Long phoneNumber;
    private LocalDate localDate;
    @Enumerated(value = EnumType.STRING)
    private Role role;
    private Long userId;
}

