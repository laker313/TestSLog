package com.example.restfulltest.controllers;


import com.example.restfulltest.models.FrontLog;
import com.example.restfulltest.repositories.LogFrontFileRepository;
import com.example.restfulltest.repositories.LogFrontRepository;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.exc.ValueInstantiationException;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.io.IOException;
import java.util.jar.JarException;


@RestController
@RequestMapping("/log")
public class LogFrontController {
    private final LogFrontRepository logFrontRepository;
    private final LogFrontFileRepository logFrontFileRepository;
    @Autowired
    LogFrontController(LogFrontRepository logFrontRepository,LogFrontFileRepository logFrontFileRepository){
        this.logFrontRepository=logFrontRepository;
        this.logFrontFileRepository=logFrontFileRepository;
    }
    @RequestMapping("/get")
    public ResponseEntity getLog(Integer id){
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PostMapping("/save")
    public ResponseEntity saveLog(@Valid @RequestBody FrontLog frontLog) {
        try {
            System.out.println(frontLog);
            System.out.println(frontLog.getType());
            frontLog.setId(logFrontRepository.save(frontLog));
            logFrontFileRepository.save(frontLog);
        }catch (JsonProcessingException  e){
            return new ResponseEntity<>("Json incorrect",HttpStatus.INTERNAL_SERVER_ERROR);
        }catch (HibernateException e){
            return new ResponseEntity<>("Not save in db",HttpStatus.INTERNAL_SERVER_ERROR);
        }catch (Exception e){
            return new ResponseEntity<>("Unexpected exception",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
