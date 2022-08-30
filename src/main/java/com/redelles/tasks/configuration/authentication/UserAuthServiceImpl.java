package com.redelles.tasks.configuration.authentication;

import java.util.Collections;

import javax.transaction.Transactional;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.redelles.tasks.user.persistence.UserEntity;
import com.redelles.tasks.user.persistence.UserRepository;

@Service
public class UserAuthServiceImpl implements UserDetailsService {

    private final UserRepository repository;

    public UserAuthServiceImpl(final UserRepository repo) {
        this.repository = repo;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        final UserEntity user = this.repository.findByUsername(username);
        if (user != null) {
            return new User(user.getUsername(), user.getPassword(), Collections.emptyList());
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }
}
