package tentech.healthcheck.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tentech.healthcheck.model.dto.UserAccountRequest;
import tentech.healthcheck.model.dto.UserAccountResponse;
import tentech.healthcheck.service.AuthService;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor

public class AuthController {

    private final AuthService authService;

    @PostMapping("/sign-up")

    public UserAccountResponse save(@RequestBody UserAccountRequest userAccountRequest) {
        return authService.signUp(userAccountRequest);
    }


}
