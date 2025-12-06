package es.upsa.programacion.Vista;

import es.upsa.programacion.Controladores.AvionController;
import es.upsa.programacion.Controladores.UsuarioController;
import es.upsa.programacion.Controladores.VueloController;
import es.upsa.programacion.Modelos.Agencia;
import es.upsa.programacion.Modelos.Avion;
import es.upsa.programacion.Modelos.Vuelo;
import es.upsa.programacion.Modelos.Usuario;
import es.upsa.programacion.Menu;

import java.util.ArrayList;
import java.util.Scanner;

public class VistaVuelo {
    private ArrayList<Avion> aviones;
    private VueloController vueloController;
    private UsuarioController usuarioController;
    private AvionController avionController;
    private Menu menu;
    Scanner sc;

    public VistaVuelo(Agencia agencia) {
        this.vueloController = new VueloController(agencia);
        this.usuarioController = new UsuarioController(agencia);
        this.avionController = new AvionController(agencia);
        this.aviones = agencia.getAviones();
        this.sc = new Scanner(System.in);
    }


    public String generarIdVuelo(String tipo, String idAvion) {
        String siglas = null;

        Avion avion = avionController.buscarAvionId(idAvion);
        String[] sufijo = avion.getCompañia().trim().split("\\s+");

        if(sufijo.length == 2){
            siglas = String.valueOf(sufijo[0].charAt(0)).toUpperCase() + String.valueOf(sufijo[1].charAt(0)).toUpperCase();
        }
        if(sufijo.length == 1){
            siglas = sufijo[0].substring(0, 2).toUpperCase();
        }

        int numeroAleatorio = (int) (Math.random() * 9999) + 1;
        if (tipo.equals("I")) {
            return "I" + siglas + numeroAleatorio;
        } else {
            return "N" + siglas + numeroAleatorio;
        }
    }

    public int addVueloVista() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Añadir nuevo vuelo ===");

        String tipo = "";
        while (!tipo.equals("N") && !tipo.equals("I")) {
            System.out.print("¿El vuelo es nacional o internacional? (N/I): ");
            tipo = sc.nextLine().trim().toUpperCase();
            if (tipo.isEmpty()) System.out.println("No puede estar vacío.");
        }



        System.out.println("=== Vuelos disponibles ==="); //Mostramos los vuelos disponibles
        for(Avion avion: aviones){
            if(avion.isDisponible()){
                System.out.println(avion);
            }
        }

        String idAvion = "";
        Avion avion = null;

        // El bucle se repite mientras NO tengamos un avión válido
        while (avion == null) {
            System.out.println("Inserte el id del avión asignado a este vuelo:");
            idAvion = sc.nextLine();

            avion = avionController.buscarAvionId(idAvion);

            if (avion == null) {
                System.out.println("Error: No existe un avión con ese ID. Inténtelo de nuevo.");
            }
            else if (!avion.isDisponible()) { //Comprobamos si esta disponible
                System.out.println("Error: Ese avión ya está ocupado. Elija otro.");
                avion = null;
            }
        }
        avion.setDisponible(false);
        System.out.println("Avión asignado correctamente.");


        // 4. Salida
        String salida = "";
        while (salida.isEmpty()) {
            System.out.print("Salida: ");
            salida = sc.nextLine().trim();
            if(salida.isEmpty()) System.out.println("Campo obligatorio.");
        }

        // 5. Destino
        String destino = "";
        while (destino.isEmpty()) {
            System.out.print("Destino: ");
            destino = sc.nextLine().trim();
            if(destino.isEmpty()) System.out.println("Campo obligatorio.");
        }

        // 6. Terminal
        String terminal = "";
        while (terminal.isEmpty()) {
            System.out.print("Inserte terminal de salida: ");
            terminal = sc.nextLine().trim();
            if(terminal.isEmpty()) System.out.println("Campo obligatorio.");
        }

        // 7. Puerta de Embarque
        String puertaEmb = "";
        while (puertaEmb.isEmpty()) {
            System.out.print("Inserte puerta de embarque: ");
            puertaEmb = sc.nextLine().trim();
            if(puertaEmb.isEmpty()) System.out.println("Campo obligatorio.");
        }

        // 8. Fecha
        String fecha = "";
        while (fecha.isEmpty()) {
            System.out.print("Fecha (ej: DD/MM/YYYY): ");
            fecha = sc.nextLine().trim();
            if(fecha.isEmpty()) System.out.println("Campo obligatorio.");
        }

        // 9. Precio (Control de errores numéricos)
        Double precio = null;
        while (precio == null) {
            System.out.print("Precio: ");
            String precioStr = sc.nextLine().trim();
            try {
                precio = Double.parseDouble(precioStr);
                if (precio < 0) {
                    System.out.println("El precio no puede ser negativo.");
                    precio = null;
                }
            } catch (NumberFormatException e) {
                System.out.println("Por favor, introduzca un número válido (ej: 120.50).");
            }
        }

        String idVuelo = generarIdVuelo(tipo,idAvion);
        Vuelo nuevoVuelo = new Vuelo(idVuelo, avion, salida, destino, terminal, puertaEmb,fecha, precio);
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
