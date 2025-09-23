package tentech.healthcheck.mapper;

import org.springframework.stereotype.Component;
import tentech.healthcheck.model.dto.ContactRequest;
import tentech.healthcheck.model.dto.ContactResponse;
import tentech.healthcheck.model.entity.Contact;

@Component
public class ContactMapper {
    public ContactResponse mapToResponse(Contact contact) {
        return ContactResponse.builder()
                .id(contact.getId())
                .contactNumber(contact.getContactNumber())
                .address(contact.getAddress())
                .operatingMode(contact.getOperatingMode())
                .email(contact.getEmail())
                .build();
    }
    public Contact mapToEntity(ContactRequest contactRequest) {
        Contact contact = new Contact();
        contact.setContactNumber(contactRequest.getContactNumber());
        contact.setAddress(contactRequest.getAddress());
        contact.setOperatingMode(contactRequest.getOperatingMode());
        contact.setEmail(contactRequest.getEmail());
        return contact;
    }
}
