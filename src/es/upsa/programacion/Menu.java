package es.upsa.programacion;

import es.upsa.programacion.Modelos.Agencia;
import java.security.Principal;
import java.util.Scanner;

public class Menu {
    private Scanner sc = new Scanner(System.in);
    private Agencia agencia;
    public Menu(Agencia agencia){
        this.agencia = agencia;
        this.sc = new Scanner(System.in);
    }

    public void mostrarMenu(){

    }
}

