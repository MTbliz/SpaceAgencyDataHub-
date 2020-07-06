package com.example.spaceagency.service.fileDbDownloadServiceImplementation;

import com.example.spaceagency.exception.FileDbNotFoundException;
import com.example.spaceagency.model.FileDb;
import com.example.spaceagency.model.Footprint;
import com.example.spaceagency.model.Mission;
import com.example.spaceagency.model.Product;
import com.example.spaceagency.repository.FileDbRepository;
import com.example.spaceagency.repository.ProductRepository;
import com.example.spaceagency.service.FileDbDownloadService;
import com.example.spaceagency.service.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.time.ZonedDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
class FileDbDownloadServiceImplementationTest {

    private final FileDbDownloadService fileDbDownloadService;

    @Autowired
    public FileDbDownloadServiceImplementationTest(FileDbDownloadService fileDbDownloadService) {
        this.fileDbDownloadService = fileDbDownloadService;
    }

    @MockBean
    FileDbRepository fileDbRepository;

    @Test
    void getFileDb() throws IOException, FileDbNotFoundException {
        long id = 1;
        byte[] fileContent = "bar".getBytes(StandardCharsets.UTF_8);
        MockMultipartFile filePart = new MockMultipartFile("file", "orig", null, fileContent);
        FileDb fileDb = new FileDb("Test File", filePart.getContentType(), filePart.getBytes());

        when(fileDbRepository.findById(id)).thenReturn(Optional.of(fileDb));
        Assertions.assertEquals(fileDb,fileDbDownloadService.getFileDb(id));
    }

    @Test
    void getFileDbByUrl() throws IOException, FileDbNotFoundException {
        String url = "url";
        byte[] fileContent = "bar".getBytes(StandardCharsets.UTF_8);
        MockMultipartFile filePart = new MockMultipartFile("file", "orig", null, fileContent);
        FileDb fileDb = new FileDb("Test File", filePart.getContentType(), filePart.getBytes());

        when(fileDbRepository.findByUrl(url)).thenReturn(Optional.of(fileDb));
        Assertions.assertEquals(fileDb,fileDbDownloadService.getFileDbByUrl(url));
    }

}