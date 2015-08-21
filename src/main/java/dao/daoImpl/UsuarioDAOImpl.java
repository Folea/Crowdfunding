package dao.daoImpl;

import dao.UsuarioDAO;
import model.Usuario;

import javax.persistence.*;
import java.util.List;

/**
 * Created by foleac on 7/5/2015.
 */
public class UsuarioDAOImpl implements UsuarioDAO {

    private static final String PERSISTENCE_UNIT_NAME = "apoyanos";
    private static EntityManagerFactory factory;

    public void anadirUsuario(Usuario usuario) {
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        em.persist(usuario);
        em.getTransaction().commit();
        em.close();
    }

    public Usuario getUsuario(String nombreUsuario) {
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        TypedQuery<Usuario> query = em.createNamedQuery("Usuario.getUsuario", Usuario.class);
        query.setParameter("username",nombreUsuario);
        return query.getSingleResult();
    }

    public List<Usuario> getUsuarios() {
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        TypedQuery<Usuario> query = em.createNamedQuery("Usuario.getUsuarios", Usuario.class);
        return query.getResultList();
    }

    public void updateUsuario(Usuario usuario) {
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        em.merge(usuario);
        em.flush();
        em.getTransaction().commit();
        em.close();
    }
}
