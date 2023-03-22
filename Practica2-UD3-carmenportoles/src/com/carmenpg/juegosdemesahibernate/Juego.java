package com.carmenpg.juegosdemesahibernate;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "juegos", schema = "juegosdemesahibernate", catalog = "")
public class Juego {
    private int idjuego;
    private String titulo;
    private String isbn;
    private String duracion;
    private String jugadores;
    private double precio;
    private Timestamp fechapublicacion;
    private String genero;
    private Disenador disenador;
    private Editorial editorial;
    private List<Tienda> tiendas;

    @Id
    @Column(name = "idjuego")
    public int getIdjuego() {
        return idjuego;
    }

    public void setIdjuego(int idjuego) {
        this.idjuego = idjuego;
    }

    @Basic
    @Column(name = "titulo")
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    @Basic
    @Column(name = "isbn")
    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    @Basic
    @Column(name = "duracion")
    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    @Basic
    @Column(name = "jugadores")
    public String getJugadores() {
        return jugadores;
    }

    public void setJugadores(String jugadores) {
        this.jugadores = jugadores;
    }

    @Basic
    @Column(name = "precio")
    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Basic
    @Column(name = "fechapublicacion")
    public Timestamp getFechapublicacion() {
        return fechapublicacion;
    }

    public void setFechapublicacion(Timestamp fechapublicacion) {
        this.fechapublicacion = fechapublicacion;
    }

    @Basic
    @Column(name = "genero")
    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Juego juego = (Juego) o;
        return idjuego == juego.idjuego &&
                Double.compare(juego.precio, precio) == 0 &&
                Objects.equals(titulo, juego.titulo) &&
                Objects.equals(isbn, juego.isbn) &&
                Objects.equals(duracion, juego.duracion) &&
                Objects.equals(jugadores, juego.jugadores) &&
                Objects.equals(fechapublicacion, juego.fechapublicacion) &&
                Objects.equals(genero, juego.genero);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idjuego, titulo, isbn, duracion, jugadores, precio, fechapublicacion, genero);
    }

    @ManyToOne
    @JoinColumn(name = "iddisenador", referencedColumnName = "iddisenador", nullable = false)
    public Disenador getDisenador() {
        return disenador;
    }

    public void setDisenador(Disenador disenador) {
        this.disenador = disenador;
    }

    @ManyToOne
    @JoinColumn(name = "ideditorial", referencedColumnName = "ideditorial", nullable = false)
    public Editorial getEditorial() {
        return editorial;
    }

    public void setEditorial(Editorial editorial) {
        this.editorial = editorial;
    }

    @ManyToMany(mappedBy = "juegos")
    public List<Tienda> getTiendas() {
        return tiendas;
    }

    public void setTiendas(List<Tienda> tiendas) {
        this.tiendas = tiendas;
    }

    public Juego() {
    }

    public Juego(int idjuego, String titulo, String isbn, String duracion, String jugadores, double precio, Timestamp fechapublicacion, String genero, Disenador disenador, Editorial editorial, List<Tienda> tiendas) {
        this.idjuego = idjuego;
        this.titulo = titulo;
        this.isbn = isbn;
        this.duracion = duracion;
        this.jugadores = jugadores;
        this.precio = precio;
        this.fechapublicacion = fechapublicacion;
        this.genero = genero;
        this.disenador = disenador;
        this.editorial = editorial;
        this.tiendas = tiendas;
    }

    @Override
    public String toString() {
        return idjuego +
                ", '" + titulo + '\'' +
                ", '" + isbn + '\'' +
                ", duracion:'" + duracion + '\'' +
                ", '" + jugadores + " jugadores'" +
                ", " + precio + " €" +
                ", " + fechapublicacion +
                ", '" + genero + '\'' +
                ", " + disenador.getNombre() + " " + disenador.getApellidos() +
                ", " + editorial.getEditorial();
    }
}
