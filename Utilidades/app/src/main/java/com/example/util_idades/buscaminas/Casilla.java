package com.example.util_idades.buscaminas;

import android.content.Context;
import android.widget.Button;

public class Casilla extends androidx.appcompat.widget.AppCompatButton {

    boolean explosive;
    boolean revealed;
    int minas;
    Button boton;
    int fila;
    int columna;
    int image;

    public Casilla(Context context) {
        super(context);
        explosive = false;
        revealed = false;
        minas = 0;
    }

    public boolean isExplosive() {
        return explosive;
    }

    public void setExplosive(boolean explosive) {
        this.explosive = explosive;
    }

    public boolean isRevealed() {
        return revealed;
    }

    public void setRevealed(boolean revealed) {
        this.revealed = revealed;
    }

    public int getMinas() {
        return minas;
    }

    public void setMinas(int minas) {
        this.minas = minas;
    }

    public Button getBoton() {
        return boton;
    }

    public void setBoton(Button boton) {
        this.boton = boton;
    }

    public int getFila() {
        return fila;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }

    public int getColumna() {
        return columna;
    }

    public void setColumna(int columna) {
        this.columna = columna;
    }

    public void setImage(int background) {
        setBackground(getResources().getDrawable(background));
        this.image = background;
    }

    public int getImage() {
        return image;
    }
}
