package es.upsa.programacion;

import es.upsa.programacion.Modelos.Agencia;
import es.upsa.programacion.Modelos.Usuario;


public class Main {


    public static void main(String[] args) {
        Usuario usuario = null;

        Agencia miAgencia = Constructor.inicializarAgencia();

        Menu menu = new Menu(miAgencia);

        menu.mostrarMenu(usuario);

    }
}

