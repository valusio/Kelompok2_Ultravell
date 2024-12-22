package com.example.ultravel;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ultravel.databinding.ActivityDetailBinding;

public class DetailActivity extends BaseActivity {
    ActivityDetailBinding binding;

    ImageView btnBack;

    ImageView ivImage;
    TextView tvTitle;

    int image;
    String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        btnBack = findViewById(R.id.backBtn);
        ivImage = findViewById(R.id.pic);
        tvTitle = findViewById(R.id.titleTxt);

        image = getIntent().getIntExtra("image", 0);
        title = getIntent().getStringExtra("title");

        ivImage.setImageResource(image);
        tvTitle.setText(title);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}