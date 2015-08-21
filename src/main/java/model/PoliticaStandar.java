package model;

import utils.ApoyanosConstans;

/**
 * Created by foleac on 7/5/2015.
 */

public class PoliticaStandar extends PoliticaComisiones {

    @Override
    public double calcular(Proyecto proyecto) {
        if (proyecto.getCategoria().getName().equalsIgnoreCase("cine") && proyecto.getFinanciacion() >= 6000){
            return ApoyanosConstans.porcentajeCine;
        }

        if (proyecto.getCategoria().getName().equalsIgnoreCase("social")) return ApoyanosConstans.porcentajeSocial;

        return ApoyanosConstans.porcentajeDefecto; //por defecto
    }
}
