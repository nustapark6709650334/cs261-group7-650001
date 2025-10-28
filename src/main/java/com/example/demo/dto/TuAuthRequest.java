package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TuAuthRequest {
    @JsonProperty("Username")
    private String username;
    @JsonProperty("PassWord")
    private String password;

    public TuAuthRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }
    // Getters
    public String getUsername() { return username; }
    public String getPassword() { return password; }
}