package juegosDeMesa.base;

import org.bson.types.ObjectId;

import java.time.LocalDate;

public class Disenador {
    private ObjectId id;
    private String nombre;
    private String apellidos;
    private LocalDate fechanac;

    public Disenador(String nombre, String apellidos, LocalDate nacimiento) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fechanac = nacimiento;
    }

    public Disenador() {

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

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public LocalDate getFechanac() {
        return fechanac;
    }

    public void setFechanac(LocalDate fechanac) {
        this.fechanac = fechanac;
    }

    @Override
    public String toString() {
        return "Disenador{" +
                "nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", fechanac=" + fechanac +
                '}';
    }
}
