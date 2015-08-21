package model;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by foleac on 7/5/2015.
 */
@Entity
public class Notificacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Basic
    private String descripcion;
    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar fecha;
    @OneToOne
    private Proyecto proyecto;

    public Notificacion(){
    }

    public Notificacion(Proyecto proyecto, Calendar fecha, String descripcion) {
        this.proyecto = proyecto;
        this.fecha = fecha;
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFecha() {
    	return fecha.getTime();
    }

    public void setFecha(Date fecha) {
    	this.fecha.setTime(fecha);
    }

    public Proyecto getProyecto() {
        return proyecto;
    }

    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTime(int hora, int minutos, int segundos) {
    	this.fecha.getTime().setHours(hora);
    	this.fecha.getTime().setMinutes(minutos);
    	this.fecha.getTime().setSeconds(segundos);
    }

    public void setCalendar(Calendar fecha) {
        this.fecha = fecha;
    }

}
