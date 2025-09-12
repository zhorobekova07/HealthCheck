package tentech.healthcheck.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import tentech.healthcheck.model.enums.Role;

@Setter
@Getter
@Builder

public class LoginResponse {
    private String token;
    private Role roleName;

}

