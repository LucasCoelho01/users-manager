package com.users_manager.services;


import com.users_manager.entities.User;
import com.users_manager.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;

    @Transactional
    public void createUser(String login,  String password) {
        String encriptedPasswd=bCryptPasswordEncoder.encode(password);

        userRepository.save(new User(login, encriptedPasswd));
    }

    public List<User> listAllUsers() {
        return userRepository.findAll();
    }
}
