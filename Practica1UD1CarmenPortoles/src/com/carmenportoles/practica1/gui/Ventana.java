package com.carmenportoles.practica1.gui;

import com.carmenportoles.practica1.base.Juego;
import com.github.lgooddatepicker.components.DatePicker;
import sun.swing.text.html.FrameEditorPaneTag;

import javax.swing.*;

/**
 * esta clase crea un objeto ventana que será la interfaz gráfica y los elementos que hay en ella
 */
public class Ventana {
    public JPanel panel1;
    public JRadioButton abstractoRadioButton;
    public JRadioButton tematicoRadioButton;
    public JTextField nombreTxt;
    public JTextField editorialTxt;
    public JLabel edadTematicalbl;
    public JTextField edadTematicaTxt;
    public JTextField nJugadoresTxt;
    public DatePicker dPicker;
    public JButton exportarBtn;
    public JButton nuevoBtn;
    public JButton importarBtn;
    public JList list1;
    public JButton modificarBtn;
    public JButton borrarBtn;

    public JFrame frame;
    public DefaultListModel<Juego>dlmJuego;

    /**
     * el constructor inicializa la ventana y la hace visible y con los atributos en los estados deseados al iniciar
     */
    public Ventana(){
        frame = new JFrame("Juegos de mesa");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        this.nombreTxt.setEnabled(false);
        this.editorialTxt.setEnabled(false);
        this.nJugadoresTxt.setEnabled(false);
        this.dPicker.setEnabled(false);
        this.edadTematicalbl.setVisible(false);
        this.edadTematicaTxt.setVisible(false);
        this.modificarBtn.setEnabled(false);
        this.borrarBtn.setEnabled(false);

        initComponents();
    }

    /**
     * inicializa el defaultListModel que guardará los juegos que se mostrarán en la lista
     */
    private void initComponents(){
        dlmJuego = new DefaultListModel<Juego>();
        list1.setModel(dlmJuego);
    }
}
