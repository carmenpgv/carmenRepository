package com.carmenportoles.practica1;

import com.carmenportoles.practica1.gui.Ventana;
import com.carmenportoles.practica1.gui.JuegosControlador;
import com.carmenportoles.practica1.gui.JuegosModelo;

public class Principal {
    public static void main(String[] args) {
        Ventana vista = new Ventana();
        JuegosModelo modelo = new JuegosModelo();
        JuegosControlador controlador = new JuegosControlador(vista,modelo);
    }
}
