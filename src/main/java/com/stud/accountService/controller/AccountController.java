package com.stud.accountService.controller;

import com.stud.accountService.User;
import com.stud.accountService.UserService;
import com.stud.accountService.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
public class AccountController {

    @Autowired
    UserRepository repository;

    @Autowired
    PasswordEncoder encoder;

    @PostMapping("/api/auth/signup")
    public ResponseEntity<User> registerUser(@Valid @RequestBody User user, BindingResult bindingResult,
                                             @Autowired UserService service) {
        if (bindingResult.hasErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        return service.registerUser(user, repository, encoder);
    }

    @GetMapping("/api/empl/payment")
    public User getAuth(@Autowired UserService service) {
       return service.testAuth(repository);
    }
}
