package es.upsa.programacion.Modelos;


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

    public void addCliente(String idUser, String nombre, String dni, String password, String email, String telefono){

        Cliente nuevoCliente = new Cliente(idUser, nombre, dni, password, email, telefono);

        if(!usuarios.contains(nuevoCliente)) {
            usuarios.add(nuevoCliente);
        }
    }

    public void addAdmin(String idUser, String password){
        Administrador administrador = new Administrador(idUser, password);
        if(!usuarios.contains(administrador)){
            usuarios.add(administrador);
        }
    }



    public void addVuelo(String idVuelo, String salida, String destino, String fecha, Double precio, Integer disponibles){
        Vuelo nuevoVuelo = new Vuelo(idVuelo, salida, destino, fecha, precio, disponibles);
        if(!vuelos.contains(nuevoVuelo)){
            vuelos.add(nuevoVuelo);
        }
    }



}
