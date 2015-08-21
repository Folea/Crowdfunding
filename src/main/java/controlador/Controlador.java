package controlador;

import dao.*;
import dao.daoImpl.*;
import exceptions.*;
import model.*;
import org.joda.time.DateTime;
import utils.ApoyanosConstans;

import javax.persistence.NoResultException;
import javax.persistence.RollbackException;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by foleac on 7/5/2015.
 */
public final class Controlador {
    private Usuario usuarioConectado;
    private UsuarioDAO usuarioDao;
    private ApoyoDAO apoyoDAO;
    private CategoriaDAO categoriaDAO;
    private MensajeDAO mensajeDAO;
    private NotificacionDAO notificacionDAO;
    private PoliticaComisionesDAO politicaComisionesDAO;
    private ProyectoDAO proyectoDAO;
    private RecompensaDAO recompensaDAO;
    private static Controlador unicaInstancia;

    public Controlador() {
        this.usuarioDao = new UsuarioDAOImpl();
        this.apoyoDAO =  new ApoyoDAOImpl();
        this.categoriaDAO = new CategoriaDAOImpl();
        this.mensajeDAO = new MensajeDAOImpl();
        this.notificacionDAO = new NotificacionDAOImpl();
        this.politicaComisionesDAO = new PoliticaComisionesDAOImpl();
        this.proyectoDAO = new ProyectoDAOImpl();
        this.recompensaDAO = new RecompensaDAOImpl();
        CatalogoUsuarios.getCatalogoUsuarios().cargarUsuarios(usuarioDao.getUsuarios());
        CatalogoProyectos.getCatalogoProyectos().cargarProyectos(proyectoDAO.getProyectos());
    }

    /**
     * Obtener instancia del controlador.
     * @return Instancia del controlador.
     */

    public static Controlador getUnicaInstancia() {
        if(unicaInstancia == null) {
            unicaInstancia = new Controlador();
        }
        return unicaInstancia;
    }

    /**
     * Registrar un usuario.
     * @param nombre Nombre del usuario.
     * @param apellido Apellido del usuario.
     * @param dni Dni del usuario.
     * @param username Nombre de usuario.
     * @param contrasena Contrasena del usuario.
     * @param email Email del usuario.
     * @throws UsuarioExistenteException Se lanza en el caso de que ya exista un usuario con ese nombre, dni o email.
     */

    public void registrar(String nombre, String apellido, String dni, String username, String contrasena, String email) throws UsuarioExistenteException {
        Usuario usuario = new Usuario(nombre, apellido, dni, username, contrasena, email);
        try {
            usuarioDao.anadirUsuario(usuario);
            CatalogoUsuarios.getCatalogoUsuarios().addUsuario(usuario);
        } catch (RollbackException e) {
            throw new UsuarioExistenteException();
        }

    }

    /**
     * Se utiliza para loguearse en la aplicacion.
     * @param username El nombre de usuario del usuario a conectar.
     * @param contrasena La contrasena del usaurio.
     * @return Devuelve un booleano indicando si se ha logueado o no el usuario.
     * @throws UsuarioInexistenteException Se lanza en el caso de que el usuario no existe en la base de datos.
     * @throws ContrasenaIncorectaException Se lanza en el caso de que la contrasena sea incorrecta.
     */

    public boolean login(String username, String contrasena) throws UsuarioInexistenteException, ContrasenaIncorectaException {
        Usuario usuario = CatalogoUsuarios.getCatalogoUsuarios().getUsuario(username);
        if(usuario == null) {
            throw new UsuarioInexistenteException();
        }
        else {
            if(!usuario.getContrasena().equals(contrasena)) {
                throw new ContrasenaIncorectaException();
            }
            else {
                usuarioConectado = usuario;
            }
        }
        return true;
    }

    /**
     * Para salir de la aplicacion.
     */

    public void logout() {
        usuarioConectado = null;
    }

    /**
     * Crear un proyecto.
     * @param nombre Nombre del proyecto.
     * @param descripcion Descripcion del proyecto.
     * @param financiacion Financion deseada.
     * @param fechaFinalizacion Fecha de finalizacion para el proyecto.
     * @param categoria Categoria del proyecto.
     * @return Devuelve el proyecto creado.
     * @throws ProyectoExistenteException Se lanza en el caso de que ya exista un proyecto con el mismo nombre.
     * @throws UsuarioNoLogeadoException Se lanza si el usuario no esta logueado.
     */

