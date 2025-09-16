package tentech.healthcheck.repository;

import jdk.jfr.Registered;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tentech.healthcheck.model.entity.UserAccount;
@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount,Long> {

}
