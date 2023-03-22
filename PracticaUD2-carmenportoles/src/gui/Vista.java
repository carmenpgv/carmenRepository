package gui;

import base.enums.GenerosJuegos;
import base.enums.ProductosEditoriales;
import com.github.lgooddatepicker.components.DatePicker;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class Vista extends JFrame{
    private final static String TITULOFRAME = "Aplicación juegos de mesa";
    JTabbedPane tabbedPane;
    JPanel panel1;

    //juegos
    JPanel JPanelJuego;
    JTextField txtTitulo;
    JComboBox comboDis;
    JComboBox comboEditorial;
    JComboBox comboGenero;
    JTextField txtIsbn;
    JTextField txtPrecio;
    JTextField txtJugadoresMax;
    JTextField txtJugadoresMin;
    JTextField txtDuracionMax;
    JTextField txtDuracionMin;
    JButton btnJuegoAnadir;
    JButton btnJuegoModificar;
    JButton btnJuegoEliminar;
    JTable juegosTabla;
    JLabel numeroJuegos;
    DatePicker fechapublicacion;

    //diseñadores
    JPanel JPanelDisenador;
    JTextField txtNombre;
    JTextField txtApellidos;
    DatePicker fechanac;
    JTextField txtPais;
    JButton btnDisAnadir;
    JButton btnDisModificar;
    JButton btnDisEliminar;
    JTable disenadoresTabla;
    JLabel numeroDisenadores;

    //editoriales
    JPanel JPanelEditorial;
    JTextField txtNombreEditorial;
    JTextField txtEmail;
    JTextField txtTelefono;
    JComboBox comboProductoEditorial;
    JTextField txtWeb;
    JButton btnEditAnadir;
    JButton btnEditModificar;
    JButton btnEditEliminar;
    JTable editorialesTabla;
    JLabel numeroEditoriales;

    //default table model
    DefaultTableModel dtmEditoriales;
    DefaultTableModel dtmDisenadores;
    DefaultTableModel dtmJuegos;

    //menubar
    JMenuItem itemOpciones;
    JMenuItem itemDesconectar;
    JMenuItem itemSalir;
    OptionDialog optionDialog;

    //cuadro dialogo
    JDialog adminPasswordDialog;
    JButton btnValidate;
    JPasswordField adminPassword;

    public Vista() {
        super(TITULOFRAME);
        initFrame();
    }

    private void initFrame() {
        this.setContentPane(panel1);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
        this.setSize(new Dimension(this.getWidth(),this.getHeight()));
        this.setLocationRelativeTo(null);
        optionDialog = new OptionDialog(this);
        setMenu();
        setAdminDialog();
        setEnumComboBox();
        setTableModels();
    }

    private void setMenu() {
        JMenuBar mbBar = new JMenuBar();
        JMenu menu= new JMenu("Archivo");
        itemOpciones = new JMenuItem("Opciones");
        itemOpciones.setActionCommand("Opciones");
        itemDesconectar = new JMenuItem("Desconectar");
        itemDesconectar.setActionCommand("Desconectar");
        itemSalir = new JMenuItem("Salir");
        itemSalir.setActionCommand("Salir");
        menu.add(itemOpciones);
        menu.add(itemDesconectar);
        menu.add(itemSalir);
        mbBar.add(menu);
        mbBar.add(Box.createHorizontalGlue());
        this.setJMenuBar(mbBar);
    }

    private void setAdminDialog() {
        btnValidate = new JButton("Validar");
        btnValidate.setActionCommand("abrirOpciones");
        adminPassword = new JPasswordField();
        adminPassword.setPreferredSize(new Dimension(100, 26));
        Object[] options = new Object[] {adminPassword, btnValidate};
        JOptionPane jop = new JOptionPane("Introduce la contraseña", JOptionPane.WARNING_MESSAGE,
                JOptionPane.YES_NO_OPTION, null, options);
        adminPasswordDialog = new JDialog(this, "Opciones", true);
        adminPasswordDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        adminPasswordDialog.setContentPane(jop);
        adminPasswordDialog.pack();
        adminPasswordDialog.setLocationRelativeTo(this);
    }

    private void setEnumComboBox() {
        for(ProductosEditoriales constant: ProductosEditoriales.values()) {
            comboProductoEditorial.addItem(constant.getValor());
        }
        comboProductoEditorial.setSelectedIndex(-1);
        for (GenerosJuegos constant: GenerosJuegos.values()) {
            comboGenero.addItem(constant.getValor());
        }
        comboGenero.setSelectedIndex(-1);
    }

    private void setTableModels() {
        this.dtmJuegos = new DefaultTableModel();
        this.juegosTabla.setModel(dtmJuegos);

        this.dtmDisenadores = new DefaultTableModel();
        this.disenadoresTabla.setModel(dtmDisenadores);

        this.dtmEditoriales = new DefaultTableModel();
        this.editorialesTabla.setModel(dtmEditoriales);
    }


}
