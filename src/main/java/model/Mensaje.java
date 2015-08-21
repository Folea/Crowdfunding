package model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by foleac on 7/5/2015.
 */
@Entity
@NamedQueries({
        @NamedQuery(name = "Mensaje.getMensajePorAsunto", query = "select m from Mensaje as m where m.asunto = :asunto" )
})
public class Mensaje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Basic
    private String cuerpo;
    @Basic
    private String asunto;
    @Basic
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @ManyToOne
    @JoinColumn(name = "emisor")
    private Usuario emisor;
    @ManyToOne
    @JoinColumn(name = "receptor")
    private Usuario receptor;
    @OneToOne
    private Proyecto proyecto;
    @Basic
    private String respuesta;
   

    public Mensaje(){
    }

    public Mensaje(String cuerpo, String asunto, Date fecha, Usuario emisor, Usuario receptor) {
        this.cuerpo = cuerpo;
        this.asunto = asunto;
        this.fecha = fecha;
        this.emisor = emisor;
        this.receptor = receptor;
    }

    public String getCuerpo() {
        return cuerpo;
    }

    public void setCuerpo(String cuerpo) {
        this.cuerpo = cuerpo;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Usuario getEmisor() {
        return emisor;
    }

    public void setEmisor(Usuario emisor) {
        this.emisor = emisor;
    }

    public Usuario getReceptor() {
        return receptor;
    }

    public void setReceptor(Usuario receptor) {
        this.receptor = receptor;
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

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }
}
