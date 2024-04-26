package com.payil.studentservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student")
public class StudentController {

    private static final Logger LOGGER = LoggerFactory.getLogger(StudentController.class);

    @GetMapping
    public String helloStudentController(){
        LOGGER.info("Welcome to Controller - Student");
        return "Welcome to Student Controller";
    }

    @GetMapping("/course/{courseId}")
    public String sendMsg(@PathVariable("courseId") String courseId){
        return "Welcome to Student Controller - for Course Id" + courseId;
    }

}
