package tentech.healthcheck.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContactRequest {
    private Long id;
    private Long contactNumber;
    private String address;
    private String operatingMode;
    private String email;
}
