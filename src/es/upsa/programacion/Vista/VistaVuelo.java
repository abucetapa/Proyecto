package es.upsa.programacion.Vista;

import es.upsa.programacion.Controladores.UsuarioController;
import es.upsa.programacion.Controladores.VueloController;
import es.upsa.programacion.Modelos.Agencia;
import es.upsa.programacion.Modelos.Vuelo;
import es.upsa.programacion.Modelos.Usuario;
import es.upsa.programacion.Menu;

import java.sql.SQLOutput;
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


    public String generarIdVuelo(String tipo) {
        int numeroAleatorio = (int) (Math.random() * 9999) + 1;
        if (tipo.equals("I")) {
            return "VI-" + numeroAleatorio;
        } else {
            return "VN-" + numeroAleatorio;
        }
    }

    public int addVueloVista() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Añadir nuevo vuelo ===");

        System.out.print("¿El vuelo es nacional o internacional? (N/I): ");
        String tipo = scanner.nextLine().trim().toUpperCase();

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

        String idVuelo = generarIdVuelo(tipo);

        Vuelo nuevoVuelo = new Vuelo(idVuelo, salida, destino, fecha, precio, disponibles);
        boolean vueloAñadido = vueloController.addVuelo(nuevoVuelo);

        if(!vueloAñadido){
            return -2;
        }
        return 0;
    }

    public int eliminarVueloVista(){
        System.out.println("=== Eliminar vuelo ===");
        System.out.println("Inserte el id del vuelo a eliminar:");
        String idVuelo = sc.nextLine();
        boolean eliminarVuelo = vueloController.eliminarVuelo(idVuelo);
        if(!eliminarVuelo){
            return -2;
        }
        System.out.println("El vuelo eliminado exitosamente");
        return 0;
    }

    // Vista modificar vuelo
    public int modificarVueloMenu(){
        System.out.println("Id de vuelo a modificar:"); //Guardamos el id del vuelo para buscar si existe
        String idVuelo = sc.nextLine();

        Vuelo vueloExiste = vueloController.buscarVueloId(idVuelo); //Llamada a funcion del controlador para encontrar el vuelo

        if(vueloExiste == null){ // Si el vuelo no existe
            System.out.println("Vuelo no encontrado.");
            return -9;//Error vuelo no encontrado
        }

        System.out.println("Vuelo actual: " + vueloExiste);
        // Petición de los datos a cambiar
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

        // Transformamos los datos a los valores correctos
        Double nuevoPrecio = null;
        if(!precioStr.isEmpty()){ //En el caso de no haber insertado nada, no se modificará
            try {
                nuevoPrecio = Double.parseDouble(precioStr);
            } catch (NumberFormatException e) { // Comprobamos que se pueda pasar a float el valor insertado
                System.out.println("Precio inválido, no se modificará.");
            }
        }

        Integer nuevosDisponibles = null;
        if(!asientosStr.isEmpty()){
            try {
                nuevosDisponibles = Integer.parseInt(asientosStr); // Comprobamos que se pueda pasar a int el valor insertado
            } catch (NumberFormatException e) {
                System.out.println("Número de asientos inválido, no se modificará.");
            }
        }

        boolean modificado = vueloController.modificarVuelo(idVuelo, nuevaSalida, nuevoDestino,
                nuevaFecha, nuevoPrecio, nuevosDisponibles); //LLamada a funcion del controlador para actualizar los valores

        if(modificado){
            return 0; //Vuelo modificado correctamente
        } else {
            return -8; //Error. No se ha podido modificar el vuelo

        }

    }

    //BUSQUEDA

    //Funcion de busqueda que devuelve int para detención de errores
    public int buscarVueloVistaId(){
        System.out.println("Ingrese el id del vuelo");
        String idVuelo = sc.nextLine(); // Recogemos el id insertado por consola

        Vuelo vueloEncontrado = vueloController.buscarVueloId(idVuelo); // Función de controlador busca el vuelo por id

        if(vueloEncontrado == null){

            return -1;
        }else {
            System.out.println("Datos del vuelo " + idVuelo +": "+ vueloEncontrado);
            return 0;
        }

    }
    // Funcion busqueda que devuelve tipo vuelo
    public Vuelo buscarVueloVistaObjeto(){
        System.out.println("Ingrese el id del vuelo");
        String idVuelo = sc.nextLine(); // Recogemos el id insertado por consola

        Vuelo vueloEncontrado = vueloController.buscarVueloId(idVuelo); // Función de controlador busca el vuelo por id

        if(vueloEncontrado == null){
            return null;
        }else {
            System.out.println("Datos del vuelo " + idVuelo +": "+ vueloEncontrado);
            return vueloEncontrado;
        }

    }

    // Reserva asientos
    public int reservaAsiento(Usuario usuario,String idVuelo){
        Vuelo vuelo = vueloController.buscarVueloId(idVuelo); //Buscamos el vuelo seleccionado

        System.out.println("Cuantos asientos quiere reservar:");
        String nReservas = sc.nextLine(); // Guardamos los asientos que queremos reservar
        int intNReservas = Integer.parseInt(nReservas);// Transformamos de String a int

        if(usuarioController.addVueloReservado(usuario, vuelo, intNReservas)){ //Llamamos al controlador para añadir el
            // asiento a la lista de vuelos del usuario
            System.out.println("Vuelo/s Reservado correctamente.");
            return 0;
        }
        return -8;
    }

    //Funcion muestra los asientos disponibles del vuelo
    public int mostrarDisponibilidad(Vuelo vuelo){
        if(vuelo.getdisponibles() == 0){
            return -8;
        }else{
            System.out.println("Asientos disponibles: " + vuelo.getdisponibles());
        }
        return 0;
    }



}
