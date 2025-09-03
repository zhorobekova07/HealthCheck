package tentech.healthcheck.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tentech.healthcheck.model.dto.LoginRequest;
import tentech.healthcheck.model.dto.LoginResponse;
import tentech.healthcheck.service.AuthService;

@RestController
@RequestMapping("/api/login")

public class AuthController {

    private final AuthService authService;
    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }
    @PostMapping("/login")
    public LoginResponse save(LoginRequest loginRequest) {
        return authService.login(loginRequest);
    }

}
