package juegosDeMesa.gui;

import com.github.lgooddatepicker.components.DatePicker;
import juegosDeMesa.base.Disenador;
import juegosDeMesa.base.Juego;

import javax.swing.*;
import java.awt.*;

public class Vista extends JFrame{
    private JPanel panel1;
    private JTabbedPane tabbedPane1;

    //Juegos
    JTextField txtNombreJuego;
    JTextField txtPrecioJuego;
    DatePicker txtfechaJuego;
    JComboBox comboDis;


    JList<Juego> listJuegos;

    JButton btnAddJuegos;
    JButton btnModifyJuegos;
    JButton btnDeleteJuegos;

    //Diseñadores
    JTextField txtNombreDis;
    JTextField txtApellidosDis;
    DatePicker txtFechaDis;

    JList<Disenador> listDis;

    JButton btnAddDis;
    JButton btnDeleteDis;
    JButton btnModifyDis;

    // Modelos
    DefaultListModel<Juego> dlmJuegos;
    DefaultListModel<Disenador> dlmDisenadores;

    //Menu
    JMenuItem itemConectar;
    JMenuItem itemSalir;

    public Vista() {
        setTitle("Excelsior - <SIN CONEXION>");
        setContentPane(panel1);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(800, 500));
        setResizable(false);
        pack();
        setVisible(true);

        inicializarModelos();
        inicializarMenu();
    }

    private void inicializarModelos() {
        dlmJuegos = new DefaultListModel<>();
        listJuegos.setModel(dlmJuegos);
        dlmDisenadores = new DefaultListModel<>();
        listDis.setModel(dlmDisenadores);
    }

    private void inicializarMenu() {
        itemConectar = new JMenuItem("Conectar");
        itemConectar.setActionCommand("conexion");
        itemSalir = new JMenuItem("Salir");
        itemSalir.setActionCommand("salir");

        JMenu menuArchivo = new JMenu("Archivo");
        menuArchivo.add(itemConectar);
        menuArchivo.add(itemSalir);

        JMenuBar menuBar = new JMenuBar();
        menuBar.add(menuArchivo);

        setJMenuBar(menuBar);
    }
}
