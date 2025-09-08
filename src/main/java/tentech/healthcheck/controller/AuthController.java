package tentech.healthcheck.controller;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tentech.healthcheck.model.dto.LoginRequest;
import tentech.healthcheck.model.dto.LoginResponse;
import tentech.healthcheck.model.dto.UserAccountRequest;
import tentech.healthcheck.model.dto.UserAccountResponse;
import tentech.healthcheck.service.AuthService;

@RestController
@RequestMapping("/api")

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
    @PostMapping("/signup")
    public UserAccountResponse signup(@RequestBody UserAccountRequest request) {
        return authService.signUp(request);
    }

}
