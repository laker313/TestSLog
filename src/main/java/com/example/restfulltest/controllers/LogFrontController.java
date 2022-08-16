package com.example.restfulltest.controllers;


import com.example.restfulltest.models.FrontLog;
import com.example.restfulltest.repositories.LogFrontFileRepository;
import com.example.restfulltest.repositories.LogFrontRepository;
import com.example.restfulltest.repositories.LogRepositoryInterface;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.ValueInstantiationException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.jar.JarException;


@RestController
@RequestMapping("/log")
public class LogFrontController {
    private final LogRepositoryInterface logFrontRepository;
    private final LogFrontFileRepository logFrontFileRepository;
    ObjectMapper objectMapper;

    @Autowired
    public LogFrontController(LogFrontRepository logFrontRepository, LogFrontFileRepository logFrontFileRepository){
        this.logFrontRepository=logFrontRepository;
        this.logFrontFileRepository=logFrontFileRepository;
        objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }




    @GetMapping("/get")
    public ResponseEntity getLog(@RequestParam Integer id) throws Exception{

        return new ResponseEntity<>(objectMapper.writeValueAsString(logFrontRepository.search(id)),HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity saveLog(@Valid @RequestBody FrontLog frontLog) throws Exception {
        frontLog.setId(logFrontRepository.save(frontLog));
        logFrontFileRepository.save(frontLog);
        return new ResponseEntity<>(frontLog.getId(),HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity getAllLog() throws Exception{

        return new ResponseEntity<>(objectMapper.writeValueAsString(logFrontRepository.getAll()),HttpStatus.OK);
    }
    @GetMapping("/delete")
    public ResponseEntity deleteLog(@RequestParam Integer id) throws Exception{

        logFrontRepository.delete(logFrontRepository.search(id));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity updateLog(@RequestParam Integer id,@Valid @RequestBody FrontLog frontLog) throws Exception{
        frontLog.setId(id);
        return new ResponseEntity<>(objectMapper.writeValueAsString(logFrontRepository.update(frontLog)),HttpStatus.OK);
    }
}
