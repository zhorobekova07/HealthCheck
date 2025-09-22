package tentech.healthcheck.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tentech.healthcheck.mapper.ContactMapper;
import tentech.healthcheck.model.dto.ContactRequest;
import tentech.healthcheck.model.dto.ContactResponse;
import tentech.healthcheck.model.entity.Contact;
import tentech.healthcheck.repository.ContactRepository;

@Service
@RequiredArgsConstructor
public class ContactService {

    private final ContactMapper contactMapper;
    private final ContactRepository contactRepository;

    public ContactResponse post(ContactRequest contactRequest) {
        Contact contact = new Contact();
        contact.setContactNumber(contactRequest.getContactNumber());
        contact.setAddress(contactRequest.getAddress());
        contact.setOperatingMode(contactRequest.getOperatingMode());
        contact.setEmail(contactRequest.getEmail());
        contactRepository.save(contact);
        return contactMapper.mapToResponse(contact);
    }
}
