package com.stud.accountService.security;

import com.stud.accountService.User;
import com.stud.accountService.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = repository.findUserByEmail(email.toLowerCase(Locale.ROOT));

        if (user.isPresent()) {
            return new UserDetailsImpl(user.get());
        } else {
            throw new UsernameNotFoundException("Not found");
        }
    }
}
