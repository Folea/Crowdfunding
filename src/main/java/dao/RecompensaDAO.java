package dao;

import model.Recompensa;

import java.util.List;

/**
 * Created by foleac on 7/5/2015.
 */
public interface RecompensaDAO {

    public void insertarRecompensa(Recompensa recompensa);

    public Recompensa getRecompensa(int id);

    public List<Recompensa> gerRecompensasParaProyecto(String proyecto);

    public void updateRecompensa(Recompensa recompensa);
}
