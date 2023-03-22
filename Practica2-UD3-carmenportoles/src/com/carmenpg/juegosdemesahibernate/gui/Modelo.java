package com.carmenpg.juegosdemesahibernate.gui;

import com.carmenpg.juegosdemesahibernate.Disenador;
import com.carmenpg.juegosdemesahibernate.Editorial;
import com.carmenpg.juegosdemesahibernate.Juego;
import com.carmenpg.juegosdemesahibernate.Tienda;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import javax.persistence.Query;
import java.util.ArrayList;

public class Modelo {
    SessionFactory sessionFactory;
    public void desconectar() {
        if (sessionFactory!=null && sessionFactory.isOpen()) {
            sessionFactory.close();
        }
    }
    public void conectar() {
        Configuration configuracion = new Configuration();
        configuracion.configure("hibernate.cfg.xml");

        configuracion.addAnnotatedClass(Juego.class);
        configuracion.addAnnotatedClass(Editorial.class);
        configuracion.addAnnotatedClass(Disenador.class);
        configuracion.addAnnotatedClass(Tienda.class);

        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().applySettings(
                configuracion.getProperties()).build();
        sessionFactory=configuracion.buildSessionFactory(ssr);
    }
    public void anadirDisenador(Disenador nuevoDisenador) {
        Session sesion = sessionFactory.openSession();
        sesion.beginTransaction();
        sesion.save(nuevoDisenador);
        sesion.getTransaction().commit();
        sesion.close();
    }
    public void anadirEditorial(Editorial nuevaEditorial) {
        Session sesion = sessionFactory.openSession();
        sesion.beginTransaction();
        sesion.save(nuevaEditorial);
        sesion.getTransaction().commit();
        sesion.close();
    }
    public void anadirTienda(Tienda nuevaTienda) {
        Session sesion = sessionFactory.openSession();
        sesion.beginTransaction();
        sesion.save(nuevaTienda);
        sesion.getTransaction().commit();
        sesion.close();
    }
    public void anadirJuego(Juego nuevoJuego) {
        Session sesion = sessionFactory.openSession();
        sesion.beginTransaction();
        sesion.save(nuevoJuego);
        sesion.getTransaction().commit();
        sesion.close();
    }
    public ArrayList<Disenador> getDisenadores() {
        Session sesion = sessionFactory.openSession();
        Query query = sesion.createQuery("FROM Disenador");
        ArrayList<Disenador> listaDisenadores = (ArrayList<Disenador>) query.getResultList();
        sesion.close();
        return listaDisenadores;
    }
    public ArrayList<Editorial> getEditoriales() {
        Session sesion = sessionFactory.openSession();
        Query query = sesion.createQuery("FROM Editorial");
        ArrayList<Editorial> listaEditoriales = (ArrayList<Editorial>) query.getResultList();
        sesion.close();
        return listaEditoriales;
    }
    public ArrayList<Tienda> getTiendas() {
        Session sesion = sessionFactory.openSession();
        Query query = sesion.createQuery("FROM Tienda");
        ArrayList<Tienda> listaTienda = (ArrayList<Tienda>) query.getResultList();
        sesion.close();
        return listaTienda;
    }
    public ArrayList<Juego> getJuegos() {
        Session sesion = sessionFactory.openSession();
        Query query = sesion.createQuery("FROM Juego");
        ArrayList<Juego> listaJuego = (ArrayList<Juego>) query.getResultList();
        sesion.close();
        return listaJuego;
    }
    public void modificarDisenador(Disenador disenadorSeleccion) {
        Session sesion = sessionFactory.openSession();
        sesion.beginTransaction();
        sesion.saveOrUpdate(disenadorSeleccion);
        sesion.getTransaction().commit();
        sesion.close();
    }
    public void modificarEditorial(Editorial editorialSeleccion) {
        Session sesion = sessionFactory.openSession();
        sesion.beginTransaction();
        sesion.saveOrUpdate(editorialSeleccion);
        sesion.getTransaction().commit();
        sesion.close();
    }
    public void modificarTienda(Tienda tiendaSeleccion) {
        Session sesion = sessionFactory.openSession();
        sesion.beginTransaction();
        sesion.saveOrUpdate(tiendaSeleccion);
        sesion.getTransaction().commit();
        sesion.close();
    }
    public void modificarJuego(Juego juegoSeleccion) {
        Session sesion = sessionFactory.openSession();
        sesion.beginTransaction();
        sesion.saveOrUpdate(juegoSeleccion);
        sesion.getTransaction().commit();
        sesion.close();
    }
    public void borrarDisenador(Disenador disenadorBorrado) {
        Session sesion = sessionFactory.openSession();
        sesion.beginTransaction();
        sesion.delete(disenadorBorrado);
        sesion.getTransaction().commit();
        sesion.close();
    }
    public void borrarEditorial(Editorial editorialBorrado) {
        Session sesion = sessionFactory.openSession();
        sesion.beginTransaction();
        sesion.delete(editorialBorrado);
        sesion.getTransaction().commit();
        sesion.close();
    }
    public void borrarTienda(Tienda tiendaBorrado) {
        Session sesion = sessionFactory.openSession();
        sesion.beginTransaction();
        sesion.delete(tiendaBorrado);
        sesion.getTransaction().commit();
        sesion.close();
    }
    public void borrarJuego(Juego juegoBorrado) {
        Session sesion = sessionFactory.openSession();
        sesion.beginTransaction();
        sesion.delete(juegoBorrado);
        sesion.getTransaction().commit();
        sesion.close();
    }
    public ArrayList<Juego> getJuegosDisenador(Disenador disenadorSeleccionado) {
        Session sesion = sessionFactory.openSession();
        Query query = sesion.createQuery("FROM Juego WHERE disenador =: dis");
        query.setParameter("dis", disenadorSeleccionado);
        ArrayList<Juego> lista = (ArrayList<Juego>)query.getResultList();
        sesion.close();
        return lista;
    }
}
