package tentech.healthcheck.mapper;

import org.springframework.stereotype.Component;
import tentech.healthcheck.model.dto.UserAccountRequest;
import tentech.healthcheck.model.dto.UserAccountResponse;
import tentech.healthcheck.model.entity.UserAccount;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class SignUpMapper {

    public UserAccount mapToEntity(UserAccountRequest userAccountRequest) {
        UserAccount userAccount = new UserAccount();
        userAccount.setEmail(userAccountRequest.getEmail());
        userAccount.setPassword(userAccountRequest.getPassword());
        userAccount.setPhoneNumber(userAccountRequest.getPhoneNumber());
        userAccount.setRole(userAccount.getRole());
        userAccount.setDate(LocalDate.now());
        return userAccount;
    }
    public UserAccountResponse mapToResponse(UserAccount userAccount) {
        UserAccountResponse userAccountResponse = new UserAccountResponse();
        userAccountResponse.setId(userAccount.getId());
        userAccountResponse.setEmail(userAccount.getEmail());
        userAccountResponse.setRole(userAccount.getRole());
        userAccountResponse.setPhoneNumber(userAccount.getPhoneNumber());
        userAccountResponse.setLocalDate(userAccount.getDate());
        return userAccountResponse;
    }

    public List<UserAccountResponse> mapToList(List<UserAccount> userAccounts) {
        List<UserAccountResponse> userAccountResponses = new ArrayList<>();
        for (UserAccount userAccount : userAccounts) {
            userAccountResponses.add(mapToResponse(userAccount));
        }
        return userAccountResponses;
    }
}
