package com.example.spaceagency.controller;

import com.example.spaceagency.model.FileDb;
import com.example.spaceagency.service.AppUserService;
import com.example.spaceagency.service.FileDbDownloadService;
import com.example.spaceagency.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class FileDbControllerTest {

    @MockBean
    private FileDbDownloadService fileDbDownloadService;

    @MockBean
    private ProductService productService;

    private MockMvc mockMvc;

    @Autowired
    FileDbControllerTest(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    @Test
    void downloadFile() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        long id = 1;
        byte[] fileContent = "bar".getBytes(StandardCharsets.UTF_8);
        MockMultipartFile filePart = new MockMultipartFile("filename", "filename", String.valueOf(MediaType.APPLICATION_JSON), fileContent);
        FileDb fileDb = new FileDb("filename", filePart.getContentType(), filePart.getBytes());

        when(fileDbDownloadService.getFileDbByUrl("http://localhost:8080/downloadFile/1/filename")).thenReturn(fileDb);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/downloadFile/1/filename"))
                .andExpect(status().isOk())
                .andReturn();
    }
}