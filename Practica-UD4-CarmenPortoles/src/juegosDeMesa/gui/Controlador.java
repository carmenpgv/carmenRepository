package juegosDeMesa.gui;

import juegosDeMesa.base.Disenador;
import juegosDeMesa.base.Juego;
import juegosDeMesa.util.Util;
import org.bson.types.ObjectId;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controlador implements ActionListener, ListSelectionListener {
    private Modelo modelo;
    private Vista vista;

    public Controlador(Modelo modelo, Vista vista) {
        this.vista = vista;
        this.modelo = modelo;

        addActionListeners(this);
        addListSelectionListeners(this);

        try {
            modelo.conectar();
            vista.itemConectar.setText("Desconectar");
            vista.setTitle("Excelsior - <CONECTADO>");
            setBotonesActivados(true);
            listarJuegos();
            listarDisenadores();
        } catch (Exception ex) {
            Util.mostrarMensajeError("No se ha podido establecer conexión con el servidor.");
        }

    }
    private void addActionListeners(ActionListener listener){
        vista.btnAddJuegos.addActionListener(listener);
        vista.btnModifyJuegos.addActionListener(listener);
        vista.btnDeleteJuegos.addActionListener(listener);
        vista.btnAddDis.addActionListener(listener);
        vista.btnModifyDis.addActionListener(listener);
        vista.btnDeleteDis.addActionListener(listener);

        vista.itemConectar.addActionListener(listener);
        vista.itemSalir.addActionListener(listener);
    }

    private void addListSelectionListeners(ListSelectionListener listener){
        vista.listJuegos.addListSelectionListener(listener);
        vista.listDis.addListSelectionListener(listener);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "conexion":
                try {
                    if (modelo.getCliente() == null) {
                        modelo.conectar();
                        vista.itemConectar.setText("Desconectar");
                        vista.setTitle("Excelsior - <CONECTADO>");
                        setBotonesActivados(true);
                    } else {
                        modelo.desconectar();
                        vista.itemConectar.setText("Conectar");
                        vista.setTitle("Excelsior - <SIN CONEXION>");
                        setBotonesActivados(false);
                        vista.dlmJuegos.clear();
                        vista.dlmDisenadores.clear();
                        limpiarCamposJuego();
                        limpiarCamposDisenador();
                    }
                } catch (Exception ex) {
                    Util.mostrarMensajeError("No se pudo establecer conexión con el servidor.");
                }
                break;

            case "salir":
                modelo.desconectar();
                System.exit(0);
                break;

            case "addJuego":
                if (comprobarCamposJuego()) {
                    ObjectId dis = null;
                    if (vista.comboDis.getSelectedItem() != null){
                        for (Disenador unDisenador: modelo.getDisenadores()) {
                            String nombre = unDisenador.getNombre() + " " + unDisenador.getApellidos();
                            if(nombre.equals(String.valueOf(vista.comboDis.getSelectedItem()))) {
                                dis = unDisenador.getId();
                            }
                        }
                    }
                    modelo.guardarObjeto(new Juego(vista.txtNombreJuego.getText(),
                            vista.txtfechaJuego.getDate(),
                            Float.parseFloat(vista.txtPrecioJuego.getText()), dis));
                    limpiarCamposJuego();
                } else {
                    Util.mostrarMensajeError("No se ha podido insertar el juego en la base de datos.\n" +
                            "Comprueba que los campos contengan el tipo de dato requerido.");
                }
                listarJuegos();
                break;

            case "modJuego":
                if (vista.listJuegos.getSelectedValue() != null) {
                    if (comprobarCamposJuego()) {
                        Juego juego = vista.listJuegos.getSelectedValue();
                        juego.setNombre(vista.txtNombreJuego.getText());
                        juego.setFechapubli(vista.txtfechaJuego.getDate());
                        juego.setPrecio(Float.parseFloat(vista.txtPrecioJuego.getText()));
                        if (vista.comboDis.getSelectedItem() != null){
                            for (Disenador unDisenador: modelo.getDisenadores()) {
                                String nombre = unDisenador.getNombre() + " " + unDisenador.getApellidos();
                                if(nombre.equals(String.valueOf(vista.comboDis.getSelectedItem()))) {
                                    juego.setDisenador(unDisenador.getId());
                                }
                            }
                        }
                        modelo.modificarObjeto(juego);
                        limpiarCamposJuego();
                    } else {
                        Util.mostrarMensajeError("No se ha podido modificar el juego en la base de datos.\n" +
                                "Comprueba que los campos contengan el tipo de dato requerido.");
                    }
                    listarJuegos();
                } else {
                    Util.mostrarMensajeError("No has seleccionado ningún juego.");
                }
                listarJuegos();
                break;

            case "delJuego":
                if (vista.listJuegos.getSelectedValue() != null) {
                    modelo.eliminarObjeto(vista.listJuegos.getSelectedValue());
                    listarJuegos();
                    limpiarCamposJuego();
                } else {
                    Util.mostrarMensajeError("No hay ningún juego seleccionado.");
                }
                listarJuegos();
                break;

            case "addDis":
                if (comprobarCamposDisenador()) {
                    modelo.guardarObjeto(new Disenador(vista.txtNombreDis.getText(),
                            vista.txtApellidosDis.getText(),
                            vista.txtFechaDis.getDate()));
                    limpiarCamposDisenador();
                } else {
                    Util.mostrarMensajeError("No se ha podido insertar el diseñador en la base de datos.\n" +
                            "Compruebe que los campos contengan el tipo de dato requerido.");
                }
                listarDisenadores();
                break;

            case "modDis":
                if (vista.listDis.getSelectedValue() != null) {
                    if (comprobarCamposDisenador()) {
                        Disenador disenador = vista.listDis.getSelectedValue();
                        disenador.setNombre(vista.txtNombreDis.getText());
                        disenador.setApellidos(vista.txtApellidosDis.getText());
                        disenador.setFechanac(vista.txtFechaDis.getDate());
                        modelo.modificarObjeto(disenador);
                        limpiarCamposDisenador();
                    } else {
                        Util.mostrarMensajeError("No se ha podido modificar el disenador en la base de datos.\n" +
                                "Comprueba que los campos contengan el tipo de dato requerido.");
                    }
                    listarDisenadores();
                } else {
                    Util.mostrarMensajeError("No hay ningún disenador seleccionado.");
                }
                break;

            case "delDis":
                if (vista.listDis.getSelectedValue() != null) {
                    modelo.eliminarObjeto(vista.listDis.getSelectedValue());
                    listarDisenadores();
                    limpiarCamposDisenador();
                } else {
                    Util.mostrarMensajeError("No hay ningún disenador seleccionado.");
                }
                break;
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (e.getSource() == vista.listJuegos) {
            if (vista.listJuegos.getSelectedValue() != null) {
                Juego juego = vista.listJuegos.getSelectedValue();
                vista.txtNombreJuego.setText(juego.getNombre());
                vista.txtfechaJuego.setDate(juego.getFechapubli());
                vista.txtPrecioJuego.setText(String.valueOf(juego.getPrecio()));
                for (Disenador unDisenador: modelo.getDisenadores()) {
                    ObjectId id = unDisenador.getId();
                    if(id.equals(juego.getDisenador())) {
                        vista.comboDis.setSelectedItem(unDisenador.getNombre() + " " + unDisenador.getApellidos());
                    }
                }
            }
        } else if (e.getSource() == vista.listDis) {
            if (vista.listDis.getSelectedValue() != null) {
                Disenador disenador = vista.listDis.getSelectedValue();
                vista.txtNombreDis.setText(disenador.getNombre());
                vista.txtApellidosDis.setText(disenador.getApellidos());
                vista.txtFechaDis.setDate(disenador.getFechanac());
            }
        }
    }

    private boolean comprobarCamposJuego() {
        return !vista.txtNombreJuego.getText().isEmpty() &&
                !vista.txtfechaJuego.getText().isEmpty() &&
                !vista.txtPrecioJuego.getText().isEmpty() &&
                comprobarFloat(vista.txtPrecioJuego.getText());
    }

    private boolean comprobarCamposDisenador() {
        return !vista.txtNombreDis.getText().isEmpty() &&
                !vista.txtApellidosDis.getText().isEmpty() &&
                !vista.txtFechaDis.getText().isEmpty();
    }

    private void limpiarCamposJuego() {
        vista.txtNombreJuego.setText("");
        vista.txtfechaJuego.clear();
        vista.txtPrecioJuego.setText("");
    }

    private void limpiarCamposDisenador() {
        vista.txtNombreDis.setText("");
        vista.txtApellidosDis.setText("");
        vista.txtFechaDis.clear();
    }

    private boolean comprobarFloat(String txt) {
        try {
            Float.parseFloat(txt);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }

    private void listarJuegos() {
        vista.dlmJuegos.clear();
        for (Juego juego : modelo.getJuegos()) {
            vista.dlmJuegos.addElement(juego);
        }
    }

    private void listarDisenadores() {
        vista.dlmDisenadores.clear();
        for (Disenador dis : modelo.getDisenadores()) {
            vista.dlmDisenadores.addElement(dis);
        }
        vista.comboDis.removeAllItems();
        for (int i = 0; i < vista.dlmDisenadores.getSize(); i++) {
            vista.comboDis.addItem(((Disenador) vista.dlmDisenadores.getElementAt(i)).getNombre() + " " + ((Disenador) vista.dlmDisenadores.getElementAt(i)).getApellidos());
        }
    }

    private void setBotonesActivados(boolean activados) {
        vista.btnAddJuegos.setEnabled(activados);
        vista.btnModifyJuegos.setEnabled(activados);
        vista.btnDeleteJuegos.setEnabled(activados);
        vista.btnAddDis.setEnabled(activados);
        vista.btnModifyDis.setEnabled(activados);
        vista.btnDeleteDis.setEnabled(activados);
    }

}
