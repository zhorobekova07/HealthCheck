package tentech.healthcheck.mapper;

import org.springframework.stereotype.Component;
import tentech.healthcheck.model.dto.UserAccountRequest;
import tentech.healthcheck.model.dto.UserAccountResponse;
import tentech.healthcheck.model.entity.UserAccount;
import tentech.healthcheck.model.enums.Role;

@Component
public class AuthMapper {
    public UserAccount mapToEntity(UserAccountRequest request) {
        UserAccount userAccount = new UserAccount();
        userAccount.setEmail(request.getEmail());
        if (userAccount.getEmail() == null || !userAccount.getEmail().contains("@")) {
            throw new IllegalArgumentException("Invalid email address");
        }
        userAccount.setPhoneNumber(request.getPhoneNumber());
        userAccount.setPassword(request.getPassword());
        userAccount.setRole(Role.ADMIN);
        userAccount.setDate(request.getDate());
        userAccount.setConfirmPassword(request.getConfirmPassword());
        if (!request.getPassword().equals(request.getConfirmPassword())) {
            throw new RuntimeException("The password does not match !");
        }
        return userAccount;
    }

    public UserAccountResponse mapToUserResponse(UserAccount userAccount) {
        return UserAccountResponse.builder()
                .id(userAccount.getId())
                .date(userAccount.getDate())
                .confirmPassword(userAccount.getConfirmPassword())
                .phoneNumber(userAccount.getPhoneNumber())
                .email(userAccount.getEmail())
                .role(userAccount.getRole())
                .user(userAccount.getUser())
                .build();
    }
}
