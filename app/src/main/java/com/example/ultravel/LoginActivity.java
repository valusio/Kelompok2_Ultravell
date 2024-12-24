package com.example.ultravel;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LoginActivity extends AppCompatActivity {

    EditText emailEdt, passEdt ;
    AppCompatButton loginBtn ;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
// kenalkan widget didalam method create
        emailEdt =(EditText) findViewById(R.id.emailEdt);
        passEdt =(EditText) findViewById(R.id.passEdt);
        loginBtn = findViewById(R.id.loginBtn);

        //event handle ketika tombol login diklik
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(emailEdt.getText().toString())) {
                    Toast.makeText(LoginActivity.this, "username ngk boleh kosong",
                            Toast.LENGTH_SHORT).show();

                } else if (TextUtils.isEmpty(passEdt.getText().toString())) {
                    Toast.makeText(LoginActivity.this, "pasword ngk boleh kosong",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(LoginActivity.this, "hayolohh",
                            Toast.LENGTH_SHORT).show();

                    //intent ketika di klik login akan pindah ke halaman main activity
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);

                    //kirim data username ke activity main menggunakan intent
                    intent.putExtra("username", emailEdt.getText().toString());
                    startActivity(intent);

                    //setelah tombol login diklik maka activity login kita matikan
                    finish();
                }
            }
        });
    }
}