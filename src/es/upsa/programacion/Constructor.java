package es.upsa.programacion;

import es.upsa.programacion.Modelos.Agencia;

public class Constructor {

    public static Agencia inicializarAgencia(){
        Agencia miAgencia = new Agencia();

        miAgencia.addUsuario("U001","Javier","36473647Y", "12345","javieradmin@gmail.com", "574637363",true);

        //Vuelos nacionales -id empieza en V
        miAgencia.addVuelo("V001", "Madrid", "Barcelona", "20/10/2025", 120.50, 200);
        miAgencia.addVuelo("V002", "Sevilla", "Valencia", "21/10/2025", 89.99, 180);
        miAgencia.addVuelo("V003", "Bilbao", "Madrid", "22/10/2025", 110.00, 150);
        miAgencia.addVuelo("V004", "Granada", "Palma de Mallorca", "23/10/2025", 135.75, 160);
        miAgencia.addVuelo("V005", "Madrid", "Tenerife", "25/10/2025", 210.25, 220);

        //Vuelos internacionales - id inicia en VI
        miAgencia.addVuelo("VI006", "Madrid", "Lisboa", "26/10/2025", 95.60, 190);
        miAgencia.addVuelo("VI007", "Barcelona", "París", "27/10/2025", 150.40, 170);
        miAgencia.addVuelo("VI008", "Valencia", "Roma", "28/10/2025", 175.99, 160);
        miAgencia.addVuelo("VI009", "Sevilla", "Londres", "29/10/2025", 180.50, 200);
        miAgencia.addVuelo("VI010", "Bilbao", "Ámsterdam", "30/10/2025", 210.00, 150);

        return miAgencia;
    }
}
