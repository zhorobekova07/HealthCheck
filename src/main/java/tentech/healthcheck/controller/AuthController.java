package tentech.healthcheck.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

    @Operation(summary = "Этот метод сохраняет Пользователя", description = "Создает и возвращает пользователя")
    @PostMapping("/sign-up")
    public UserAccountResponse signup(@RequestBody UserAccountRequest request) {
        return authService.signUp(request);
    }

    @Operation(summary = "Логин", description = "Принимает email и пароль, возвращает JWT-токен и информацию о пользователе")
    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest) {
        return authService.login(loginRequest);
    }

    @PostMapping("/send")
    public ResponseEntity<String> sendSimpleMail(@RequestBody EmailRequest emailRequest) {
        String response = emailService.sendSimpleMail(emailRequest);
        if (response.startsWith("Mail Sent Successfully")) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    } // Endpoint для отправки кода сброса пароля

    @PostMapping("/reset-code")
    public ResponseEntity<String> sendResetCode(@RequestParam String email) {
        try {
            authService.sendResetCodeForPassword(email);
            return ResponseEntity.ok("Reset code sent to " + email);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    // Endpoint для установки нового пароля
    @PostMapping("/reset-password")
    public ResponseEntity

            <String> resetPassword(@RequestParam String email, @RequestParam Long resetCode,
                                   @RequestParam String newPassword, @RequestParam String confirmPassword) {
        return new ResponseEntity<>
                (authService.setNewPassword(email, resetCode, newPassword, confirmPassword), HttpStatus.CREATED);
    }
}


