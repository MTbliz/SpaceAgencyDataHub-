package com.example.spaceagency.configuration.security.services;

import com.example.spaceagency.model.User.User;
import com.example.spaceagency.model.securitypayload.response.JwtResponse;
import com.example.spaceagency.model.securitypayload.response.MessageResponse;
import org.springframework.http.ResponseEntity;

import java.util.Set;

public interface RegisterService {

    ResponseEntity<MessageResponse> registerUser(User user, Set<String> strRoles);

    ResponseEntity<JwtResponse> authenticateUser(String userName, String password);
}
