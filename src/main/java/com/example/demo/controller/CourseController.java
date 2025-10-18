package com.example.demo.controller;

import com.example.demo.model.dto.CourseResponse;
import com.example.demo.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    // GET (ดึงข้อมูล) จะส่งกลับเป็น CourseResponse
    @GetMapping("/{courseCode}")
    public ResponseEntity<CourseResponse> getCourseByCode(@PathVariable String courseCode) {
        CourseResponse response = courseService.getCourseDetailsByCode(courseCode);
        return ResponseEntity.ok(response);
    }
}