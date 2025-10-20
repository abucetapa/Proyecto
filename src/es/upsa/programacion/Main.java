package es.upsa.programacion;

import es.upsa.programacion.Modelos.Agencia;
import es.upsa.programacion.Modelos.Usuario;


public class Main {


    public static void main(String[] args) {
        Usuario usuario= null;

        Agencia miAgencia = Constructor.inicializarAgencia();
        System.out.println("Usuarios cargados: " + miAgencia.getUsuarios().size());
        System.out.println("Vuelos cargados: " + miAgencia.getVuelos().size());
        System.out.println("Usuarios: " + miAgencia.getUsuarios());

        Menu menu = new Menu(miAgencia);

        menu.mostrarMenu(usuario);

    }
}

