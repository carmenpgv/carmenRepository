package com.carmenpg.juegosdemesahibernate.gui;

import com.carmenpg.juegosdemesahibernate.Disenador;
import com.carmenpg.juegosdemesahibernate.Editorial;
import com.carmenpg.juegosdemesahibernate.Juego;
import com.carmenpg.juegosdemesahibernate.Tienda;
import org.omg.CORBA.portable.ValueInputStream;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Controlador implements ActionListener, ListSelectionListener {
    private Vista vista;
    private Modelo modelo;
    public Controlador(Vista vista, Modelo modelo) {
        this.vista=vista;
        this.modelo=modelo;

        addActionListeners(this);
        addListSelectionListeners(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando=e.getActionCommand();
        switch (comando) {
            case "Salir":
                modelo.desconectar();
                System.exit(0);
                break;
            case "Conectar":
                vista.conexionItem.setEnabled(false);
                modelo.conectar();
                break;
            case "AñadirDiseñador":
                Disenador nuevoDisenador = new Disenador();
                nuevoDisenador.setNombre(vista.txtNombreDis.getText());
                nuevoDisenador.setApellidos(vista.txtApellidosDis.getText());
                nuevoDisenador.setFechanacimiento(Timestamp.valueOf(vista.txtFechaDis.getDateTimePermissive()));
                nuevoDisenador.setPais(vista.txtPaisDis.getText());
                modelo.anadirDisenador(nuevoDisenador);
                break;
            case "AñadirEditorial":
                Editorial nuevaEditorial = new Editorial();
                nuevaEditorial.setEditorial(vista.txtNombreEdit.getText());
                nuevaEditorial.setProductos(vista.txtProductosEdit.getText());
                nuevaEditorial.setEmail(vista.txtEmailEdit.getText());
                nuevaEditorial.setTelefono(vista.txtTelefonoEdit.getText());
                nuevaEditorial.setWeb(vista.txtWebEdit.getText());
                modelo.anadirEditorial(nuevaEditorial);
                break;
            case "AñadirTienda":
                Tienda nuevaTienda = new Tienda();
                nuevaTienda.setDireccion(vista.txtDireccionTienda.getText());

                modelo.anadirTienda(nuevaTienda);
                break;
            case "AñadirJuego":
                Juego nuevoJuego = new Juego();
                nuevoJuego.setTitulo(vista.txtTituloJuego.getText());
                nuevoJuego.setIsbn(vista.txtIsbnJuego.getText());
                nuevoJuego.setGenero(vista.txtGeneroJuego.getText());
                nuevoJuego.setDuracion(vista.txtDuracionJuego.getText());
                nuevoJuego.setJugadores(vista.txtJugadoresJuego.getText());
                nuevoJuego.setPrecio(Float.valueOf(vista.txtPrecioJuego.getText()));
                nuevoJuego.setFechapublicacion(Timestamp.valueOf(vista.txtFechaJuego.getDateTimePermissive()));
                for (Disenador unDisenador: modelo.getDisenadores()) {
                    String nombre = unDisenador.getNombre() + " " + unDisenador.getApellidos();
                    if(nombre.equals(String.valueOf(vista.comboDis.getSelectedItem()))) {
                        nuevoJuego.setDisenador(unDisenador);
                    }
                }
                for (Editorial unaEditorial: modelo.getEditoriales()) {
                    String nombre = unaEditorial.getEditorial();
                    if(nombre.equals(String.valueOf(vista.comboEdit.getSelectedItem()))) {
                        nuevoJuego.setEditorial(unaEditorial);
                    }
                }
                modelo.anadirJuego(nuevoJuego);
                break;
            case "ModificarDiseñador":
                Disenador disenadorSeleccion = (Disenador) vista.listDisenadores.getSelectedValue();
                disenadorSeleccion.setNombre(vista.txtNombreDis.getText());
                disenadorSeleccion.setApellidos(vista.txtApellidosDis.getText());
                disenadorSeleccion.setPais(vista.txtPaisDis.getText());
                disenadorSeleccion.setFechanacimiento(Timestamp.valueOf(vista.txtFechaDis.getDateTimePermissive()));
                modelo.modificarDisenador(disenadorSeleccion);
                break;
            case "ModificarEditorial":
                Editorial editorialSeleccion = (Editorial) vista.listEditoriales.getSelectedValue();
                editorialSeleccion.setEditorial(vista.txtNombreEdit.getText());
                editorialSeleccion.setProductos(vista.txtProductosEdit.getText());
                editorialSeleccion.setEmail(vista.txtEmailEdit.getText());
                editorialSeleccion.setTelefono(vista.txtTelefonoEdit.getText());
                editorialSeleccion.setWeb(vista.txtWebEdit.getText());
                modelo.modificarEditorial(editorialSeleccion);
                break;
            case "ModificarTienda":
                Tienda tiendaSeleccion = (Tienda) vista.listTiendas.getSelectedValue();
                tiendaSeleccion.setDireccion(vista.txtDireccionTienda.getText());
                modelo.modificarTienda(tiendaSeleccion);
                break;
            case "ModificarJuego":
                Juego juegoSeleccion = (Juego) vista.listJuegos.getSelectedValue();
                juegoSeleccion.setTitulo(vista.txtTituloJuego.getText());
                juegoSeleccion.setIsbn(vista.txtIsbnJuego.getText());
                juegoSeleccion.setGenero(vista.txtGeneroJuego.getText());
                juegoSeleccion.setDuracion(vista.txtDuracionJuego.getText());
                juegoSeleccion.setJugadores(vista.txtJugadoresJuego.getText());
                juegoSeleccion.setPrecio(Float.valueOf(vista.txtPrecioJuego.getText()));
                juegoSeleccion.setFechapublicacion(Timestamp.valueOf(vista.txtFechaJuego.getDateTimePermissive()));
                for (Disenador unDisenador: modelo.getDisenadores()) {
                    String nombre = unDisenador.getNombre() + " " + unDisenador.getApellidos();
                    if(nombre.equals(String.valueOf(vista.comboDis.getSelectedItem()))) {
                        juegoSeleccion.setDisenador(unDisenador);
                    }
                }
                for (Editorial unaEditorial: modelo.getEditoriales()) {
                    String nombre = unaEditorial.getEditorial();
                    if(nombre.equals(String.valueOf(vista.comboEdit.getSelectedItem()))) {
                        juegoSeleccion.setEditorial(unaEditorial);
                    }
                }
                modelo.modificarJuego(juegoSeleccion);
                break;
            case "EliminarDiseñador":
                Disenador disenadorBorrado = (Disenador) vista.listDisenadores.getSelectedValue();
                modelo.borrarDisenador(disenadorBorrado);
                break;
            case "EliminarEditorial":
                Editorial editorialBorrada = (Editorial) vista.listEditoriales.getSelectedValue();
                modelo.borrarEditorial(editorialBorrada);
                break;
            case "EliminarTienda":
                Tienda tiendaBorrada = (Tienda) vista.listTiendas.getSelectedValue();
                modelo.borrarTienda(tiendaBorrada);
                break;
            case "EliminarJuego":
                Juego juegoBorrado = (Juego) vista.listJuegos.getSelectedValue();
                modelo.borrarJuego(juegoBorrado);
                break;
        }
        listarTodo();
    }

    private void listarDisenadores(ArrayList<Disenador>listaDisenadores) {
        vista.dlmDisenadores.clear();
        for(Disenador unDisenador: listaDisenadores) {
            vista.dlmDisenadores.addElement(unDisenador);
        }
        vista.comboDis.removeAllItems();
        for (int i = 0; i < vista.dlmDisenadores.getSize(); i++) {
            vista.comboDis.addItem(((Disenador) vista.dlmDisenadores.getElementAt(i)).getNombre() + " " + ((Disenador) vista.dlmDisenadores.getElementAt(i)).getApellidos());
        }
    }
    private void listarEditoriales(ArrayList<Editorial>listaEditoriales) {
        vista.dlmEditoriales.clear();
        for(Editorial unaEditorial: listaEditoriales) {
            vista.dlmEditoriales.addElement(unaEditorial);
        }
        vista.comboEdit.removeAllItems();
        for (int i = 0; i < vista.dlmEditoriales.getSize(); i++) {
            vista.comboEdit.addItem(((Editorial)vista.dlmEditoriales.getElementAt(i)).getEditorial());
        }
    }
    private void listarJuegos(ArrayList<Juego>listaJuegos) {
        vista.dlmJuegos.clear();
        for(Juego unJuego: listaJuegos) {
            vista.dlmJuegos.addElement(unJuego);
        }
    }
    private void listarTiendas(ArrayList<Tienda>listaTiendas) {
        vista.dlmTiendas.clear();
        for(Tienda unaTienda: listaTiendas) {
            vista.dlmTiendas.addElement(unaTienda);
        }
    }
    private void listarJuegosDisenador(List<Juego>listaJuegos) {
       vista.dlmJuegosDisenador.clear();
        for(Juego unJuego: listaJuegos) {
            vista.dlmJuegosDisenador.addElement(unJuego);
        }
    }
    private void listarTodo() {
        listarTiendas(modelo.getTiendas());
        listarDisenadores(modelo.getDisenadores());
        listarEditoriales(modelo.getEditoriales());
        listarJuegos(modelo.getJuegos());
    }

    private void addActionListeners(ActionListener listener) {
        vista.conexionItem.addActionListener(listener);
        vista.btnAnadirDis.addActionListener(listener);
        vista.btnAnadirEdit.addActionListener(listener);
        vista.btnAnadirJuego.addActionListener(listener);
        vista.btnAnadirTienda.addActionListener(listener);
        vista.salirItem.addActionListener(listener);
        vista.btnEliminarDis.addActionListener(listener);
        vista.btnEliminarEdit.addActionListener(listener);
        vista.btnEliminarJuego.addActionListener(listener);
        vista.btnEliminarTienda.addActionListener(listener);
        vista.btnModificarDis.addActionListener(listener);
        vista.btnModificarEdit.addActionListener(listener);
        vista.btnModificarJuego.addActionListener(listener);
        vista.btnModificarTienda.addActionListener(listener);
    }
    private void  addListSelectionListeners(ListSelectionListener listener) {
        vista.listJuegos.addListSelectionListener(listener);
        vista.listDisenadores.addListSelectionListener(listener);
        vista.listEditoriales.addListSelectionListener(listener);
        vista.listTiendas.addListSelectionListener(listener);
    }


    @Override
    public void valueChanged(ListSelectionEvent e) {
        if(e.getValueIsAdjusting()){
            if(e.getSource() == vista.listDisenadores) {
                Disenador disenadorSeleccion = (Disenador) vista.listDisenadores.getSelectedValue();
                vista.txtNombreDis.setText(String.valueOf(disenadorSeleccion.getNombre()));
                vista.txtApellidosDis.setText(String.valueOf(disenadorSeleccion.getApellidos()));
                vista.txtPaisDis.setText(String.valueOf(disenadorSeleccion.getPais()));
                vista.txtFechaDis.setDateTimePermissive(disenadorSeleccion.getFechanacimiento().toLocalDateTime());
                listarJuegosDisenador(modelo.getJuegosDisenador(disenadorSeleccion));
            }else if(e.getSource() == vista.listEditoriales) {
                Editorial editorialSeleccion = (Editorial) vista.listEditoriales.getSelectedValue();
                vista.txtNombreEdit.setText(String.valueOf(editorialSeleccion.getEditorial()));
                vista.txtProductosEdit.setText(String.valueOf(editorialSeleccion.getProductos()));
                vista.txtEmailEdit.setText(String.valueOf(editorialSeleccion.getEmail()));
                vista.txtTelefonoEdit.setText(String.valueOf(editorialSeleccion.getTelefono()));
                vista.txtWebEdit.setText(String.valueOf(editorialSeleccion.getWeb()));
            }else if(e.getSource() == vista.listJuegos) {
                Juego juegoSeleccion = (Juego) vista.listJuegos.getSelectedValue();
                vista.txtTituloJuego.setText(String.valueOf(juegoSeleccion.getTitulo()));
                vista.txtIsbnJuego.setText(String.valueOf(juegoSeleccion.getIsbn()));
                vista.txtGeneroJuego.setText(String.valueOf(juegoSeleccion.getGenero()));
                vista.txtDuracionJuego.setText(String.valueOf(juegoSeleccion.getDuracion()));
                vista.txtJugadoresJuego.setText(String.valueOf(juegoSeleccion.getJugadores()));
                vista.txtPrecioJuego.setText(String.valueOf(juegoSeleccion.getPrecio()));
                vista.txtFechaJuego.setDateTimePermissive(juegoSeleccion.getFechapublicacion().toLocalDateTime());
                vista.comboDis.setSelectedItem(String.valueOf(juegoSeleccion.getDisenador()));
                vista.comboEdit.setSelectedItem(String.valueOf(juegoSeleccion.getDisenador()));
            }else if(e.getSource() == vista.listTiendas){
                Tienda tiendaSeleccion = (Tienda) vista.listTiendas.getSelectedValue();
                vista.txtDireccionTienda.setText(String.valueOf(tiendaSeleccion.getDireccion()));
            }
        }
    }
}
