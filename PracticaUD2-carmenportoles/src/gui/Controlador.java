package gui;

import util.Util;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

public class Controlador implements ActionListener, ItemListener, ListSelectionListener, WindowListener {
    private Modelo modelo;
    private Vista vista;
    boolean refrescar;

    public Controlador(Modelo modelo, Vista vista) {
        this.modelo = modelo;
        this.vista = vista;
        modelo.conectar();
        setOptions();
        addActionListeners(this);
        addWindowListeners(this);
        refrescarTodo();
        iniciar();
    }

    private void refrescarTodo() {
        refrescarDisenadores();
        refrescarEditoriales();
        refrescarJuegos();
        refrescar = false;
    }

    private void addActionListeners(ActionListener listener) {
        vista.btnJuegoAnadir.addActionListener(listener);
        vista.btnDisAnadir.addActionListener(listener);
        vista.btnEditAnadir.addActionListener(listener);
        vista.btnJuegoEliminar.addActionListener(listener);
        vista.btnDisEliminar.addActionListener(listener);
        vista.btnEditEliminar.addActionListener(listener);
        vista.btnJuegoModificar.addActionListener(listener);
        vista.btnDisModificar.addActionListener(listener);
        vista.btnEditModificar.addActionListener(listener);
        vista.optionDialog.btnOpcionesGuardar.addActionListener(listener);
        vista.itemOpciones.addActionListener(listener);
        vista.itemSalir.addActionListener(listener);
        vista.btnValidate.addActionListener(listener);
    }

    private void addWindowListeners(WindowListener listener) {
        vista.addWindowListener(listener);
    }

