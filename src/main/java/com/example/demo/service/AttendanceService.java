package com.example.demo.service;

import com.example.demo.model.Classroom;
import com.example.demo.model.dto.AttendanceRequest;
import com.example.demo.model.dto.AttendanceResponse;
import com.example.demo.repo.InMemoryClassroomRepository;
import org.springframework.stereotype.Service;

@Service
public class AttendanceService {
    private final InMemoryClassroomRepository classroomRepository;
    private final GeoService geoService;

    public AttendanceService(InMemoryClassroomRepository classroomRepository, GeoService geoService) {
        this.classroomRepository = classroomRepository;
        this.geoService = geoService;
    }

    public AttendanceResponse check(AttendanceRequest req) {
        Classroom room = classroomRepository.findById(req.getClassId())
                .orElseThrow(() -> new IllegalArgumentException("Classroom not found: " + req.getClassId()));

        double d = geoService.haversineDistanceMeters(
                req.getLatitude(), req.getLongitude(),
                room.getLatitude(), room.getLongitude()
        );
        boolean ok = d <= room.getRadiusMeters();
        String status = ok ? "CHECKIN_OK" : "TOO_FAR";

        return new AttendanceResponse(
                ok, d, room.getRadiusMeters(), status, System.currentTimeMillis(), room.getId(), room.getName()
        );
    }
}