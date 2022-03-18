package com.stud.accountService;

import com.stud.accountService.dao.UserRepository;
import com.stud.accountService.security.UserExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class UserService {


    public ResponseEntity<User> registerUser(User currentUser, @Autowired UserRepository repository, @Autowired PasswordEncoder encoder) {
        if (repository.findUserByEmail(currentUser.getEmail().toLowerCase(Locale.ROOT)).isEmpty()) {
            currentUser.setPass(encoder.encode(currentUser.getPass()));
            currentUser.setRole("USER");
            currentUser.setEmail(currentUser.getEmail().toLowerCase(Locale.ROOT));
            repository.save(currentUser);
            return new ResponseEntity<>(currentUser, HttpStatus.OK);
        }
       throw new UserExistsException();
    }

    public User testAuth(@Autowired UserRepository repository) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = repository.findUserByEmail(auth.getName().toLowerCase(Locale.ROOT)).get();
        return user;
    }
}
