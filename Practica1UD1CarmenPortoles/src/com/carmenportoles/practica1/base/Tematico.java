package com.carmenportoles.practica1.base;

import java.time.LocalDate;

/**
 * @author carmen portolés
 * esta clase es hija de juego y establece las propiedades y métodos de los juegos de tipo temático
 */
public class Tematico extends Juego {
    String tematica;

    /**
     * constructor vacío
     */
    public Tematico(){
        super();
    }

    /**
     * constructor que rellena los atributos con los pasados como parámetro
     * @param nombre
     * @param editorial
     * @param nJugadores
     * @param fechaPublicacion
     * @param tematica
     */
    public Tematico(String nombre, String editorial, String nJugadores, LocalDate fechaPublicacion,
                    String tematica){
        super(nombre,editorial,nJugadores,fechaPublicacion);
        this.tematica = tematica;
    }
    public String getTematica() {
        return tematica;
    }

    public void setTematica(String tematica) {
        this.tematica = tematica;
    }

    /**
     * llama al metodo modificar de juego y cambia el atributo tematica por el pasado como parametro
     * @param nombre
     * @param editorial
     * @param nJugadores
     * @param fechaPublicacion
     * @param tematica
     */
    public void modificar(String nombre, String editorial, String nJugadores, LocalDate fechaPublicacion,
                          String tematica){
        super.modificar(nombre, editorial, nJugadores, fechaPublicacion);
        this.setTematica(tematica);
    }
    @Override
    public String toString() {
        return "Tematico: " + getNombre() + "-" + getEditorial() + " " + getNJugadores() + " jugadores";
    }
}
