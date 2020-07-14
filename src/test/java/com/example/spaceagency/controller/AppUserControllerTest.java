package com.example.spaceagency.controller;

import com.example.spaceagency.model.AppUser;
import com.example.spaceagency.service.AppUserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AppUserControllerTest {

    @MockBean
    private AppUserService appUserService;

    private MockMvc mockMvc;

    @Autowired
    AppUserControllerTest(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    @Test
    void getAppUsersTest() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        AppUser appUser1 = new AppUser();
        AppUser appUser2 = new AppUser();
        List<AppUser> appUsers = new ArrayList<>();
        appUsers.add(appUser1);
        appUsers.add(appUser2);
        when(appUserService.getAppUsers()).thenReturn(appUsers);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/appUser"))
                .andExpect(status().isOk())
                .andReturn();
        String response = mvcResult.getResponse().getContentAsString();
        Assertions.assertEquals(objectMapper.writeValueAsString(appUsers), response);
    }

    @Test
    void addAppUserTest() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        AppUser appUser = new AppUser((long) 1, "Jan", "Kowalski", "kowal@test.com", "address");
        when(appUserService.saveAppUser(appUser)).thenReturn(appUser);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/appUser")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(appUser)))
                .andExpect(status().isOk())
                .andReturn();
        String response = mvcResult.getResponse().getContentAsString();
        Assertions.assertEquals(objectMapper.writeValueAsString(appUser), response);
    }

    @Test
    void getAppUserTest() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        AppUser appUser = new AppUser((long) 1, "Jan", "Kowalski", "kowal@test.com", "address");
        when(appUserService.getAppUser((long) 1)).thenReturn(appUser);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/appUser/1"))
                .andExpect(status().isOk())
                .andReturn();
        String response = mvcResult.getResponse().getContentAsString();
        Assertions.assertEquals(objectMapper.writeValueAsString(appUser), response);
    }

    @Test
    void updateAppUserTest() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        AppUser appUser1 = new AppUser((long) 1, "Jan", "Kowalski", "kowal@test.com", "address");
        AppUser appUser2 = new AppUser((long) 1, "Michal", "Kowalski", "kowal@test.com", "address");
        when(appUserService.updateAppUser(appUser1)).thenReturn(appUser2);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put("/appUser/1")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(appUser1)))
                .andExpect(status().isOk())
                .andReturn();
        String response = mvcResult.getResponse().getContentAsString();
        Assertions.assertEquals(objectMapper.writeValueAsString(appUser2), response);
    }

    @Test
    void getAppUserBySecurityUserIdTest() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        AppUser appUser = new AppUser((long) 1, "Jan", "Kowalski", "kowal@test.com", "address");
        when(appUserService.getAppUserBySecurityUserId((long) 1)).thenReturn(appUser);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/appUser/search/1"))
                .andExpect(status().isOk())
                .andReturn();
        String response = mvcResult.getResponse().getContentAsString();
        Assertions.assertEquals(objectMapper.writeValueAsString(appUser), response);
    }
}
