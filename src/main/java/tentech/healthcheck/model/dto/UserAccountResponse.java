package tentech.healthcheck.model.dto;

import lombok.Getter;
import lombok.Setter;
import tentech.healthcheck.model.enums.Status;

import java.time.LocalDate;

@Setter
@Getter
public class UserAccountResponse {
    private Long id;
    private String name;
    private String email;
    private LocalDate createDate;
    private Status status;
}
