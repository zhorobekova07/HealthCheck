package tentech.healthcheck.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tentech.healthcheck.model.dto.*;
import tentech.healthcheck.service.AuthService;
import tentech.healthcheck.serviceImpl.EmailService;

@Tag(name = "User Controller")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;
    private final EmailService emailService;

    @Operation(summary = "Этот метод сохраняет Пользователя" ,description = "Создает и возвращает пользователя")
    @PostMapping("/sign-up")
    public UserAccountResponse signup(@RequestBody UserAccountRequest request) {
        return authService.signUp(request);
    }

    @Operation(summary = "Логин" ,description = "Принимает email и пароль, возвращает JWT-токен и информацию о пользователе")
    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest) {
        return authService.login(loginRequest);
    }

    @PostMapping("/sendMail")
    public String sendMail(@RequestBody EmailRequest details) {
        return emailService.sendSimpleMail(details);
    }

    @PostMapping("/sendMailWithAttachment")
    public String sendMailWithAttachment(@RequestBody EmailRequest details) {
        return emailService.sendMailWithAttachment(details);
    }
}
