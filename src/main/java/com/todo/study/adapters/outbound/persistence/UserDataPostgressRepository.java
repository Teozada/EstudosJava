package com.todo.study.adapters.outbound.persistence;

import com.todo.study.application.entities.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserDataPostgressRepository extends JpaRepository<UserModel, UUID> {
    public Optional<UserModel> findByEmail(String email);
}
