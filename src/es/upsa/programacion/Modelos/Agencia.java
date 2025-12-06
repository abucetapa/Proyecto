package es.upsa.programacion.Modelos;


import java.lang.reflect.Array;
import java.util.ArrayList;

public class Agencia {

    private ArrayList<Avion> aviones;
    private ArrayList<Vuelo> vuelos;
    private ArrayList<Usuario> usuarios;

    public Agencia(){
        this.aviones = new ArrayList<>();
        this.vuelos = new ArrayList<>();
        this.usuarios = new ArrayList<>();
    }
    public ArrayList<Avion> getAviones() {
        return aviones;
    }
    public ArrayList<Avion> getAvionesComerciales() {
        // 1. Creamos una lista temporal nueva cada vez que llamamos al método
        ArrayList<Avion> listaComerciales = new ArrayList<>();


        for (Avion avion : aviones) {
            if (avion.gettAvion().getDescripcion().equals("Comercial")) {
                listaComerciales.add(avion);
            }
        }
        return listaComerciales;
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



    public void addVuelo(String idVuelo, String idAvion, String salida, String destino,String terminal, String puertaEmb, String fecha, Double precio){
        // Buscar el avión por ID
        Avion avion = buscarAvionId(idAvion);

        if(avion == null){
            return;
        }

        // Crear el vuelo con el objeto Avion encontrado
        VueloComercial nuevoVuelo = new VueloComercial(idVuelo, avion, salida, destino, terminal, puertaEmb, fecha, precio);

        if(!vuelos.contains(nuevoVuelo)){
            vuelos.add(nuevoVuelo);
            avion.setDisponible(false);
        }
    }

    public void addAvion(String idAvion, String compania, Avion.tipoAvion tipoAvion){
        Avion avion = new Avion(idAvion,compania, tipoAvion);
        if(!aviones.contains(avion)){
            aviones.add(avion);
        }
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
