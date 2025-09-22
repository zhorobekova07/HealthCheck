package tentech.healthcheck.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ContactResponse {
    private Long id;
    private Long contactNumber;
    private String address;
    private String operatingMode;
    private String email;
}
