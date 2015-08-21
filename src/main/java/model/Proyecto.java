package model;

import controlador.Controlador;
import org.joda.time.DateTime;
import org.joda.time.Days;
import umu.tds.cargador.FinanciacionEvent;
import umu.tds.cargador.IFinanciacionListener;
import umu.tds.cargador.Ingreso;
import umu.tds.cargador.ProyectoF;

import javax.persistence.*;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by foleac on 7/5/2015.
 */

@Entity
@NamedQueries({
        @NamedQuery(name = "Proyecto.getProyecto", query = "select p from Proyecto as p where p.nombre = :nombre" ),
        @NamedQuery(name = "Proyecto.getProyectos", query = "select p from Proyecto as p" ),
        @NamedQuery(name = "Proyecto.getProyectosEstado", query = "select p from Proyecto as p where p.estado = :estado")
})

public class Proyecto implements IFinanciacionListener{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Basic
    private String descripcion;
    @Basic
    @Column(unique = true)
    private String nombre;
    @Basic
    private double financiacion;
    @Basic
    private double financionEsperada;
    @Basic
    @Temporal(TemporalType.DATE)
    private Date fechaFinalizacion;
    @Basic
    private int cuentaVotos;
    @Basic
    private EstadoProyecto estado;
    @ManyToOne
    private Usuario creador;
    @OneToOne
    private Categoria categoria;
    @OneToMany
    private List<Recompensa> recompensas;

    public Proyecto() {
        this.recompensas = new LinkedList<Recompensa>();
    }

    public Proyecto(String descripcion, String nombre, double financionEsperada, DateTime fechaFinalizacion, Usuario creador, List<Recompensa> recompensas) {
        this();
        this.descripcion = descripcion;
        this.nombre = nombre;
        this.financionEsperada = financionEsperada;
        this.fechaFinalizacion = fechaFinalizacion.toDate();
        this.creador = creador;
        this.estado = EstadoProyecto.VOTACION;

    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getFinanciacion() {
        return financiacion;
    }

    public void incrementarFinanciacion(double financiacion) {
        this.financiacion += financiacion;
    }

    public Date getFechaFinalizacion() {
        return fechaFinalizacion;
    }

    public void setPlazo(Date fechaFinalizacion) {
        this.fechaFinalizacion = fechaFinalizacion;
    }

    public EstadoProyecto getEstado() {
        return estado;
    }

    public void setEstado(EstadoProyecto estado) {
        this.estado = estado;
    }

    public Usuario getCreador() {
        return creador;
    }

    public void setCreador(Usuario creador) {
        this.creador = creador;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public List<Recompensa> getRecompensas() {
        return recompensas;
    }

    public void setRecompensas(LinkedList<Recompensa> recompensas) {
        this.recompensas = recompensas;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void anadirRecompensa(Recompensa recompensa) {
        recompensas.add(recompensa);
    }


    public int getCuentaVotos() {
        return cuentaVotos;
    }

    public void incrementarVotos() {
        cuentaVotos++;
    }

    public double getFinancionEsperada() {
        return financionEsperada;
    }

    public void setFinancionEsperada(double financionEsperada) {
        this.financionEsperada = financionEsperada;
    }

    public int getDiasRestantes() {
    	System.out.println(fechaFinalizacion.toString());
    	return Days.daysBetween(new DateTime(), new DateTime(fechaFinalizacion)).getDays();
    }

    public double getPorcentajeFinanciado() {
    	return ((financiacion * 100) / financionEsperada);
    }

    public void obtenerFinanciacionExterna(FinanciacionEvent financiacionEvent) {

        for (ProyectoF pf : financiacionEvent.getProyectosF().getAllProyectos()) {

            int idProyectoF = Integer.parseInt(pf.getId());
            if (idProyectoF == this.id) {
                for (Ingreso i : pf.getIngresos()) {
                    this.financiacion += i.getImporte();
                }
            }
        }
        // Actualizamos la financiacion externa en la BBDD
        Controlador.getUnicaInstancia().updateProyecto(this);
    }
}
