package com.example.util_idades;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class ConversorActivity extends AppCompatActivity implements View.OnClickListener{
    //ImageView imagen;

    TextView valorInicio;
    TextView valorFinal;
    TextView simboloInicio;
    TextView simboloFinal;

    RadioButton euroInicio;
    RadioButton dolarInicio;
    RadioButton rupiaInicio;
    RadioButton yenInicio;

    RadioButton euroFinal;
    RadioButton dolarFinal;
    RadioButton rupiaFinal;
    RadioButton yenFinal;

    Button btnConvertir;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversor);

        valorInicio = findViewById(R.id.tvValorInicio);
        simboloInicio = findViewById(R.id.tvSimboloInicio);
        simboloInicio.setVisibility(View.INVISIBLE);
        valorFinal = findViewById(R.id.tvValorFinal);
        simboloFinal = findViewById(R.id.tvSimboloFinal);
        simboloFinal.setVisibility(View.INVISIBLE);
        btnConvertir = findViewById(R.id.bConvertir);
        btnConvertir.setOnClickListener(this);

        euroInicio = findViewById(R.id.rbInicioEuro);
        euroInicio.setOnClickListener(this);
        dolarInicio = findViewById(R.id.rbInicioDolar);
        dolarInicio.setOnClickListener(this);
        rupiaInicio = findViewById(R.id.rbInicioRupia);
        rupiaInicio.setOnClickListener(this);
        yenInicio = findViewById(R.id.rbInicioYen);
        yenInicio.setOnClickListener(this);

        euroFinal = findViewById(R.id.rbFinalEuro);
        euroFinal.setOnClickListener(this);
        dolarFinal = findViewById(R.id.rbFinalDolar);
        dolarFinal.setOnClickListener(this);
        rupiaFinal = findViewById(R.id.rbFinalRupia);
        rupiaFinal.setOnClickListener(this);
        yenFinal = findViewById(R.id.rbFinalYen);
        yenFinal.setOnClickListener(this);


    }

    private void faltanRBs(boolean inicio){
        if (inicio) {
            Toast.makeText(this, "No has seleccionado la moneda final", Toast.LENGTH_LONG).show();
        }else {
            Toast.makeText(this, "No has seleccionado la moneda de inicio", Toast.LENGTH_LONG).show();
        }
    }

    private void mostrarCambio(float conversion){
        Float cambio;
        cambio = Float.parseFloat(valorInicio.getText().toString())*conversion;
        valorFinal.setText("" + cambio);
        if(valorFinal.getText().toString().substring(valorFinal.getText().toString().indexOf(".")).length()>2){
            valorFinal.setText(valorFinal.getText().toString().substring(0,valorFinal.getText().toString().indexOf(".")+3));
        }else if(valorFinal.getText().toString().substring(valorFinal.getText().toString().length()-2).equals(".0")){
            valorFinal.setText(valorFinal.getText().toString().substring(0,valorFinal.getText().toString().length()-2));
        }
        simboloFinal.setVisibility(View.VISIBLE);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.rbInicioEuro:
                euroInicio.setSelected(true);
                dolarInicio.setSelected(false);
                rupiaInicio.setSelected(false);
                yenInicio.setSelected(false);
                simboloInicio.setText("€");
                simboloInicio.setVisibility(View.VISIBLE);
                valorFinal.setText("");
                simboloFinal.setVisibility(View.INVISIBLE);
                break;
            case R.id.rbInicioDolar:
                euroInicio.setSelected(false);
                dolarInicio.setSelected(true);
                rupiaInicio.setSelected(false);
                yenInicio.setSelected(false);
                simboloInicio.setText("$");
                simboloInicio.setVisibility(View.VISIBLE);
                valorFinal.setText("");
                simboloFinal.setVisibility(View.INVISIBLE);
                break;
            case R.id.rbInicioRupia:
                euroInicio.setSelected(false);
                dolarInicio.setSelected(false);
                rupiaInicio.setSelected(true);
                yenInicio.setSelected(false);
                simboloInicio.setText("₹");
                simboloInicio.setVisibility(View.VISIBLE);
                valorFinal.setText("");
                simboloFinal.setVisibility(View.INVISIBLE);
                break;
            case R.id.rbInicioYen:
                euroInicio.setSelected(false);
                dolarInicio.setSelected(false);
                rupiaInicio.setSelected(false);
                yenInicio.setSelected(true);
                simboloInicio.setText("¥");
                simboloInicio.setVisibility(View.VISIBLE);
                valorFinal.setText("");
                simboloFinal.setVisibility(View.INVISIBLE);
                break;
            case R.id.rbFinalEuro:
                euroFinal.setSelected(true);
                dolarFinal.setSelected(false);
                rupiaFinal.setSelected(false);
                yenFinal.setSelected(false);
                simboloFinal.setText("€");
                valorFinal.setText("");
                simboloFinal.setVisibility(View.INVISIBLE);
                break;
            case R.id.rbFinalDolar:
                euroFinal.setSelected(false);
                dolarFinal.setSelected(true);
                rupiaFinal.setSelected(false);
                yenFinal.setSelected(false);
                simboloFinal.setText("$");
                valorFinal.setText("");
                simboloFinal.setVisibility(View.INVISIBLE);
                break;
            case R.id.rbFinalRupia:
                euroFinal.setSelected(false);
                dolarFinal.setSelected(false);
                rupiaFinal.setSelected(true);
                yenFinal.setSelected(false);
                simboloFinal.setText("₹");
                valorFinal.setText("");
                simboloFinal.setVisibility(View.INVISIBLE);
                break;
            case R.id.rbFinalYen:
                euroFinal.setSelected(false);
                dolarFinal.setSelected(false);
                rupiaFinal.setSelected(false);
                yenFinal.setSelected(true);
                simboloFinal.setText("¥");
                valorFinal.setText("");
                simboloFinal.setVisibility(View.INVISIBLE);
                break;
            case R.id.bConvertir:
                if (valorInicio.getText().toString().equals("")){
                    Toast.makeText(this, "Escribe un valor", Toast.LENGTH_SHORT).show();
                }else if(euroInicio.isSelected()){
                    if (euroFinal.isSelected()){
                        mostrarCambio(1);
                    }else if (dolarFinal.isSelected()){
                        mostrarCambio(1.04f);
                    }else if (rupiaFinal.isSelected()){
                        mostrarCambio(85);
                    }else if (yenFinal.isSelected()){
                        mostrarCambio(144.23f);
                    }else{
                        faltanRBs(true);
                    }
                }else if(dolarInicio.isSelected()){
                    if (euroFinal.isSelected()){
                        mostrarCambio(0.96f);
                    }else if (dolarFinal.isSelected()){
                        mostrarCambio(1);
                    }else if (rupiaFinal.isSelected()){
                        mostrarCambio(81.71f);
                    }else if (yenFinal.isSelected()){
                        mostrarCambio(138.55f);
                    }else{
                        faltanRBs(true);
                    }
                }else if(rupiaInicio.isSelected()){
                    if (euroFinal.isSelected()){
                        mostrarCambio(1.04f);
                    }else if (dolarFinal.isSelected()){
                        mostrarCambio(0.012f);
                    }else if (rupiaFinal.isSelected()){
                        mostrarCambio(1);
                    }else if (yenFinal.isSelected()){
                        mostrarCambio(1.7f);
                    }else{
                        faltanRBs(true);
                    }
                }else if(yenInicio.isSelected()){
                    if (euroFinal.isSelected()){
                        mostrarCambio(0.0069f);
                    }else if (dolarFinal.isSelected()){
                        mostrarCambio(0.0072f);
                    }else if (rupiaFinal.isSelected()){
                        mostrarCambio(0.59f);
                    }else if (yenFinal.isSelected()){
                        mostrarCambio(1);
                    }else{
                        faltanRBs(true);
                    }
                }else {
                    faltanRBs(false);
                }
                break;
        }
    }
}