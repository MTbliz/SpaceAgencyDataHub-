package com.example.spaceagency.service;

import com.example.spaceagency.exception.AppUserNotFoundException;
import com.example.spaceagency.model.AppUser;

public interface AppUserService {

    AppUser saveAppUser(AppUser newAppUser);

    Iterable<AppUser> getAppUsers();

    AppUser getAppUser(Long id) throws AppUserNotFoundException;

    AppUser updateAppUser(AppUser appUserToUpdate) throws AppUserNotFoundException;

    AppUser getAppUserBySecurityUserId(Long id) throws AppUserNotFoundException;
}
