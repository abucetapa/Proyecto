package es.upsa.programacion.Vista;

import es.upsa.programacion.Controladores.UsuarioController;
import es.upsa.programacion.Controladores.VueloController;
import es.upsa.programacion.Modelos.Agencia;
import es.upsa.programacion.Modelos.Vuelo;
import es.upsa.programacion.Modelos.Usuario;
import es.upsa.programacion.Menu;

import java.util.Scanner;

public class VistaVuelo {
    private VueloController vueloController;
    private UsuarioController usuarioController;
    private Menu menu;
    Scanner sc;

    public VistaVuelo(Agencia agencia) {
        this.vueloController = new VueloController(agencia);
        this.usuarioController = new UsuarioController(agencia);
        this.sc = new Scanner(System.in);
    }

    public int addVueloVista(){
        System.out.println("**Añadir vuelo**");
        Scanner sc = new Scanner(System.in);

        System.out.println("Inserte el id del vuelo:");
        String idVuelo = sc.nextLine();

        System.out.println("Inserte el lugar de salida el vuelo:");
        String lugarSalida = sc.nextLine();

        System.out.println("Inserte el destino del vuelo:");
        String destino = sc.nextLine();

        System.out.println("Inserte la fecha del vuelo:");
        String fecha = sc.nextLine();

        System.out.println("Inserte el precio del billete");
        String precioStr = sc.nextLine();

        Double precioDouble = null;
        if(!precioStr.isEmpty()){
            try {
                precioDouble = Double.parseDouble(precioStr);
            } catch (NumberFormatException e) {
                System.out.println("Precio inválido, no se modificará.");
            }
        }

        System.out.println("Inserte asientos disponibles:");
        String asientosStr = sc.nextLine();

        Integer asientosInt = null;
        if(!asientosStr.isEmpty()){
            try {
                asientosInt = Integer.parseInt(asientosStr);
            } catch (NumberFormatException e) {
                System.out.println("Número de asientos inválido, no se modificará.");
            }
        }

        Vuelo vuelo = new Vuelo(idVuelo,lugarSalida,destino,fecha,precioDouble,asientosInt);

        boolean vueloAñadido = vueloController.addVuelo(vuelo);

        if(vueloAñadido){
            System.out.println("✓ Vuelo añadido correctamente.");
            return 0;
        } else {
            return -2;
        }
    }

    public int modificarVueloMenu(){
        System.out.println("Id de vuelo a modificar:");
        String idVuelo = sc.nextLine();

        Vuelo vueloExiste = vueloController.buscarVueloId(idVuelo);

        if(vueloExiste == null){
            System.out.println("Vuelo no encontrado.");
            return -9;
        }

        System.out.println("Vuelo actual: " + vueloExiste);
        System.out.println("\n--- Modificar Vuelo ---");
        System.out.println("(Presione Enter para no modificar un campo)");
        System.out.print("Nueva salida: ");
        String nuevaSalida = sc.nextLine();

        System.out.print("Nuevo destino: ");
        String nuevoDestino = sc.nextLine();

        System.out.print("Nueva fecha: ");
        String nuevaFecha = sc.nextLine();

        System.out.print("Nuevos asientos disponibles: ");
        String asientosStr = sc.nextLine();

        System.out.print("Nuevo precio: ");
        String precioStr = sc.nextLine();

        Double nuevoPrecio = null;
        if(!precioStr.isEmpty()){
            try {
                nuevoPrecio = Double.parseDouble(precioStr);
            } catch (NumberFormatException e) {
                System.out.println("Precio inválido, no se modificará.");
            }
        }

        Integer nuevosDisponibles = null;
        if(!asientosStr.isEmpty()){
            try {
                nuevosDisponibles = Integer.parseInt(asientosStr);
            } catch (NumberFormatException e) {
                System.out.println("Número de asientos inválido, no se modificará.");
            }
        }

        boolean modificado = vueloController.modificarVuelo(idVuelo, nuevaSalida, nuevoDestino,
                nuevaFecha, nuevoPrecio, nuevosDisponibles);

        if(modificado){
            return 0;
        } else {
            return -8;
        }

    }

    public int buscarVueloVistaId(){
        System.out.println("Ingrese el id del vuelo");
        String idVuelo = sc.nextLine();

        Vuelo vueloEncontrado = vueloController.buscarVueloId(idVuelo);

        if(vueloEncontrado == null){
            return -1;
        }else {
            System.out.println("Datos del vuelo " + idVuelo +": "+ vueloEncontrado);
            return 0;
        }

    }

    public Vuelo buscarVueloVistaObjeto(){
        System.out.println("Ingrese el id del vuelo");
        String idVuelo = sc.nextLine();

        Vuelo vueloEncontrado = vueloController.buscarVueloId(idVuelo);

        if(vueloEncontrado == null){
            return null;
        }else {
            System.out.println("Datos del vuelo " + idVuelo +": "+ vueloEncontrado);
            return vueloEncontrado;
        }

    }

    public int reservaAsiento(Usuario usuario,String idVuelo){
        Vuelo vuelo = vueloController.buscarVueloId(idVuelo);

        System.out.println("Cuantos asientos quiere reservar:");
        String nReservas = sc.nextLine();
        int intNReservas = Integer.parseInt(nReservas);

        if(usuarioController.addVueloReservado(usuario, vuelo, intNReservas)){
            System.out.println("Vuelo/s Reservado correctamente.");
            return 0;
        }
        return -8;
    }

    public int mostrarDisponibilidad(Vuelo vuelo){
        if(vuelo.getdisponibles() == 0){
            return -8;
        }else{
            System.out.println("Asientos disponibles: " + vuelo.getdisponibles());
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
        System.out.println("=== Añadir nuevo vuelo ===");
        System.out.print("Salida: ");
        String salida = sc.nextLine();

        System.out.print("Destino: ");
        String destino = sc.nextLine();

        System.out.print("Fecha (ej: 2025-11-10): ");
        String fecha = sc.nextLine();

        System.out.print("Precio: ");
        Double precio = sc.nextDouble();

        System.out.print("Plazas disponibles: ");
        Integer disponibles = sc.nextInt();
        sc.nextLine();

        System.out.print("¿El vuelo es nacional o internacional? (N/I): ");
        String tipo = sc.nextLine().trim().toUpperCase();

        String idVuelo = generarIdVuelo(tipo);

        Vuelo nuevoVuelo = new Vuelo(idVuelo, salida, destino, fecha, precio, disponibles);

        boolean exito = vueloController.addVuelo(nuevoVuelo);

        if (exito) {
            System.out.println("Vuelo añadido correctamente con ID: " + idVuelo);
        } else {
            System.out.println("Error al añadir el vuelo.");
        }
    }
}