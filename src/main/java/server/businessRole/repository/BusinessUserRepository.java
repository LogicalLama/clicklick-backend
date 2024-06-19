package server.businessRole.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import server.businessRole.model.BusinessUserKey;
import server.businessRole.model.BusinessUser;

public interface BusinessUserRepository extends JpaRepository<BusinessUser, BusinessUserKey> {

}