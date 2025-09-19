package tentech.healthcheck.mapper;

import org.springframework.stereotype.Component;
import tentech.healthcheck.model.dto.UserAccountRequest;
import tentech.healthcheck.model.dto.UserAccountResponse;
import tentech.healthcheck.model.entity.UserAccount;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class AuthMapper {

    public UserAccountResponse mapToResponse(UserAccount userAccount) {
        return UserAccountResponse.builder()
                .id(userAccount.getId())
                .role(userAccount.getRole())
                .phoneNumber(userAccount.getPhoneNumber())
                .email(userAccount.getEmail())
                .localDate(LocalDate.now())
                .build();
    }

    public UserAccount mapToEntity(UserAccountRequest userAccountRequest) {
        UserAccount userAccount = new UserAccount();
        userAccount.setEmail(userAccountRequest.getEmail());
        userAccount.setPassword(userAccountRequest.getPassword());
        userAccount.setPhoneNumber(userAccountRequest.getPhoneNumber());
        userAccount.setRole(userAccount.getRole());
        userAccount.setDate(LocalDate.now());
        return userAccount;
    }

    public List<UserAccountResponse> mapToList(List<UserAccount> userAccounts) {
        List<UserAccountResponse> userAccountResponses = new ArrayList<>();
        for (UserAccount userAccount : userAccounts) {
            userAccountResponses.add(mapToResponse(userAccount));
        }
        return userAccountResponses;
    }
}
