package tentech.healthcheck.model.dto;

import lombok.Getter;
import lombok.Setter;
import tentech.healthcheck.model.enums.Status;

@Setter
@Getter
public class UserAccountRequest {
    private String name;
    private String email;
    private String password;
    private int resetCode;
    private Status status;

}
