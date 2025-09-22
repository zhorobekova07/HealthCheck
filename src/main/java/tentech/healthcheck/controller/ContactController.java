package tentech.healthcheck.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tentech.healthcheck.model.dto.ContactRequest;
import tentech.healthcheck.model.dto.ContactResponse;
import tentech.healthcheck.service.ContactService;

@RestController
@RequestMapping("api/contact")
@RequiredArgsConstructor
public class ContactController {

    private final ContactService contactService;

@PostMapping("/save")
    public ContactResponse save(@RequestBody ContactRequest contactRequest) {
        return contactService.post(contactRequest);
    }
}

