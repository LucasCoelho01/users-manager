package com.users_manager.controllers;


import com.users_manager.entities.ResponseTokenJwt;
import com.users_manager.entities.User;
import com.users_manager.entities.dtos.LoginDto;
import com.users_manager.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/login")
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    ResponseEntity<ResponseTokenJwt> login(@RequestBody LoginDto loginDto) throws Exception {
        var authenticationToken = new UsernamePasswordAuthenticationToken(loginDto.login(), loginDto.password());
        var authentication = authenticationManager.authenticate(authenticationToken);

        var tokenJwt = tokenService.generateToken((User) authentication.getPrincipal());

        return new ResponseEntity<>(new ResponseTokenJwt(tokenJwt), HttpStatus.OK);
    }
}
