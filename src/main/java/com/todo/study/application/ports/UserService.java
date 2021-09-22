package com.todo.study.application.ports;

import com.todo.study.application.entities.UserModel;

import java.util.Optional;
import java.util.UUID;

public interface UserService {
    void register(UserModel userModel) throws Exception;
    void delete (UUID uuid) throws Exception;
    UserModel update(UserModel userModel);
    Optional<UserModel> get(UUID uuid);
}
