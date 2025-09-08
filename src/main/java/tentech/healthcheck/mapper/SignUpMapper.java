package tentech.healthcheck.mapper;

import org.springframework.stereotype.Component;
import tentech.healthcheck.model.dto.LoginRequest;
import tentech.healthcheck.model.dto.UserAccountRequest;
import tentech.healthcheck.model.dto.UserAccountResponse;
import tentech.healthcheck.model.entity.UserAccount;

import java.time.LocalDate;

@Component
public class SignUpMapper {

    public UserAccount mapToEntity(UserAccountRequest userAccountRequest) {
        UserAccount userAccount = new UserAccount();
        userAccount.setEmail(userAccountRequest.getEmail());
        userAccount.setPassword(userAccountRequest.getPassword());
        userAccount.setRole(userAccount.getRole());
        userAccount.setDate(LocalDate.now());
        userAccount.setUser(userAccountRequest.getUser());
        return userAccount;
    }
    public UserAccountResponse mapToResponse(UserAccount userAccount) {
        UserAccountResponse userAccountResponse = new UserAccountResponse();
        userAccountResponse.setEmail(userAccount.getEmail());
        userAccountResponse.setPassword(userAccount.getPassword());
        userAccountResponse.setRole(userAccount.getRole());
        userAccountResponse.setUser(userAccount.getUser());
        userAccountResponse.setConfirmPassword(userAccount.getConfirmPassword());
        userAccountResponse.setLocalDate(userAccount.getDate());
        return userAccountResponse;
    }
}
