package dao;

import model.Usuario;

import java.util.List;

/**
 * Created by foleac on 7/5/2015.
 */
public interface UsuarioDAO {

    public void anadirUsuario(Usuario usuario);

    public Usuario getUsuario(String nombreUsuario);

    public List<Usuario> getUsuarios();

    public void updateUsuario(Usuario usuario);

}
