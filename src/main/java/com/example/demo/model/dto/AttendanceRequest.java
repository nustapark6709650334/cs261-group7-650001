package com.example.demo.model.dto;

import jakarta.validation.constraints.NotNull;

public class AttendanceRequest {
    @NotNull
    private Long classId;

    @NotNull
    private String studentId; // หรือรหัสนักศึกษา

    @NotNull
    private Double latitude;

    @NotNull
    private Double longitude;

    public Long getClassId() { return classId; }
    public void setClassId(Long classId) { this.classId = classId; }
    public String getStudentId() { return studentId; }
    public void setStudentId(String studentId) { this.studentId = studentId; }
    public Double getLatitude() { return latitude; }
    public void setLatitude(Double latitude) { this.latitude = latitude; }
    public Double getLongitude() { return longitude; }
    public void setLongitude(Double longitude) { this.longitude = longitude; }
}
