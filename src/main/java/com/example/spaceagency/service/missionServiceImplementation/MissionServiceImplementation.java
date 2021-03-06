package com.example.spaceagency.service.missionServiceImplementation;

import com.example.spaceagency.exception.FileDbStorageException;
import com.example.spaceagency.exception.MissionAlredyExistException;
import com.example.spaceagency.model.Mission;
import com.example.spaceagency.repository.MissionRepository;
import com.example.spaceagency.service.MissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class MissionServiceImplementation implements MissionService {

    private final MissionRepository missionRepository;

    @Autowired
    public MissionServiceImplementation(MissionRepository missionRepository) {
        this.missionRepository = missionRepository;
    }

    @Override
    @Transactional
    public Mission create(Mission mission) throws MissionAlredyExistException, FileDbStorageException {
        // Check if the file's name contains invalid characters
        if (mission.getName().contains("..")) {
            throw new FileDbStorageException("Sorry! Filename contains invalid path sequence " + mission.getName());
        }
        List<Mission> missions = missionRepository.findAllByName(mission.getName());
        if (missions.size() == 0) {
            return missionRepository.save(mission);
        } else {
            throw new MissionAlredyExistException("Mission already exist");
        }
    }

    @Override
    public Mission read(Long missionId) {
        Optional<Mission> missionOptional = missionRepository.findById(missionId);
        return missionOptional.get();
    }

    @Override
    @Transactional
    public Mission update(Mission mission) throws MissionAlredyExistException {
        List<Mission> missions = missionRepository.findAllByName(mission.getName());
        Optional<Mission> missionById = missionRepository.findById(mission.getId());
        if (missions.size() == 0 || missionById.get().getName().equals(mission.getName())) {
            return missionRepository.save(mission);
        } else {
            throw new MissionAlredyExistException("Mission already exist");
        }
    }

    @Override
    public void delete(Long missionId) {
        missionRepository.deleteById(missionId);
    }

    @Override
    public Iterable<Mission> getAllMissions() {
        return missionRepository.findAll();
    }
}
