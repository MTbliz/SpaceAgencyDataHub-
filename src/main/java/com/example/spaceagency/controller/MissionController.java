package com.example.spaceagency.controller;

import com.example.spaceagency.exception.FileDbStorageException;
import com.example.spaceagency.exception.MissionAlredyExistException;
import com.example.spaceagency.model.FileDb;
import com.example.spaceagency.model.ImageryType;
import com.example.spaceagency.model.Mission;
import com.example.spaceagency.service.MissionService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.Serializable;
import java.time.ZonedDateTime;


@RestController
@RequestMapping("/missions")
public class MissionController {

    private static final Logger LOG = LogManager.getLogger(MissionController.class);

    private final MissionService missionService;

    @Autowired
    public MissionController(MissionService missionService) {
        this.missionService = missionService;
    }

    @RequestMapping()
    public Iterable<Mission> getAllMissions() {
        LOG.info("All missions received.");
        return missionService.getAllMissions();
    }

    @RequestMapping("/{id}")
    public Mission getMission(@PathVariable Long id) {
        LOG.info("Mission with id: " + id + " received.");
        return missionService.read(id);
    }

    @PostMapping()
    public Mission createMission(@RequestParam("file") MultipartFile file,
                                 @RequestParam("name") String name,
                                 @RequestParam("type") ImageryType type,
                                 @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) ZonedDateTime startDate,
                                 @RequestParam("finishDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) ZonedDateTime finishDate
                                 ) throws MissionAlredyExistException, FileDbStorageException, IOException {
        FileDb fileDb = new FileDb(name, file.getContentType(), file.getBytes());
        Mission mission = new Mission(name, type, startDate, finishDate, fileDb);
        Mission createdMission = missionService.create(mission);
        LOG.info("Mission with id: " + createdMission.getId() + " received.");
        return createdMission;
    }

    @DeleteMapping("/{id}")
    public void deleteMission(@PathVariable Long id) {
        LOG.info("Mission with id: " + id + " deleted.");
        missionService.delete(id);
    }

    @PutMapping("/{id}")
    public Mission updateMission(@RequestBody Mission mission) throws MissionAlredyExistException {
        LOG.info("Mission with id: " + mission.getId() + " updated.");
        return missionService.update(mission);
    }
}
