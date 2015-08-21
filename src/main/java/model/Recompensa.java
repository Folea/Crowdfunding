package model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by foleac on 7/5/2015.
 */
@Entity
@NamedQueries({
        @NamedQuery(name = "Recompensa.getRecompensa", query = "select r from Recompensa as r where r.id = :id" ),
        @NamedQuery(name = "Recompensa.getRecompensasParaProyecto", query = "select r from Recompensa as r where r.proyecto.nombre = :proyecto"),
})

public class Recompensa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @Basic
    private String descripcion;
    @Basic
    private double cantidad;
    @Basic
    @Column
    private String nombre;
    @Basic
    private int maxApoyos;
    @OneToMany
    private List<Apoyo> apoyos;
    @ManyToOne
    private Proyecto proyecto;

    public Recompensa() {

    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getMaxApoyos() {
        return maxApoyos;
    }

    public void setMaxApoyos(int maxApoyos) {
        this.maxApoyos = maxApoyos;
    }

    public List<Apoyo> getApoyos() {
        return apoyos;
    }

    public void anadirApoyo(Apoyo apoyo) {
        apoyos.add(apoyo);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Proyecto getProyecto() {
        return proyecto;
    }

    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
    }
}
