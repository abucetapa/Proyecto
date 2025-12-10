package es.upsa.programacion.Controladores;

import es.upsa.programacion.Modelos.*;

import java.util.*;


public class VueloController {
    private List<Vuelo> vuelos;
    private Agencia agencia;
    private Map<String,Usuario> usuariosMap;

    public VueloController(Agencia agencia) {
        this.agencia = agencia;
        this.vuelos = agencia.getVuelos();
        this.usuariosMap = agencia.getUsuariosMap();


    }

    // FUNCIONES AÑADIR, ELIMINAR Y MODIFICAR

    public boolean addVueloComercial(Vuelo vuelo) {
        // Comprueba que todos los parametros no son nulos
        if(vuelo instanceof VueloComercial vc){
            if (vc.getIdVuelo() != null &&
                    vc.getsalida() != null &&
                    vc.getdestino() != null &&
                    vc.getTerminal() != null &&
                    vc.getPuertaEmb() != null &&
                    vc.getfecha() != null &&
                    vc.getPrecio() != null) {
                vuelos.add(vc); // Añade el vuelo al ArrayList de vuelos
                System.out.println("El vuelo " + vuelo.getIdVuelo() + " ha sido añadido correctamente.");
                return true;
            } else return false;
        }
        return false;

    }
    public boolean addVueloPrivado(Vuelo vuelo) {
        // Comprueba que todos los parametros no son nulos
        if(vuelo instanceof VueloPrivado vc){
            if (vc.getIdVuelo() != null &&
                    vc.getsalida() != null &&
                    vc.getdestino() != null &&
                    vc.getfecha() != null &&
                    vc.getPrecio() != null) {
                vuelos.add(vc); // Añade el vuelo al ArrayList de vuelos
                System.out.println("El vuelo " + vuelo.getIdVuelo() + " ha sido añadido correctamente.");
                return true;
            } else return false;
        }
        return false;

    }

    public boolean modificarVuelo(String idVuelo, String salida, String destino,
                                  String fecha, Double precio, Integer disponibles) {

        // Comprueba que los parametros no esten vacíos
        // En caso de no estar vacíos, modifica los valores a traves de las funciones set
        Vuelo vueloExiste = buscarVueloId(idVuelo);
        if (idVuelo.isEmpty()) {
            vueloExiste.setIdVuelo(idVuelo);
        }


        // Solo modifica si el valor no es nulo o vacío
        if (!salida.isEmpty()) {
            vueloExiste.setsalida(salida);
        }

        if (!destino.isEmpty()) {
            vueloExiste.setdestino(destino);
        }

        if (!fecha.isEmpty()) {
            vueloExiste.setfecha(fecha);
        }

        VueloComercial vcExiste = (VueloComercial) vueloExiste;
        if (precio != null && precio > 0) {
            vcExiste.setPrecio(precio);
        }

        if (disponibles != null && disponibles >= 0) {
            vueloExiste.setAsientos(disponibles);
        }

        return true;
    }

    public boolean eliminarVuelo(String idVuelo) {

        Vuelo vueloExiste = buscarVueloId(idVuelo); // Comprueba que el vuelo existe y lo guarda

        if (vueloExiste != null) {
            vuelos.remove(vueloExiste); // Elimina el vuelo del ArrayList
            return true;
        }
        return false;
    }

    // FUNCION BUSQUEDA

    public Vuelo buscarVueloId(String idVuelo) {
        for (Vuelo v : vuelos) { // Recorre Arraylist de vuelos
            if (v.getIdVuelo().equals(idVuelo)) { // Comprueba que los id coinciden
                return v; // Devuelve el vuelo
            }
        }
        return null;
    }
    public List<Vuelo> buscarVuelosPorSalida(String salida) {

        List<Vuelo> resultados = new ArrayList<>();
        for (Vuelo v : vuelos) {
            if (v.getsalida().equalsIgnoreCase(salida)) { // equalsIgnoreCase para ignorar mayús/minús
                resultados.add(v);
            }
        }
        return resultados;
    }

    public List<Vuelo> buscarVuelosPorDestino(String destino) {
        List<Vuelo> resultados = new ArrayList<>();
        for (Vuelo v : vuelos) {
            if (v.getdestino().equalsIgnoreCase(destino)) {
                resultados.add(v);
            }
        }
        return resultados;
    }

    // FUNCIONES MOSTRAR
    public int mostrarVuelos() {
        System.out.println("**Vuelos**");


        if (vuelos.isEmpty()) { // Array de vuelos vacío
            return -5;
        }
        vuelos.sort(Comparator.comparing(Vuelo::getIdVuelo)); //Ordena los vuelos
        for (Vuelo v : vuelos) { // Recorre Array de vuelos del Cliente
            System.out.println(v); //Muestra datos del vuelo
        }
        return 0;
    }

    public int mostrarVuelosAdmin() {
        System.out.println("**Vuelos**");


        if (vuelos.isEmpty()) { // Array de vuelos vacío
            return -5;
        }
        vuelos.sort(Comparator.comparing(Vuelo::getIdVuelo)); //Ordena los vuelos
        for (Vuelo v : vuelos) { // Recorre Array de vuelos del Cliente
            System.out.println(v  + "\tAvion: " + v.getAvion().getIdAvion()); //Muestra datos del vuelo
        }
        return 0;
    }
    // Mostrar vuelos reservados del cliente
    public int mostrarVuelosCliente(Usuario usuario) {
        System.out.println("**Vuelos**");

        // Verificar que vuelos no sea nula o vacía
        if (vuelos == null || vuelos.isEmpty()) {
            return -5;
        }

        // Verificar que el cliente no sea null
        if (usuario == null) {
            return -1;
        }
        Cliente clienteLogueado = (Cliente) usuario;

        // Iterar sobre todos los vuelos disponibles
        for (Vuelo v : vuelos) {
            int contadorReservas = 0;

            // Verificar si el cliente tiene vuelos reservados
            if (clienteLogueado.getReservados() != null) {
                // Contar cuántas veces este usuario tiene reservado este vuelo
                for (Vuelo vueloReservado : clienteLogueado.getReservados()) {
                    if (vueloReservado != null && vueloReservado.equals(v)) {
                        contadorReservas++;
                    }
                }
            }

            // Mostrar el vuelo
            if (contadorReservas > 0) {
                System.out.println(v + ": El usuario tiene " + contadorReservas + " reserva(s) de este vuelo.");
            } else {
                System.out.println(v);
            }
        }

        return 0;
    }

    // SET
    public Set<String> getSalidasDisponibles() {
        Set<String> salidas = new HashSet<>();
        for (Vuelo v : vuelos) {
            salidas.add(v.getsalida()); // El Set se encarga de no guardar duplicados
        }
        return salidas;
    }

    public Set<String> getDestinosDisponibles() {
        Set<String> destinos = new HashSet<>();
        for (Vuelo v : vuelos) {
            destinos.add(v.getdestino());
        }
        return destinos;
    }



}