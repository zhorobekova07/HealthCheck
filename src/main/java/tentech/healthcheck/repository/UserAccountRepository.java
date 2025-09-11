package tentech.healthcheck.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tentech.healthcheck.model.entity.UserAccount;

import java.util.List;

public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {

    @Query("select user from User user where upper(user.name) like concat('%', :text, '%') or " +
            "upper(user.email) like concat('%', :text, '%') ")
    List<UserAccount> searchAndPagination (@Param("text") String text, Pageable pageable);
}
