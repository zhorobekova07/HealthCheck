package tentech.healthcheck.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.*;
import tentech.healthcheck.model.entity.User;
import tentech.healthcheck.model.enums.Role;
import java.time.LocalDate;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserAccountResponse {
    private Long id;
    private String email;
    private Long phoneNumber;
    private String password;
    private String newPassword;
    private String confirmPassword;
    private LocalDate date;
    private Role role;
    private User user;
}
