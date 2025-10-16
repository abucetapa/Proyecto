package es.upsa.programacion.Modelos;

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
    public ArrayList<Usuario> setUsuarios() {
        return usuarios;
    }

}
