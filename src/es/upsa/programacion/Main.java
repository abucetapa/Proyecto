package es.upsa.programacion;

import es.upsa.programacion.Modelos.Agencia;
import es.upsa.programacion.Modelos.Vuelo;
import es.upsa.programacion.Controladores.VueloController;

import java.util.Scanner;
public class Main {


    public static void main(String[] args) {

        Agencia miAgencia = Constructor.inicializarAgencia();
        Menu menu = new Menu(miAgencia);

    }
}

