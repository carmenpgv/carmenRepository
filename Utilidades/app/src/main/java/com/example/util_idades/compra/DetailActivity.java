package com.example.util_idades.compra;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.util_idades.R;

public class DetailActivity extends AppCompatActivity {
    TextView tvNombre;
    TextView tvMarca;
    TextView tvSeccion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        tvNombre = findViewById(R.id.tvDetailNombre);
        tvMarca = findViewById(R.id.tvDetailMarca);
        tvSeccion = findViewById(R.id.tvDetailSeccion);

        Intent i = getIntent();

        tvNombre.setText(i.getStringExtra("NOMBRE"));
        tvMarca.setText(i.getStringExtra("MARCA"));
        tvSeccion.setText(i.getStringExtra("SECCION"));
    }
}