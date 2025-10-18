package com.example.demo.repository;

import com.example.demo.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CourseRepository extends JpaRepository<Course, String> {
    // ค้นหาจากชื่อวิชาหรือรหัสวิชา
    List<Course> findByCourseNameContainingIgnoreCaseOrCourseCodeContainingIgnoreCase(String courseName, String courseCode);
}