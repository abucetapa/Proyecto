package es.upsa.programacion;

import es.upsa.programacion.Modelos.Agencia;
import es.upsa.programacion.Modelos.Usuario;


public class Main {


    public static void main(String[] args) {

        Usuario usuario = null; //Inicializamos usuario a null para que primero entre en el Menu sin estar logeados

        Agencia miAgencia = Constructor.inicializarAgencia(); //Inicializamos constructor

        Menu menu = new Menu(miAgencia); // Iniciamos Menu con los datos de agencia

        menu.mostrarMenu(usuario); // Hacemos llamada a la función mostrarMenu

    }
}

