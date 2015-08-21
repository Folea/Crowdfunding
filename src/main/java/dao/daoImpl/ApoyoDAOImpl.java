package dao.daoImpl;

import dao.ApoyoDAO;
import model.Apoyo;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by foleac on 7/8/2015.
 */
public class ApoyoDAOImpl implements ApoyoDAO {

    private static final String PERSISTENCE_UNIT_NAME = "apoyanos";
    private static EntityManagerFactory factory;

    public void insertarApoyo(Apoyo apoyo) {
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        em.persist(apoyo);
        em.getTransaction().commit();
        em.close();
    }
}
