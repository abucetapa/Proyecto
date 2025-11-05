package es.upsa.programacion;

import es.upsa.programacion.Modelos.Agencia;
import es.upsa.programacion.Vista.VistaUsuario;
import es.upsa.programacion.Vista.VistaVuelo;

public class Constructor {

    public static Agencia inicializarAgencia(){
        Agencia miAgencia = new Agencia();

        VistaUsuario vistaUsuario = new VistaUsuario(miAgencia);
        VistaVuelo vistaVuelo = new VistaVuelo(miAgencia);

        miAgencia.addAdmin(vistaUsuario.generarNuevoIdAdmin(), "12345");
        miAgencia.addCliente(vistaUsuario.generarNuevoIdCliente(),"Antonio", "35601443L","asdf","antonio@upsa.es","666343234");
        miAgencia.addCliente(vistaUsuario.generarNuevoIdCliente(),"Raúl", "05952801X","qwer","rsanchezpe@upsa.es","635214237");


        //Vuelos nacionales -id empieza en V
        miAgencia.addVuelo(vistaVuelo.generarIdVuelo("VN"), "Madrid", "Barcelona", "20/10/2025", 120.50, 200);
        miAgencia.addVuelo(vistaVuelo.generarIdVuelo("VN"), "Sevilla", "Valencia", "21/10/2025", 89.99, 180);
        miAgencia.addVuelo(vistaVuelo.generarIdVuelo("VN"), "Bilbao", "Madrid", "22/10/2025", 110.00, 150);
        miAgencia.addVuelo(vistaVuelo.generarIdVuelo("VN"), "Granada", "Palma de Mallorca", "23/10/2025", 135.75, 160);
        miAgencia.addVuelo(vistaVuelo.generarIdVuelo("VN"), "Madrid", "Tenerife", "25/10/2025", 210.25, 220);

        //Vuelos internacionales - id inicia en VI
        miAgencia.addVuelo(vistaVuelo.generarIdVuelo("I"), "Madrid", "Lisboa", "26/10/2025", 95.60, 190);
        miAgencia.addVuelo(vistaVuelo.generarIdVuelo("I"), "Barcelona", "París", "27/10/2025", 150.40, 170);
        miAgencia.addVuelo(vistaVuelo.generarIdVuelo("I"), "Valencia", "Roma", "28/10/2025", 175.99, 160);
        miAgencia.addVuelo(vistaVuelo.generarIdVuelo("I"), "Sevilla", "Londres", "29/10/2025", 180.50, 200);
        miAgencia.addVuelo(vistaVuelo.generarIdVuelo("I"), "Bilbao", "Ámsterdam", "30/10/2025", 210.00, 150);

        return miAgencia;
    }
}
