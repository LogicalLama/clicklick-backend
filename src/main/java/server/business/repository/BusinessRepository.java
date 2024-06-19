package server.business.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import server.business.model.Business;


@Repository
public interface BusinessRepository extends JpaRepository<Business, String> {

}
