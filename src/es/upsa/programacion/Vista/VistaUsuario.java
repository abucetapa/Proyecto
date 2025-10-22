package es.upsa.programacion.Vista;

import es.upsa.programacion.Controladores.UsuarioController;
import es.upsa.programacion.Modelos.Agencia;
import es.upsa.programacion.Modelos.Usuario;
import es.upsa.programacion.Modelos.Vuelo;

import java.util.Scanner;
import java.util.List;

public class VistaUsuario {
    private UsuarioController usuarioController;
    Scanner sc = new Scanner(System.in);
    private Agencia agencia;
    private List<Usuario> usuarios;

    public VistaUsuario(Agencia agencia) {
        this.agencia = agencia;
        this.usuarioController = new UsuarioController(agencia);
        this.usuarios = agencia.getUsuarios();
    }

    public Usuario buscarUsuarioVista(String idUsuario) {
        Usuario usuarioEncontrado = usuarioController.buscarUsuarioId(idUsuario);

        if (usuarioEncontrado == null) {
            return null;
        } else {
            System.out.print("Ingrese su contraseña: ");
            String password = sc.nextLine();

            if(usuarioEncontrado.getPassword().equals(password)){
                return usuarioEncontrado;
            } else {
                return null;
            }
        }
    }

    public int addUsuarioVista() {
        System.out.println("**Registrarse**");
        Scanner sc = new Scanner(System.in);

        String dni = "";
        while (dni.isEmpty()) {
            System.out.println("Ingrese su DNI:");
            dni = sc.nextLine().trim();
            if (dni.isEmpty()) System.out.println("⚠️ Campo obligatorio. Por favor, ingrese un DNI válido.");
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

        Usuario usuario = new Usuario(idUser, nombre, dni, password, email, telefono,false);

        boolean usuarioAñadido = usuarioController.addUsuario(usuario);

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
        sc = new Scanner(System.in);

        System.out.println("Inserte dni:");
        String dni = sc.nextLine();

        Usuario usuario = usuarioController.buscarUsuarioDni(dni);

        if(usuario != null) {
            System.out.println("Inserte su contraseña:");
            String contraseña = sc.nextLine();

            if(contraseña.equals(usuario.getPassword())){
                System.out.println("Sesion iniciado correctamente.");
                return usuario;
            }else System.out.println("Contraseña incorrecta:");
        }

        return null;
    }

    public int mostrarMisBilletes(Usuario usuario){
        if(usuario == null) return -5;
        if(usuario.getReservados().isEmpty()) {
            return -9;
        } else {
            System.out.println("Tus vuelos reservados:");
            for(Vuelo v : usuario.getReservados()) {
                System.out.println("- " + v.toString());
            }
            return 0;
        }
    }
}
