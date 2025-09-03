package tentech.healthcheck.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder

public class LoginResponse {
    private String token;
    private String roleName;

}

