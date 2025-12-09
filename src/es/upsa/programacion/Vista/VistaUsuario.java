package es.upsa.programacion.Vista;

import es.upsa.programacion.Controladores.UsuarioController;
import es.upsa.programacion.Modelos.Agencia;
import es.upsa.programacion.Modelos.Cliente;
import es.upsa.programacion.Modelos.Usuario;
import es.upsa.programacion.Modelos.Vuelo;

import java.util.Scanner;
import java.util.List;

public class VistaUsuario {
    private UsuarioController usuarioController;
    private Agencia agencia;
    private List<Usuario> usuarios;
    Scanner sc ;

    public VistaUsuario(Agencia agencia) {
        this.agencia = agencia;
        this.usuarioController = new UsuarioController(agencia);
        this.usuarios = agencia.getUsuariosMapList();
        this.sc = new Scanner(System.in);
    }


    // Inicio sesión
    public Usuario iniciarSesion(){
        System.out.println("**Iniciar Sesion**");

        System.out.println("Inserte dni, email o teléfono:"); //Pide uno de los tres datos para el inicio de sesión
        String inicioSesion = sc.nextLine();



        // Buscar usuario (Clientes: DNI, email, teléfono | Admins: solo ID)
        // Inicio de sesión para administrador es opción oculta unicamente por id
        Usuario usuario = usuarioController.buscarAdminId(inicioSesion);
        if(usuario == null) usuario = usuarioController.buscarClienteDni(inicioSesion);
        if(usuario == null) usuario = usuarioController.buscarClienteEmail(inicioSesion);
        if(usuario == null) usuario = usuarioController.buscarClienteTelefono(inicioSesion);



        if(usuario != null) {
            System.out.println("Contraseña usuario: " + usuario.getPassword()); // ELiminar para entrega, es para
                                                                                // visualización en pruebas
            System.out.println("Inserte su contraseña:");
            String password = sc.nextLine();

            if(password.equals(usuario.getPassword())) { // Comprueba que la contraseña es correcta
                System.out.println("Sesion iniciado correctamente.");
                return usuario;
            }else System.out.println("Contraseña incorrecta.");
        }
        return null;
    }

    //Registrar usuario
    public int addUsuarioVista() {
        System.out.println("**Registrarse**");
        Scanner sc = new Scanner(System.in);

        // Pide cada uno de los valores que debe insertar, ninguno de los valores puede ser nulo
        String dni = "";
        boolean validez = false;
        while (dni.isEmpty() || validez == false) {
            System.out.println("Ingrese su DNI:");
            dni = sc.nextLine().trim();
            validez = comprobarDniValido(dni);
            if (dni.isEmpty()) System.out.println("Campo obligatorio. Por favor, ingrese un DNI válido.");
        }

        String nombre = "";
        while (nombre.isEmpty() || nombre.split("\\s+").length < 2) {
            System.out.println("Ingrese su nombre:");
            nombre = sc.nextLine().trim();
            if (nombre.isEmpty()){
                System.out.println("Campo obligatorio. Por favor, ingrese un nombre válido.");
            } else if (nombre.split("\\s+").length < 2) {
                System.out.println("Debe ingresar al menos nombre y apellido.");
            }
        }

        String email = "";
        while (email.isEmpty()) {
            System.out.println("Ingrese su email:");
            email = sc.nextLine().trim();
            if (email.isEmpty()) System.out.println("Campo obligatorio. Por favor, ingrese un email.");
        }

        String password = "";
        while (password.isEmpty()) {
            System.out.println("Ingrese su contraseña:");
            password = sc.nextLine().trim();
            if (password.isEmpty()) System.out.println("Campo obligatorio. Por favor, ingrese una contraseña.");
        }

        String telefono = "";
        while (telefono.isEmpty()) {
            System.out.println("Ingrese su teléfono:");
            telefono = sc.nextLine().trim();
            if (telefono.isEmpty()) System.out.println("Campo obligatorio. Por favor, ingrese un teléfono válido.");
        }

        String idUser = generarNuevoIdCliente(); // Llamada a funcion auxiliar para generar id

        //Crea nuevo cliente con los datos insertados
        Cliente cliente = new Cliente(idUser, nombre, dni, password, email, telefono);

        //Añade el cliente al Array de Usuarios
        boolean usuarioAñadido = usuarioController.addUsuario(cliente);

        if (usuarioAñadido) {
            System.out.println(" Usuario registrado correctamente.");
            return 0;
        } else {
            return -6;
        }
    }

    // Mostrar billetes del usuario
    public int mostrarMisBilletes(Usuario usuario){

        if(usuario == null) return -9; // Caso usuario nulo

        if(usuario instanceof Cliente cliente){ // Comprobamos que el usuario es de tipo cliente
            if(cliente.getReservados().isEmpty()) { // Caso Array reservados esta vacio
                return -5;
            } else {
                System.out.println("Tus vuelos reservados:");
                // Iteramos en el array de los vuelos reservamos del cliente y los mostramos
                for(Vuelo v : cliente.getReservados()) {
                    System.out.println("- " + v.toString());
                }
                return 0;
            }
        }
        return 0;

    }

    //Mostrar usuarios
    public int mostrarUsuarios(){
        System.out.println("**Usuarios**");

        // Array usuarios no vacío
        if (usuarios == null || usuarios.isEmpty()) {
            return -5;
        }

        usuarios.sort((v1, v2) -> v1.getIdUser().compareTo(v2.getIdUser())); //Ordena los vuelos
            // Iteramos en array de usuarios y mostramos
        for (Usuario u : usuarios) {
            System.out.println(u);
        }
        return 0;


    }

    // FUNCION AUXILIAR

    // Generar id aleatorio
    public String generarNuevoIdCliente() {
        // Solicitamos el Array de usuarios
        List<Usuario> usuarios = agencia.getUsuariosMapList();

        String nuevoId;
        boolean idExiste;

        do {
            // Generar número aleatorio entre 31 y 9999 (reservados U0001-U0030 para admins)
            int numId = (int) (Math.random() * 99999) +1 ;
            nuevoId = String.format("U%06d", numId); // El id va a tener 6 numeros

            // Verificar si el ID ya existe
            idExiste = false;
            for (Usuario u : usuarios) {
                if (u.getIdUser().equals(nuevoId)) {
                    idExiste = true;
                    break;
                }
            }
        } while (idExiste); // Repetir mientras el ID ya exista

        return nuevoId;
    }

    public String generarNuevoIdAdmin() {
        // Solicitamos el Array de usuarios
        List<Usuario> usuarios = agencia.getUsuariosMapList();

        String nuevoId;
        boolean idExiste;

        do {
            // Generar número aleatorio hasta el 31
            int numId = (int) (Math.random() * 99 ) + 1;

            nuevoId = String.format("A%03d", numId); // El id va a tener 3 digitos

            // Verificar si el ID ya existe
            idExiste = false;
            for (Usuario u : usuarios) {
                if (u.getIdUser().equals(nuevoId)) {
                    idExiste = true;
                    break;
                }
            }
        } while (idExiste); // Repetir mientras el ID ya exista

        return nuevoId;
    }

    public boolean comprobarDniValido(String dni) {

    }
}
