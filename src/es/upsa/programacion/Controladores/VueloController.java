package es.upsa.programacion.Controladores;

import es.upsa.programacion.Modelos.Agencia;
import es.upsa.programacion.Modelos.Vuelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class VueloController {
    private List<Vuelo> vuelos;
    private Agencia agencia;

    public VueloController(Agencia agencia) {
        this.agencia = agencia;
        this.vuelos = agencia.getVuelos();
    }


    public boolean addVuelo(Vuelo vuelo){

        if(vuelo.getIdVuelo() != null && vuelo.getsalida() != null && vuelo.getdestino() != null && vuelo.getfecha() != null && vuelo.getprecio() != null){
            vuelos.add(vuelo);
            System.out.println("El vuelo" + vuelo.getIdVuelo() + " ha sido añadido correctamente.");
            return true;
        }else return false;
    }


    public Vuelo buscarVueloId(String idVuelo){
        for( Vuelo v : vuelos){
            if(v.getIdVuelo().equals(idVuelo)){
                return v;
            }
        }
        return null;
    }


    public boolean modificarVuelo(String idVuelo, String salida, String destino,
                                  String fecha, Float precio, Integer disponibles){

        if (idVuelo == null || idVuelo.isEmpty()) {
            System.out.println("ID de vuelo inválido.");
            return false;
        }

        Vuelo vueloExiste = buscarVueloId(idVuelo);

        if(vueloExiste == null){
            System.out.println("Vuelo no encontrado.");
            return false;
        }

        if(idVuelo != null && !idVuelo.isEmpty()){
            vueloExiste.setIdVuelo(salida);
        }
        // Solo modifica si el valor no es nulo o vacío
        if(salida != null && !salida.isEmpty()){
            vueloExiste.setsalida(salida);
        }

        if(destino != null && !destino.isEmpty()){
            vueloExiste.setdestino(destino);
        }

        if(fecha != null && !fecha.isEmpty()){
            vueloExiste.setfecha(fecha);
        }

        if(precio != null && precio > 0){
            vueloExiste.setprecio(Double.valueOf(precio));
        }

        if(disponibles != null && disponibles >= 0){
            vueloExiste.setdisponibles(disponibles);
        }

        return true;
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

        if(vuelo.getdisponibles() > 0){
            vuelo.setdisponibles(vuelo.getdisponibles() - 1);  // ← Aquí restas 1
            System.out.println("Asiento reservado. Asientos disponibles: " + vuelo.getdisponibles());

        } else {
            System.out.println("No hay asientos disponibles.");

        }
    }

    public int mostrarVuelos(){
        System.out.println("**Vuelos**");


        if (vuelos == null || vuelos.isEmpty()) {
            return -5;
        } else {
            for (Vuelo v : vuelos) {
                System.out.println(v);
            }
            System.out.println();
            return 0;
        }

    }
}
