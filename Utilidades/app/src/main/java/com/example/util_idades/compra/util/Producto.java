package com.example.util_idades.compra.util;

import android.content.Intent;
import android.graphics.Bitmap;

public class Producto {
    private long id;

    private String nombre;
    private String marca;
    private String seccion;
    private  float precio;
    private Bitmap foto;

    public Producto() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getSeccion() {
        return seccion;
    }

    public void setSeccion(String seccion) {
        this.seccion = seccion;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public Bitmap getFoto() {
        return foto;
    }

    public void setFoto(Bitmap foto) {
        this.foto = foto;
    }

    public Intent putExtraProducto(Intent i){
        i.putExtra("NOMBRE", this.getNombre());
        i.putExtra("MARCA",this.getMarca());
        i.putExtra("SECCION", this.getSeccion());
        i.putExtra("PRECIO", this.getPrecio());
        return i;
    }
}
