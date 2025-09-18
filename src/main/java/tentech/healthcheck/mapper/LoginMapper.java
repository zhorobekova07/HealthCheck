package tentech.healthcheck.mapper;

import org.springframework.stereotype.Component;
import tentech.healthcheck.model.dto.LoginResponse;
import tentech.healthcheck.model.entity.UserAccount;

@Component
public class LoginMapper {

    public LoginResponse mapToResponse(String token, UserAccount userAccount) {
        return LoginResponse.builder()
                .token(token)
                .roleName(userAccount.getRole())
                .build();
    }
}
