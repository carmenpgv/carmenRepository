package com.carmenpg.juegosdemesahibernate.gui;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DateTimePicker;

import javax.swing.*;

public class Vista {
    private JFrame frame;
    private JTabbedPane tabbedPane1;
    private JPanel panel1;
    //Juegos
    JTextField txtTituloJuego;
    JTextField txtIsbnJuego;
    JTextField txtGeneroJuego;
    JTextField txtDuracionJuego;
    JTextField txtJugadoresJuego;
    JTextField txtPrecioJuego;
    DateTimePicker txtFechaJuego;
    JButton btnAnadirJuego;
    JButton btnModificarJuego;
    JButton btnEliminarJuego;
    JList listJuegos;
    JComboBox comboDis;
    JComboBox comboEdit;

    //Diseñadores
    JTextField txtNombreDis;
    JTextField txtApellidosDis;
    JTextField txtPaisDis;
    DateTimePicker txtFechaDis;
    JButton btnAnadirDis;
    JButton btnModificarDis;
    JButton btnEliminarDis;
    JList listDisenadores;
    JList listJuegosDisenador;

    //Tiendas
    JTextField txtDireccionTienda;
    JButton btnAnadirTienda;
    JButton btnModificarTienda;
    JButton btnEliminarTienda;
    JList listTiendas;

    //Editoriales
    JTextField txtNombreEdit;
    JTextField txtProductosEdit;
    JTextField txtEmailEdit;
    JTextField txtTelefonoEdit;
    JTextField txtWebEdit;
    JButton btnAnadirEdit;
    JButton btnModificarEdit;
    JButton btnEliminarEdit;
    JList listEditoriales;

    //DLM
    DefaultListModel dlmJuegos;
    DefaultListModel dlmDisenadores;
    DefaultListModel dlmEditoriales;
    DefaultListModel dlmTiendas;
    DefaultListModel dlmJuegosDisenador;

    //Menu
    JMenuItem conexionItem;
    JMenuItem salirItem;

    public Vista() {
        frame = new JFrame("Vista");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel1.setBounds(500,500,500,500);

        crearMenu();

        frame.pack();
        frame.setVisible(true);

        crearModelos();

        frame.setLocationRelativeTo(null);
    }

    private void crearModelos() {
        dlmJuegos= new DefaultListModel();
        listJuegos.setModel(dlmJuegos);
        dlmDisenadores= new DefaultListModel();
        listDisenadores.setModel(dlmDisenadores);
        dlmEditoriales= new DefaultListModel();
        listEditoriales.setModel(dlmEditoriales);
        dlmTiendas= new DefaultListModel();
        listTiendas.setModel(dlmTiendas);
        dlmJuegosDisenador= new DefaultListModel();
        listJuegosDisenador.setModel(dlmJuegosDisenador);
    }

    private void crearMenu() {
        JMenuBar barra = new JMenuBar();
        JMenu menu = new JMenu("Archivo");
        conexionItem= new JMenuItem("Conectar");
        conexionItem.setActionCommand("Conectar");

        salirItem= new JMenuItem("Salir");
        salirItem.setActionCommand("Salir");

        menu.add(conexionItem);
        menu.add(salirItem);
        barra.add(menu);
        frame.setJMenuBar(barra);
    }
}
