package juegosDeMesa.main;

import juegosDeMesa.gui.Controlador;
import juegosDeMesa.gui.Modelo;
import juegosDeMesa.gui.Vista;

public class Main {
    public static void main(String[] args) {
        new Controlador(new Modelo(), new Vista());
    }
}
