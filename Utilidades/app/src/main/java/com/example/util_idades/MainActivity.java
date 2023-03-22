package com.example.util_idades;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.example.util_idades.buscaminas.BuscaminasActivity;
import com.example.util_idades.calculadora.CalculatorActivity;
import com.example.util_idades.compra.CompraActivity;
import com.example.util_idades.hospital.HospitalesActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    ImageView goCalculator;
    ImageView goConversor;
    ImageView goCompra;
    ImageView goBuscaminas;
    ImageView goHospital;
    ImageView goEscenarios;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        goCalculator = findViewById(R.id.ivMainCalculadora);
        goCalculator.setOnClickListener(this);
        goConversor = findViewById(R.id.ivMainMonedas);
        goConversor.setOnClickListener(this);
        goCompra = findViewById(R.id.ivMainListaCompra);
        goCompra.setOnClickListener(this);
        goBuscaminas = findViewById(R.id.ivMainBuscaminas);
        goBuscaminas.setOnClickListener(this);
        goHospital = findViewById(R.id.ivMainHospital);
        goHospital.setOnClickListener(this);
        goEscenarios = findViewById(R.id.ivMainEscenarios);
        goEscenarios.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.menuMainPrefs:
                Intent ip = new Intent(this, PreferencesActivity.class);
                startActivity(ip);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.ivMainCalculadora:
                Intent i = new Intent(this, CalculatorActivity.class);
                startActivity(i);
                break;
            case R.id.ivMainMonedas:
                Intent im = new Intent(this, ConversorActivity.class);
                startActivity(im);
                break;
            case R.id.ivMainListaCompra:
                Intent ic = new Intent(this, CompraActivity.class);
                startActivity(ic);
                break;
            case R.id.ivMainBuscaminas:
                Intent ib = new Intent(this, BuscaminasActivity.class);
                startActivity(ib);
                break;
            case R.id.ivMainHospital:
                Intent ih = new Intent(this, HospitalesActivity.class);
                startActivity(ih);
                break;
            case R.id.ivMainEscenarios:
                Intent ie = new Intent(this, EscenariosActivity.class);
                startActivity(ie);
                break;
        }
    }
}