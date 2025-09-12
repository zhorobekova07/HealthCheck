package tentech.healthcheck.mapper;

import org.springframework.stereotype.Component;
import tentech.healthcheck.model.dto.LoginRequest;
import tentech.healthcheck.model.dto.LoginResponse;
import tentech.healthcheck.model.dto.UserAccountResponse;
import tentech.healthcheck.model.entity.UserAccount;

import java.util.ArrayList;
import java.util.List;

@Component
public class LoginMapper {

    public LoginResponse mapToResponse(String token, UserAccount userAccount) {
        return LoginResponse.builder()
                .token(token)
                .roleName(userAccount.getRole())
                .build();
    }

    public UserAccount mapToEntity(LoginRequest loginRequest) {
        UserAccount userAccount = new UserAccount();
        userAccount.setEmail(loginRequest.getEmail());
        userAccount.setPassword(loginRequest.getPassword());
        return userAccount;
    }

}
