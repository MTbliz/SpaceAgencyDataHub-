package com.example.spaceagency.controller;

import com.example.spaceagency.configuration.security.services.RegisterService;
import com.example.spaceagency.model.User.User;
import com.example.spaceagency.model.securitypayload.request.LoginRequest;
import com.example.spaceagency.model.securitypayload.request.SignupRequest;
import com.example.spaceagency.model.securitypayload.response.JwtResponse;
import com.example.spaceagency.model.securitypayload.response.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Set;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private PasswordEncoder encoder;

    private RegisterService registerService;

    @Autowired
    public AuthController(PasswordEncoder encoder, RegisterService registerService) {
        this.encoder = encoder;
        this.registerService = registerService;
    }

    @PostMapping("/signin")
    public ResponseEntity<JwtResponse> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();
        return registerService.authenticateUser(username, password);
    }

    @PostMapping("/signup")
    public ResponseEntity<MessageResponse> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        User user = new User(signUpRequest.getFirstName(),signUpRequest.getLastName(), signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));
        Set<String> strRoles = signUpRequest.getRole();
        ResponseEntity<MessageResponse> responseEntity = registerService.registerUser(user, strRoles);
        return responseEntity;
    }
}

