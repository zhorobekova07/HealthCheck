package tentech.healthcheck.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tentech.healthcheck.model.entity.User;
import tentech.healthcheck.model.entity.UserAccount;

@Repository
    public interface UserAccountRepository extends JpaRepository <UserAccount, Long> {
    @Query("select user from UserAccount user where user.email=:email")
    UserAccount findByEmail(@Param("email") String email);

}




