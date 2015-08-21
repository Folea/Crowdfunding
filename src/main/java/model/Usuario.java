package model;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by foleac on 7/5/2015.
 */
@Entity
@NamedQueries({
        @NamedQuery(name = "Usuario.getUsuario", query = "select u from Usuario as u where u.username = :username" ),
        @NamedQuery(name = "Usuario.getUsuarios", query = "select u from Usuario as u" )
})

public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Basic
    private String nombre;
    @Basic
    private String apellido;
    @Basic
    @Column(unique = true)
    private String dni;
    @Basic
    @Column(unique = true)
    private String email;
    @Basic
    @Column(unique = true)
    private String username;
    @Basic
    private String contrasena;
    @OneToMany
    @JoinColumn
    private List<Proyecto> proyectosCreados;
    @ManyToMany
    @JoinColumn
    private List<Proyecto> proyectosVotados;
    @OneToMany
    @JoinColumn
    private List<Mensaje> mensajesRecibidos;
    @OneToMany
    @JoinColumn(name = "mensajesEnviados")
    private List<Mensaje> mensajesEnviados;
    @OneToMany
    @JoinColumn(name = "notificaciones")
    private List<Notificacion> notificaciones;
    @OneToMany
    @JoinColumn(name = "apoyos")
    private List<Apoyo> apoyos;

    public Usuario(){
        this.proyectosCreados = new LinkedList<Proyecto>();
        this.proyectosVotados = new LinkedList<Proyecto>();
        this.mensajesEnviados = new LinkedList<Mensaje>();
        this.mensajesRecibidos = new LinkedList<Mensaje>();
        this.notificaciones = new LinkedList<Notificacion>();
        this.apoyos = new LinkedList<Apoyo>();
    }

    public Usuario(String nombre, String apellido, String dni, String username, String contrasena, String email) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.username = username;
        this.contrasena = contrasena;
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Proyecto> getProyectosCreados() {
        return proyectosCreados;
    }

    public void addProyectoCreado(Proyecto proyecto) {
        proyectosCreados.add(proyecto);
    }

    public List<Proyecto> getProyectosVotados() {
        return proyectosVotados;
    }

    public void addProyectoVotado(Proyecto proyecto) {
        proyectosVotados.add(proyecto);
    }

    public List<Mensaje> getMensajesRecibidos() {
        return mensajesRecibidos;
    }

    public void addMensajeRecibido(Mensaje mensaje) {
        mensajesRecibidos.add(mensaje);
    }

    public List<Mensaje> getMensajesEnviados() {
        return mensajesEnviados;
    }

    public void addMensajeEnviado(Mensaje mensaje) {
        mensajesEnviados.add(mensaje);
    }

    public List<Notificacion> getNotificaciones() {
        return notificaciones;
    }

    public void addNotificacion(Notificacion notificacion) {
        notificaciones.add(notificacion);
    }

    public List<Apoyo> getApoyos() {
        return apoyos;
    }

    public void addApoyo(Apoyo apoyo) {
        apoyos.add(apoyo);
    }

    public Mensaje getMensajeEnviadoPorAsunto(String asunto) {
        for (Mensaje m : mensajesEnviados) {
            if (m.getAsunto().equals(asunto)) {
                return m;
            }
        }

        return null;
    }

    public Mensaje getMensajeRecibidosPorAsunto(String asunto) {
        for (Mensaje m : mensajesRecibidos) {
            if (m.getAsunto().equals(asunto)) {
                return m;
            }
        }

        return null;
    }


}
