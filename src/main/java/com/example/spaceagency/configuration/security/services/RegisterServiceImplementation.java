package com.example.spaceagency.configuration.security.services;

import com.example.spaceagency.model.securityuser.SecurityUser;
import com.example.spaceagency.model.securitypayload.response.JwtResponse;
import com.example.spaceagency.model.securitypayload.response.MessageResponse;
import com.example.spaceagency.repository.SecurityUserRepository;
import com.example.spaceagency.configuration.security.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RegisterServiceImplementation implements RegisterService {

    private SecurityUserRepository securityUserRepository;

    private RegisterServiceValidator registerServiceValidator;

    private AuthenticationManager authenticationManager;

    private JwtUtils jwtUtils;

    @Autowired
    public RegisterServiceImplementation(SecurityUserRepository securityUserRepository, RegisterServiceValidator registerServiceValidator, AuthenticationManager authenticationManager, JwtUtils jwtUtils) {
        this.securityUserRepository = securityUserRepository;
        this.registerServiceValidator = registerServiceValidator;
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
    }

    @Override
    public ResponseEntity<MessageResponse> registerUser(SecurityUser securityUser, Set<String> strRoles) {

        if (securityUserRepository.existsByUsername(securityUser.getUsername())) {

            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }
        if (securityUserRepository.existsByEmail(securityUser.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }
        registerServiceValidator.onSaveUserRolesValidate(securityUser, strRoles);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    @Override
    public ResponseEntity<JwtResponse> authenticateUser(String userName, String password) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userName, password));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));
    }
}

