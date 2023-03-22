
package com.carmenportoles.practica1.gui;

import com.carmenportoles.practica1.base.Abstracto;
import com.carmenportoles.practica1.base.Juego;
import com.carmenportoles.practica1.base.Tematico;
import com.carmenportoles.practica1.util.Util;
import org.xml.sax.SAXException;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.*;
import java.time.LocalDate;
import java.util.Properties;

/**
 * @author Carmen Portolés
 *
 * esta clase controla que se lleven a cabo todas las acciones que debe realizar cada botón y elemento de la interfaz
 *
 *
 */
public class JuegosControlador implements ActionListener, ListSelectionListener, WindowListener {
    private Ventana vista;
    private JuegosModelo modelo;
    private File ultimaRutaExportada;

    /**
     * constructor del controlador con una ventana y una lista de juegos
     * @param vista
     * @param modelo
     */
    public JuegosControlador(Ventana vista, JuegosModelo modelo){
        this.vista = vista;
        this.modelo = modelo;

        try{
            cargarDatosConfiguracion();
        } catch (IOException e) {
            System.out.println("No existe fichero de configuracion " + e.getMessage());
        }

        addActionListener(this);
        addListSelectionListener(this);
        addWindowListener(this);
    }

    /**
     * Sirve para sacar la última ruta exportada del fichero juegos.conf
     * @throws IOException
     */
    private void cargarDatosConfiguracion() throws IOException {
        Properties configuracion = new Properties();
        configuracion.load(new FileReader("juegos.conf"));
        ultimaRutaExportada = new File((configuracion.getProperty("ultimaRutaExportada")));
    }

    /**
     * Se establece la última ruta exportada la pasada como parámetro
     * @param ultimaRutaExportada
     */
    private void actualizarDatosConfiguracion(File ultimaRutaExportada){
        this.ultimaRutaExportada = ultimaRutaExportada;
    }

    /**
     * guarda en juegos conf la última ruta exportada
     * @throws IOException
     */
    private void guardarDatosConfiguracion() throws IOException {
        Properties configuracion = new Properties();
        configuracion.setProperty("ultimaRutaExportada", ultimaRutaExportada.getAbsolutePath());
        configuracion.store(new PrintWriter("juegos.conf"),"datos configuracion juegos");
    }

    /**
     * les pone un listener a los botones para que el programa sepa cuánddo el usuario hace clic en ellos y
     * pueda actuar en consecuencia
     * @param listener
     */
    private void addActionListener(ActionListener listener) {
        vista.abstractoRadioButton.addActionListener(listener);
        vista.tematicoRadioButton.addActionListener(listener);
        vista.exportarBtn.addActionListener(listener);
        vista.importarBtn.addActionListener(listener);
        vista.nuevoBtn.addActionListener(listener);
        vista.modificarBtn.addActionListener(listener);
        vista.borrarBtn.addActionListener(listener);
    }

    /**
     * le pone un listener a la lista para que el programa sepa cuándo el usuario hace clic en alguno de sus
     * elementos
     * @param listener
     */
    private void addListSelectionListener(ListSelectionListener listener) {
        vista.list1.addListSelectionListener(listener);
    }

    /**
     * le pone un listener a la ventana para añadir funcionalidad al botónde cerrado
     * @param listener
     */
    private void addWindowListener(WindowListener listener) {
        vista.frame.addWindowListener(listener);
    }

