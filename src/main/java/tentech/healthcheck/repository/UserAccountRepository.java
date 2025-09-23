package tentech.healthcheck.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tentech.healthcheck.model.entity.User;
import tentech.healthcheck.model.entity.UserAccount;

import java.util.List;

@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {
    @Query("select user_account from UserAccount user_account where user_account.email=:email")
    UserAccount findByEmail(@Param("email") String email);

    List<UserAccount> searchAndPagination(String upperCase, Pageable pageable);
}




