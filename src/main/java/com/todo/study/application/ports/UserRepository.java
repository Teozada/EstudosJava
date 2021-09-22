package com.todo.study.application.ports;

import com.todo.study.application.entities.UserModel;
import org.apache.catalina.User;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository {
    void delete (UUID uuid);
    UserModel create(UserModel userModel);
    Optional<UserModel> get(UUID uuid);
    Optional<UserModel> findByEmail(String email);
}
