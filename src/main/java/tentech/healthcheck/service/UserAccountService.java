package tentech.healthcheck.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tentech.healthcheck.mapper.UserAccountMapper;
import tentech.healthcheck.model.dto.UserAccountResponse;
import tentech.healthcheck.model.entity.UserAccount;
import tentech.healthcheck.repository.UserAccountRepository;
import tentech.healthcheck.security.jwt.JwtTokenUtil;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserAccountService {
    private final UserAccountRepository userAccountRepository;
    private final UserAccountMapper userAccountMapper;


    public UserAccountResponse findById(Long id) {
        UserAccount userAccount = userAccountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id " + id));
        return userAccountMapper.mapToResponse(userAccount);
    }

    public List<UserAccountResponse> findAll() {
        List<UserAccount> userAccounts = userAccountRepository.findAll();
        return userAccountMapper.mapToList(userAccounts);
    }

    public void deleteById(Long id) {
        userAccountRepository.deleteById(id);
    }
}
