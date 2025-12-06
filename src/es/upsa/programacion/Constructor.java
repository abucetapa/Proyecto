package es.upsa.programacion;

import es.upsa.programacion.Modelos.Agencia;
import es.upsa.programacion.Modelos.Avion;
import es.upsa.programacion.Vista.VistaAvion;
import es.upsa.programacion.Vista.VistaUsuario;
import es.upsa.programacion.Vista.VistaVuelo;

import java.util.List;

public class Constructor {

    public static Agencia inicializarAgencia(){
        Agencia miAgencia = new Agencia();


        VistaUsuario vistaUsuario = new VistaUsuario(miAgencia);
        VistaVuelo vistaVuelo = new VistaVuelo(miAgencia);
        VistaAvion vistaAvion = new VistaAvion(miAgencia);

        // ========== AVIONES ==========
        // Aviones comerciales
// Aviones comerciales
        miAgencia.addAvion(vistaAvion.generarIdAvion("Iberia"), "Iberia", Avion.tipoAvion.COMERCIAL);
        miAgencia.addAvion(vistaAvion.generarIdAvion("Vueling"), "Vueling", Avion.tipoAvion.COMERCIAL);
        miAgencia.addAvion(vistaAvion.generarIdAvion("Ryanair"), "Ryanair", Avion.tipoAvion.COMERCIAL);
        miAgencia.addAvion(vistaAvion.generarIdAvion("Air Europa"), "Air Europa", Avion.tipoAvion.COMERCIAL);
        miAgencia.addAvion(vistaAvion.generarIdAvion("Lufthansa"), "Lufthansa", Avion.tipoAvion.COMERCIAL);
        miAgencia.addAvion(vistaAvion.generarIdAvion("British Airways"), "British Airways", Avion.tipoAvion.COMERCIAL);
        miAgencia.addAvion(vistaAvion.generarIdAvion("Air France"), "Air France", Avion.tipoAvion.COMERCIAL);

// Aviones privados de negocios
        miAgencia.addAvion(vistaAvion.generarIdAvion("NetJets"), "NetJets", Avion.tipoAvion.PRIVADO_GRANDE);
        miAgencia.addAvion(vistaAvion.generarIdAvion("VistaJet"), "VistaJet", Avion.tipoAvion.PRIVADO_GRANDE);
        miAgencia.addAvion(vistaAvion.generarIdAvion("Flexjet"), "Flexjet", Avion.tipoAvion.PRIVADO_GRANDE);
        miAgencia.addAvion(vistaAvion.generarIdAvion("Sentient Jet"), "Sentient Jet", Avion.tipoAvion.PRIVADO_GRANDE);

// Aviones privados pequeños
        miAgencia.addAvion(vistaAvion.generarIdAvion("Elite Airways"), "Elite Airways", Avion.tipoAvion.PRIVADO);
        miAgencia.addAvion(vistaAvion.generarIdAvion("Private Sky"), "Private Sky", Avion.tipoAvion.PRIVADO);
        miAgencia.addAvion(vistaAvion.generarIdAvion("Luxury Air"), "Luxury Air", Avion.tipoAvion.PRIVADO);
        miAgencia.addAvion(vistaAvion.generarIdAvion("Executive Jet"), "Executive Jet", Avion.tipoAvion.PRIVADO);        // Aviones privados pequeños


        miAgencia.addAdmin(vistaUsuario.generarNuevoIdAdmin(), "12345");
        miAgencia.addCliente(vistaUsuario.generarNuevoIdCliente(),"Antonio", "35601443L","asdf","antonio@upsa.es","666343234");
        miAgencia.addCliente(vistaUsuario.generarNuevoIdCliente(),"Raúl", "05952801X","qwer","rsanchezpe@upsa.es","635214237");


        String idAvion = miAgencia.getAviones().get((int)(Math.random()*miAgencia.getAviones().size())).getIdAvion();; // Usamos ID explícito o aleatorio: miAgencia.getAviones().get(...).getIdAvion()
        miAgencia.addVuelo(vistaVuelo.generarIdVuelo("VN", idAvion), idAvion, "Madrid", "Barcelona", "T4", "J45", "20/10/2025", 120.50);

        idAvion = miAgencia.getAviones().get((int)(Math.random()*miAgencia.getAviones().size())).getIdAvion();;
        miAgencia.addVuelo(vistaVuelo.generarIdVuelo("VN", idAvion), idAvion, "Sevilla", "Valencia", "T1", "B12", "21/10/2025", 89.99);

        idAvion = miAgencia.getAviones().get((int)(Math.random()*miAgencia.getAviones().size())).getIdAvion();;
        miAgencia.addVuelo(vistaVuelo.generarIdVuelo("VN", idAvion), idAvion, "Bilbao", "Madrid", "T2", "C05", "22/10/2025", 110.00);

        idAvion = miAgencia.getAviones().get((int)(Math.random()*miAgencia.getAviones().size())).getIdAvion();;
        miAgencia.addVuelo(vistaVuelo.generarIdVuelo("VN", idAvion), idAvion, "Granada", "Palma de Mallorca", "T1", "A10", "23/10/2025", 135.75);

        idAvion = miAgencia.getAviones().get((int)(Math.random()*miAgencia.getAviones().size())).getIdAvion();;
        miAgencia.addVuelo(vistaVuelo.generarIdVuelo("VN", idAvion), idAvion, "Madrid", "Tenerife", "T4S", "R01", "25/10/2025", 210.25);

        // Vuelos internacionales
        idAvion = miAgencia.getAviones().get((int)(Math.random()*miAgencia.getAviones().size())).getIdAvion();;
        miAgencia.addVuelo(vistaVuelo.generarIdVuelo("I", idAvion), idAvion, "Madrid", "Lisboa", "T4", "K22", "26/10/2025", 95.60);

        idAvion = miAgencia.getAviones().get((int)(Math.random()*miAgencia.getAviones().size())).getIdAvion();;
        miAgencia.addVuelo(vistaVuelo.generarIdVuelo("I", idAvion), idAvion, "Barcelona", "París", "T2", "D15", "27/10/2025", 150.40);

        idAvion = miAgencia.getAviones().get((int)(Math.random()*miAgencia.getAviones().size())).getIdAvion();;
        miAgencia.addVuelo(vistaVuelo.generarIdVuelo("I", idAvion), idAvion, "Valencia", "Roma", "T1", "B01", "28/10/2025", 175.99);

        idAvion = miAgencia.getAviones().get((int)(Math.random()*miAgencia.getAviones().size())).getIdAvion();;
        miAgencia.addVuelo(vistaVuelo.generarIdVuelo("I", idAvion), idAvion, "Sevilla", "Londres", "T3", "E09", "29/10/2025", 180.50);

        idAvion = miAgencia.getAviones().get((int)(Math.random()*miAgencia.getAviones().size())).getIdAvion();;
        miAgencia.addVuelo(vistaVuelo.generarIdVuelo("I", idAvion), idAvion, "Bilbao", "Ámsterdam", "T2", "C11", "30/10/2025", 210.00);
        return miAgencia;
    }
}
