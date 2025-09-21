package tentech.healthcheck.model.dto;

import lombok.Builder;
import lombok.Data;
import tentech.healthcheck.model.enums.Role;

import java.time.LocalDate;

@Data
@Builder
public class UserAccountResponse {
    private Long id;
    private String email;
    private Long phoneNumber;
    private LocalDate localDate;
    private String password;
    private String newPassword;
    private String confirmPassword;
    private Role role;
    private Long userId;
}
