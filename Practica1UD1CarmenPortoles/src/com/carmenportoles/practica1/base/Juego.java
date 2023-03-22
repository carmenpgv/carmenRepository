package com.carmenportoles.practica1.base;

import java.time.LocalDate;

/**
 * @author carmen portolés
 * esta es la clase objeto juego
 */
public abstract class Juego {
    private String nombre;
    private String editorial;
    private String nJugadores;
    private LocalDate fechaPublicacion;

    /**
     * constructor vacío de juego
     */
    public Juego(){
    }

    /**
     * Constructor de juego que rellena los atributos
     * @param nombre
     * @param editorial
     * @param nJugadores
     * @param fechaPublicacion
     */
    public Juego(String nombre, String editorial, String nJugadores,
                 LocalDate fechaPublicacion){
        this.nombre = nombre;
        this.editorial = editorial;
        this.nJugadores = nJugadores;
        this.fechaPublicacion = fechaPublicacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public String getNJugadores() {
        return nJugadores;
    }

    public void setNJugadores(String nJugadores) {
        this.nJugadores = nJugadores;
    }

    public LocalDate getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(LocalDate fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    /**
     * cambia los datos de los atributos por los pasados por parámetro
     * @param nombre
     * @param editorial
     * @param nJugadores
     * @param fechaPublicacion
     */
    public void modificar(String nombre, String editorial, String nJugadores, LocalDate fechaPublicacion){
        this.setNombre(nombre);
        this.setEditorial(editorial);
        this.setNJugadores(nJugadores);
        this.setFechaPublicacion(fechaPublicacion);
    }
}
