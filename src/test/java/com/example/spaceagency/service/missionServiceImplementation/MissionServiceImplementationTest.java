package com.example.spaceagency.service.missionServiceImplementation;

import com.example.spaceagency.exception.MissionAlredyExistException;
import com.example.spaceagency.model.Mission;
import com.example.spaceagency.repository.MissionRepository;
import com.example.spaceagency.service.MissionService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.mockito.Mockito.*;


@RunWith(SpringRunner.class)
@SpringBootTest
class MissionServiceImplementationTest {

    private final MissionService missionService;

    @Autowired
    public MissionServiceImplementationTest(MissionService missionService) {
        this.missionService = missionService;
    }

    @MockBean
    MissionRepository missionRepository;


    @Test
    void createTest() throws MissionAlredyExistException {
        Mission mission = new Mission();
        when(missionRepository.save(mission)).thenReturn(mission);
        Assertions.assertEquals(mission,missionService.create(mission));
    }

    @Test
    void readTest() {
        long id = 1;
        Mission mission = new Mission();
        when(missionRepository.findById(id))
                .thenReturn(Optional.of(mission));
        Assertions.assertEquals(mission, missionService.read(id));
    }

    @Test
    void updateTest() throws MissionAlredyExistException {
        Mission mission = new Mission();
        when(missionRepository.save(mission)).thenReturn(mission);
        Assertions.assertEquals(mission,missionService.update(mission));
    }

    @Test
    void deleteTest() {
        long id = 1;
        missionService.delete(id);
        verify(missionRepository,times(1)).deleteById((long) 1);
    }

    @Test
    void getAllMissionsTest() {
        List<Mission> missions = new ArrayList<>();
        Mission mission = new Mission();
        missions.add(mission);
        Iterable<Mission> iterable = missions;
        when(missionRepository.findAll()).thenReturn(iterable);
        Assertions.assertEquals(1,((List<Mission>) missionService.getAllMissions()).size());
    }
}