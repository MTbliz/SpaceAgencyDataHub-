package com.example.spaceagency.service.appUserServiceImplementation;

import com.example.spaceagency.exception.AppUserNotFoundException;
import com.example.spaceagency.model.AppUser;
import com.example.spaceagency.repository.AppUserRepository;
import com.example.spaceagency.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AppUserServiceImplementation implements AppUserService {

    private AppUserRepository appUserRepository;

    @Autowired
    public AppUserServiceImplementation(AppUserRepository appUserRepository){
        this.appUserRepository = appUserRepository;
    }

    public AppUser saveAppUser(AppUser newAppUser){
        return appUserRepository.save(newAppUser);
    }

    public Iterable<AppUser> getAppUsers(){
        return appUserRepository.findAll();
    }

    public AppUser getAppUser(Long id) throws AppUserNotFoundException {
        Optional<AppUser> loadedAppUser = appUserRepository.findById(id);
        return loadedAppUser.orElseThrow(() -> new AppUserNotFoundException(id));
    }

    public AppUser updateAppUser(AppUser appUserToUpdate) throws AppUserNotFoundException {
        Optional<AppUser> loadedAppUser = appUserRepository.findById(appUserToUpdate.getId());
        if (loadedAppUser.isPresent()){
            return appUserRepository.save(appUserToUpdate);
        } else {
            throw new AppUserNotFoundException(appUserToUpdate.getId());
        }
    }

    public AppUser getAppUserBySecurityUserId(Long id) throws AppUserNotFoundException {
        Optional<AppUser> loadedAppUser = appUserRepository.findBySecurityUserId(id);
        return loadedAppUser.orElseThrow(() -> new AppUserNotFoundException());
    }
}

