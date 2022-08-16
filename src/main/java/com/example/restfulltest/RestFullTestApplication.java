package com.example.restfulltest;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:application.properties")
public class RestFullTestApplication {

    public static void main(String[] args){

        SpringApplication.run(RestFullTestApplication.class, args);
    }

}
