package com.payil.courseservice.controller;

import com.payil.courseservice.client.StudentClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/course")
public class CourseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CourseController.class);

    @Autowired
    private StudentClient studentClient;

    @GetMapping
    public String helloCourseController(){
        LOGGER.info("Welcome to Controller - Course");
        return "Welcome to Course Controller";
    }

    @GetMapping("/with-student")
    public String getStudentInfo(){
        return "Welcome to Course Controller " + studentClient.sendMsg("1");
    }
}
