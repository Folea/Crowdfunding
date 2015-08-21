package model;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by foleac on 7/5/2015.
 */
public class CatalogoUsuarios {
    private static CatalogoUsuarios catalogoUsuarios = null;
    private List<Usuario> usuarios;

    private CatalogoUsuarios() {
        usuarios = new LinkedList<Usuario>();
    }

    public static CatalogoUsuarios getCatalogoUsuarios() {
        if(catalogoUsuarios == null) {
            catalogoUsuarios = new CatalogoUsuarios();
        }

        return catalogoUsuarios;
    }

    public Usuario getUsuario(String usuario) {
        for(Usuario u : usuarios) {
            if(u.getUsername().equals(usuario)) {
                return u;
            }
        }
        return null;
    }

    public void addUsuario(Usuario usuario) {
        usuarios.add(usuario);
    }

    public void cargarUsuarios(List<Usuario> usuarios) {
        this.usuarios = new LinkedList<Usuario>(usuarios);
    }

}
