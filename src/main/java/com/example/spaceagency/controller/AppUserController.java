package com.example.spaceagency.controller;

import com.example.spaceagency.exception.AppUserNotFoundException;
import com.example.spaceagency.model.AppUser;
import com.example.spaceagency.service.AppUserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/appUser")
public class AppUserController {
    private static final Logger LOG = LogManager.getLogger(AppUserController.class);

    private AppUserService appUserService;

    @Autowired
    public AppUserController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @GetMapping()
    public Iterable<AppUser> getAppUsers() throws AppUserNotFoundException {
        LOG.info("All AppUsers received.");
        return appUserService.getAppUsers();
    }

    @PostMapping
    public AppUser addAppUser(@RequestBody AppUser newAppUser) {
        AppUser createdAppUser = appUserService.saveAppUser(newAppUser);
        LOG.info("AppUser with id: " + createdAppUser.getId() + " created.");
        return createdAppUser;
    }

    @GetMapping("/{id}")
    public AppUser getAppUser(@PathVariable Long id) throws AppUserNotFoundException {
        LOG.info("AppUser with id: " + id + " received.");
        return appUserService.getAppUser(id);
    }

    @PutMapping("/{id}")
    public AppUser updateAppUser(@RequestBody AppUser appUserToUpdate) throws AppUserNotFoundException {
        LOG.info("AppUser with id: " + appUserToUpdate.getId() + " updated.");
        return appUserService.updateAppUser(appUserToUpdate);
    }

    @GetMapping("/search/{id}")
    public AppUser getAppUserBySecurityUserId(@PathVariable Long id) throws AppUserNotFoundException {
        LOG.info("AppUser with id: " + id + " received.");
        return appUserService.getAppUserBySecurityUserId(id);
    }

}
