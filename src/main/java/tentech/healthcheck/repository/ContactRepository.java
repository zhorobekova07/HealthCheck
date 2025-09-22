package tentech.healthcheck.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tentech.healthcheck.model.entity.Contact;

@Repository
public interface ContactRepository extends JpaRepository<Contact,Long> {
}
