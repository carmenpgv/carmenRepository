package com.example.util_idades.compra;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.util_idades.R;
import com.example.util_idades.compra.bd.DataBase;
import com.example.util_idades.compra.util.Producto;

public class AddActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView image;
    Button btnAdd;
    EditText etNombre, etMarca, etSeccion, etPrecio;
    final int CARGA_IMAGEN = 42;
    DataBase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        image = findViewById(R.id.ivAddImage);
        image.setOnClickListener(this);

        btnAdd = findViewById(R.id.bAddAdd);
        btnAdd.setOnClickListener(this);
        etNombre = findViewById(R.id.etAddNombre);
        etMarca = findViewById(R.id.etAddMarca);
        etSeccion = findViewById(R.id.etAddSeccion);
        etPrecio = findViewById(R.id.etAddPrecio);

        db = new DataBase(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.ivAddImage:
                Intent i = new Intent(this, ImageActivity.class);
                startActivityForResult(i,CARGA_IMAGEN);
                break;
            case R.id.bAddAdd:
                Producto producto = new Producto();
                producto.setNombre(etNombre.getText().toString());
                producto.setMarca(etMarca.getText().toString());
                producto.setSeccion(etSeccion.getText().toString());
                producto.setPrecio(Float.valueOf(etPrecio.getText().toString()));
                producto.setFoto(((BitmapDrawable) image.getDrawable()).getBitmap());

                db.nuevoProducto(producto);
                finish();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK && data != null){
            switch (requestCode){
                case CARGA_IMAGEN:
                    int resultado = data.getExtras().getInt("Imagen_seleccionada");
                    switch (resultado){
                        case 1:
                            image.setImageDrawable(getResources().getDrawable(R.drawable.verduras));
                            break;
                        case 2:
                            image.setImageDrawable(getResources().getDrawable(R.drawable.frutas));
                            break;
                        case 3:
                            image.setImageDrawable(getResources().getDrawable(R.drawable.pasta));
                            break;
                        case 4:
                            image.setImageDrawable(getResources().getDrawable(R.drawable.bebidas));
                            break;
                        case 5:
                            image.setImageDrawable(getResources().getDrawable(R.drawable.guarradas));
                            break;
                        case 6:
                            image.setImageDrawable(getResources().getDrawable(R.drawable.maquillaje));
                            break;
                    }
                    break;
            }
        }
    }
}