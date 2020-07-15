package com.example.spaceagency.configuration.security.services;

import com.example.spaceagency.model.securityuser.ERole;
import com.example.spaceagency.model.securityuser.Role;
import com.example.spaceagency.model.securityuser.SecurityUser;
import com.example.spaceagency.repository.RoleRepository;
import com.example.spaceagency.repository.SecurityUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class RegisterServiceValidator {

    private SecurityUserRepository securityUserRepository;

    private RoleRepository roleRepository;

    @Autowired
    public RegisterServiceValidator(SecurityUserRepository securityUserRepository, RoleRepository roleRepository) {
        this.securityUserRepository = securityUserRepository;
        this.roleRepository = roleRepository;
    }

    public void onSaveUserRolesValidate(SecurityUser securityUser, Set<String> strRoles) throws RuntimeException {
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;
                    case "mod":
                        Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(modRole);

                        break;
                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }
        securityUser.setRoles(roles);
        securityUserRepository.save(securityUser);
    }
}
