package es.upsa.programacion.Controladores;

import es.upsa.programacion.Modelos.Agencia;
import es.upsa.programacion.Modelos.Cliente;
import es.upsa.programacion.Modelos.Usuario;
import es.upsa.programacion.Modelos.Vuelo;

import java.util.ArrayList;
import java.util.List;

public class VueloController {
    private List<Vuelo> vuelos;
    private Agencia agencia;
    private List<Usuario> usuarios;

    public VueloController(Agencia agencia) {
        this.agencia = agencia;
        this.vuelos = agencia.getVuelos();
        vuelos = new ArrayList<>(agencia.getVuelos());
        usuarios = new ArrayList<>(agencia.getUsuarios());
    }


    public boolean addVuelo(Vuelo vuelo) {

        if (vuelo.getIdVuelo() != null && vuelo.getsalida() != null && vuelo.getdestino() != null && vuelo.getfecha() != null && vuelo.getprecio() != null) {
            vuelos.add(vuelo);
            System.out.println("El vuelo" + vuelo.getIdVuelo() + " ha sido añadido correctamente.");
            return true;
        } else return false;
    }


    public Vuelo buscarVueloId(String idVuelo) {
        for (Vuelo v : vuelos) {
            if (v.getIdVuelo().equals(idVuelo)) {
                return v;
            }
        }
        return null;
    }


    public boolean modificarVuelo(String idVuelo, String salida, String destino,
                                  String fecha, Float precio, Integer disponibles) {


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

        if (precio != null && precio > 0) {
            vueloExiste.setprecio(Double.valueOf(precio));
        }

        if (disponibles != null && disponibles >= 0) {
            vueloExiste.setdisponibles(disponibles);
        }

        return true;
    }

    public int eliminarVuelo(String idVuelo) {

        Vuelo vueloExiste = buscarVueloId(idVuelo);
        if (vueloExiste != null) {
            vuelos.remove(vueloExiste);
            return 0;
        }
        return -1;
    }


    public int mostrarVuelos() {
        System.out.println("**Vuelos**");


        if (vuelos == null || vuelos.isEmpty()) {
            return -5;
        }
        for (Vuelo v : vuelos) {
            System.out.println(v);
        }
        return 0;
    }

    public int mostrarVuelosCliente(Usuario usuario) {
        System.out.println("**Vuelos**");

        // Verificar que vuelos no sea nula o vacía
        if (vuelos == null || vuelos.isEmpty()) {
            return -5;
        }

        // Verificar que el cliente logueado no sea null
        if (usuario == null) {
            return -1;
        }
        Cliente clienteLogueado = (Cliente) usuario;

        // Iterar sobre todos los vuelos disponibles
        for (Vuelo v : vuelos) {
            int contadorReservas = 0;

            // Verificar si el cliente logueado tiene vuelos reservados
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
}