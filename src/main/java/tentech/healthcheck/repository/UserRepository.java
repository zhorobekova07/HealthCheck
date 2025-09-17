package tentech.healthcheck.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tentech.healthcheck.model.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
