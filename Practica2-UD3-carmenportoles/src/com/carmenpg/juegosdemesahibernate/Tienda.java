package com.carmenpg.juegosdemesahibernate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "tiendas", schema = "juegosdemesahibernate", catalog = "")
public class Tienda {
    private int idtienda;
    private String direccion;
    private List<Juego> juegos;

    @Id
    @Column(name = "idtienda")
    public int getIdtienda() {
        return idtienda;
    }

    public void setIdtienda(int idtienda) {
        this.idtienda = idtienda;
    }

    @Basic
    @Column(name = "direccion")
    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tienda tienda = (Tienda) o;
        return idtienda == tienda.idtienda &&
                Objects.equals(direccion, tienda.direccion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idtienda, direccion);
    }

    @ManyToMany
    @JoinTable(name = "tienda_juego", catalog = "", schema = "juegosdemesahibernate", joinColumns = @JoinColumn(name = "idtienda", referencedColumnName = "idtienda", nullable = false), inverseJoinColumns = @JoinColumn(name = "idjuego", referencedColumnName = "idjuego", nullable = false))
    public List<Juego> getJuegos() {
        return juegos;
    }

    public void setJuegos(List<Juego> juegos) {
        this.juegos = juegos;
    }

    public Tienda() {
    }

    public Tienda(int idtienda, String direccion, List<Juego> juegos) {
        this.idtienda = idtienda;
        this.direccion = direccion;
        this.juegos = juegos;
    }

    @Override
    public String toString() {
        return idtienda +
                ", '" + direccion + '\'';
    }
}
