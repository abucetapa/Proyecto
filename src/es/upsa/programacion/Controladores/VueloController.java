package es.upsa.programacion.Controladores;

import es.upsa.programacion.Modelos.Agencia;
import es.upsa.programacion.Modelos.Cliente;
import es.upsa.programacion.Modelos.Usuario;
import es.upsa.programacion.Modelos.Vuelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class VueloController {
    private List<Vuelo> vuelos;
    private Agencia agencia;
    private List<Usuario> usuarios;

    public VueloController(Agencia agencia) {
        this.agencia = agencia;
        this.vuelos = agencia.getVuelos();
        this.usuarios = agencia.getUsuarios();

    }

    // Añadir vuelo
    public boolean addVuelo(Vuelo vuelo) {
        // Comprueba que todos los parametros no son nulos
        if (vuelo.getIdVuelo() != null && vuelo.getsalida() != null && vuelo.getdestino() != null && vuelo.getfecha() != null && vuelo.getprecio() != null) {
            vuelos.add(vuelo); // Añade el vuelo al ArrayList de vuelos
            System.out.println("El vuelo" + vuelo.getIdVuelo() + " ha sido añadido correctamente.");
            return true;
        } else return false;
    }

    // Modificar vuelo
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

        if (precio != null && precio > 0) {
            vueloExiste.setprecio(precio);
        }

        if (disponibles != null && disponibles >= 0) {
            vueloExiste.setdisponibles(disponibles);
        }

        return true;
    }

    // Eliminar vuelo
    public int eliminarVuelo(String idVuelo) {

        Vuelo vueloExiste = buscarVueloId(idVuelo); // Comprueba que el vuelo existe y lo guarda

        if (vueloExiste != null) {
            vuelos.remove(vueloExiste); // Elimina el vuelo del ArrayList
            return 0;
        }
        return -1;
    }

    // Buscar vuelo
    public Vuelo buscarVueloId(String idVuelo) {
        for (Vuelo v : vuelos) { // Recorre Arraylist de vuelos
            if (v.getIdVuelo().equals(idVuelo)) { // Comprueba que los id coinciden
                return v; // Devuelve el vuelo
            }
        }
        return null;
    }

    // Mostrar vuelos
    public int mostrarVuelos() {
        System.out.println("**Vuelos**");


        if (vuelos.isEmpty()) { // Array de vuelos vacío
            return -5;
        }
        for (Vuelo v : vuelos) { // Recorre Array de vuelos del Cliente
            System.out.println(v); //Muestra datos del vuelo
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

    private String generarIdVuelo(String tipo) {
        int numeroAleatorio = (int) (Math.random() * 9999) + 1;
        if (tipo.equals("I")) {
            return "VI-" + numeroAleatorio;
        } else {
            return "VN-" + numeroAleatorio;
        }
    }

    public void añadirVueloPorId() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Añadir nuevo vuelo ===");
        System.out.print("Salida: ");
        String salida = scanner.nextLine();

        System.out.print("Destino: ");
        String destino = scanner.nextLine();

        System.out.print("Fecha (ej: 2025-11-10): ");
        String fecha = scanner.nextLine();

        System.out.print("Precio: ");
        Double precio = scanner.nextDouble();

        System.out.print("Plazas disponibles: ");
        Integer disponibles = scanner.nextInt();
        scanner.nextLine();

        System.out.print("¿El vuelo es nacional o internacional? (N/I): ");
        String tipo = scanner.nextLine().trim().toUpperCase();

        String idVuelo = generarIdVuelo(tipo);

        Vuelo nuevoVuelo = new Vuelo(idVuelo, salida, destino, fecha, precio, disponibles);
        addVuelo(nuevoVuelo);
    }
}