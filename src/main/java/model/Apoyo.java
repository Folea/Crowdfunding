package model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by foleac on 7/5/2015.
 */

@Entity
public class Apoyo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Basic
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @ManyToOne
    private Usuario usuario;
    @ManyToOne
    private Recompensa recompensa;


    public Apoyo() {

    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Recompensa getRecompensa() {
        return recompensa;
    }

    public void setRecompensa(Recompensa recompensa) {
        this.recompensa = recompensa;
    }
}
