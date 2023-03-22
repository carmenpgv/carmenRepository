package com.carmenportoles.practica1.base;

import java.time.LocalDate;
/**
 * @author carmen portolés
 * esta clase es hija de juego y establece las propiedades y métodos de los juegos de tipo abstracto
 */
public class Abstracto extends Juego{
   private int edadMinima;
    /**
     * constructor vacío
     */
   public Abstracto(){
       super();
   }
    /**
     * constructor que rellena los atributos con los pasados como parámetro
     * @param nombre
     * @param editorial
     * @param nJugadores
     * @param fechaPublicacion
     * @param edadMinima
     */
   public Abstracto(String nombre, String editorial, String nJugadores,
                    LocalDate fechaPublicacion, int edadMinima){
       super(nombre, editorial, nJugadores, fechaPublicacion);
       this.edadMinima = edadMinima;
   }

    public int getEdadMinima() {
        return edadMinima;
    }

    public void setEdadMinima(int edadMinima) {
        this.edadMinima = edadMinima;
    }
    /**
     * llama al metodo modificar de juego y cambia el atributo edadMinima por el pasado como parametro
     * @param nombre
     * @param editorial
     * @param nJugadores
     * @param fechaPublicacion
     * @param edadMinima
     */
    public void modificar(String nombre, String editorial, String nJugadores, LocalDate fechaPublicacion,int edadMinima){
       super.modificar(nombre, editorial, nJugadores, fechaPublicacion);
       this.setEdadMinima(edadMinima);
    }

    @Override
    public String toString() {
        return "Abstracto: " + getNombre() + "-" + getEditorial() + " " + getNJugadores() + " jugadores";
    }
}
