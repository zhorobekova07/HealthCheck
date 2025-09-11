package tentech.healthcheck.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import tentech.healthcheck.mapper.LoginMapper;
import tentech.healthcheck.mapper.SignUpMapper;
import tentech.healthcheck.model.dto.LoginRequest;
import tentech.healthcheck.model.dto.LoginResponse;
import tentech.healthcheck.model.dto.UserAccountRequest;
import tentech.healthcheck.model.dto.UserAccountResponse;
import tentech.healthcheck.model.entity.UserAccount;
import tentech.healthcheck.repository.UserAccountRepository;
import tentech.healthcheck.security.jwt.JwtTokenUtil;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor

public class AuthService {
//    private final AuthService authService;
    private final LoginMapper loginMapper;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final UserAccountRepository userAccountRepository;
    private final SignUpMapper  signUpMapper;
    public UserAccountResponse signUp(UserAccountRequest userAccountRequest) {
        UserAccount userAccount = signUpMapper.mapToEntity(userAccountRequest);
        userAccountRepository.save(userAccount);
        return signUpMapper.mapToResponse(userAccount);

    }

    public LoginResponse login(LoginRequest loginRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
        UserAccount userAccount = userAccountRepository.findByEmail(loginRequest.getEmail());
        String jwt = jwtTokenUtil.generateToken(userAccount);
        return loginMapper.mapToResponse(jwt, userAccount);
    }

}

