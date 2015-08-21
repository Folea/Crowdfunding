package dao.daoImpl;

import dao.RecompensaDAO;
import model.Recompensa;

import javax.persistence.*;
import java.util.List;

/**
 * Created by foleac on 7/8/2015.
 */
public class RecompensaDAOImpl implements RecompensaDAO {

    private static final String PERSISTENCE_UNIT_NAME = "apoyanos";
    private static EntityManagerFactory factory;

    public void insertarRecompensa(Recompensa recompensa) {
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        em.persist(recompensa);
        em.getTransaction().commit();
        em.close();
    }

    public Recompensa getRecompensa(int id) {
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        TypedQuery<Recompensa> query = em.createNamedQuery("Recompensa.getRecompensa", Recompensa.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    public List<Recompensa> gerRecompensasParaProyecto(String proyecto) {
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        TypedQuery<Recompensa> query = em.createNamedQuery("Recompensa.getRecompensasParaProyecto", Recompensa.class);
        query.setParameter("proyecto", proyecto);
        return query.getResultList();
    }

    public void updateRecompensa(Recompensa recompensa) {
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        em.merge(recompensa);
        em.flush();
        em.getTransaction().commit();
        em.close();
    }
}
