package com.payil.courseservice.client;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

@HttpExchange
public interface StudentClient {

     @GetExchange("/student/course/{courseId}")
     String sendMsg(@PathVariable("courseId") String courseId);
}
