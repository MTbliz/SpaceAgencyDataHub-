package com.example.spaceagency.controller;

import com.example.spaceagency.exception.FileDbNotFoundException;
import com.example.spaceagency.exception.ProductNotFoundException;
import com.example.spaceagency.model.FileDb;
import com.example.spaceagency.model.Product;
import com.example.spaceagency.service.FileDbDownloadService;
import com.example.spaceagency.service.ProductService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FileDbController {

    private static final Logger LOG = LogManager.getLogger(FileDbController.class);

    FileDbDownloadService fileDbDownloadService;

    ProductService productService;

    @Autowired
    private FileDbController(FileDbDownloadService fileDbDownloadService, ProductService productService) {
        this.fileDbDownloadService = fileDbDownloadService;
        this.productService = productService;
    }

    @GetMapping("/downloadFile/{id}/{fileName}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String id, @PathVariable String fileName) throws FileDbNotFoundException {
        // Load file from database

        FileDb fileDb = fileDbDownloadService.getFileDbByUrl("http://localhost:8080/downloadFile/" + id + "/" + fileName);
        LOG.info("File with id: " + fileDb.getId() + " downloaded.");
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(fileDb.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDb.getFileName() + "\"")
                .body(new ByteArrayResource(fileDb.getData()));
    }
}
