package server.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import server.user.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByEmail(String email);

//    @Query("SELECT ou from  OrganizationUser ou where ou.email=?1")
//    List<User> getUserByEmail(String email);
//
//    @Query("SELECT ou from  OrganizationUser ou where ou.email=?1 and ou.password=?2")
//    User getUserByEmailAndPassword(String email, String password);
//
//    @Query("SELECT ou from  OrganizationUser ou where ou.orgID=?1")
//    List<User> findOrganizationUserByID(String organizationID);
//
//    @Query("SELECT ou from  OrganizationUser ou where ou.id=?1 and ou.orgID=?2")
//    Optional<User> findByIdAndOrgID(String id, String orgID);
//
//    @Query("SELECT ou from  OrganizationUser ou where ou.orgID=?1 and ou.role=?2")
//    List<User> findByRole(String orgID, UserRole userRole);
}
