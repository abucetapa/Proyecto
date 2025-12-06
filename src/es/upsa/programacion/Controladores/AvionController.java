package es.upsa.programacion.Controladores;

import es.upsa.programacion.Modelos.Agencia;
import es.upsa.programacion.Modelos.Avion;

import java.util.ArrayList;

public class AvionController {

    private ArrayList<Avion> aviones;

    public AvionController(Agencia agencia){
        this.aviones = agencia.getAviones();
    }

    public int mostrarAviones(){

        System.out.println("**AVIONES**");
        if(aviones.isEmpty()){
            return -10;
        }

        Avion.tipoAvion tipoAnterior = null;

        for(Avion a : aviones){
            if(tipoAnterior == null || tipoAnterior != a.gettAvion()){
                System.out.println("\n** " + a.gettAvion().getDescripcion().toUpperCase() + "**");
                tipoAnterior = a.gettAvion();
            }
            System.out.println(a);
        }
        return 0;
    }

    public Avion buscarAvionId(String idAvion){
        for(Avion a : aviones){
            if(a.getIdAvion().equals(idAvion)){
                return a;
            }
        }
        return null;
    }



}
