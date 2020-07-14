package com.example.spaceagency.configuration.security.services;

import com.example.spaceagency.model.securityuser.SecurityUser;
import com.example.spaceagency.model.securitypayload.response.JwtResponse;
import com.example.spaceagency.model.securitypayload.response.MessageResponse;
import org.springframework.http.ResponseEntity;

import java.util.Set;

public interface RegisterService {

    ResponseEntity<MessageResponse> registerUser(SecurityUser securityUser, Set<String> strRoles);

    ResponseEntity<JwtResponse> authenticateUser(String userName, String password);
}
