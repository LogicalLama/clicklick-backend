package server.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import server.user.model.UserDetails;
import server.user.model.UserStatus;
import server.user.repository.UserRepository;
import server.user.request.CreateUserRequest;
import server.user.request.UpdateUserRequest;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserDetails saveUser(UserDetails user) {
        return userRepository.save(user);
    }

    public UserDetails createUser(CreateUserRequest request) {
        UserDetails newUser = new UserDetails(
                UUID.randomUUID().toString(),
                request.getName(),
                request.getEmail(),
                request.getPhoneNumber(),
                UserStatus.ACTIVE,
                passwordEncoder.encode(request.getPassword())
        );
        return saveUser(newUser);
    }

    public Object updateUser(UpdateUserRequest request) {
        UserDetails user = userRepository.findById(request.getId())
                .orElseThrow(()-> new RuntimeException("draft not found"));

        Optional.ofNullable(request.getName()).ifPresent(user::setName);
        Optional.ofNullable(request.getPhoneNumber()).ifPresent(user::setPhoneNumber);
        if(request.getPassword()!=null)   user.setPassword(passwordEncoder.encode(request.getPassword()));

        return userRepository.save(user);
    }
}
