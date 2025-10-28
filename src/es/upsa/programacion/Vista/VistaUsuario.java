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
    Scanner sc;
    private Agencia agencia;
    private List<Usuario> usuarios;

    public VistaUsuario(Agencia agencia) {
        this.agencia = agencia;
        this.usuarioController = new UsuarioController(agencia);
        this.usuarios = agencia.getUsuarios();
        this.sc = new Scanner(System.in);
    }



    public int addUsuarioVista() {
        System.out.println("**Registrarse**");
        Scanner sc = new Scanner(System.in);

        String dni = "";
        while (dni.isEmpty()) {
            System.out.println("Ingrese su DNI:");
            dni = sc.nextLine().trim();
            if (dni.isEmpty()) System.out.println("Campo obligatorio. Por favor, ingrese un DNI válido.");
        }

        String nombre = "";
        while (nombre.isEmpty()) {
            System.out.println("Ingrese su nombre:");
            nombre = sc.nextLine().trim();
            if (nombre.isEmpty()) System.out.println("Campo obligatorio. Por favor, ingrese un nombre válido.");
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

        String idUser = generarNuevoId();

        Cliente cliente = new Cliente(idUser, nombre, dni, password, email, telefono);

        boolean usuarioAñadido = usuarioController.addUsuario(cliente);

        if (usuarioAñadido) {
            System.out.println(" Usuario registrado correctamente.");
            return 0;
        } else {
            return -6;
        }
    }

    public String generarNuevoId() {
        List<Usuario> usuarios = agencia.getUsuarios();

        if (usuarios.isEmpty()) {
            return "U0031"; // Reservados U0001 - U0030 para admins
        }

        int max = 30; // los primeros 30 reservados para administradores

        for (Usuario u : usuarios) {
            try {
                // Extraer la parte numérica del ID
                // Quita el primer valor del string
                int num = Integer.parseInt(u.getIdUser().substring(1));
                if (num > max) max = num;
            } catch (NumberFormatException ignored) {}
        }

        return String.format("U%04d", max + 1);

    }

    public int mostrarUsuarios(){
        System.out.println("**Usuarios**");


        if (usuarios == null || usuarios.isEmpty()) {
            return -5;
        } else {
            for (Usuario u : usuarios) {
                System.out.println(u);
            }
            System.out.println();
            return 0;
        }

    }

    public Usuario iniciarSesion(){
        System.out.println("**Iniciar Sesion**");

        System.out.println("Inserte dni, email o teléfono:");
        String inicioSesion = sc.nextLine();



        // Buscar usuario (Clientes: DNI, email, teléfono | Admins: solo ID)
        Usuario usuario = usuarioController.buscarAdminId(inicioSesion);
        if(usuario == null) usuario = usuarioController.buscarClienteDni(inicioSesion);
        if(usuario == null) usuario = usuarioController.buscarClienteEmail(inicioSesion);
        if(usuario == null) usuario = usuarioController.buscarClienteTelefono(inicioSesion);



        if(usuario != null) {
            System.out.println("Contraseña usuario: " + usuario.getPassword());
            System.out.println("Inserte su contraseña:");
            String password = sc.nextLine();

            if(password.equals(usuario.getPassword())) {
                System.out.println("Sesion iniciado correctamente.");
                return usuario;
            }
        }
        return null;
    }

    public int mostrarMisBilletes(Usuario usuario){
        if(usuario == null) return -9;
        if(usuario instanceof Cliente cliente){
            if(cliente.getReservados().isEmpty()) {
                return -5;
            } else {
                System.out.println("Tus vuelos reservados:");
                for(Vuelo v : cliente.getReservados()) {
                    System.out.println("- " + v.toString());
                }
                return 0;
            }
        }
        return 0;

    }
}
