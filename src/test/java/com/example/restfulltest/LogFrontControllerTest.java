package com.example.restfulltest;


import com.example.restfulltest.controllers.LogFrontController;
import com.example.restfulltest.models.FrontLog;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Parameter;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.junit.runners.Parameterized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

import java.io.DataInput;
import java.sql.Timestamp;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class LogFrontControllerTest {
    @Autowired
    private LogFrontController logFrontController;
    @Autowired
    MockMvc mockMvc;

    Integer idTest;
    FrontLog frontLogTest;
    ObjectMapper objectMapper;

    @Test
    public void saveLog() throws Exception {
        frontLogTest= new FrontLog("message","type","level",new Timestamp(64573645));
        objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        String JSON = objectMapper.writeValueAsString(frontLogTest);
        ResultActions resultActions =this.mockMvc.perform(post("/log/save")
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
                        .content(JSON))
                .andExpect(status().isOk());
        idTest = Integer.parseInt(resultActions.andReturn().getResponse().getContentAsString());

        frontLogTest.setId(idTest);
        getLog(frontLogTest,idTest);
        getAllLog(frontLogTest,idTest);
        updateLog(frontLogTest,idTest);
        deleteLog(frontLogTest,idTest);
    }


    public void getLog(FrontLog frontLogTest,Integer idTest) throws Exception {
        ResultActions resultActions = this.mockMvc.perform(get("/log/get").param("id",idTest.toString())).andDo(print()).andExpect(status().isOk());
        String sFromDB=resultActions.andReturn().getResponse().getContentAsString();
        Assertions.assertEquals(sFromDB,objectMapper.writeValueAsString(frontLogTest));
    }



    public void getAllLog(FrontLog frontLogTest,Integer idTest) throws Exception {
        this.mockMvc.perform(get("/log/getAll")).andDo(print()).andExpect(status().isOk());
    }

    public void updateLog(FrontLog frontLogTest,Integer idTest) throws Exception {
        frontLogTest.setLevel("nextlevel");
        ResultActions resultActions = this.mockMvc.perform(post("/log/update")
                .param("id",idTest.toString()).contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(frontLogTest)))
                .andDo(print()).andExpect(status().isOk());
        String sFromDB=resultActions.andReturn().getResponse().getContentAsString();
        Assertions.assertEquals((sFromDB),objectMapper.writeValueAsString(frontLogTest));
        //idTest=objectMapper.readValue((DataInput) frontLogTest,FrontLog.class).getId();

    }

    public void deleteLog(FrontLog frontLogTest,Integer idTest) throws Exception {
        this.mockMvc.perform(get("/log/delete").param("id",idTest.toString())).andDo(print())
                .andExpect(status().isOk());
    }


}