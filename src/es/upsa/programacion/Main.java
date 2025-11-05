package es.upsa.programacion;

import es.upsa.programacion.Modelos.Agencia;


public class Main {


    public static void main(String[] args) {

        Agencia miAgencia = Constructor.inicializarAgencia(); //Inicializamos constructor

        Menu menu = new Menu(miAgencia); // Iniciamos Menu con los datos de agencia

        menu.mostrarMenu(null); // Hacemos llamada a la función mostrarMenu

    }
}