    /**
     * Añade la funcionalidad de los botones (este método es llamado al hacer clic en alguno de ellos)
     * Nuevo: añade un elemento a la lista con las propiedades que ha escrito el usuario en los campos y
     *      no permite que se produzca la acción si hay algún campo vacío o ya existe un juego con el mismo
     *      nombre.
     * Borrar: Elimina de la lista el elemento seleccionado
     * Modificar: modifica las propiedades del elemento de la lista seleccionado y les pone las escritas en
     *      los campos pero no permite convertir un juego de tipo temático en abstracto y saca un mensaje de error
     *      si el usuario lo intenta
     * Importar: permite seleccionar un archivo xml del equipo y mostrar sus elementos en la lista
     * Exportar: permite guardar en el equipo un archivo xml con los elementos que hay en la lista
     * Abstracto: Hace aparecer el campo edad mínima y habilita los campos para escribir en ellos
     * Tematico: Hace aparecer el campo temática y habilita los campos para escribir en ellos
     *
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();
        switch (actionCommand){
            case "Nuevo":
                if(hayCamposVacios()){
                    Util.mensajeError("Has dejado campos vacios");
                    break;
                }
                if(modelo.existeNombre(vista.nombreTxt.getText())){
                    Util.mensajeError("Ya existe " + vista.nombreTxt.getText());
                    break;
                }
                if(vista.abstractoRadioButton.isSelected()){
                    modelo.altaAbstracto(vista.nombreTxt.getText(),vista.editorialTxt.getText(),
                            vista.nJugadoresTxt.getText(),vista.dPicker.getDate(),
                            Integer.parseInt(vista.edadTematicaTxt.getText()));
                }else {
                    modelo.altaTematico(vista.nombreTxt.getText(),vista.editorialTxt.getText(),
                            vista.nJugadoresTxt.getText(),vista.dPicker.getDate(),vista.edadTematicaTxt.getText());
                }
                limpiarCampos();
                refrescar();
                break;
            case "Borrar":
                modelo.obtenerJuegos().remove(vista.list1.getSelectedValue());
                limpiarCampos();
                refrescar();
            case "Modificar":
                for (Juego juego: modelo.obtenerJuegos()){
                    if(juego.equals(vista.list1.getSelectedValue())){
                        if(juego instanceof Abstracto && vista.tematicoRadioButton.isSelected()){
                            Util.mensajeError("No puedes convertir un juego abstracto en temático");
                        }else if(juego instanceof Tematico && vista.abstractoRadioButton.isSelected()){
                            Util.mensajeError("No puedes convertir un juego temático en abstracto");
                        }else {
                            String nombre = vista.nombreTxt.getText();
                            String editorial = vista.editorialTxt.getText();
                            String nJugadores = vista.nJugadoresTxt.getText();
                            LocalDate fechaPublicacion = vista.dPicker.getDate();
                            if (juego instanceof Abstracto) {
                                int edadMinima = Integer.valueOf(vista.edadTematicaTxt.getText());
                                ((Abstracto) juego).modificar(nombre, editorial, nJugadores, fechaPublicacion,
                                        edadMinima);
                            } else {
                                String tematica = vista.edadTematicaTxt.getText();
                                ((Tematico) juego).modificar(nombre, editorial, nJugadores, fechaPublicacion, tematica);
                            }
                            limpiarCampos();
                            refrescar();
                        }
                    }
                }
                break;
            case "Importar":
                JFileChooser selectorFichero = Util.crearSelectorFichero(ultimaRutaExportada,"Archivos XML",
                        "xml");
                int opt = selectorFichero.showOpenDialog(null);
                if(opt == JFileChooser.APPROVE_OPTION){
                    try {
                        modelo.importarXML(selectorFichero.getSelectedFile());
                    } catch (ParserConfigurationException ex) {
                        ex.printStackTrace();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    } catch (SAXException ex) {
                        ex.printStackTrace();
                    }
                }
                refrescar();
                break;
            case "Exportar":
                JFileChooser selectorFichero2 = Util.crearSelectorFichero(ultimaRutaExportada,
                        "Archivos XML", "xml");
                int opt2 = selectorFichero2.showSaveDialog(null);
                if(opt2 == JFileChooser.APPROVE_OPTION){
                    try {
                        modelo.exportarXML(selectorFichero2.getSelectedFile());
                        actualizarDatosConfiguracion(selectorFichero2.getSelectedFile());
                    } catch (ParserConfigurationException ex) {
                        ex.printStackTrace();
                    } catch (TransformerException ex) {
                        ex.printStackTrace();
                    }
                }
                break;
            case "Abstracto":
                vista.edadTematicalbl.setText("Edad mínima");
                vista.nombreTxt.setEnabled(true);
                vista.editorialTxt.setEnabled(true);
                vista.nJugadoresTxt.setEnabled(true);
                vista.dPicker.setEnabled(true);
                vista.edadTematicalbl.setVisible(true);
                vista.edadTematicaTxt.setVisible(true);
                break;
            case "Temático":
                vista.edadTematicalbl.setText("Temática");
                vista.nombreTxt.setEnabled(true);
                vista.editorialTxt.setEnabled(true);
                vista.nJugadoresTxt.setEnabled(true);
                vista.dPicker.setEnabled(true);
                vista.edadTematicalbl.setVisible(true);
                vista.edadTematicaTxt.setVisible(true);
                break;
        }
    }

    /**
     * devuelve true si hay algún campo vacío y false si están todos rellenados
     * @return
     */
    private boolean hayCamposVacios(){
        if(vista.nombreTxt.getText().isEmpty() ||
            vista.editorialTxt.getText().isEmpty() ||
            vista.nJugadoresTxt.getText().isEmpty() ||
            vista.edadTematicaTxt.getText().isEmpty() ||
            vista.dPicker.getText().isEmpty()){
            return true;
        }
        return false;
    }

