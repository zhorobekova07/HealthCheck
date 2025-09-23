package tentech.healthcheck.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import tentech.healthcheck.mapper.UserAccountMapper;
import tentech.healthcheck.model.dto.UserAccountResponse;
import tentech.healthcheck.model.entity.UserAccount;
import tentech.healthcheck.repository.UserAccountRepository;
import java.util.ArrayList;
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

    public List<UserAccountResponse> searchAndPagination(String text, int page, int size) {
        String name = text == null ? "" : text;
        Pageable pageable = PageRequest.of(page - 1, size);
        List<UserAccount> users = userAccountRepository.searchAndPagination(name.toUpperCase(), pageable);
        List<UserAccountResponse> responses = new ArrayList<>();
        for (UserAccount userAcc : users) {
            responses.add(userAccountMapper.mapToResponse(userAcc));
        }
        return responses;
    }
}
