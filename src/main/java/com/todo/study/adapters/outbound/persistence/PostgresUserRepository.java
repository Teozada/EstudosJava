package com.todo.study.adapters.outbound.persistence;

import com.todo.study.application.entities.UserModel;
import com.todo.study.application.ports.UserRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;
@Component
@Primary
public class PostgresUserRepository implements UserRepository {
    private final UserDataPostgressRepository userDataPostgressRepository;
    public PostgresUserRepository(UserDataPostgressRepository userDataPostgressRepository){
        this.userDataPostgressRepository = userDataPostgressRepository;
    }

    @Override
    public void delete(UUID uuid) {
        userDataPostgressRepository.deleteById(uuid);
    }

    @Override
    public UserModel create(UserModel userModel) {
        return userDataPostgressRepository.save(userModel);
    }


    @Override
    public Optional<UserModel> get(UUID uuid) {
        return userDataPostgressRepository.findById(uuid);
    }

    @Override
    public Optional<UserModel> findByEmail(String email) {
        return userDataPostgressRepository.findByEmail(email);
    }
}
