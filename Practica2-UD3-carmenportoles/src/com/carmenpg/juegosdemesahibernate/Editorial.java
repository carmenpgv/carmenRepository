package com.carmenpg.juegosdemesahibernate;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "editoriales", schema = "juegosdemesahibernate", catalog = "")
public class Editorial {
    private int ideditorial;
    private String editorial;
    private String email;
    private String telefono;
    private String productos;
    private String web;
    private List<Juego> juegos;

    @Id
    @Column(name = "ideditorial")
    public int getIdeditorial() {
        return ideditorial;
    }

    public void setIdeditorial(int ideditorial) {
        this.ideditorial = ideditorial;
    }

    @Basic
    @Column(name = "editorial")
    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    @Basic
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "telefono")
    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Basic
    @Column(name = "productos")
    public String getProductos() {
        return productos;
    }

    public void setProductos(String productos) {
        this.productos = productos;
    }

    @Basic
    @Column(name = "web")
    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Editorial that = (Editorial) o;
        return ideditorial == that.ideditorial &&
                Objects.equals(editorial, that.editorial) &&
                Objects.equals(email, that.email) &&
                Objects.equals(telefono, that.telefono) &&
                Objects.equals(productos, that.productos) &&
                Objects.equals(web, that.web);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ideditorial, editorial, email, telefono, productos, web);
    }

    @OneToMany(mappedBy = "editorial")
    public List<Juego> getJuegos() {
        return juegos;
    }

    public void setJuegos(List<Juego> juegos) {
        this.juegos = juegos;
    }

    public Editorial() {
    }

    public Editorial(int ideditorial, String editorial, String email, String telefono, String productos, String web, List<Juego> juegos) {
        this.ideditorial = ideditorial;
        this.editorial = editorial;
        this.email = email;
        this.telefono = telefono;
        this.productos = productos;
        this.web = web;
        this.juegos = juegos;
    }

    @Override
    public String toString() {
        return ideditorial +
                ". '" + editorial + '\'' +
                ", '" + email + '\'' +
                ", '" + telefono + '\'' +
                ", '" + productos + '\'' +
                ", '" + web + '\'';
    }
}
