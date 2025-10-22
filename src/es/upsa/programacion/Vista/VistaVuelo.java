package es.upsa.programacion.Vista;

import es.upsa.programacion.Controladores.UsuarioController;
import es.upsa.programacion.Controladores.VueloController;
import es.upsa.programacion.Modelos.Agencia;
import es.upsa.programacion.Modelos.Vuelo;
import es.upsa.programacion.Modelos.Usuario;
import es.upsa.programacion.Menu;
import es.upsa.programacion.Controladores.VueloController;

import java.util.Scanner;

public class VistaVuelo {
    private Agencia agencia;
    private Usuario usuario;
    private VueloController vueloController;
    private UsuarioController usuarioController;
    private Menu menu;
    Scanner sc = new Scanner(System.in);

    public VistaVuelo(Agencia agencia) {
        this.agencia = agencia;
        this.vueloController = new VueloController(agencia);
        this.usuarioController = new UsuarioController(agencia);
    }



    public int modificarVueloMenu(){
        System.out.println("Id de vuelo a modificar:");
        String idVuelo = sc.nextLine();

        Vuelo vueloExiste = vueloController.buscarVueloId(idVuelo);

        if(vueloExiste == null){
            System.out.println("Vuelo no encontrado.");
            return -9;//Error vuelo no encontrado
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
        Float nuevoPrecio = null;
        if(!precioStr.isEmpty()){
            try {
                nuevoPrecio = Float.parseFloat(precioStr);
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
            return 0; //Vuelo modificado correctamente
        } else {
            return -8; //Error. No se ha podido modificar el vuelo

        }

    }

    public int buscarVueloVista(){
        System.out.println("Buscar vuelo por id:");
        String idVuelo = sc.nextLine();

        Vuelo vueloEncontrado = vueloController.buscarVueloId(idVuelo);

        if(vueloEncontrado == null){

            return -1;
        }else {
            System.out.println("Datos del vuelo " + idVuelo +": "+ vueloEncontrado.toString());
            return 0;
        }

    }

    public Vuelo buscarVueloVistaObjeto(){
        System.out.println("Buscar vuelo por id:");
        String idVuelo = sc.nextLine();

        Vuelo vueloEncontrado = vueloController.buscarVueloId(idVuelo);

        if(vueloEncontrado == null){
            return null;
        }else {
            System.out.println("Datos del vuelo " + idVuelo +": "+ vueloEncontrado.toString());
            return vueloEncontrado;
        }

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
        Double precio = Double.parseDouble(precioStr);

        System.out.println("Inserte asientos disponibles:");
        String asientosInt = sc.nextLine();
        Integer asientosDisponibles = Integer.parseInt(asientosInt);

        Vuelo vuelo = new Vuelo(idVuelo,lugarSalida,destino,fecha,precio,asientosDisponibles);

        boolean vueloAñadido = vueloController.addVuelo(vuelo);

        if(vueloAñadido){
            System.out.println("✓ Vuelo añadido correctamente.");
            return 0;
        } else {
            return -2;
        }
    }
    public int reservaAsiento(Usuario usuario,String idVuelo){
        Vuelo vuelo = vueloController.buscarVueloId(idVuelo);

        if(vuelo.getdisponibles() > 0){
            if(usuarioController.añadirVueloReservado(usuario.getIdUser(),vuelo)){
                System.out.println("Vuelo Reservado correctamente.");
                return 0;
            }
            return -7;

        }
        return -8;
    }


}
