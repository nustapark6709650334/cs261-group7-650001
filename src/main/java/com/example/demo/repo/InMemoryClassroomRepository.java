package com.example.demo.repo;

import com.example.demo.model.Classroom;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class InMemoryClassroomRepository {
    private final Map<Long, Classroom> data = new HashMap<>();

    public InMemoryClassroomRepository() {
        // ตัวอย่าง: ห้องเรียนที่คณะวิทยาศาสตร์และเทคโนโลยี มธ. (แก้ได้ตามจริง)
        // latitude/longitude ตัวอย่างเท่านั้น
        data.put(1L, new Classroom(1L, "SCI-101", 14.072800, 100.603500, 120.0)); // รัศมี 120 m
        data.put(2L, new Classroom(2L, "SCI-202", 14.073200, 100.602900, 120.0));
    }

    public Optional<Classroom> findById(Long id) {
        return Optional.ofNullable(data.get(id));
    }

    public Collection<Classroom> findAll() { return data.values(); }
}