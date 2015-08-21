package dao.daoImpl;

import dao.NotificacionDAO;
import model.Notificacion;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by foleac on 7/8/2015.
 */
public class NotificacionDAOImpl implements NotificacionDAO {

    private static final String PERSISTENCE_UNIT_NAME = "apoyanos";
    private static EntityManagerFactory factory;

    public void insertarNotificacion(Notificacion notificacion) {
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        em.persist(notificacion);
        em.getTransaction().commit();
        em.close();
    }
}
