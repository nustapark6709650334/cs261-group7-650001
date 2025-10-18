package com.example.demo.model;


import java.util.List;

import jakarta.persistence.*;

@Entity
public class Course {

    @Id
    @Column(name = "course_code") 
    private String courseCode;

    @Column(name = "course_name")
    private String courseName;

    @Column(name = "credit")
    private int credit;

    @Column(name = "course_detail", length = 1000)
    private String courseDetail;

    @Column(name = "course_group")
    private String courseGroup;
    
    // --- Prerequisite/Next Course ---
    @ElementCollection
    private List<String> prerequisites; // วิชาที่ต้องเรียนก่อน

    @ElementCollection
    private List<String> nextCourses; // วิชาต่อยอด
 

    // --- สร้าง Getters and Setters สำหรับ field ทั้งหมด ---
    public String getCourseCode() { return courseCode; }
    public void setCourseCode(String courseCode) { this.courseCode = courseCode; }
    public String getCourseName() { return courseName; }
    public void setCourseName(String courseName) { this.courseName = courseName; }
    public int getCredit() { return credit; }
    public void setCredit(int credit) { this.credit = credit; }
    public String getCourseDetail() { return courseDetail; }
    public void setCourseDetail(String courseDetail) { this.courseDetail = courseDetail; }
    public String getCourseGroup() { return courseGroup; }
    public void setCourseGroup(String courseGroup) { this.courseGroup = courseGroup; }
    public List<String> getPrerequisites() { return prerequisites; }
    public void setPrerequisites(List<String> prerequisites) { this.prerequisites = prerequisites; }
    public List<String> getNextCourses() { return nextCourses; }
    public void setNextCourses(List<String> nextCourses) { this.nextCourses = nextCourses; }
}