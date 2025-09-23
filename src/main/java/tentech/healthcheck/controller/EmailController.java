package tentech.healthcheck.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tentech.healthcheck.model.dto.EmailRequest;
import tentech.healthcheck.serviceImpl.EmailService;
@Tag(name = "Email Controller")
@RestController
@RequestMapping("/api/email")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @Operation(summary = "Отправка письма" ,description = "Принимает EmailRequest (адрес, тема, текст) и возвращает статус операции")
    @PostMapping("/sendMail")
    public String
    sendMail(@RequestBody EmailRequest details) {
        String status = emailService.sendSimpleMail(details);
        return status;
    }

    @Operation(summary = "Отправка письма с вложением" ,description = "Принимает EmailRequest (адрес, тема, текст, вложение) и возвращает статус операции")
    @PostMapping("/sendMailWithAttachment")
    public String sendMailWithAttachment(
            @RequestBody EmailRequest details) {
        String status = emailService.sendMailWithAttachment(details);
        return status;
    }
}