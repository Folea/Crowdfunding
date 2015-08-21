package dao;

import model.EstadoProyecto;
import model.Proyecto;

import java.util.List;

/**
 * Created by foleac on 7/5/2015.
 */
public interface ProyectoDAO {

    public void anadirProyecto(Proyecto proyecto);

    public Proyecto getProyecto(String nombre);

    public List<Proyecto> getProyectos();

    public void updateProyecto(Proyecto proyecto);

    public List<Proyecto> getProyectosEstado(EstadoProyecto estado);
}