    void iniciar(){
        vista.editorialesTabla.setCellSelectionEnabled(true);
        ListSelectionModel cellSelectionModel =  vista.editorialesTabla.getSelectionModel();
        cellSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        cellSelectionModel.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()
                        && !((ListSelectionModel) e.getSource()).isSelectionEmpty()) {
                    if (e.getSource().equals(vista.editorialesTabla.getSelectionModel())) {
                        int row = vista.editorialesTabla.getSelectedRow();
                        vista.txtNombreEditorial.setText(String.valueOf(vista.editorialesTabla.getValueAt(row, 1)));
                        vista.txtEmail.setText(String.valueOf(vista.editorialesTabla.getValueAt(row, 2)));
                        vista.txtTelefono.setText(String.valueOf(vista.editorialesTabla.getValueAt(row, 3)));
                        vista.comboProductoEditorial.setSelectedItem(String.valueOf(vista.editorialesTabla.getValueAt(row, 4)));
                        vista.txtWeb.setText(String.valueOf(vista.editorialesTabla.getValueAt(row, 5)));
                    } else if (e.getValueIsAdjusting()
                            && ((ListSelectionModel) e.getSource()).isSelectionEmpty() && !refrescar) {
                        if (e.getSource().equals(vista.editorialesTabla.getSelectionModel())) {
                            borrarCamposEditoriales();
                        } else if (e.getSource().equals(vista.disenadoresTabla.getSelectionModel())) {
                            borrarCamposDisenadores();
                        } else if (e.getSource().equals(vista.juegosTabla.getSelectionModel())) {
                            borrarCamposJuegos();
                        }
                    }
                }
            }
        });

        vista.disenadoresTabla.setCellSelectionEnabled(true);
        ListSelectionModel cellSelectionModel2 =  vista.disenadoresTabla.getSelectionModel();
        cellSelectionModel2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        cellSelectionModel2.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()
                        && !((ListSelectionModel) e.getSource()).isSelectionEmpty()) {
                    if (e.getSource().equals(vista.disenadoresTabla.getSelectionModel())) {
                        int row = vista.disenadoresTabla.getSelectedRow();
                        vista.txtNombre.setText(String.valueOf(vista.disenadoresTabla.getValueAt(row, 1)));
                        vista.txtApellidos.setText(String.valueOf(vista.disenadoresTabla.getValueAt(row, 2)));
                        vista.fechanac.setDate((Date.valueOf(String.valueOf(vista.disenadoresTabla.getValueAt(row, 3)))).toLocalDate());
                        vista.txtPais.setText(String.valueOf(vista.disenadoresTabla.getValueAt(row, 4)));
                    } else if (e.getValueIsAdjusting()
                            && ((ListSelectionModel) e.getSource()).isSelectionEmpty() && !refrescar) {
                        if (e.getSource().equals(vista.editorialesTabla.getSelectionModel())) {
                            borrarCamposEditoriales();
                        } else if (e.getSource().equals(vista.disenadoresTabla.getSelectionModel())) {
                            borrarCamposDisenadores();
                        } else if (e.getSource().equals(vista.juegosTabla.getSelectionModel())) {
                            borrarCamposJuegos();
                        }
                    }
                }
            }
        });

        vista.juegosTabla.setCellSelectionEnabled(true);
        ListSelectionModel cellSelectionModel3 =  vista.juegosTabla.getSelectionModel();
        cellSelectionModel3.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        cellSelectionModel3.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()
                        && !((ListSelectionModel) e.getSource()).isSelectionEmpty()) {
                    if (e.getSource().equals(vista.juegosTabla.getSelectionModel())) {
                        int row = vista.juegosTabla.getSelectedRow();
                        vista.txtTitulo.setText(String.valueOf(vista.juegosTabla.getValueAt(row, 1)));
                        vista.txtDuracionMin.setText((String.valueOf(vista.juegosTabla.getValueAt(row, 3)).split("-"))[0]);
                        vista.txtDuracionMax.setText((String.valueOf(vista.juegosTabla.getValueAt(row, 3)).split("-"))[1]);
                        String[]jugadores = String.valueOf(vista.juegosTabla.getValueAt(row, 4)).split("-");
                        vista.txtJugadoresMin.setText(jugadores[0]);
                        if (jugadores.length==1) {
                            vista.txtJugadoresMax.setText(jugadores[0]);
                        } else {
                            vista.txtJugadoresMax.setText(jugadores[1]);
                        }
                        vista.comboDis.setSelectedItem(String.valueOf(vista.juegosTabla.getValueAt(row, 7)));
                        vista.comboEditorial.setSelectedItem(String.valueOf(vista.juegosTabla.getValueAt(row, 5)));
                        vista.comboGenero.setSelectedItem(String.valueOf(vista.juegosTabla.getValueAt(row, 6)));
                        vista.fechapublicacion.setDate((Date.valueOf(String.valueOf(vista.juegosTabla.getValueAt(row, 9)))).toLocalDate());
                        vista.txtIsbn.setText(String.valueOf(vista.juegosTabla.getValueAt(row, 2)));
                        vista.txtPrecio.setText(String.valueOf(vista.juegosTabla.getValueAt(row, 8)));
                    } else if (e.getValueIsAdjusting()
                            && ((ListSelectionModel) e.getSource()).isSelectionEmpty() && !refrescar) {
                        if (e.getSource().equals(vista.editorialesTabla.getSelectionModel())) {
                            borrarCamposEditoriales();
                        } else if (e.getSource().equals(vista.disenadoresTabla.getSelectionModel())) {
                            borrarCamposDisenadores();
                        } else if (e.getSource().equals(vista.juegosTabla.getSelectionModel())) {
                            borrarCamposJuegos();
                        }
                    }
                }
            }
        });
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting()
                && !((ListSelectionModel) e.getSource()).isSelectionEmpty()) {
            if (e.getSource().equals(vista.editorialesTabla.getSelectionModel())) {
                int row = vista.editorialesTabla.getSelectedRow();
                vista.txtNombreEditorial.setText(String.valueOf(vista.editorialesTabla.getValueAt(row, 1)));
                vista.txtEmail.setText(String.valueOf(vista.editorialesTabla.getValueAt(row, 2)));
                vista.txtTelefono.setText(String.valueOf(vista.editorialesTabla.getValueAt(row, 3)));
                vista.comboProductoEditorial.setSelectedItem(String.valueOf(vista.editorialesTabla.getValueAt(row, 4)));
                vista.txtWeb.setText(String.valueOf(vista.editorialesTabla.getValueAt(row, 5)));
            } else if (e.getSource().equals(vista.disenadoresTabla.getSelectionModel())) {
                int row = vista.disenadoresTabla.getSelectedRow();
                vista.txtNombre.setText(String.valueOf(vista.disenadoresTabla.getValueAt(row, 1)));
                vista.txtApellidos.setText(String.valueOf(vista.disenadoresTabla.getValueAt(row, 2)));
                vista.fechanac.setDate((Date.valueOf(String.valueOf(vista.disenadoresTabla.getValueAt(row, 3)))).toLocalDate());
                vista.txtPais.setText(String.valueOf(vista.disenadoresTabla.getValueAt(row, 4)));
            } else if (e.getSource().equals(vista.juegosTabla.getSelectionModel())) {
                int row = vista.juegosTabla.getSelectedRow();
                vista.txtTitulo.setText(String.valueOf(vista.juegosTabla.getValueAt(row, 1)));
                vista.txtDuracionMin.setText((String.valueOf(vista.juegosTabla.getValueAt(row, 3)).split("-"))[0]);
                vista.txtDuracionMax.setText((String.valueOf(vista.juegosTabla.getValueAt(row, 3)).split("-"))[1]);
                String[]jugadores = String.valueOf(vista.juegosTabla.getValueAt(row, 4)).split("-");
                vista.txtJugadoresMin.setText(jugadores[0]);
                if (jugadores.length==1) {
                    vista.txtJugadoresMax.setText(jugadores[0]);
                } else {
                    vista.txtJugadoresMax.setText(jugadores[1]);
                }
                vista.comboDis.setSelectedItem(String.valueOf(vista.juegosTabla.getValueAt(row, 7)));
                vista.comboEditorial.setSelectedItem(String.valueOf(vista.juegosTabla.getValueAt(row, 5)));
                vista.comboGenero.setSelectedItem(String.valueOf(vista.juegosTabla.getValueAt(row, 6)));
                vista.fechapublicacion.setDate((Date.valueOf(String.valueOf(vista.juegosTabla.getValueAt(row, 9)))).toLocalDate());
                vista.txtIsbn.setText(String.valueOf(vista.juegosTabla.getValueAt(row, 2)));
                vista.txtPrecio.setText(String.valueOf(vista.juegosTabla.getValueAt(row, 8)));
            } else if (e.getValueIsAdjusting()
                    && ((ListSelectionModel) e.getSource()).isSelectionEmpty() && !refrescar) {
                if (e.getSource().equals(vista.editorialesTabla.getSelectionModel())) {
                    borrarCamposEditoriales();
                } else if (e.getSource().equals(vista.disenadoresTabla.getSelectionModel())) {
                    borrarCamposDisenadores();
                } else if (e.getSource().equals(vista.juegosTabla.getSelectionModel())) {
                    borrarCamposJuegos();
                }
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command) {
            case "Opciones":
                vista.adminPasswordDialog.setVisible(true);
                break;
            case "Desconectar":
                modelo.desconectar();
                break;
            case "Salir":
                System.exit(0);
                break;
            case "abrirOpciones":
                if(String.valueOf(vista.adminPassword.getPassword()).equals(modelo.getAdminPassword())) {
                    vista.adminPassword.setText("");
                    vista.adminPasswordDialog.dispose();
                    vista.optionDialog.setVisible(true);
                } else {
                    Util.showErrorAlert("La contraseña introducida no es correcta.");
                }
                break;
            case "guardarOpciones":
                modelo.setPropValues(vista.optionDialog.txtIP.getText(), vista.optionDialog.txtUsuario.getText(),
                        String.valueOf(vista.optionDialog.pfPass), String.valueOf(vista.optionDialog.pfAdmin));
                vista.optionDialog.dispose();
                vista.dispose();
                new Controlador(new Modelo(), new Vista());
                break;
            case "anadirJuego":
                try {
                    if(comprobarJuegoVacio()) {
                        Util.showErrorAlert("Rellena todos los campos");
                    } else if (modelo.juegoIsbnYaExiste(vista.txtIsbn.getText())) {
                        Util.showErrorAlert("Ese ISBN ya existe.\nIntroduce un juego diferente.");
                        vista.juegosTabla.clearSelection();
                    } else {
                        modelo.insertarJuego(vista.txtTitulo.getText(), vista.txtIsbn.getText(),
                                Integer.valueOf(vista.txtDuracionMin.getText()),
                                Integer.valueOf(vista.txtDuracionMax.getText()),
                                Integer.valueOf(vista.txtJugadoresMin.getText()),
                                Integer.valueOf(vista.txtJugadoresMax.getText()),
                                String.valueOf(vista.comboEditorial.getSelectedItem()),
                                String.valueOf(vista.comboGenero.getSelectedItem()),
                                String.valueOf(vista.comboDis.getSelectedItem()),
                                Float.parseFloat(vista.txtPrecio.getText()),
                                vista.fechapublicacion.getDate());
                    }
                } catch (NumberFormatException nfe) {
                    Util.showErrorAlert("Introduce números en los campos que lo requieren.");
                    vista.juegosTabla.clearSelection();
                }
                borrarCamposJuegos();
                refrescarJuegos();
                break;
            case "modificarJuego":
                try {
                    if(comprobarJuegoVacio()) {
                        Util.showErrorAlert("Rellena todos los campos");
                        vista.juegosTabla.clearSelection();
                    } else {
                        modelo.modificarJuego(vista.txtTitulo.getText(), vista.txtIsbn.getText(),
                                Integer.valueOf(vista.txtDuracionMin.getText()),
                                Integer.valueOf(vista.txtDuracionMax.getText()),
                                Integer.valueOf(vista.txtJugadoresMin.getText()),
                                Integer.valueOf(vista.txtJugadoresMax.getText()),
                                String.valueOf(vista.comboEditorial.getSelectedItem()),
                                String.valueOf(vista.comboGenero.getSelectedItem()),
                                String.valueOf(vista.comboDis.getSelectedItem()),
                                Float.parseFloat(vista.txtPrecio.getText()),
                                vista.fechapublicacion.getDate(),
                                Integer.parseInt((String)vista.juegosTabla.getValueAt(vista.juegosTabla.getSelectedRow(), 0)));
                    }
                } catch (NumberFormatException nfe) {
                    Util.showErrorAlert("Introduce números en los campos que lo requieren");
                    vista.juegosTabla.clearSelection();
                }
                borrarCamposJuegos();
                refrescarJuegos();
                break;
            case "eliminarJuego":
                modelo.borrarJuego(Integer.parseInt((String)vista.juegosTabla.getValueAt(vista.juegosTabla.getSelectedRow(),0)));
                borrarCamposJuegos();
                refrescarJuegos();
                break;
            case "anadirDis":
                try {
                    if (comprobarDisVacio()) {
                        Util.showErrorAlert("Rellena todos los campos");
                        vista.disenadoresTabla.clearSelection();
                    } else if (modelo.disenadorNombreYaExiste(vista.txtNombre.getText(), vista.txtApellidos.getText())) {
                        Util.showErrorAlert("Ese nombre ya existe.\nIntroduce un diseñador diferente");
                        vista.disenadoresTabla.clearSelection();
                    } else {
                        modelo.insertarDisenador(vista.txtNombre.getText(),
                                vista.txtApellidos.getText(),
                                vista.fechanac.getDate(),
                                vista.txtPais.getText());
                        refrescarDisenadores();
                    }
                } catch (NumberFormatException nfe) {
                    Util.showErrorAlert("Introduce números en los campos que lo requieren");
                    vista.disenadoresTabla.clearSelection();
                }
                refrescarDisenadores();
                borrarCamposDisenadores();
                break;
            case "modificarDis":
                try {
                    if(comprobarDisVacio()) {
                        Util.showErrorAlert("Rellena todos los campos");
                        vista.disenadoresTabla.clearSelection();
                    } else {
                        modelo.modificarDisenador(vista.txtNombre.getText(), vista.txtApellidos.getText(),
                                vista.fechanac.getDate(), vista.txtPais.getText(),
                                Integer.parseInt((String)vista.disenadoresTabla.getValueAt(vista.disenadoresTabla.getSelectedRow(), 0)));
                        refrescarDisenadores();
                    }
                } catch (NumberFormatException nfe) {
                    Util.showErrorAlert("Introduce números en los campos que lo requieren");
                    vista.juegosTabla.clearSelection();
                }
                borrarCamposDisenadores();
                refrescarDisenadores();
                break;
            case "eliminarDis":
                modelo.borrarDisenador(Integer.parseInt((String)vista.disenadoresTabla.getValueAt(vista.disenadoresTabla.getSelectedRow(),0)));
                borrarCamposDisenadores();
                refrescarDisenadores();
                refrescarJuegos();
                break;
            case "anadirEditorial":
                try {
                    if (comprobarEditorialVacia()) {
                        Util.showErrorAlert("Rellena todos los campos");
                        vista.editorialesTabla.clearSelection();
                    } else if (modelo.editorialNombreYaExiste(vista.txtNombreEditorial.getText())) {
                        Util.showErrorAlert("Ese nombre ya existe.\nIntroduce una editorial diferente.");
                        vista.editorialesTabla.clearSelection();
                    } else {
                        modelo.insertarEditorial(vista.txtNombreEditorial.getText(), vista.txtEmail.getText(),
                                vista.txtTelefono.getText(),
                                (String) vista.comboProductoEditorial.getSelectedItem(),
                                vista.txtWeb.getText());
                        refrescarEditoriales();
                    }
                } catch (NumberFormatException nfe) {
                    Util.showErrorAlert("Introduce números en los campos que lo requieren");
                    vista.editorialesTabla.clearSelection();
                }
                borrarCamposEditoriales();
                refrescarEditoriales();
                break;
            case "modificarEditorial":
                try {
                    if(comprobarEditorialVacia()) {
                        Util.showErrorAlert("Rellena todos los campos");
                        vista.editorialesTabla.clearSelection();
                    } else {
                        modelo.modificarEditorial(vista.txtNombreEditorial.getText(), vista.txtEmail.getText(),
                                vista.txtTelefono.getText(), String.valueOf(vista.comboProductoEditorial.getSelectedItem()),
                                vista.txtWeb.getText(), Integer.parseInt((String)vista.editorialesTabla.getValueAt(vista.editorialesTabla.getSelectedRow(), 0)));
                        refrescarEditoriales();
                    }
                } catch (NumberFormatException nfe) {
                    Util.showErrorAlert("Introduce números en los campos que lo requieren");
                    vista.editorialesTabla.clearSelection();
                }
                borrarCamposEditoriales();
                refrescarEditoriales();
                break;
            case "eliminarEditorial":
                modelo.borrarEditorial(Integer.parseInt((String)vista.editorialesTabla.getValueAt(vista.editorialesTabla.getSelectedRow(),0)));
                borrarCamposEditoriales();
                refrescarEditoriales();
                refrescarJuegos();
                break;
        }
    }

    @Override
    public void windowClosing(WindowEvent e) {
        System.exit(0);
    }

    private void refrescarEditoriales() {
        try {
            vista.editorialesTabla.setModel(construirTableModel(modelo.consultarEditorial(), vista.dtmEditoriales));
            vista.comboEditorial.removeAllItems();
            for (int i = 0; i < vista.dtmEditoriales.getRowCount(); i++) {
                vista.comboEditorial.addItem(vista.dtmEditoriales.getValueAt(i, 0) + " - " +
                        vista.dtmEditoriales.getValueAt(i, 1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        int editoriales = modelo.contarEditoriales();
        if(editoriales==1) {
            vista.numeroEditoriales.setText(modelo.contarEditoriales() + " editorial");
        } else {
            vista.numeroEditoriales.setText(modelo.contarEditoriales() + " editoriales");
        }
    }

    private void refrescarDisenadores() {
        try {
            vista.disenadoresTabla.setModel(construirTableModel(modelo.consultarDisenador(), vista.dtmDisenadores));
            vista.comboDis.removeAllItems();
            for (int i = 0; i < vista.dtmDisenadores.getRowCount(); i++) {
                vista.comboDis.addItem(vista.dtmDisenadores.getValueAt(i, 0) + " - " +
                        vista.dtmDisenadores.getValueAt(i, 2) + ", " +
                        vista.dtmDisenadores.getValueAt(i, 1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        int disenadores = modelo.contarDisenadores();
        if(disenadores==1) {
            vista.numeroDisenadores.setText(disenadores + " diseñador");
        } else {
            vista.numeroDisenadores.setText(disenadores + " diseñadores");
        }
    }

    private void refrescarJuegos() {
        try {
            vista.juegosTabla.setModel(construirTableModel(modelo.consultarJuegos(), vista.dtmJuegos));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        int juegos = modelo.contarJuegos();
        if(juegos==1) {
            vista.numeroJuegos.setText(modelo.contarJuegos() + " juego");
        } else {
            vista.numeroJuegos.setText(modelo.contarJuegos() + " juegos");
        }
    }

    private DefaultTableModel construirTableModel(ResultSet rs, DefaultTableModel dtm) throws SQLException {
        ResultSetMetaData metaData = rs.getMetaData();

        Vector<String> columnNames = new Vector<>();
        int columnCount = metaData.getColumnCount();
        for (int column = 1; column <= columnCount; column++) {
            columnNames.add(metaData.getColumnName(column));
        }

        Vector<Vector<Object>> data = new Vector<>();
        setDataVector(rs, columnCount, data);

        dtm.setDataVector(data, columnNames);
        return dtm;
    }

    private void setDataVector(ResultSet rs, int columnCount, Vector<Vector<Object>> data) throws SQLException {
        while (rs.next()) {
            Vector<Object> vector = new Vector<>();
            for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                vector.add(rs.getObject(columnIndex));
            }
            data.add(vector);
        }
    }

    private void setOptions() {
        vista.optionDialog.txtIP.setText(modelo.getIp());
        vista.optionDialog.txtUsuario.setText(modelo.getUser());
        vista.optionDialog.pfPass.setText(modelo.getPassword());
        vista.optionDialog.pfAdmin.setText(modelo.getAdminPassword());
    }

    private void borrarCamposJuegos() {
        vista.comboEditorial.setSelectedIndex(-1);
        vista.comboDis.setSelectedIndex(-1);
        vista.comboGenero.setSelectedIndex(-1);
        vista.txtTitulo.setText("");
        vista.txtIsbn.setText("");
        vista.txtDuracionMax.setText("");
        vista.txtDuracionMin.setText("");
        vista.txtJugadoresMax.setText("");
        vista.txtJugadoresMin.setText("");
        vista.txtPrecio.setText("");
        vista.fechapublicacion.setText("");
    }

    private void borrarCamposDisenadores() {
        vista.txtNombre.setText("");
        vista.txtApellidos.setText("");
        vista.txtPais.setText("");
        vista.fechanac.setText("");
    }

    private void borrarCamposEditoriales() {
        vista.txtNombreEditorial.setText("");
        vista.txtEmail.setText("");
        vista.txtTelefono.setText("");
        vista.comboProductoEditorial.setSelectedIndex(-1);
        vista.txtWeb.setText("");
    }

    private boolean comprobarJuegoVacio() {
        return vista.txtTitulo.getText().isEmpty() ||
                vista.txtPrecio.getText().isEmpty() ||
                vista.txtIsbn.getText().isEmpty() ||
                vista.txtDuracionMin.getText().isEmpty() ||
                vista.txtDuracionMax.getText().isEmpty() ||
                vista.txtJugadoresMin.getText().isEmpty() ||
                vista.txtJugadoresMax.getText().isEmpty() ||
                vista.comboGenero.getSelectedIndex() == -1 ||
                vista.comboDis.getSelectedIndex() == -1 ||
                vista.comboEditorial.getSelectedIndex() == -1 ||
                vista.fechapublicacion.getText().isEmpty();
    }

    private boolean comprobarDisVacio() {
        return vista.txtApellidos.getText().isEmpty() ||
                vista.txtNombre.getText().isEmpty() ||
                vista.txtPais.getText().isEmpty() ||
                vista.fechanac.getText().isEmpty();
    }

    private boolean comprobarEditorialVacia() {
        return vista.txtNombreEditorial.getText().isEmpty() ||
                vista.txtEmail.getText().isEmpty() ||
                vista.txtTelefono.getText().isEmpty() ||
                vista.comboProductoEditorial.getSelectedIndex() == -1 ||
                vista.txtWeb.getText().isEmpty();
    }

    @Override
    public void itemStateChanged(ItemEvent e) {

    }

    @Override
    public void windowOpened(WindowEvent e) {

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
}
