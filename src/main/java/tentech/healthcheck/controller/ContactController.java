package tentech.healthcheck.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tentech.healthcheck.model.dto.ContactRequest;
import tentech.healthcheck.model.dto.ContactResponse;
import tentech.healthcheck.service.ContactService;

@Tag(name = "Contact Controller")
@RestController
@RequestMapping("api/contact")
@RequiredArgsConstructor
public class ContactController {

    private final ContactService contactService;

    @Operation(summary = "Этот метод сохраняет Контакты" ,description = "Создает и возвращает контакт")
    @PostMapping("/save")
    public ContactResponse save(@RequestBody ContactRequest contactRequest) {
        return contactService.post(contactRequest);
    }
}

