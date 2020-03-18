package com.example.spaceagency.controller;

import com.example.spaceagency.exception.MissionAlredyExistException;
import com.example.spaceagency.model.ImageryType;
import com.example.spaceagency.model.Mission;
import com.example.spaceagency.service.MissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/missions")
public class MissionController {


   private final MissionService missionService;

   @Autowired
   public MissionController(MissionService missionService){
       this.missionService = missionService;
   }

   @RequestMapping()
   public Iterable<Mission> getAllMissions(){
      return missionService.getAllMissions();
   }

    @RequestMapping("/{id}")
    public Mission getMission(@PathVariable Long id){
        return missionService.read(id);
    }

   @PostMapping()
    public void createMission(@RequestBody Mission mission) throws MissionAlredyExistException {
      missionService.create(mission);
   }

    @DeleteMapping("/{id}")
   public void deleteMission(@PathVariable Long id){
       missionService.delete(id);
    }

    @PutMapping("/{id}")
    public void updateMission(@RequestBody Mission mission ) throws MissionAlredyExistException {
       missionService.update(mission);
    }
}
