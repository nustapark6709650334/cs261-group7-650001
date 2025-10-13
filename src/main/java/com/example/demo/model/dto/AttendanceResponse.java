package com.example.demo.model.dto;

public class AttendanceResponse {
    private boolean withinRadius;
    private double distanceMeters;
    private double allowedRadius;
    private String status; // e.g. CHECKIN_OK, TOO_FAR
    private long serverTimestamp;
    private Long classId;
    private String className;

    public AttendanceResponse() {}

    public AttendanceResponse(boolean withinRadius, double distanceMeters, double allowedRadius,
                              String status, long serverTimestamp, Long classId, String className) {
        this.withinRadius = withinRadius;
        this.distanceMeters = distanceMeters;
        this.allowedRadius = allowedRadius;
        this.status = status;
        this.serverTimestamp = serverTimestamp;
        this.classId = classId;
        this.className = className;
    }

    public boolean isWithinRadius() { return withinRadius; }
    public double getDistanceMeters() { return distanceMeters; }
    public double getAllowedRadius() { return allowedRadius; }
    public String getStatus() { return status; }
    public long getServerTimestamp() { return serverTimestamp; }
    public Long getClassId() { return classId; }
    public String getClassName() { return className; }

    public void setWithinRadius(boolean withinRadius) { this.withinRadius = withinRadius; }
    public void setDistanceMeters(double distanceMeters) { this.distanceMeters = distanceMeters; }
    public void setAllowedRadius(double allowedRadius) { this.allowedRadius = allowedRadius; }
    public void setStatus(String status) { this.status = status; }
    public void setServerTimestamp(long serverTimestamp) { this.serverTimestamp = serverTimestamp; }
    public void setClassId(Long classId) { this.classId = classId; }
    public void setClassName(String className) { this.className = className; }
}