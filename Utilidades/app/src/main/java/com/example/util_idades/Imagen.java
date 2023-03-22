package com.example.util_idades;

public class Imagen {
    int imagen;
    String texto;

    public Imagen(int imagen, String texto) {
        this.imagen = imagen;
        this.texto = texto;
    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }

    @Override
    public String toString() {
        return texto;
    }
}