    public Proyecto crearProyecto(String nombre, String descripcion, Double financiacion, DateTime fechaFinalizacion, String categoria) throws ProyectoExistenteException, UsuarioNoLogeadoException {
        if(usuarioConectado == null) {
            throw new UsuarioNoLogeadoException();
        }
        else{
            Categoria categoriaNueva = null;
            try{
                categoriaNueva = crearCategoria(categoria);
            } catch (RollbackException e) {
                categoriaNueva = getCategoria(categoria);
            }

            Proyecto proyecto = new Proyecto(descripcion, nombre, financiacion, fechaFinalizacion, usuarioConectado, new LinkedList<Recompensa>());
            proyecto.setCategoria(categoriaNueva);

            try{
                proyectoDAO.anadirProyecto(proyecto);
                usuarioConectado.addProyectoCreado(proyecto);
                usuarioDao.updateUsuario(usuarioConectado);
                CatalogoProyectos.getCatalogoProyectos().addProyecto(proyecto);
            } catch (Exception e) {
                throw new ProyectoExistenteException();
            }

            return proyecto;
        }
    }

    /**
     * Crear recompensa.
     * @param nombre El nombre de la recompensa.
     * @param descripcion La descripcion de la recompensa.
     * @param maxApoyos El numero maximo de apoyos para la recompensa.
     * @param proyecto El proyecto para el que se genera la recompensa.
     * @param cantidad La cantidad con la que se va a apoyar la recompensa.
     * @throws UsuarioNoLogeadoException Se lanza si el usuario no esta logueado.
     */

    public void crearRecompensa(String nombre, String descripcion, int maxApoyos, Proyecto proyecto, double cantidad) throws UsuarioNoLogeadoException {
        if(usuarioConectado == null) {
            throw new UsuarioNoLogeadoException();
        }
        else {
            Recompensa recompensa = new Recompensa();
            recompensa.setNombre(nombre);
            recompensa.setDescripcion(descripcion);
            recompensa.setMaxApoyos(maxApoyos);
            recompensa.setProyecto(proyecto);
            recompensa.setCantidad(cantidad);

            recompensaDAO.insertarRecompensa(recompensa);
            proyecto.anadirRecompensa(recompensa);
            proyectoDAO.updateProyecto(proyecto);
            CatalogoProyectos.getCatalogoProyectos().updateProyecto(proyecto);
        }
    }

    /**
     * Para sacar de la base de datos una recompensa.
     * @param id El id de la recompensa deseada.
     * @return Devuelve la recompensa.
     * @throws RecompensaInexistenteException Se lanza si la recompensa no existe.
     */

    public Recompensa getRecompensa(int id) throws RecompensaInexistenteException {
        try {
            return recompensaDAO.getRecompensa(id);
        } catch (NoResultException e) {
            throw new RecompensaInexistenteException();
        }
    }

    /**
     * Devuelve todas las recompensas para un proyecto.
     * @param proyecto Nombre del proyecto.
     * @return Lista de recompensas.
     */

    public List<Recompensa> getRecompensasParaProyecto(String proyecto) {
        return recompensaDAO.gerRecompensasParaProyecto(proyecto);
    }

    /**
     * Crear un apoyo.
     * @param recompensa La recompensa que se va a apoyar.
     * @throws MaximoApoyosParaRecompensaException Se lanza en el caso de que ya se haya alcanzado el numero maximo
     * de apoyos para una recompensa.
     */

    public void crearApoyo(Recompensa recompensa) throws MaximoApoyosParaRecompensaException {
        Apoyo apoyo = new Apoyo();
        apoyo.setUsuario(usuarioConectado);
        apoyo.setFecha(new Date());
        apoyo.setRecompensa(recompensa);

        if(recompensa.getApoyos().size() < recompensa.getMaxApoyos()) {
            recompensa.anadirApoyo(apoyo);
            recompensa.getProyecto().incrementarFinanciacion(recompensa.getCantidad());
            if(recompensa.getProyecto().getPorcentajeFinanciado() >= 100) {
                recompensa.getProyecto().setEstado(EstadoProyecto.FINANCIADO);
                crearNotificacion(recompensa.getProyecto(), "El proyecto se ha financiado.");
                updateProyecto(recompensa.getProyecto());
            }
            usuarioConectado.addApoyo(apoyo);
            usuarioDao.updateUsuario(usuarioConectado);
            recompensaDAO.updateRecompensa(recompensa);
            proyectoDAO.updateProyecto(recompensa.getProyecto());
        } else {
            throw new MaximoApoyosParaRecompensaException();
        }
    }

