package dao.daoImpl;

import dao.CategoriaDAO;
import model.Categoria;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by foleac on 7/8/2015.
 */
public class CategoriaDAOImpl implements CategoriaDAO {

    private static final String PERSISTENCE_UNIT_NAME = "apoyanos";
    private static EntityManagerFactory factory;

    public void insertarCategoria(Categoria categoria) {
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        em.persist(categoria);
        em.getTransaction().commit();
        em.close();
    }

    public List<Categoria> getCategorias() {
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        TypedQuery<Categoria> query = em.createNamedQuery("Categoria.getCategorias", Categoria.class);
        return query.getResultList();
    }

    public Categoria getCategoria(String nombre) {
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        TypedQuery<Categoria> query = em.createNamedQuery("Categoria.getCategoria", Categoria.class);
        query.setParameter("nombre", nombre);
        return query.getSingleResult();
    }
}
