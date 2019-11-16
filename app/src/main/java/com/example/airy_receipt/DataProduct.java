package com.example.airy_receipt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DataProduct extends AppCompatActivity {

    private TextView tvdata, tvdes;
    private ImageView display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_product);

        tvdata = findViewById(R.id.tvdata);
        tvdes = findViewById(R.id.tvdes);
        display = findViewById(R.id.display);

        Intent intent = getIntent();
        String Name = intent.getExtras().getString("Name");
        String Description = intent.getExtras().getString("Description");
        String image = intent.getExtras().getString("Image");

        tvdata.setText(Name);
        tvdes.setText(Description);

        //load image dr url dn masukin k imageview dgn Glide
        Glide.with(this).load(image).placeholder(R.drawable.loading).into(display);

        getSupportActionBar().setTitle("Detail Product Data");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
