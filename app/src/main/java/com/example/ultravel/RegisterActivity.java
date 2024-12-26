package com.example.ultravel;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ultravel.api.ApiService;
import com.example.ultravel.api.RegisterRequest;
import com.example.ultravel.api.RegisterResponse;
import com.example.ultravel.api.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    private EditText nameEdt, emailEdt, passEdt, rePassEdt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register); // Mengacu pada XML yang Anda berikan

        // Inisialisasi view
        nameEdt = findViewById(R.id.NameEdt);
        emailEdt = findViewById(R.id.emailEdt);
        passEdt = findViewById(R.id.passEdt);
        rePassEdt = findViewById(R.id.RePassEdt);

        findViewById(R.id.loginBtn).setOnClickListener(view -> {
            String username = nameEdt.getText().toString().trim();
            String email = emailEdt.getText().toString().trim();
            String password = passEdt.getText().toString().trim();
            String rePassword = rePassEdt.getText().toString().trim();

            if (validateInput(username, email, password, rePassword)) {
                registerUser(username, email, password);
            }
        });
    }

    private boolean validateInput(String username, String email, String password, String rePassword) {
        if (TextUtils.isEmpty(username)) {
            Toast.makeText(this, "Nama tidak boleh kosong", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Email tidak boleh kosong", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Password tidak boleh kosong", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!password.equals(rePassword)) {
            Toast.makeText(this, "Password tidak sesuai", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void registerUser(String username, String email, String password) {
        ApiService apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);
        RegisterRequest registerRequest = new RegisterRequest(username, email, password);

        Call<RegisterResponse> call = apiService.register(registerRequest);
        call.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Toast.makeText(RegisterActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    if ("true".equals(response.body().getStatus())) {
                        finish(); // Menutup activity setelah registrasi berhasil
                    }
                } else {
                    Toast.makeText(RegisterActivity.this, "Registrasi gagal: " + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                Toast.makeText(RegisterActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
