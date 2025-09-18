package tentech.healthcheck.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tentech.healthcheck.model.dto.EmailRequest;
import tentech.healthcheck.model.dto.UserAccountRequest;
import tentech.healthcheck.model.dto.UserAccountResponse;
import tentech.healthcheck.service.AuthService;
import tentech.healthcheck.serviceImpl.EmailService;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final EmailService emailService;

    @PostMapping("/sign-up")
    public UserAccountResponse save(@RequestBody UserAccountRequest userAccountRequest) {
        return authService.signUp(userAccountRequest);
    }

    @PostMapping("/sendMail")
    public String
    sendMail(@RequestBody EmailRequest details) {
        String status
                = emailService.sendSimpleMail(details);
        return status;
    }

    @PostMapping("/sendMailWithAttachment")
    public String sendMailWithAttachment(
            @RequestBody EmailRequest details) {
        String status
                = emailService.sendMailWithAttachment(details);
        return status;
    }
}
