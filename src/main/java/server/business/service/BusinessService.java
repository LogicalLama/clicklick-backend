package server.business.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import server.business.model.Business;
import server.business.model.BusinessStatus;
import server.business.repository.BusinessRepository;
import server.business.request.CreateBusinessRequest;
import server.business.request.UpdateBusinessRequest;

import java.util.Optional;
import java.util.UUID;
//import server.user.repository.UserRepository;
//import server.user.service.UserService;

@Service
public class BusinessService {
//    @Autowired
//    private UserRepository userRepository;
//    @Autowired
//    private UserService userService;
    @Autowired
    private BusinessRepository businessRepository;

    public Business setup(CreateBusinessRequest setupRequest) {
        return createOrganization(setupRequest);
    }

    private Business createOrganization(CreateBusinessRequest request) {
        String businessID = UUID.randomUUID().toString();
        Business business =
                new Business(businessID, request.getName(), BusinessStatus.NEW,
                        request.getAddress(), request.getPincode(),
                        request.getWebsite(), request.getBase64logo());
        return businessRepository.save(business);
    }

    public Business update(UpdateBusinessRequest request) {
        Business business = businessRepository.findById(request.getId())
                .orElseThrow(()-> new RuntimeException("draft not found"));

        Optional.ofNullable(request.getName()).ifPresent(business::setName);
        Optional.ofNullable(request.getAddress()).ifPresent(business::setAddress);
        Optional.ofNullable(request.getPincode()).ifPresent(business::setPincode);
        Optional.ofNullable(request.getWebsite()).ifPresent(business::setWebsite);
        Optional.ofNullable(request.getBase64logo()).ifPresent(business::setBase64logo);

        return businessRepository.save(business);
    }

//    private Long ttlMillis = 3600000l;
//
//
//    public void approveSignup(
//            String organizationName, String name,
//            String email, String phoneNumber) {
//        Organization organization = createOrganization(organizationName);
//        User user =
//                userService.createOrganizationUser(
//                        null,
//                        UserStatus.ACTIVE,
//                        organization.getId(),
//                        UserRole.ADMIN,
//                        name,
//                        email,
//                        phoneNumber,
//                        null);
//    }
//
//
//    public Organization getOrganizationDetails(String organizationID) {
//        return organizationRepository.findById(organizationID)
//                .orElseThrow(()-> new RuntimeException(" invalid org id"));
//    }

}
