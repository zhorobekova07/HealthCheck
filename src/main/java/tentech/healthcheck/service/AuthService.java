package tentech.healthcheck.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import tentech.healthcheck.mapper.AuthMapper;
import tentech.healthcheck.mapper.LoginMapper;
import tentech.healthcheck.model.dto.LoginRequest;
import tentech.healthcheck.model.dto.LoginResponse;
import tentech.healthcheck.model.dto.UserAccountRequest;
import tentech.healthcheck.model.dto.UserAccountResponse;
import tentech.healthcheck.model.entity.UserAccount;
import tentech.healthcheck.model.enums.Role;
import tentech.healthcheck.repository.UserAccountRepository;
import tentech.healthcheck.security.jwt.JwtTokenUtil;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final LoginMapper loginMapper;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final UserAccountRepository userAccountRepository;
    private final AuthMapper authMapper;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserAccountResponse signUp(UserAccountRequest userAccountRequest) {
        UserAccount userAccount = authMapper.mapToEntity(userAccountRequest);
        if (findAll().isEmpty()){
            userAccount.setRole(Role.ADMIN);
        }
        else {
            userAccount.setRole(Role.USER);
        }
        Optional<UserAccount> user1 = Optional.ofNullable(userAccountRepository.findByEmail(userAccountRequest.getEmail()));
        if (user1.isPresent()) {
            throw new RuntimeException("User with this mail already exists in the database");
        }
        if (userAccountRequest.getPassword().matches(("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*._-]).{8,}$"))){
            userAccount.setPassword(passwordEncoder.encode(userAccountRequest.getPassword()));
        }
        else {
            throw new RuntimeException("invalid password");
        }
        userAccountRepository.save(userAccount);
        return authMapper.mapToResponse(userAccount);
    }

    public LoginResponse login(LoginRequest loginRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
        UserAccount userAccount = userAccountRepository.findByEmail(loginRequest.getEmail());
        String jwt = jwtTokenUtil.generateToken(userAccount);
        return loginMapper.mapToResponse(jwt, userAccount);
    }

    public List<UserAccountResponse> findAll() {
        List<UserAccount> userAccounts = userAccountRepository.findAll();
        return authMapper.mapToList(userAccounts);
    }
}

