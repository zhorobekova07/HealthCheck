package tentech.healthcheck.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import tentech.healthcheck.model.entity.UserAccount;
import tentech.healthcheck.repository.UserAccountRepository;
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserAccountRepository userAccountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserAccount userAccount = userAccountRepository.findByEmail(username);
        if (userAccount == null) {
            throw new UsernameNotFoundException("Пользователь не найден");
        }
        return userAccount;
    }
}
