package com.carmenportoles.practica1.util;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.util.ArrayList;

/**
 * @author carmen portolés
 * Esta es la clase de utilidades
 */
public class Util {
    /**
     * muestra un mensaje de error, con título "Error" y mensaje pasado por parámetro
     * @param mensaje
     */
    public static void mensajeError(String mensaje){
        JOptionPane.showMessageDialog(null,mensaje,"Error",JOptionPane.ERROR_MESSAGE);
    }

    /**
     * muestra un mensaje con una opcion de confirmación y otra de cancelación, con título y mensaje pasados por
     * parámetro
     * @param titulo
     * @param mensaje
     * @return
     */
    public static int mensajeConfirmacion(String titulo, String mensaje){
        return JOptionPane.showConfirmDialog(null,mensaje,titulo,JOptionPane.YES_NO_OPTION);
    }

    /**
     * muestra un mensaje de confirmación con tres botones personalizados pasados por parámetro, igual que el título y
     * el mensaje
     * @param mensaje
     * @param titulo
     * @param botones
     * @return
     */
    public static int mensajeGuardado(String mensaje, String titulo, String[]botones){
        return JOptionPane.showOptionDialog(null, mensaje, titulo, JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE, null, botones, botones[0]);
    }

    /**
     * Abre un selector de archivos del equipo
     * @param rutaDefecto
     * @param tipoArchivos
     * @param extension
     * @return
     */
    public static JFileChooser crearSelectorFichero(File rutaDefecto,String tipoArchivos,String extension){
        JFileChooser selectorFichero = new JFileChooser();
        if(rutaDefecto!=null){
            selectorFichero.setCurrentDirectory(rutaDefecto);
        }
        if(extension!=null){
            FileNameExtensionFilter filtro = new FileNameExtensionFilter(tipoArchivos,extension);
            selectorFichero.setFileFilter(filtro);
        }
        return selectorFichero;
    }
}
