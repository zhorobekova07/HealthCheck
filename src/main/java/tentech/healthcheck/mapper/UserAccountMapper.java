package tentech.healthcheck.mapper;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import tentech.healthcheck.model.dto.UserAccountRequest;
import tentech.healthcheck.model.dto.UserAccountResponse;
import tentech.healthcheck.model.entity.UserAccount;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class UserAccountMapper {

    public UserAccountResponse mapToResponse(UserAccount userAccount) {
        return UserAccountResponse.builder()
                .id(userAccount.getId())
                .email(userAccount.getEmail())
                .phoneNumber(userAccount.getPhoneNumber())
                .password(userAccount.getPassword())
                .newPassword(userAccount.getNewPassword())
                .confirmPassword(userAccount.getConfirmPassword())
                .date(userAccount.getDate())
                .build();
    }

    public UserAccount mapToEntity(UserAccountRequest userAccountRequest) {
        UserAccount userAccount = new UserAccount();
        userAccount.setEmail(userAccountRequest.getEmail());
        userAccount.setPhoneNumber(userAccountRequest.getPhoneNumber());
        userAccount.setPassword(userAccountRequest.getPassword());
        userAccount.setNewPassword(userAccountRequest.getNewPassword());
        userAccount.setConfirmPassword(userAccountRequest.getConfirmPassword());
        userAccount.setDate(userAccountRequest.getDate());
        return userAccount;
    }

    public List<UserAccountResponse> mapToList(List<UserAccount> userAccounts) {
        List<UserAccountResponse> userAccountResponses = new ArrayList<>();
        for (UserAccount userAcc : userAccounts) {
            userAccountResponses.add(mapToResponse(userAcc));
        }
        return userAccountResponses;
    }
}
