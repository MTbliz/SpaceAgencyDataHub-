package com.example.spaceagency.controller;

import com.example.spaceagency.exception.AppUserNotFoundException;
import com.example.spaceagency.model.AppUser;
import com.example.spaceagency.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/appUser")
public class AppUserController {
    private AppUserService appUserService;

    @Autowired
    public AppUserController(AppUserService appUserService){
        this.appUserService = appUserService;
    }



    @GetMapping()
    public Iterable<AppUser> getAppUsers() throws AppUserNotFoundException {
        return appUserService.getAppUsers();
    }

    @PostMapping
    public AppUser addAppUser(@RequestBody AppUser newAppUser){
        return appUserService.saveAppUser(newAppUser);
    }

    @GetMapping("/{id}")
    public AppUser getAppUser(@PathVariable Long id) throws AppUserNotFoundException {
        return appUserService.getAppUser(id);
    }

    @PutMapping("/{id}")
    public AppUser updateAppUser(@RequestBody AppUser appUserToUpdate) throws AppUserNotFoundException {
        return appUserService.updateAppUser(appUserToUpdate);
    }

    @GetMapping("/search/{id}")
    public AppUser getAppUserBySecurityUserId(@PathVariable Long id) throws AppUserNotFoundException {
        return appUserService.getAppUserBySecurityUserId(id);
    }

}
