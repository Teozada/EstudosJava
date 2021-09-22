package com.todo.study.adapters.inbound.controllers;

import com.todo.study.adapters.inbound.dtos.CreateUserDto;
import com.todo.study.application.entities.UserModel;
import com.todo.study.application.ports.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;
import java.util.UUID;

@RestController(value = "/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping("/register")
    public ResponseEntity<Object> registerUser(@RequestBody @Valid CreateUserDto createUserDto) throws Exception {
        try {
            UserModel userModel = new UserModel();
            BeanUtils.copyProperties(createUserDto, userModel);
            userService.register(userModel);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception error) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getUserData(@PathVariable(value = "id")UUID uuid){
        Optional<UserModel> userModelOptional = userService.get(uuid);
        if(userModelOptional.isPresent()) {
            return new ResponseEntity<>(userModelOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("User Not Found", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable(value = "id") UUID uuid){
        try{
            this.userService.delete(uuid);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception error) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
