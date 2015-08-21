package dao;

import model.Mensaje;

/**
 * Created by foleac on 7/5/2015.
 */
public interface MensajeDAO {

    public void insertarMensaje(Mensaje mensaje);

    public void updateMensaje(Mensaje mensaje);

    public Mensaje getMensajePorAsunto(String asunto);

}
