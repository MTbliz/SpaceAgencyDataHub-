package com.example.spaceagency.controller;

import com.example.spaceagency.model.FileDb;
import com.example.spaceagency.model.ImageryType;
import com.example.spaceagency.model.Mission;
import com.example.spaceagency.service.MissionService;
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

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class MissionControllerTest {

    @MockBean
    private MissionService missionService;

    private MockMvc mockMvc;

    @Autowired
    MissionControllerTest(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    @Test
    void getAllMissionsTest() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        Mission mission1 = new Mission();
        Mission mission2 = new Mission();
        List<Mission> missions = new ArrayList<>();
        missions.add(mission1);
        missions.add(mission2);
        when(missionService.getAllMissions()).thenReturn(missions);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/missions"))
                .andExpect(status().isOk())
                .andReturn();
        String response = mvcResult.getResponse().getContentAsString();
        Assertions.assertEquals(objectMapper.writeValueAsString(missions), response);
    }

    @Test
    void getMissionTest() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        Mission mission = new Mission("test.pdf", ImageryType.TYPE_HYPERSPECTRAL, null,
                null, new FileDb());
        when(missionService.read((long) 1)).thenReturn(mission);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/missions/1"))
                .andExpect(status().isOk())
                .andReturn();
        String response = mvcResult.getResponse().getContentAsString();
        Assertions.assertEquals(objectMapper.writeValueAsString(mission), response);
    }

    @Test
    void createMissionTest() throws Exception {
        byte[] fileContent = "bar".getBytes(StandardCharsets.UTF_8);
        MockMultipartFile filePart = new MockMultipartFile("file", "orig", null, fileContent);

        when(missionService.create(any(Mission.class))).thenReturn(new Mission("test.pdf", ImageryType.TYPE_HYPERSPECTRAL, null,
                null, new FileDb()));

        MvcResult result = mockMvc.perform(multipart("/missions")
                .file(filePart)
                .param("name", "test.pdf")
                .param("type", "TYPE_HYPERSPECTRAL")
                .param("startDate", "")
                .param("finishDate", "")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.name").value("test.pdf"))
                .andExpect(jsonPath("$.type").value("TYPE_HYPERSPECTRAL"))
                .andReturn();
    }

    @Test
    void deleteMissionTest() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        Mission mission = new Mission("test.pdf", ImageryType.TYPE_HYPERSPECTRAL, null,
                null, new FileDb());
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete("/missions/1"))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void updateMissionTest() throws Exception  {
        byte[] fileContent = "bar".getBytes(StandardCharsets.UTF_8);
        MockMultipartFile filePart = new MockMultipartFile("file", "orig", null, fileContent);
        FileDb fileDb = new FileDb("Test File", filePart.getContentType(), filePart.getBytes());

        ObjectMapper objectMapper = new ObjectMapper();
        Mission mission = new Mission("Mission1", ImageryType.TYPE_HYPERSPECTRAL, null,
                null, fileDb);
        when(missionService.update(any(Mission.class))).thenReturn(mission);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put("/missions/1")
        .content(objectMapper.writeValueAsString(mission))
        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();
        String response = mvcResult.getResponse().getContentAsString();
        Assertions.assertEquals(objectMapper.writeValueAsString(mission), response);
    }
}