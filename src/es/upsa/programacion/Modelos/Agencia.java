package es.upsa.programacion.Modelos;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Agencia {


    private ArrayList<Avion> aviones;
    private ArrayList<Vuelo> vuelos;
    private Map<String, Usuario> usuariosMap;

    //Mapas para busquedas rapidas
    private Map<String, Cliente> dniClientesMap;
    private Map<String, Cliente> emailClientesMap;
    private Map<String,Cliente> telefonoClientesMap;

    public Agencia(){
        this.aviones = new ArrayList<>();
        this.vuelos = new ArrayList<>();
        this.usuariosMap = new HashMap<>();
        this.dniClientesMap = new HashMap<>();
        this.emailClientesMap = new HashMap<>();
        this.telefonoClientesMap = new HashMap<>();
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

    public ArrayList<Avion> getAvionesPrivados() {
        // 1. Creamos una lista temporal nueva cada vez que llamamos al metodo
        ArrayList<Avion> listaComerciales = new ArrayList<>();


        for (Avion avion : aviones) {
            if (avion.gettAvion().getDescripcion().equals("Privado") || avion.gettAvion().getDescripcion().equals("Privado negocios")) {
                listaComerciales.add(avion);
            }
        }
        return listaComerciales;
    }


    public ArrayList<Vuelo> getVuelos() {
        return vuelos;
    }
    public Map<String,Usuario> getUsuariosMap() {
        return usuariosMap;
    }
    public Map<String, Cliente> getDniClientesMap() {
        return dniClientesMap;
    }
    public Map<String, Cliente> getEmailClientesMap() {
        return emailClientesMap;
    }
    public Map<String, Cliente> getTelefonoClientesMap() {
        return telefonoClientesMap;
    }
    public ArrayList<Usuario> getUsuariosMapList() {
        return new ArrayList<>(usuariosMap.values());
    }

    // FUNCIONES AÑADIR

    public void addCliente(String idUser, String nombre, String dni, String password, String email, String telefono){

        if(!usuariosMap.containsKey(idUser)) {
            Cliente nuevoCliente = new Cliente(idUser, nombre, dni, password, email, telefono);
            usuariosMap.put(idUser, nuevoCliente);

            dniClientesMap.put(dni, nuevoCliente);
            emailClientesMap.put(email, nuevoCliente);
            telefonoClientesMap.put(telefono, nuevoCliente);
        }
    }

    public void addAdmin(String idUser, String password){
        if(!usuariosMap.containsKey(idUser)) {
            Administrador administrador = new Administrador(idUser, password);
            usuariosMap.put(idUser,administrador);
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

    public void addAvion(String idAvion, String compania, Avion.tipoAvion tipoAvion, String ciudadActual){
        Avion avion = new Avion(idAvion,compania, tipoAvion, ciudadActual);
        if(!aviones.contains(avion)){
            aviones.add(avion);
        }
    }

    //FUNCION BUSCAR
    public Avion buscarAvionId(String idAvion){
        for(Avion a : aviones){
            if(a.getIdAvion().equals(idAvion)){
                return a;
            }
        }
        return null;
    }



}
