package com.carmenpg.juegosdemesahibernate;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "disenadores", schema = "juegosdemesahibernate", catalog = "")
public class Disenador {
    private int iddisenador;
    private String nombre;
    private String apellidos;
    private Timestamp fechanacimiento;
    private String pais;
    private List<Juego> juegos;

    @Id
    @Column(name = "iddisenador")
    public int getIddisenador() {
        return iddisenador;
    }

    public void setIddisenador(int iddisenador) {
        this.iddisenador = iddisenador;
    }

    @Basic
    @Column(name = "nombre")
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Basic
    @Column(name = "apellidos")
    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    @Basic
    @Column(name = "fechanacimiento")
    public Timestamp getFechanacimiento() {
        return fechanacimiento;
    }

    public void setFechanacimiento(Timestamp fechanacimiento) {
        this.fechanacimiento = fechanacimiento;
    }

    @Basic
    @Column(name = "pais")
    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Disenador that = (Disenador) o;
        return iddisenador == that.iddisenador &&
                Objects.equals(nombre, that.nombre) &&
                Objects.equals(apellidos, that.apellidos) &&
                Objects.equals(fechanacimiento, that.fechanacimiento) &&
                Objects.equals(pais, that.pais);
    }

    @Override
    public int hashCode() {
        return Objects.hash(iddisenador, nombre, apellidos, fechanacimiento, pais);
    }

    @OneToMany(mappedBy = "disenador")
    public List<Juego> getJuegos() {
        return juegos;
    }

    public void setJuegos(List<Juego> juegos) {
        this.juegos = juegos;
    }

    public Disenador() {

    }

    public Disenador(int iddisenador, String nombre, String apellidos, Timestamp fechanacimiento, String pais, List<Juego> juegos) {
        this.iddisenador = iddisenador;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fechanacimiento = fechanacimiento;
        this.pais = pais;
        this.juegos = juegos;
    }

    @Override
    public String toString() {
        return iddisenador +
                ". '" + nombre + '\'' +
                " " + apellidos + '\'' +
                ", " + fechanacimiento +
                ", '" + pais + '\'';
    }
}
