package tentech.healthcheck.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tentech.healthcheck.mapper.AuthMapper;
import tentech.healthcheck.model.dto.UserAccountRequest;
import tentech.healthcheck.model.dto.UserAccountResponse;
import tentech.healthcheck.model.entity.UserAccount;
import tentech.healthcheck.repository.UserAccountRepository;

@Service
@RequiredArgsConstructor

public class AuthService {
    private final AuthMapper authMapper;
    private final UserAccountRepository userAccountRepository;

    public UserAccountResponse signUp(UserAccountRequest userAccountRequest) {
        UserAccount userAccount = authMapper.mapToEntity(userAccountRequest);
        userAccountRepository.save(userAccount);
        return authMapper.mapToUserResponse(userAccount);
    }



}