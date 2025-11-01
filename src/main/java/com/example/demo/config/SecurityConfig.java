package com.example.demo.config;

import com.example.demo.security.JwtRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JwtRequestFilter jwtRequestFilter; // Filter ที่เราสร้างเข้ามา

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // ปิด CSRF เพราะเราใช้ JWT
            .cors(cors -> {}) // อนุญาต CORS (ถ้าตั้งค่า @CrossOrigin ไว้ที่ Controller)

            // กำหนดสิทธิ์การเข้าถึง
            .authorizeHttpRequests(authz -> authz
                // "อนุญาต" ให้ทุกคนเข้า Path นี้ได้ (ไม่ต้องใช้ Token)
                .requestMatchers("/api/login").permitAll() 
                
                // "ต้องยืนยันตัวตน" (มี Token) สำหรับ Request อื่นๆ ทั้งหมด
                .anyRequest().authenticated() 
            )
            
            // ไม่ใช้ Session (เพราะเราใช้ Token)
            .sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS) 
            );

        // เพิ่ม Filter ของเราให้ทำงาน "ก่อน" Filter มาตรฐานของ Spring
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}