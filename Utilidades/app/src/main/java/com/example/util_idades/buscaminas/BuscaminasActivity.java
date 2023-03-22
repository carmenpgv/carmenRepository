package com.example.util_idades.buscaminas;

import androidx.appcompat.app.AppCompatActivity;
import static android.preference.PreferenceManager.getDefaultSharedPreferences;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.util_idades.MainActivity;
import com.example.util_idades.R;

public class BuscaminasActivity extends AppCompatActivity implements View.OnClickListener,View.OnLongClickListener{
    TableLayout tablero;
    int dimensionx;
    int dimensiony;
    ImageButton restart;
    Boolean fin;
    int minas;
    TextView nBanderas;
    SharedPreferences prefs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscaminas);
        prefs = getDefaultSharedPreferences(getApplicationContext());
        if(prefs.getBoolean("swPrefsDificultad", false)){
            dimensionx = 8;
            dimensiony = 12;
            minas = 15;
        } else{
            dimensiony = 8;
            dimensionx = 8;
            minas = 10;
        }
        restart = findViewById(R.id.bBuscaminasReintentar);
        restart.setOnClickListener(this);

        nBanderas = findViewById(R.id.tvBuscaminasMinas);

        tablero = findViewById(R.id.lyBuscaminasCasillas);
        Casilla casilla;
        TableLayout.LayoutParams tableroParams = new TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT,
                TableLayout.LayoutParams.WRAP_CONTENT);
        TableRow.LayoutParams rowParams = new TableRow.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT,
                TableLayout.LayoutParams.WRAP_CONTENT);

        for (int i = 0; i<dimensiony; i++){
            TableRow tableRow = new TableRow(this);

            for (int j = 0; j<dimensionx;j++){
                casilla = new Casilla(this);
                casilla.setOnClickListener(this);
                casilla.setOnLongClickListener(this);
                casilla.setLayoutParams(rowParams);
                casilla.setId((10*i)+j);
                casilla.setFila(i);
                casilla.setColumna(j);
                casilla.setImage(R.drawable.vacio);
                tableRow.addView(casilla);
            }
            tablero.addView(tableRow);

            tableRow.setLayoutParams(rowParams);
        }
        tablero.setLayoutParams(tableroParams);
        crearMinas();
        fin = false;

    }

    public void reintentar(){
        Casilla casilla;
        for (int i = 0; i<dimensiony; i++){

            for (int j = 0; j<dimensionx;j++){
                casilla = findViewById((i*10)+j);
                casilla.setImage(R.drawable.vacio);
                casilla.setExplosive(false);
                casilla.setRevealed(false);
                casilla.setMinas(0);
            }
        }
        crearMinas();
        fin = false;
        nBanderas.setText("0");
        nBanderas.setVisibility(View.INVISIBLE);
        restart.setImageDrawable(getResources().getDrawable(R.drawable.jugando));
    }

    public void crearMinas(){
        for(int i=0;i<minas;i++){
            Casilla casilla;
            do {
                int x = (int) (Math.random()*dimensionx);
                int y = (int) (Math.random()*dimensiony);
                casilla = getCasilla(y,x);
            }while (casilla.isExplosive());
            casilla.setExplosive(true);
        }
        for (int i=0;i<dimensiony;i++){
            for (int j=0;j<dimensionx;j++) {
                Casilla casilla = getCasilla(i,j);
                if (!casilla.isExplosive()){
                    if(i==0){
                        if(j==0){
                            sumarMinas(casilla,i,j+1);
                            sumarMinas(casilla,i+1,j+1);
                            sumarMinas(casilla,i+1,j);
                        }else if(j==(dimensionx-1)){
                            sumarMinas(casilla,i+1,j);
                            sumarMinas(casilla,i+1,j-1);
                            sumarMinas(casilla,i,j-1);
                        }else {
                            sumarMinas(casilla,i,j-1);
                            sumarMinas(casilla,i+1,j-1);
                            sumarMinas(casilla,i+1,j);
                            sumarMinas(casilla,i+1,j+1);
                            sumarMinas(casilla,i,j+1);
                        }
                    }else if(i==(dimensiony-1)){
                        if(j==0){
                            sumarMinas(casilla,i,j+1);
                            sumarMinas(casilla,i-1,j+1);
                            sumarMinas(casilla,i-1,j);
                        }else if(j==dimensionx-1){
                            sumarMinas(casilla,i-1,j);
                            sumarMinas(casilla,i-1,j-1);
                            sumarMinas(casilla,i,j-1);
                        }else {
                            sumarMinas(casilla,i,j-1);
                            sumarMinas(casilla,i-1,j-1);
                            sumarMinas(casilla,i-1,j);
                            sumarMinas(casilla,i-1,j+1);
                            sumarMinas(casilla,i,j+1);
                        }
                    }else if(j==0){
                        sumarMinas(casilla,i-1,j);
                        sumarMinas(casilla,i-1,j+1);
                        sumarMinas(casilla,i,j+1);
                        sumarMinas(casilla,i+1,j+1);
                        sumarMinas(casilla,i+1,j);
                    }else if(j==(dimensionx-1)){
                        sumarMinas(casilla,i-1,j);
                        sumarMinas(casilla,i-1,j-1);
                        sumarMinas(casilla,i,j-1);
                        sumarMinas(casilla,i+1,j-1);
                        sumarMinas(casilla,i+1,j);
                    }else {
                        sumarMinas(casilla,i-1,j);
                        sumarMinas(casilla,i-1,j+1);
                        sumarMinas(casilla,i,j+1);
                        sumarMinas(casilla,i+1,j+1);
                        sumarMinas(casilla,i+1,j);
                        sumarMinas(casilla,i+1,j-1);
                        sumarMinas(casilla,i,j-1);
                        sumarMinas(casilla,i-1,j-1);
                    }
                }
            }
        }
    }

    private void cambiarTextoMinas(boolean sumar){
        if (sumar){
            int minas = Integer.valueOf(nBanderas.getText().toString());
            minas++;
            nBanderas.setText(minas + "");
            nBanderas.setVisibility(View.VISIBLE);
        } else {
            int minas = Integer.valueOf(nBanderas.getText().toString());
            minas--;
            nBanderas.setText(minas + "");
            if(minas == 0){
                nBanderas.setVisibility(View.INVISIBLE);
            }
        }
    }

    public Casilla getCasilla(int y, int x){
        return findViewById((10*y)+x);
    }

    public void sumarMinas(Casilla casilla, int i, int j){
        Casilla c = getCasilla(i,j);
        if (c.isExplosive()){
            casilla.setMinas(casilla.getMinas()+1);
        }else {

        }
    }

    public void revelarAlrededor(Casilla casilla){
        int y = casilla.getFila();
        int x = casilla.getColumna();
        if(y==0){
            if (x==0){
                revelarCasilla(y,x+1);
                revelarCasilla(y+1,x+1);
                revelarCasilla(y+1,x);
            }else if (x==(dimensionx-1)){
                revelarCasilla(y,x-1);
                revelarCasilla(y+1,x-1);
                revelarCasilla(y+1,x);
            }else {
                revelarCasilla(y,x-1);
                revelarCasilla(y+1,x-1);
                revelarCasilla(y+1,x);
                revelarCasilla(y+1,x+1);
                revelarCasilla(y,x+1);
            }
        }else if(y==(dimensiony-1)){
            if (x==0){
                revelarCasilla(y,x+1);
                revelarCasilla(y-1,x+1);
                revelarCasilla(y-1,x);
            }else if (x==dimensionx-1){
                revelarCasilla(y,x-1);
                revelarCasilla(y-1,x-1);
                revelarCasilla(y-1,x);
            }else {
                revelarCasilla(y,x-1);
                revelarCasilla(y-1,x-1);
                revelarCasilla(y-1,x);
                revelarCasilla(y-1,x+1);
                revelarCasilla(y,x+1);
            }
        }else if (x==0){
            revelarCasilla(y-1,x);
            revelarCasilla(y-1,x+1);
            revelarCasilla(y,x+1);
            revelarCasilla(y+1,x+1);
            revelarCasilla(y+1,x);
        }else if(x==(dimensionx-1)){
            revelarCasilla(y-1,x);
            revelarCasilla(y-1,x-1);
            revelarCasilla(y,x-1);
            revelarCasilla(y+1,x-1);
            revelarCasilla(y+1,x);
        }else {
            revelarCasilla(y-1,x);
            revelarCasilla(y-1,x+1);
            revelarCasilla(y,x+1);
            revelarCasilla(y+1,x+1);
            revelarCasilla(y+1,x);
            revelarCasilla(y+1,x-1);
            revelarCasilla(y,x-1);
            revelarCasilla(y-1,x-1);
        }
    }

    public void revelarCasilla(int y, int x){
        Casilla casilla = getCasilla(y,x);
        if(!(casilla.isRevealed())){
            if (!(casilla.isExplosive())){
                casilla.callOnClick();
            }
        }
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.bBuscaminasReintentar){
            reintentar();
        }else{
            Casilla casilla = findViewById(view.getId());
            if(fin){
                Toast.makeText(this,"Pulsa el botón para volver a jugar",Toast.LENGTH_LONG).show();
            }else{
                if(casilla.getImage()==(R.drawable.bandera)){
                    Toast.makeText(this,"Presiona varios segundos para quitar la marca",Toast.LENGTH_LONG).show();
                }else if (casilla.isExplosive()){
                    perder(casilla);
                } else{
                    switch (casilla.getMinas()){
                        case 0:
                            casilla.setImage(R.drawable.cero);
                            casilla.setRevealed(true);
                            revelarAlrededor(casilla);
                            comprobarVictoria();
                            break;
                        case 1:
                            casilla.setImage(R.drawable.uno);
                            casilla.setRevealed(true);
                            comprobarVictoria();
                            break;
                        case 2:
                            casilla.setImage(R.drawable.dos);
                            casilla.setRevealed(true);
                            comprobarVictoria();
                            break;
                        case 3:
                            casilla.setImage(R.drawable.tres);
                            casilla.setRevealed(true);
                            comprobarVictoria();
                            break;
                        case 4:
                            casilla.setImage(R.drawable.cuatro);
                            casilla.setRevealed(true);
                            comprobarVictoria();
                            break;
                        case 5:
                            casilla.setImage(R.drawable.cinco);
                            casilla.setRevealed(true);
                            comprobarVictoria();
                            break;
                        case 6:
                            casilla.setImage(R.drawable.seis);
                            casilla.setRevealed(true);
                            comprobarVictoria();
                            break;
                        case 7:
                            casilla.setImage(R.drawable.siete);
                            casilla.setRevealed(true);
                            comprobarVictoria();
                            break;
                    }
                }
            }
        }
    }

    @Override
    public boolean onLongClick(View view) {
        if (view.getId()!=R.id.bBuscaminasReintentar) {
            Casilla casilla = findViewById(view.getId());
            if (casilla.getImage()==R.drawable.bandera) {
                casilla.setImage(R.drawable.vacio);
                casilla.setRevealed(false);
                cambiarTextoMinas(false);
            } else if (casilla.getImage()==R.drawable.vacio) {
                casilla.setImage(R.drawable.bandera);
                cambiarTextoMinas(true);
                if(casilla.isExplosive()){
                    casilla.setRevealed(true);
                }
            }
            comprobarVictoria();
        }
        return true;
    }
    public void perder(Casilla casillaFallada){
        for (int i=0;i<dimensiony;i++){
            for (int j=0;j<dimensionx;j++){
                Casilla casilla = getCasilla(i,j);
                if (casilla.isRevealed()){
                    if(casilla.getImage()==R.drawable.bandera){
                        casilla.setImage(R.drawable.bomba);
                    }
                }else {
                    if (casilla.getImage()==R.drawable.bandera){
                        casilla.setImage(R.drawable.banderafallada);
                    }else if (casilla.isExplosive()) {
                        casilla.setImage(R.drawable.bomba);
                    }
                }
            }
        }
        casillaFallada.setImage(R.drawable.bombafallada);
        restart.setImageDrawable(getResources().getDrawable(R.drawable.perdido));
        fin = true;
    }

    public void comprobarVictoria(){
        fin = true;
        Casilla casilla;
        for (int i = 0; i<dimensiony; i++){

            for (int j = 0; j<dimensionx;j++){
                casilla = findViewById((i*10)+j);
                if(!casilla.isRevealed()){
                    fin = false;
                }
            }
        }
        if (fin){
            restart.setImageDrawable(getResources().getDrawable(R.drawable.ganado));
        }
    }
}