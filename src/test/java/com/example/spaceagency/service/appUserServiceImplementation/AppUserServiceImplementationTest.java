package com.example.spaceagency.service.appUserServiceImplementation;

import com.example.spaceagency.exception.AppUserNotFoundException;
import com.example.spaceagency.model.AppUser;
import com.example.spaceagency.repository.AppUserRepository;
import com.example.spaceagency.service.AppUserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class AppUserServiceImplementationTest {

    private final AppUserService appUserService;

    @Autowired
    public AppUserServiceImplementationTest(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @MockBean
    AppUserRepository appUserRepository;

    @Test
    void saveAppUser() {
        AppUser appUser = new AppUser((long) 1, "Jan", "Kowalski", "kowal@test.com", "address");
        when(appUserRepository.save(appUser)).thenReturn(appUser);
        Assertions.assertEquals(appUser, appUserService.saveAppUser(appUser));
    }

    @Test
    void getAppUsers() {
        List<AppUser> appUserList = new ArrayList<>();
        AppUser appUser = new AppUser();
        appUserList.add(appUser);
        when(appUserRepository.findAll()).thenReturn(appUserList);
        Assertions.assertEquals(1, ((List<AppUser>) appUserService.getAppUsers()).size());
    }

    @Test
    void getAppUser() throws AppUserNotFoundException {
        long id = 1;
        AppUser appUser = new AppUser((long) 1, "Jan", "Kowalski", "kowal@test.com", "address");
        when(appUserRepository.findById(id)).thenReturn(Optional.of(appUser));
        Assertions.assertEquals(appUser, appUserService.getAppUser(id));
    }

    @Test
    void ThrowAppUserNotFoundExceptionWhenGetAppUserNotExistTest() throws AppUserNotFoundException {
        long id = 1;
        AppUser appUser = new AppUser((long) 1, "Jan", "Kowalski", "kowal@test.com", "address");
        when(appUserRepository.findById(id)).thenReturn(Optional.empty());
        Assertions.assertThrows(AppUserNotFoundException.class, () -> {
            appUserService.updateAppUser(appUser);
        });
    }

    @Test
    void ThrowAppUserNotFoundExceptionWhenUpdateNotExistingAppUserTest() throws AppUserNotFoundException {
        AppUser appUser = new AppUser((long) 1, "Jan", "Kowalski", "kowal@test.com", "address");
        when(appUserRepository.save(appUser)).thenReturn(appUser);
        Assertions.assertThrows(AppUserNotFoundException.class, () -> {
            appUserService.updateAppUser(appUser);
        });
    }

    @Test
    void saveTest() throws AppUserNotFoundException {
        AppUser appUser = new AppUser((long) 1, "Jan", "Kowalski", "kowal@test.com", "address");
        when(appUserRepository.findById(1L)).thenReturn(Optional.of(appUser));
        appUserService.updateAppUser(appUser);
        verify(appUserRepository, times(1)).save(appUser);
    }

    @Test
    void getAppUserBySecurityUserId() throws AppUserNotFoundException {
        long securityUserId = 1;
        AppUser appUser = new AppUser((long) 1, "Jan", "Kowalski", "kowal@test.com", "address");
        when(appUserRepository.findBySecurityUserId(securityUserId)).thenReturn(Optional.of(appUser));
        Assertions.assertEquals(appUser, appUserService.getAppUserBySecurityUserId(securityUserId));
    }

    @Test
    void ThrowAppUserNotFoundExceptionWhenSearchNotExistingSecurityUser() throws AppUserNotFoundException {
        long securityUserId = 1;
        AppUser appUser = new AppUser((long) 1, "Jan", "Kowalski", "kowal@test.com", "address");
        when(appUserRepository.findBySecurityUserId(securityUserId)).thenReturn(Optional.empty());
        Assertions.assertThrows(AppUserNotFoundException.class, () -> {
            appUserService.getAppUserBySecurityUserId(securityUserId);
        });
    }

}