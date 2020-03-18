package com.example.spaceagency.service;

import com.example.spaceagency.exception.MissionAlredyExistException;
import com.example.spaceagency.model.Mission;

public interface MissionService {
    Mission create(Mission mission) throws MissionAlredyExistException;

    Mission read(Long missionId);

    void update(Mission mission) throws MissionAlredyExistException;

    void delete(Long missionId);

    Iterable<Mission> getAllMissions();
}
