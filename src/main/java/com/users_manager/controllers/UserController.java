package com.users_manager.controllers;

import com.users_manager.entities.User;
import com.users_manager.entities.dtos.CreateUserDto;
import com.users_manager.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    ResponseEntity<String> createPatient(@RequestBody CreateUserDto createUserDto) throws Exception {
        userService.createUser(createUserDto.login(), createUserDto.password());
        return new ResponseEntity<>("Usuário criado", HttpStatus.CREATED);
    }

    @GetMapping
    ResponseEntity<List<User>> listPatients() {
        return new ResponseEntity<>(userService.listAllUsers(), HttpStatus.OK);
    }
}
