package server.user.controller;

import io.micrometer.core.annotation.Timed;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import server.user.model.User;
import server.user.request.CreateUserRequest;
import server.user.service.UserService;

@RestController
@RequestMapping("/user")
@Slf4j
@Timed
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody CreateUserRequest request) {
//        User registeredUser = authenticationService.signup(registerUserDto);

        return ResponseEntity.ok(new User());
    }
}
