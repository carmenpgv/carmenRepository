package juegosDeMesa.base;

import org.bson.types.ObjectId;

import java.time.LocalDate;

public class Juego {
    private ObjectId id;
    private String nombre;
    private LocalDate fechapubli;
    private float precio;
    private ObjectId disenador;

    public Juego(String nombre, LocalDate fecha, float precio, ObjectId disenador) {
        this.nombre = nombre;
        this.fechapubli = fecha;
        this.precio = precio;
        this.disenador = disenador;
    }

    public Juego () {

    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDate getFechapubli() {
        return fechapubli;
    }

    public void setFechapubli(LocalDate fechapubli) {
        this.fechapubli = fechapubli;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public ObjectId getDisenador() {
        return disenador;
    }

    public void setDisenador(ObjectId disenador) {
        this.disenador = disenador;
    }

    @Override
    public String toString() {
        return "Juego{" +
                "nombre='" + nombre + '\'' +
                ", fechapubli=" + fechapubli +
                ", precio=" + precio +
                '}';
    }
}
