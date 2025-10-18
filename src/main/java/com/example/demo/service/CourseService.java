package com.example.demo.service;

import com.example.demo.model.Course;
import com.example.demo.model.dto.CourseResponse;
import com.example.demo.repo.CourseRepository;
import org.springframework.stereotype.Service;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    //ดึงข้อมูลวิชาและแปลงเป็น DTO เพื่อส่งกลับ
 
    public CourseResponse getCourseDetailsByCode(String courseCode) {
        // 1. ค้นหา Entity จาก DB
        Course courseEntity = courseRepository.findByCourseCode(courseCode)
                .orElseThrow(() -> new RuntimeException("Course not found with code: " + courseCode));
        
        // 2. แปลง Entity -> Response DTO
        return mapEntityToResponse(courseEntity);
    }
    
    //สำหรับแปลง Entity เป็น Response DTO
    private CourseResponse mapEntityToResponse(Course course) {
        // เราต้องส่งข้อมูลกลับให้ครบตามที่ CourseResponse มี
        return new CourseResponse(
            course.getId(),
            course.getCourseCode(),
            course.getCourseName(),
            course.getCourseDetail(),
            course.getCourseGroup(),
            course.getCredit()
        );
    }
}