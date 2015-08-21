package dao.daoImpl;

import dao.PoliticaComisionesDAO;
import model.PoliticaComisiones;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by foleac on 7/8/2015.
 */
public class PoliticaComisionesDAOImpl implements PoliticaComisionesDAO {

    private static final String PERSISTENCE_UNIT_NAME = "apoyanos";
    private static EntityManagerFactory factory;

    public void insertarPolitica(PoliticaComisiones politica) {
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        em.persist(politica);
        em.getTransaction().commit();
        em.close();
    }
}
