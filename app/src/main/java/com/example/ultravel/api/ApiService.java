package com.example.ultravel.api;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {
    @POST("index.php")
    Call<LoginResponse> login(@Body LoginRequest loginRequest);

    @POST("register.php") // Endpoint sesuai dengan API Anda
    Call<RegisterResponse> register(@Body RegisterRequest registerRequest);
}
