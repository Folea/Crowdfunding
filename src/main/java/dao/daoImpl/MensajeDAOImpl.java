package dao.daoImpl;

import dao.MensajeDAO;
import model.Mensaje;
import model.Usuario;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 * Created by foleac on 7/8/2015.
 */
public class MensajeDAOImpl implements MensajeDAO {

    private static final String PERSISTENCE_UNIT_NAME = "apoyanos";
    private static EntityManagerFactory factory;

    public void insertarMensaje(Mensaje mensaje) {
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        em.persist(mensaje);
        em.getTransaction().commit();
        em.close();
    }

    public void updateMensaje(Mensaje mensaje) {
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        em.merge(mensaje);
        em.flush();
        em.getTransaction().commit();
        em.close();
    }

    public Mensaje getMensajePorAsunto(String asunto) {
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        TypedQuery<Mensaje> query = em.createNamedQuery("Mensaje.getMensajePorAsunto", Mensaje.class);
        query.setParameter("asunto",asunto);
        return query.getSingleResult();
    }
}
