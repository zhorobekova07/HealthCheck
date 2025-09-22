package tentech.healthcheck.service;

import org.springframework.stereotype.Service;
import tentech.healthcheck.model.dto.ContactRequest;
import tentech.healthcheck.model.dto.ContactResponse;

@Service
public class ContactService {

    public ContactResponse post(ContactRequest contactRequest) {
        ContactResponse contactResponse = new ContactResponse();
        contactResponse.setId(contactRequest.getId());
        contactResponse.setContactNumber(contactRequest.getContactNumber());
        contactResponse.setAddress(contactRequest.getAddress());
        contactResponse.setOperatingMode(contactRequest.getOperatingMode());
        contactResponse.setEmail(contactRequest.getEmail());
        return contactResponse;
    }
}
