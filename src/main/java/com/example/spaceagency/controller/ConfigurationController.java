package com.example.spaceagency.controller;

import com.example.spaceagency.repository.ConfigurationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ConfigurationController {

    ConfigurationRepository configurationRepository;

    @Autowired
    public ConfigurationController(ConfigurationRepository configurationRepository) {
        this.configurationRepository = configurationRepository;
    }

    @PostMapping("/configuration")

    public @ResponseBody
    String addRecordsToDatabase() {
        return configurationRepository.insertInitialData();
    }
}
