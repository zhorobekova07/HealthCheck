package tentech.healthcheck.model.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class LoginRequest {

    private String email;
    private String password;
}
