package dao.daoImpl;

import dao.ProyectoDAO;
import model.EstadoProyecto;
import model.Proyecto;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by foleac on 7/7/2015.
 */
public class ProyectoDAOImpl implements ProyectoDAO {

    private static final String PERSISTENCE_UNIT_NAME = "apoyanos";
    private static EntityManagerFactory factory;

    public void anadirProyecto(Proyecto proyecto) {
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        em.persist(proyecto);
        em.getTransaction().commit();
        em.close();
    }

    public Proyecto getProyecto(String nombre) {
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        TypedQuery<Proyecto> query = em.createNamedQuery("Proyecto.getProyecto", Proyecto.class);
        query.setParameter("nombre", nombre);
        return query.getSingleResult();
    }

    public List<Proyecto> getProyectos() {
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        TypedQuery<Proyecto> query = em.createNamedQuery("Proyecto.getProyectos", Proyecto.class);
        return query.getResultList();
    }

    public void updateProyecto(Proyecto proyecto) {
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        em.merge(proyecto);
        em.flush();
        em.getTransaction().commit();
        em.close();
    }

    public List<Proyecto> getProyectosEstado(EstadoProyecto estado) {
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        TypedQuery<Proyecto> query = em.createNamedQuery("Proyecto.getProyectosEstado", Proyecto.class);
        query.setParameter("estado", estado);
        return query.getResultList();
    }
}
