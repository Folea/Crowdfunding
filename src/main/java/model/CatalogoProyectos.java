package model;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by foleac on 7/5/2015.
 */
public class CatalogoProyectos {
    private static CatalogoProyectos catalogoProyectos = null;
    private LinkedList<Proyecto> proyectos;

    private CatalogoProyectos() {

    }

    public static CatalogoProyectos getCatalogoProyectos(){
        if(catalogoProyectos == null) {
            catalogoProyectos = new CatalogoProyectos();
        }

        return catalogoProyectos;
    }

    public Proyecto getProyecto(String proyecto) {
        for(Proyecto p : proyectos) {
            if(p.getNombre().equals(proyecto)) {
                return p;
            }
        }

        return null;
    }

    public void addProyecto(Proyecto proyecto) {
        proyectos.add(proyecto);
    }

    public void cargarProyectos(List<Proyecto> proyectos) {
        this.proyectos = new LinkedList<Proyecto>(proyectos);
    }

    public List<Proyecto> getProyectos() {
        return proyectos;
    }

    public void updateProyecto(Proyecto proyecto) {
        proyectos.remove(proyecto);
        proyectos.add(proyecto);
    }
}