    /**
     * Votar un proyecto.
     * @param proyecto El proyecto que se quiere votar.
     * @throws ProyectoVotadoException Se lanza si el proyecto se ha votado ya.
     * @throws MismoUsuarioException Se lanza si se quiere votar un proyecto con el mismo usuario que ya ha votado.
     */

    public void votarProyecto(Proyecto proyecto) throws ProyectoVotadoException, MismoUsuarioException {
        if(proyecto.getCreador().getUsername().equals(usuarioConectado.getUsername())){
            throw new MismoUsuarioException();
        }
        else if(!usuarioConectado.getProyectosVotados().contains(proyecto) && proyecto.getEstado() == EstadoProyecto.VOTACION) {
            usuarioConectado.addProyectoVotado(proyecto);
            proyecto.incrementarVotos();
            if(proyecto.getCuentaVotos() >= ApoyanosConstans.votos) {
                proyecto.setEstado(EstadoProyecto.FINANCIACION);
                crearNotificacion(proyecto, "El proyecto ha pasado en fase de financiacion.");
            }
            proyectoDAO.updateProyecto(proyecto);
        }
        else {
            throw new ProyectoVotadoException();
        }
    }

    /**
     * Conseguir un proyecto por su nombre.
     * @param nombre El nombre del proyecto deseado
     * @return El proyecto.
     * @throws ProyectoInexistenteException Si el proyecto no existe.
     */
    public Proyecto getProyecto(String nombre) throws ProyectoInexistenteException {
        try{
            return proyectoDAO.getProyecto(nombre);
        } catch (RollbackException ex) {
            throw new ProyectoInexistenteException();
        }

    }

    /**
     * Conseguir los mensajes enviados por el usuario conectado.
     * @return Lista de mensajes enviados.
     */
    public List<Mensaje> getMensajesEnviados() {
        return usuarioConectado.getMensajesEnviados();
    }

    /**
     * Conseguir los mensajes recibidos por el usuario conectado.
     * @return Lista de mensajes recibidos.
     */
    public List<Mensaje> getMensajesRecibidos() {
        return usuarioConectado.getMensajesRecibidos();
    }

    /**
     * Conseguir las notificaciones recibidas por el usuario conectado.
     * @return Lista de notificaciones.
     */
    public List<Notificacion> getNotificaciones() {
        return usuarioConectado.getNotificaciones();
    }

    /**
     * Enviar un mensaje.
     * @param cuerpo Cuerpo del mensaje.
     * @param asunto Asunto del mensaje.
     * @param proyecto El proyecto al que se quiere mandar un mensaje.
     */
    public void enviarMensaje(String cuerpo, String asunto, Proyecto proyecto) {
        Mensaje mensaje = new Mensaje();
        mensaje.setCuerpo(cuerpo);
        mensaje.setAsunto(asunto);
        mensaje.setProyecto(proyecto);
        mensaje.setFecha(new Date());
        mensaje.setEmisor(usuarioConectado);
        mensaje.setReceptor(proyecto.getCreador());
        usuarioConectado.addMensajeEnviado(mensaje);
        proyecto.getCreador().addMensajeRecibido(mensaje);
        mensajeDAO.insertarMensaje(mensaje);
        usuarioDao.updateUsuario(usuarioConectado);
        usuarioDao.updateUsuario(proyecto.getCreador());
    }

    /**
     * Crear categoria.
     * @param nombre Nombre de la categoria a crear.
     * @return La categoria.
     */
    private Categoria crearCategoria(String nombre) {
        Categoria categoria = new Categoria(nombre.toUpperCase());
        categoriaDAO.insertarCategoria(categoria);
        return categoria;
    }

    /**
     * Conseguir una categoria por su nombre.
     * @param nombre Nombre de la categoria.
     * @return La categoria.
     */
    public Categoria getCategoria(String nombre) {
        return categoriaDAO.getCategoria(nombre.toUpperCase());
    }

    /**
     * Crear notifiacion.
     * @param proyecto El proyecto del que se va a crear una notifiacion.
     * @param descripcion La descripcion de la notificacion.
     */
    public void crearNotificacion(Proyecto proyecto, String descripcion) {
        Notificacion notificacion = new Notificacion();
        notificacion.setProyecto(proyecto);
        notificacion.setDescripcion(descripcion);
        notificacion.setCalendar(Calendar.getInstance());
        usuarioConectado.addNotificacion(notificacion);

        notificacionDAO.insertarNotificacion(notificacion);
        usuarioDao.updateUsuario(usuarioConectado);
    }

