package es.upsa.programacion;

import es.upsa.programacion.Controladores.AvionController;
import es.upsa.programacion.Controladores.UsuarioController;
import es.upsa.programacion.Controladores.VueloController;
import es.upsa.programacion.Modelos.Administrador;
import es.upsa.programacion.Modelos.Agencia;
import es.upsa.programacion.Modelos.Usuario;
import es.upsa.programacion.Modelos.Vuelo;
import es.upsa.programacion.Vista.VistaUsuario;
import es.upsa.programacion.Vista.VistaVuelo;
import es.upsa.programacion.Controladores.AvionController;

import java.security.Principal;
import java.util.Scanner;

public class Menu {
    private Scanner sc;
    private VistaVuelo vistaVuelo;
    private VistaUsuario vistaUsuario;
    private AvionController avionController;
    private VueloController vueloController;

    public Menu(Agencia agencia){
        this.sc = new Scanner(System.in);

        this.vistaVuelo = new VistaVuelo(agencia);
        this.vistaUsuario = new VistaUsuario(agencia);
        this.vueloController = new VueloController(agencia);
        this.avionController = new AvionController(agencia);

    }

    public void mostrarMenu(Usuario usuario){

        if(usuario==null){
            mostrarMenuAdmin(usuario); //Caso usuario es nulo, por eso en el main declaramos usuario nulo
        }else{
            if(usuario instanceof Administrador){ // Caso de que el usuario es un administrador
                mostrarMenuAdmin(usuario); //Se muestra menu especifico para administradores
            }else mostrarMenuLogIn(usuario); // Menu para usuarios logueadosa
        }
    }


    // MENUS
    public void mostrarMenuLogOut(){

        int opcion;

        do {
            System.out.println("\n***MENU***");
            System.out.println("1. Buscar vuelo");
            System.out.println("2. Mostrar vuelos");
            System.out.println("3. Iniciar sesion");
            System.out.println("4. Registrarse");
            System.out.println("5. Mostrar usuarios //Borrar para final ");//BORRAR
            System.out.println("6. Mostrar aviones //Borrar para final"); //Borrar
            System.out.println("0. Salir");

            // Comprobamos que la opción insertada es correcta
            try {
                opcion = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                opcion = -100;
            }

            //Se procesa la opción seleccionada segun la opción que muestre el menu con su correspondiente codigo
            //de error si hay
            switch (opcion) {
                case 1:
                    this.error(vistaVuelo.buscarVueloVistaId());
                    break;
                case 2:
                    this.error(vueloController.mostrarVuelos());
                    break;
                case 3:
                    //Inicio sesion
                    // guardamos en usuario el retorno de la funcion iniciarSesión, la cual devuelve un usuario si
                    // es que existe
                    Usuario usuario = vistaUsuario.iniciarSesion();
                    if(usuario != null) {
                        mostrarMenu(usuario);
                    }else this.error(-3);
                    break;
                case 4:
                    // Iniciamos el proceso de registro como Cliente
                    this.error(vistaUsuario.addUsuarioVista());
                    break;
                case 5:
                    this.error(vistaUsuario.mostrarUsuarios());
                    break;
                case 6:
                    this.error(avionController.mostrarAviones());
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
            System.out.println("2. Mostrar vuelos");
            System.out.println("3. Mis billetes");
            System.out.println("4. Perfil");
            System.out.println("5. Cerrar sesion");
            System.out.println("0. Salir");

            // Comprobamos que la opción insertada es correcta
            try {
                opcion = Integer.parseInt(sc.nextLine()); // ← CAMBIO AQUÍ
            } catch (NumberFormatException e) {
                opcion = -100;
            }

            switch (opcion) {
                case 1:
                    // Guarda en vuelo el vuelo que encuentre en buscarVueloVistaObjeto, el cual devuelve un vuelo
                    Vuelo vuelo = vistaVuelo.buscarVueloVistaObjeto();
                    if(vuelo!=null){
                        menuVuelo(usuario, vuelo);
                    }else this.error(-1);
                    break;
                case 2:
                    // Muestra todos los vuelos que tiene reservado el cliente
                    vueloController.mostrarVuelosCliente(usuario);
                    break;
                case 3:
                    //Muestra todos los bitelles del usuario
                    System.out.println("**MIS BILLETES**");
                    this.error(vistaUsuario.mostrarMisBilletes(usuario));
                    break;
                case 4:
                    //Funcion modificarDatosUsuario

                    break;
                case 5:
                    // Cerramos sesión y se muestra el MenuLogOut
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

    // Menu autentificacion como administrador
    public void mostrarMenuAdmin(Usuario usuario){
        int opcion;

        //Opciones para administrador
        do{
            System.out.println("\n***MENU***");
            System.out.println("1. Buscar vuelo");
            System.out.println("2. Añadir vuelo");
            System.out.println("3. Modificar vuelo");
            System.out.println("4. Eliminar vuelo");
            System.out.println("5. Cerrar sesion");
            System.out.println("6. Mostrar vuelos //Borrar para final ");
            System.out.println("0. Salir");

            try {
                opcion = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                opcion = -100;
            }

            switch (opcion) {
                case 1:
                    //Buscamos vuelo por id
                    this.error(vistaVuelo.buscarVueloVistaId());
                    break;
                case 2:
                    //Añadimos vuelo
                    System.out.println("**AÑADIR VUELO**");
                    this.error(vistaVuelo.addVueloVista());
                    break;
                case 3:
                    //Modificamos vuelos
                    this.error(vistaVuelo.modificarVueloMenu());
                    break;
                case 4:
                    this.error(vistaVuelo.eliminarVueloVista());
                    break;
                case 5:
                    System.out.println("Cerrando sesión.......");
                    mostrarMenu(null);
                    break;
                case 6:
                    this.error(vueloController.mostrarVuelosAdmin());
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

        // Comprobamos que la opción insertada es correcta
        try {
            accion = Integer.parseInt(sc.nextLine()); // ← CAMBIO AQUÍ
        } catch (NumberFormatException e) {
            System.out.println("Error: Debe introducir un número.");
            accion = -100;
        }

        switch (accion) {
            case 1:
                //Acción reserva asiento
                this.error(vistaVuelo.reservaAsiento(usuario,vuelo.getIdVuelo()));
                break;
            case 2:
                //Accion que muestra los asientos disponibles para ese vuelo
                this.error(vistaVuelo.mostrarDisponibilidad(vuelo));
                menuVuelo(usuario, vuelo);
                break;
            case 3:
                // Cancelamos acción y nos manda de vuelta al menu del Cliente
                mostrarMenuLogIn(usuario);
                break;
            default:
                System.out.println("Opcion no valida");
                break;
        }
    }


    // FUNCION CODIGOS ERRORES
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

