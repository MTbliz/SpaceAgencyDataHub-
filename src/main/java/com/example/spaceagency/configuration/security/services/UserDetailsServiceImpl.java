package com.example.spaceagency.configuration.security.services;

import com.example.spaceagency.model.securityuser.SecurityUser;
import com.example.spaceagency.repository.SecurityUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private SecurityUserRepository securityUserRepository;

    @Autowired
    public UserDetailsServiceImpl(SecurityUserRepository securityUserRepository) {
        this.securityUserRepository = securityUserRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SecurityUser securityUser = securityUserRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

        return UserDetailsImpl.build(securityUser);
    }
}
