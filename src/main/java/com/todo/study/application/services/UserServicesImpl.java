package com.todo.study.application.services;

import com.todo.study.application.entities.UserModel;
import com.todo.study.application.ports.UserRepository;
import com.todo.study.application.ports.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;
import java.util.UUID;

public class UserServicesImpl implements UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    public UserServicesImpl(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }
    @Override
    public void register(UserModel userModel) throws Exception {
        try {
            userModel.setSenha(bCryptPasswordEncoder.encode(userModel.getSenha()));
            this.userRepository.create(userModel);
        } catch (Exception error) {
            throw new Exception(error);
        }
    }

    @Override
    public Optional<UserModel> get(UUID uuid) {
        return this.userRepository.get(uuid);
    }

    @Override
    public void delete(UUID uuid) throws Exception{
        try{
            Optional<UserModel> userModel = this.userRepository.get(uuid);
            if(userModel.isPresent()){
                this.userRepository.delete(uuid);
            }
        } catch (Exception error) {
            throw new Exception(error);
        }
    }

    @Override
    public UserModel update(UserModel userModel) {

        return null;
    }

}
