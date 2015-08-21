package dao;

import model.Categoria;

import java.util.List;

/**
 * Created by foleac on 7/5/2015.
 */
public interface CategoriaDAO {

    public void insertarCategoria(Categoria categoria);

    public List<Categoria> getCategorias();

    public Categoria getCategoria(String nombre);

}
