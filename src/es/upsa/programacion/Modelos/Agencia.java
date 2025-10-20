package es.upsa.programacion.Modelos;

import es.upsa.programacion.Controladores.VueloController;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Agencia {

    private ArrayList<Vuelo> vuelos;
    private ArrayList<Usuario> usuarios;

    public Agencia(){
        this.vuelos = new ArrayList<>();
        this.usuarios = new ArrayList<>();
    }

    public ArrayList<Vuelo> getVuelos() {
        return vuelos;
    }
    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }

    //funcion añadirVuelo
    //funcion añadirUsuario

    public int addUsuario(String idUser, String nombre, String dni, String password, String email, String telefono, boolean admin){
        Usuario nuevoUsuario = new Usuario(idUser, nombre, dni, password, email, telefono, admin);

        if(usuarios.contains(nuevoUsuario)){
            return -4;
        }else{
            usuarios.add(nuevoUsuario);
            return 0;
        }

    }

    public int addVuelo(String idVuelo, String salida, String destino, String fecha, Double precio, Integer disponibles){
        Vuelo nuevoVuelo = new Vuelo(idVuelo, salida, destino, fecha, precio, disponibles);
        if(vuelos.contains(nuevoVuelo)){
            return -4;
        }else {
            vuelos.add(nuevoVuelo);
            return 0;
        }
    }



}
