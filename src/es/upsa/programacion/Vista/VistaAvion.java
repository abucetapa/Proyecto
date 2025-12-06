package es.upsa.programacion.Vista;

import es.upsa.programacion.Controladores.AvionController;
import es.upsa.programacion.Modelos.Agencia;
import es.upsa.programacion.Modelos.Avion;

import java.util.List;

public class VistaAvion {
    private AvionController avionController;
    private Agencia agencia;
    private List<Avion> aviones;
    public VistaAvion(Agencia agencia){
        this.agencia = agencia;
        this.avionController = new AvionController(agencia);
        this.aviones = agencia.getAviones();
    }

    public String generarIdAvion(String compania){

        String sufijo = null;

        String[] palabras = compania.trim().split("\\s+");

        String prefijo = null;

        int siguienteId = agencia.getAviones().size() + 1;

        if(palabras.length == 2){
            sufijo = String.valueOf(palabras[0].charAt(0)).toUpperCase() + String.valueOf(palabras[1].charAt(0)).toUpperCase();
        }
        if(palabras.length == 1){
            sufijo = palabras[0].substring(0, 2).toUpperCase();
        }

        String idAvion = sufijo + String.valueOf(siguienteId);

        return idAvion;
    }

}
