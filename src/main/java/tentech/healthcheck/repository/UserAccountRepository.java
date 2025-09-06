package tentech.healthcheck.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tentech.healthcheck.model.entity.UserAccount;

public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {

}