    /**
     * Comprobar si el usuario esta registrado.
     * @param username Nombre del usuario a comprobar.
     * @return True/False dependiendo de si esta o no registrado.
     */
    public boolean esRegistrado(String username) {
    	Usuario usuario = CatalogoUsuarios.getCatalogoUsuarios().getUsuario(username);
    	if(usuario == null) {
    		return false;
    	} 
    	else { 
    		return true;
    	}
    }

    /**
     * Conseguir todos los proyectos.
     * @return Lista de proyectos.
     */
    public List<Proyecto> getProyectos() {
    	return proyectoDAO.getProyectos();
    }

    /**
     * Conseguir todos los proyectos en votacion.
     * @return Lista de proyectos,
     */
    public List<Proyecto> getProyectosVotacion() {
        return proyectoDAO.getProyectosEstado(EstadoProyecto.VOTACION);
    }

    /**
     * Conseguir todos los proyectos en financiacion.
     * @return Lista de proyectos.
     */
    public List<Proyecto> getProyectosFinanciacion() {
        return proyectoDAO.getProyectosEstado(EstadoProyecto.FINANCIACION);
    }

    /**
     * Conseguir todos los proyectos financiados.
     * @return Lista de proyectos.
     */
    public List<Proyecto> getProyectosFinanciados() {
        return proyectoDAO.getProyectosEstado(EstadoProyecto.FINANCIADO);
    }

    /**
     * Conseguir todos los proyectos cancelados.
     * @return Lista de proyectos.
     */
    public List<Proyecto> getProyectosCancelados() {
        return proyectoDAO.getProyectosEstado(EstadoProyecto.CANCELADO);
    }

    /**
     * Conseguir el nombre del usuario conectado.
     * @return Nombre del usuario conectado.
     */
    public String getUsuarioConectado() {
        return usuarioConectado.getUsername();
    }

    /**
     * Actualizar la informacion de un proyecto.
     * @param proyecto El proyecto a actualizar.
     */
    public void updateProyecto(Proyecto proyecto) {
        proyectoDAO.updateProyecto(proyecto);
        CatalogoProyectos.getCatalogoProyectos().updateProyecto(proyecto);
    }

    /**
     * Actualizar el estado de todos los proyectos comprobando los nuevos datos.
     */
    public void actualizarEstadoProyectos() {
        for(Proyecto p : getProyectos()) {
            if (p.getEstado().equals(EstadoProyecto.VOTACION)&& p.getCuentaVotos() >= ApoyanosConstans.votos) {
                p.setEstado(EstadoProyecto.FINANCIACION);
                updateProyecto(p);
            }
            else if(p.getEstado().equals(EstadoProyecto.FINANCIACION) && p.getPorcentajeFinanciado() >= 100) {
                p.setEstado(EstadoProyecto.FINANCIADO);
                updateProyecto(p);
                crearNotificacion(p, "El proyecto se ha financiado.");
            }
            else if(p.getDiasRestantes() < 0) {
                p.setEstado(EstadoProyecto.CANCELADO);
                crearNotificacion(p, "El proyecto esta cancelado.");
            }
        }
    }

    /**
     * Conseguir los apoyos realizados por el usuario conectado.
     * @return Lista de apoyos.
     */
    public List<Apoyo> getApoyosUsuarioConectado() {
        return usuarioConectado.getApoyos();
    }

    /**
     * Actualizar la informacion de un mensaje.
     * @param mensaje Mensaje a actualizar.
     */
    public void actualizarMensaje(Mensaje mensaje) {
        mensajeDAO.updateMensaje(mensaje);
    }

    /**
     * Conseguir un mensaje enviado por su asunto.
     * @param asunto Asunto del mensaje.
     * @return Mensaje enviado.
     */
    public Mensaje getMensajeEnviadoPorAsunto(String asunto) {
        return usuarioConectado.getMensajeEnviadoPorAsunto(asunto);
    }

    /**
     * Conseguir un mensaje recibido por su asunto.
     * @param asunto Asunto del mensaje.
     * @return Mensaje recibido.
     */
    public Mensaje getMensajeRecibidoPorAsunto(String asunto) {
        return usuarioConectado.getMensajeRecibidosPorAsunto(asunto);
    }

}
