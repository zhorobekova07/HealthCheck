package tentech.healthcheck.model.dto;

import lombok.*;
import tentech.healthcheck.model.entity.User;
import tentech.healthcheck.model.enums.Role
import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserAccountResponse {
    private Long id;
    private String email;
    private Long phoneNumber;
    private LocalDate date;
    private Role role;
    private User user;
}
