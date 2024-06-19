package server.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import server.user.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

}
