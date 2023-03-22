package com.example.util_idades.calculadora;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.util_idades.R;

public class CalculatorActivity extends AppCompatActivity implements View.OnClickListener{

    TableLayout layout;
    Boton[] numeros;
    Boton btnPorc;
    Boton btnSupr;
    Boton btnBorrar;
    Boton btnDividir;
    Boton btnMultiplicar;
    Boton btnRestar;
    Boton btnSumar;
    Boton btnCambiar;
    Boton btnPunto;
    Boton btnIgual;
    TextView pantalla;
    String operacion;
    float ultNum;
    boolean nuevoNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        layout = findViewById(R.id.lyCalculatorBotones);
        numeros = new Boton[10];
        pantalla = findViewById(R.id.tvPantalla);
        Boton b;
        TableLayout.LayoutParams tableParams = new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT);
        TableRow.LayoutParams rowParams = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT);

        int id;
        operacion = "";
        ultNum = 0;
        nuevoNum = true;
        //Fila 1
        TableRow tableRow = new TableRow(this);
        tableRow.setGravity(Gravity.RIGHT);

        btnSupr = new Boton(this);
        btnSupr.setOnClickListener(this);
        btnSupr.setText("C");
        id = 11;
        btnSupr.setId(id);
        btnSupr.setLayoutParams(rowParams);
        tableRow.addView(btnSupr);

        btnBorrar = new Boton(this);
        btnBorrar.setOnClickListener(this);
        btnBorrar.setText("<---");
        id = 12;
        btnBorrar.setId(id);
        btnBorrar.setLayoutParams(rowParams);
        tableRow.addView(btnBorrar);

        btnPorc = new Boton(this);
        btnPorc.setOnClickListener(this);
        btnPorc.setText("%");
        id = 13;
        btnPorc.setId(id);
        btnPorc.setLayoutParams(rowParams);
        tableRow.addView(btnPorc);

        btnDividir = new Boton(this);
        btnDividir.setOnClickListener(this);
        btnDividir.setText("÷");
        id = 14;
        btnDividir.setId(id);
        btnDividir.setLayoutParams(rowParams);
        tableRow.addView(btnDividir);

        layout.addView(tableRow);
        tableRow.setLayoutParams(rowParams);

        //Fila 2
        tableRow = new TableRow(this);
        tableRow.setGravity(Gravity.RIGHT);
        for(int i=7;i<=9;i++) {
            b = new Boton(this);
            b.setOnClickListener(this);
            b.setText("" + i);
            b.setId(i);
            b.setLayoutParams(rowParams);
            numeros[i] = b;
            tableRow.addView(b);
        }

        btnMultiplicar = new Boton(this);
        btnMultiplicar.setOnClickListener(this);
        btnMultiplicar.setText("x");
        id = 24;
        btnMultiplicar.setId(id);
        btnMultiplicar.setLayoutParams(rowParams);
        tableRow.addView(btnMultiplicar);

        layout.addView(tableRow);
        tableRow.setLayoutParams(rowParams);

        //Fila 3
        tableRow = new TableRow(this);
        tableRow.setGravity(Gravity.RIGHT);
        for(int i=4;i<=6;i++) {
            b = new Boton(this);
            b.setOnClickListener(this);
            b.setText("" + i);
            b.setId(i);
            b.setLayoutParams(rowParams);
            numeros[i] = b;
            tableRow.addView(b);
        }

        btnRestar = new Boton(this);
        btnRestar.setOnClickListener(this);
        btnRestar.setText("-");
        id = 34;
        btnRestar.setId(id);
        btnRestar.setLayoutParams(rowParams);
        tableRow.addView(btnRestar);

        layout.addView(tableRow);
        tableRow.setLayoutParams(rowParams);

        //Fila 4
        tableRow = new TableRow(this);
        tableRow.setGravity(Gravity.RIGHT);
        for(int i=1;i<=3;i++) {
            b = new Boton(this);
            b.setOnClickListener(this);
            b.setText("" + i);
            b.setId(i);
            b.setLayoutParams(rowParams);
            numeros[i] = b;
            tableRow.addView(b);
        }

        btnSumar = new Boton(this);
        btnSumar.setOnClickListener(this);
        btnSumar.setText("+");
        id = 44;
        btnSumar.setId(id);
        btnSumar.setLayoutParams(rowParams);
        tableRow.addView(btnSumar);

        layout.addView(tableRow);
        tableRow.setLayoutParams(rowParams);

        //Fila 5
        tableRow = new TableRow(this);
        tableRow.setGravity(Gravity.RIGHT);
        btnCambiar = new Boton(this);
        btnCambiar.setOnClickListener(this);
        btnCambiar.setText("+/-");
        id = 51;
        btnCambiar.setId(id);
        btnCambiar.setLayoutParams(rowParams);
        tableRow.addView(btnCambiar);

        b = new Boton(this);
        b.setOnClickListener(this);
        b.setText("0");
        b.setId(0);
        b.setLayoutParams(rowParams);
        numeros[0] = b;
        tableRow.addView(b);

        btnPunto = new Boton(this);
        btnPunto.setOnClickListener(this);
        btnPunto.setText(",");
        id = 53;
        btnPunto.setId(id);
        btnPunto.setLayoutParams(rowParams);
        tableRow.addView(btnPunto);

        btnIgual = new Boton(this);
        btnIgual.setOnClickListener(this);
        btnIgual.setText("=");
        id = 54;
        btnIgual.setId(id);
        btnIgual.setLayoutParams(rowParams);
        tableRow.addView(btnIgual);

        layout.addView(tableRow);
        tableRow.setLayoutParams(rowParams);

        layout.setLayoutParams(tableParams);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case 11:
                pantalla.setText("0");
                operacion = "";
                ultNum = 0;
                break;
            case 12:
                if(!pantalla.getText().toString().equals("0")) {
                    if(pantalla.getText().toString().length()==1){
                        pantalla.setText("0");
                    }else{
                        pantalla.setText(pantalla.getText().subSequence(0, pantalla.getText().length() - 1));
                    }
                }
                break;
            case 13:
                btnIgual.callOnClick();
                if((pantalla.getText().toString().substring(pantalla.getText().toString().length()-1)).equals(".")){
                    pantalla.setText(pantalla.getText().toString().substring(0,pantalla.getText().toString().length()-1));
                }
                operacion = "%";
                ultNum = Float.parseFloat(pantalla.getText().toString());
                break;
            case 14:
                btnIgual.callOnClick();
                if((pantalla.getText().toString().substring(pantalla.getText().toString().length()-1)).equals(".")){
                    pantalla.setText(pantalla.getText().toString().substring(0,pantalla.getText().toString().length()-1));
                }
                operacion = "/";
                ultNum = Float.parseFloat(pantalla.getText().toString());
                break;
            case 24:
                btnIgual.callOnClick();
                if((pantalla.getText().toString().substring(pantalla.getText().toString().length()-1)).equals(".")){
                    pantalla.setText(pantalla.getText().toString().substring(0,pantalla.getText().toString().length()-1));
                }
                operacion = "x";
                ultNum = Float.parseFloat(pantalla.getText().toString());
                break;
            case 34:
                btnIgual.callOnClick();
                if((pantalla.getText().toString().substring(pantalla.getText().toString().length()-1)).equals(".")){
                    pantalla.setText(pantalla.getText().toString().substring(0,pantalla.getText().toString().length()-1));
                }
                operacion = "-";
                ultNum = Float.parseFloat(pantalla.getText().toString());
                break;
            case 44:
                btnIgual.callOnClick();
                if((pantalla.getText().toString().substring(pantalla.getText().toString().length()-1)).equals(".")){
                    pantalla.setText(pantalla.getText().toString().substring(0,pantalla.getText().toString().length()-1));
                }
                operacion = "+";
                ultNum = Float.parseFloat(pantalla.getText().toString());
                break;
            case 51:
                if(!(pantalla.getText().toString().equals("0"))){
                    if (pantalla.getText().toString().substring(0,1).equals("-")){
                        pantalla.setText(pantalla.getText().toString().substring(1));
                    }else {
                        pantalla.setText("-" + pantalla.getText().toString());
                    }
                }
                break;
            case 53:
                pantalla.setText(pantalla.getText().toString() + ".");
                break;
            case 54:
                if((pantalla.getText().toString().substring(pantalla.getText().toString().length()-1)).equals(".")){
                    pantalla.setText(pantalla.getText().toString().substring(0,pantalla.getText().toString().length()-1));
                }
                if(operacion.equals("x")){
                    float resultado = ultNum*(Float.parseFloat(pantalla.getText().toString()));
                    pantalla.setText("" + resultado);
                    operacion = "";
                    if((pantalla.getText().toString().substring(pantalla.getText().toString().length()-2,pantalla.getText().toString().length())).equals(".0")){
                        pantalla.setText(pantalla.getText().toString().substring(0,pantalla.getText().toString().length()-2));
                    }
                }else if(operacion.equals("/")){
                    float resultado = ultNum/(Float.parseFloat(pantalla.getText().toString()));
                    pantalla.setText("" + resultado);
                    operacion = "";
                    if((pantalla.getText().toString().substring(pantalla.getText().toString().length()-2,pantalla.getText().toString().length())).equals(".0")){
                        pantalla.setText(pantalla.getText().toString().substring(0,pantalla.getText().toString().length()-2));
                    }
                }else if(operacion.equals("-")){
                    float resultado = ultNum - (Float.parseFloat(pantalla.getText().toString()));
                    pantalla.setText("" + resultado);
                    operacion = "";
                    if((pantalla.getText().toString().substring(pantalla.getText().toString().length()-2,pantalla.getText().toString().length())).equals(".0")){
                        pantalla.setText(pantalla.getText().toString().substring(0,pantalla.getText().toString().length()-2));
                    }
                }else if(operacion.equals("+")){
                    float resultado = ultNum + (Float.parseFloat(pantalla.getText().toString()));
                    pantalla.setText("" + resultado);
                    operacion = "";
                    if((pantalla.getText().toString().substring(pantalla.getText().toString().length()-2,pantalla.getText().toString().length())).equals(".0")){
                        pantalla.setText(pantalla.getText().toString().substring(0,pantalla.getText().toString().length()-2));
                    }
                }else if(operacion.equals("%")) {
                    float resultado = (ultNum / 100) * (Float.parseFloat(pantalla.getText().toString()));
                    pantalla.setText("" + resultado);
                    operacion = "";
                    if ((pantalla.getText().toString().substring(pantalla.getText().toString().length() - 2, pantalla.getText().toString().length())).equals(".0")) {
                        pantalla.setText(pantalla.getText().toString().substring(0, pantalla.getText().toString().length() - 2));
                    }
                }
                nuevoNum = true;
                break;
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
                if(pantalla.getText().toString().equals("0")||nuevoNum) {
                    pantalla.setText(((Boton) v).getText().toString());
                    nuevoNum = false;
                }else{
                    pantalla.setText(pantalla.getText().toString() + ((Boton) v).getText().toString());
                }
                break;
        }
    }
}