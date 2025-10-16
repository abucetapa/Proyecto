package es.upsa.programacion.Controladores;


import es.upsa.programacion.Modelos.Vuelo;

import java.util.ArrayList;
import java.util.List;

public class VueloController {
    private List<Vuelo> vuelos;

    public VueloController() {
        this.vuelos = new ArrayList<>();
    }

    public boolean añadirVuelo(Vuelo vuelo){
        if(vuelo.getIdVuelo() != null && vuelo.getSalida() != null && vuelo.getDestino() != null && vuelo.getFecha() != null){
            vuelos.add(vuelo);
            System.out.println("El vuelo" + vuelo.getIdVuelo() + "ha sido añadido correctamente.");
            return true;
        }else return false;

    }

    //Condiiciones que se pueda buscar por cualquiera de los parametros
    public Vuelo buscarVueloId(String idVuelo){

        for( Vuelo v : vuelos){
            if(v.getIdVuelo().equals(idVuelo)){
                return v;
            }
        }
        return null;
    }


    public boolean modificarVuelo(Vuelo vuelo){
        if (vuelo == null || vuelo.getIdVuelo() == null) {
            System.out.println("Vuelo nulo o ID inválido.");
            return false;
        }

        Vuelo vueloExiste = buscarVueloId(vuelo.getIdVuelo());
        if(vueloExiste != null){
            vueloExiste.setSalida(vuelo.getSalida());
            vueloExiste.setDestino(vuelo.getDestino());
            vueloExiste.setFecha(vuelo.getFecha());
            return true;
        }

        System.out.println("Vuelo no encontrado");
        return false;
    }

    public boolean eliminarVuelo(String idVuelo){
        if(idVuelo == null){
            System.out.println("El vuelo no existe.");
            return false;
        }

        Vuelo vueloExiste = buscarVueloId(idVuelo);
        if(vueloExiste != null){
            vuelos.remove(vueloExiste);
            return true;
        }
        System.out.println("Vuelo no encontrado.");
        return false;
    }
    public void reservaAsiento(String idVuelo){
        Vuelo vuelo = buscarVueloId(idVuelo);

        if(vuelo.getAsientos() > 0){
            vuelo.setAsientos(vuelo.getAsientos() - 1);  // ← Aquí restas 1
            System.out.println("Asiento reservado. Asientos disponibles: " + vuelo.getAsientos());

        } else {
            System.out.println("No hay asientos disponibles.");

        }
    }
}
