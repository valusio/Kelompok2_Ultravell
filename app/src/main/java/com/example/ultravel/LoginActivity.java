package com.example.ultravel;

import android.content.Context;
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
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {

    // buat variable volley utk mengirim request ke json
    RequestQueue requestQueue;
    String base_url = "http://192.168.42.185/aplikasi_mobile/ultravell/api.php";

    ArrayList<UserDBModel> list = new ArrayList<>();
    EditText emailEdt, passEdt;
    AppCompatButton loginBtn;
    TextView tvError;
    TextView tvLog;

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

        requestQueue = Volley.newRequestQueue(this);
        getData();

        // kenalkan widget didalam method create
        emailEdt = (EditText) findViewById(R.id.emailEdt);
        passEdt = (EditText) findViewById(R.id.passEdt);
        loginBtn = findViewById(R.id.loginBtn);
        tvError = findViewById(R.id.errLog);
        tvLog = findViewById(R.id.log);

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

                    for (int i = 0; i < list.size(); i++) {
                        if ((list.get(i).getEmail().equals(emailEdt.getText().toString())) && (list.get(i).getPassword().equals(passEdt.getText().toString()))) {
                            Toast.makeText(LoginActivity.this, "Welcome.",
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
                    

                }
            }
        });
    }

    /*
        Tolong maafkan saya
        API ini akan menarik semua data dari table user, yang mana
        akan menjadi masalah besar ketika database nya besar.

        Wajarlah saya ini manusia, bukan Nabi
     */
    private void getData() {
        list.clear();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, base_url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    String status = response.getString("status");
                    String message = response.getString("message");

                    if (status.equalsIgnoreCase("true")) {
                        JSONArray jsonArray = response.getJSONArray("data");

                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            UserDBModel userDBModel = new UserDBModel();
                            userDBModel.setEmail(jsonObject.getString("email"));
                            userDBModel.setPassword(jsonObject.getString("password"));
                            list.add(userDBModel);
                        }
                        tvLog.setText(list.get(0).getEmail() + " " + list.get(0).getPassword());

                    }
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(LoginActivity.this, "That didn't work!", Toast.LENGTH_SHORT).show();
                tvError.setText(error.toString());
            }
        });
        requestQueue.add(jsonObjectRequest);
    }
}
