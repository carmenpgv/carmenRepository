package com.example.util_idades.compra;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.util_idades.R;

public class ImageActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView image1, image2, image3, image4, image5, image6;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        image1 = findViewById(R.id.ivImage1);
        image2 = findViewById(R.id.ivImage2);
        image3 = findViewById(R.id.ivImage3);
        image4 = findViewById(R.id.ivImage4);
        image5 = findViewById(R.id.ivImage5);
        image6 = findViewById(R.id.ivImage6);

        image1.setOnClickListener(this);
        image2.setOnClickListener(this);
        image3.setOnClickListener(this);
        image4.setOnClickListener(this);
        image5.setOnClickListener(this);
        image6.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int seleccion = 1;
        switch (view.getId()){
            case R.id.ivImage1:
                seleccion = 1;
                break;
            case R.id.ivImage2:
                seleccion = 2;
                break;
            case R.id.ivImage3:
                seleccion = 3;
                break;
            case R.id.ivImage4:
                seleccion = 4;
                break;
            case R.id.ivImage5:
                seleccion = 5;
                break;
            case R.id.ivImage6:
                seleccion = 6;
                break;
        }
        Intent intent = getIntent();
        intent.putExtra("Imagen_seleccionada",seleccion);
        this.setResult(RESULT_OK,intent);
        finish();
    }
}