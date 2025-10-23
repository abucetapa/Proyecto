package es.upsa.programacion;

import es.upsa.programacion.Controladores.UsuarioController;
import es.upsa.programacion.Controladores.VueloController;
import es.upsa.programacion.Modelos.Administrador;
import es.upsa.programacion.Modelos.Agencia;
import es.upsa.programacion.Modelos.Usuario;
import es.upsa.programacion.Modelos.Vuelo;
import es.upsa.programacion.Vista.VistaUsuario;
import es.upsa.programacion.Vista.VistaVuelo;

import java.security.Principal;
import java.util.Scanner;

public class Menu {
    private Scanner sc = new Scanner(System.in);
    private Agencia agencia;
    private VistaVuelo vistaVuelo;
    private VistaUsuario vistaUsuario;
    private VueloController vueloController;

    public Menu(Agencia agencia){
        this.agencia = agencia;
        this.sc = new Scanner(System.in);

        this.vistaVuelo = new VistaVuelo(agencia);
        this.vistaUsuario = new VistaUsuario(agencia);
        this.vistaUsuario = new VistaUsuario(agencia);
        this.vueloController = new VueloController(agencia);

    }

    public void mostrarMenu(Usuario usuario){

        if(usuario==null){
            mostrarMenuLogOut();
        }else{
            if(usuario instanceof Administrador){
                mostrarMenuAdmin(usuario);
            }else mostrarMenuLogIn(usuario);
        }
    }



    public void mostrarMenuLogOut(){

        int opcion;

        do {
            System.out.println("\n***MENU***");
            System.out.println("1. Buscar vuelo");
            System.out.println("2. Mostrar vuelos");
            System.out.println("3. Iniciar sesion");
            System.out.println("4. Registrarse");
            //*Eliminar, solo visualizacion de registros correctos*
            System.out.println("5. Mostrar usuarios");
            System.out.println("0. Salir");

            try {
                opcion = Integer.parseInt(sc.nextLine()); // ← CAMBIO AQUÍ
            } catch (NumberFormatException e) {
                opcion = -100;
            }

            switch (opcion) {
                case 1:
                    this.error(vistaVuelo.buscarVueloVista());
                    break;
                case 2:
                    this.error(vueloController.mostrarVuelos());
                    break;
                case 3:
                    Usuario usuario = vistaUsuario.iniciarSesion();
                    if(usuario != null) {
                        mostrarMenu(usuario);
                    }else this.error(-3);
                    break;
                case 4:
                    this.error(vistaUsuario.addUsuarioVista());
                    break;
                case 5:
                    this.error(vistaUsuario.mostrarUsuarios());
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opcion no valida");
                    break;

            }
        }while(opcion != 0);

    }

    public void mostrarMenuLogIn(Usuario usuario){
        int opcion;

        do {
            System.out.println("\n***MENU***");
            System.out.println("1. Buscar vuelo");
            System.out.println("2. Mis billetes");
            System.out.println("3. Perfil");
            System.out.println("4. Cerrar sesion");
            System.out.println("0. Salir");


            try {
                opcion = Integer.parseInt(sc.nextLine()); // ← CAMBIO AQUÍ
            } catch (NumberFormatException e) {
                opcion = -100;
            }

            switch (opcion) {
                case 1:
                    System.out.println("Ingrese el id del vuelo");
                    Vuelo vuelo = vistaVuelo.buscarVueloVistaObjeto();
                    if(vuelo!=null){
                        menuVuelo(usuario, vuelo);
                    }else this.error(-1);
                    break;
                case 2:
                    System.out.println("**MIS BILLETES**");
                    this.error(vistaUsuario.mostrarMisBilletes(usuario));
                    break;
                case 3:
                    //Funcion modificarDatosUsuario
                    break;
                case 4:
                    System.out.println("Cerrando sesión.......");
                    mostrarMenu(null);
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opcion no valida");
                    break;

            }

        }while(opcion != 0);
    }

    public void mostrarMenuAdmin(Usuario usuario){
        int opcion = -100;

        do{
            System.out.println("\n***MENU***");
            System.out.println("1. Buscar vuelo");
            System.out.println("2. Añadir vuelo");
            System.out.println("3. Modificar vuelo");
            System.out.println("4. Eliminar vuelo");
            System.out.println("5. Cerrar sesion");
            System.out.println("0. Salir");

            try {
                opcion = Integer.parseInt(sc.nextLine()); // ← CAMBIO AQUÍ
            } catch (NumberFormatException e) {
                System.out.println("Error: Debe introducir un número.");
                continue;
            }

            switch (opcion) {
                case 1:
                    System.out.println("Ingrese el id del vuelo");
                    this.error(vistaVuelo.buscarVueloVista());
                    break;
                case 2:
                    System.out.println("**AÑADIR VUELO**");
                    this.error(vistaVuelo.addVueloVista());
                    break;
                case 3:
                    vueloController.mostrarVuelos();
                    this.error(vistaVuelo.modificarVueloMenu());
                    break;
                case 4:
                    //Funcion eliminarVuelo
                    break;
                case 5:
                    System.out.println("Cerrando sesión.......");
                    mostrarMenu(null);
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opcion no valida");
                    break;

            }
        }while(opcion != 0);

    }

    public void menuVuelo(Usuario usuario, Vuelo vuelo){
        int accion;

        System.out.println("Acciones vuelo:");
        System.out.println("1. Reservar vuelo.");
        System.out.println("2. Consultar disponibilidad.");
        System.out.println("3. Cancelar.");
        try {
            accion = Integer.parseInt(sc.nextLine()); // ← CAMBIO AQUÍ
        } catch (NumberFormatException e) {
            System.out.println("Error: Debe introducir un número.");
            accion = -100;
        }

        switch (accion) {
            case 1:
                this.error(vistaVuelo.reservaAsiento(usuario,vuelo.getIdVuelo()));
                break;
            case 2:
                break;
            case 3:
                mostrarMenuLogIn(usuario);
                break;
            case 0:
                break;
            default:
                System.out.println("Opcion no valida");
                break;
        }
    }

    public void error(int codigoError){
        switch (codigoError){
            case -1:
                System.out.println("ERROR. No existe el vuelo.");
                break;
            case -2:
                System.out.println("ERROR. No se ha podido añadir el vuelo.");
                break;
            case -3:
                System.out.println("ERROR. No existe el usuario o la contraseña es incorrecta.");
                break;
            case -4:
                System.out.println("ERROR. Usuario ya existe.");
                break;
            case -5:
                System.out.println("No hay vuelos registrados.\n");
            case -6:
                System.out.println("ERROR. No se ha podido registrar el usuario.");
                break;
            case -7:
                System.out.println("ERROR. No se ha podido reservar el vuelo.");
                break;
            case -8:
                System.out.println("ERROR. El vuelo esta completo.");
                break;
            case -9:
                System.out.println("ERROR. Usuario no encontrado");
                break;
            case 0:
                break;
            default:
                System.out.println("Opcion no valida");
                break;

        }
    }
}

