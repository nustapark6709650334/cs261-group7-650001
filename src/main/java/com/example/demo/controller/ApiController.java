package com.example.demo.controller;

import com.example.demo.model.Course;
import com.example.demo.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class ApiController {

    @Autowired
    private CourseRepository courseRepository;
    
    // สำหรับค้นหาวิชา
    @GetMapping("/courses")
    public List<Course> searchCourses(@RequestParam(required = false) String query) {
        if (query == null || query.isBlank()) {
            return courseRepository.findAll();
        }
        return courseRepository.findByCourseNameContainingIgnoreCaseOrCourseCodeContainingIgnoreCase(query, query);
    }
    
    // สำหรับดูรายละเอียดวิชาเดียว
    @GetMapping("/courses/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable String id) {
        return courseRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    
}