package es.upsa.programacion;

import es.upsa.programacion.Controladores.VueloController;
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
    private Usuario usuario;

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

            if(usuario.getAdmin()==true){
                mostrarMenuAdmin(usuario);
            }else mostrarMenuLogIn(usuario);
        }
    }



    public void mostrarMenuLogOut(){

        int opcion;

        do {
            System.out.println("1. Buscar vuelo");
            System.out.println("2. Mostrar vuelos");
            System.out.println("3. Iniciar sesion");
            System.out.println("4. Registrarse");
            //*Eliminar, solo visualizacion de registros correctos*
            System.out.println("5. Mostrar usuarios");
            System.out.println("0. Salir");

            opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    this.error(vistaVuelo.buscarVueloVista());
                    break;
                case 2:
                    this.error(vueloController.mostrarVuelos());
                    break;
                case 3:
                    //this.error(vistaUsuario.inicioSesion());
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
            sc.nextLine();
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


            opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    System.out.println("Ingrese el id del vuelo");
                    this.error(vistaVuelo.buscarVueloVista());
                    break;
                case 2:
                    System.out.println("**MIS BILLETES**");
                    //Funcion de UsuarioController que muestra lista de billetes del usuario
                    break;
                case 3:
                    //Funcion modificarDatosUsuario
                    break;
                case 4:
                    usuario = null;
                    mostrarMenu(usuario);
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
        int opcion;

        do{
            System.out.println("\n***MENU***");
            System.out.println("1. Buscar vuelo");
            System.out.println("2. Añadir vuelo");
            System.out.println("3. Modificar vuelo");
            System.out.println("4. Eliminar vuelo");
            System.out.println("5. Cerrar sesion");
            System.out.println("0. Salir");


            opcion = sc.nextInt();
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
                    //Funcion modificarVuelo
                    break;
                case 4:
                    //Funcion eliminarVuelo
                    break;
                case 5:
                    boolean logIn = false;
                    mostrarMenu(usuario);
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opcion no valida");
                    break;

            }
        }while(opcion != 0);

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
                break;
            case 0:
                break;
            default:
                System.out.println("Opcion no valida");
                break;

        }
    }
}

