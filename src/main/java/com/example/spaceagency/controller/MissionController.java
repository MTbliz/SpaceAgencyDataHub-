package com.example.spaceagency.controller;

import com.example.spaceagency.exception.MissionAlredyExistException;
import com.example.spaceagency.model.Mission;
import com.example.spaceagency.service.MissionService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



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
        LOG.info("Info log in our getAllMissions method");
        return missionService.getAllMissions();
    }

    @RequestMapping("/{id}")
    public Mission getMission(@PathVariable Long id) {
        LOG.info("Info log in our getMission method");
        return missionService.read(id);
    }

    @PostMapping()
    public String createMission(@RequestBody Mission mission) {
        try{
            LOG.info("createMission method");
            missionService.create(mission);
            return "Mission created";
        } catch (MissionAlredyExistException e){
          return e.getMessage();
        }
    }

    @DeleteMapping("/{id}")
    public void deleteMission(@PathVariable Long id) {
        LOG.info("deleteMission");
        missionService.delete(id);
    }

    @PutMapping("/{id}")
    public void updateMission(@RequestBody Mission mission) throws MissionAlredyExistException {
        LOG.info("updateMission");
        missionService.update(mission);
    }
}