    /**
     * Quita el texto de todos los campos
     */
    private void limpiarCampos(){
        vista.nombreTxt.setText(null);
        vista.editorialTxt.setText(null);
        vista.nJugadoresTxt.setText(null);
        vista.edadTematicaTxt.setText(null);
        vista.dPicker.setText(null);
        vista.nombreTxt.requestFocus();
        vista.borrarBtn.setEnabled(false);
        vista.modificarBtn.setEnabled(false);
    }

    /**
     * actualiza el defaultListModel con los elementos que hay en el ArrayList de
     * juegos
     */
    public void refrescar(){
        vista.dlmJuego.clear();
        for(Juego juego: modelo.obtenerJuegos()){
            vista.dlmJuego.addElement(juego);
        }
    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    /**
     * Si hay algún elemento en la lista te pregunta si quieres exportar el xml y te da
     * la opción de guardar y cerras, no guardar y cerrar o cancelar la acción
     * Si no hay elementos en la lista pregunta al usuario si está seguro de que quiere salir
     * @param e
     */
    @Override
    public void windowClosing(WindowEvent e) {
        if(modelo.obtenerJuegos().size()==0){
            int resp = Util.mensajeConfirmacion("¿Desea cerrar la ventana?", "Salir");
            if(resp == JOptionPane.YES_OPTION){
                try{
                    guardarDatosConfiguracion();
                }catch (IOException e1){
                    e1.printStackTrace();
                }
                finally{
                    System.exit(0);
                }
                System.exit(0);
            }
        }else {
            String[] botones = {"Guardar","No guardar","Cancelar"};
            int resp = Util.mensajeGuardado("¿Quieres guardar los datos antes de salir?", "Salir",
                    botones);
            if(resp == 0) {
                JFileChooser selectorFichero2 = Util.crearSelectorFichero(ultimaRutaExportada,
                        "Archivos XML", "xml");
                int opt2 = selectorFichero2.showSaveDialog(null);
                if(opt2 == JFileChooser.APPROVE_OPTION){
                    try {
                        modelo.exportarXML(selectorFichero2.getSelectedFile());
                        actualizarDatosConfiguracion(selectorFichero2.getSelectedFile());
                    } catch (ParserConfigurationException ex) {
                        ex.printStackTrace();
                    } catch (TransformerException ex) {
                        ex.printStackTrace();
                    }
                    try {
                        guardarDatosConfiguracion();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    System.exit(0);
                }
            }
            else if(resp == 1) {
                try {
                    guardarDatosConfiguracion();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                System.exit(0);
            }
        }

    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }

    /**
     * cuando el usuario selecciona un elemento ecribe en los campos las propiedades de dicho elemento
     * @param e
     */
    @Override
    public void valueChanged(ListSelectionEvent e) {
        if(e.getValueIsAdjusting()) {

            Juego juegoSeleccionado = (Juego) vista.list1.getSelectedValue();
            vista.nombreTxt.setText(juegoSeleccionado.getNombre());
            vista.editorialTxt.setText(juegoSeleccionado.getEditorial());
            vista.nJugadoresTxt.setText(juegoSeleccionado.getNJugadores());
            vista.dPicker.setDate(juegoSeleccionado.getFechaPublicacion());

            if (juegoSeleccionado instanceof Abstracto) {
                vista.abstractoRadioButton.doClick();
                vista.edadTematicaTxt.setText(String.valueOf(((Abstracto) juegoSeleccionado).getEdadMinima()));
            } else {
                vista.tematicoRadioButton.doClick();
                vista.edadTematicaTxt.setText(String.valueOf(((Tematico) juegoSeleccionado).getTematica()));
            }
            vista.modificarBtn.setEnabled(true);
            vista.borrarBtn.setEnabled(true);
        }
    }
}
