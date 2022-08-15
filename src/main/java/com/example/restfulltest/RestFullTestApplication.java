package com.example.restfulltest;

import com.example.restfulltest.models.FrontLog;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.Writer;
import java.sql.Timestamp;

@SpringBootApplication
public class RestFullTestApplication {

    public static void main(String[] args){

        SpringApplication.run(RestFullTestApplication.class, args);
    }

}
