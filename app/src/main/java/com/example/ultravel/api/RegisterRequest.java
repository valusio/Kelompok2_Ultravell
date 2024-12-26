package com.example.ultravel.api;

public class RegisterRequest {
    private String username;
    private String email;
    private String password;

    public RegisterRequest(String name, String email, String password) {
        this.username = name;
        this.email = email;
        this.password = password;
    }

    // Getter dan Setter
}
