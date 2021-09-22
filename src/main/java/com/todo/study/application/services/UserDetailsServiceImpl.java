package com.todo.study.application.services;

import com.todo.study.application.entities.UserDetailsData;
import com.todo.study.application.entities.UserModel;
import com.todo.study.application.ports.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException{
        Optional<UserModel> userModelOptional = userRepository.findByEmail(email);
        if (userModelOptional.isEmpty()){
            throw new UsernameNotFoundException("User Not Found");
        }
        return new UserDetailsData(userModelOptional);
    }
}
