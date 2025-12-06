package es.upsa.programacion.Modelos;

import java.util.Objects;

public class Vuelo {

    private String idVuelo;
    private Avion avion;
    private String compania;
    private String salida;
    private String destino;
    private String terminal;
    private String puertaEmb;
    private String fecha;
    private Double precio;
    private Integer disponibles;


    public Vuelo(String idVuelo, Avion avion, String salida, String destino,String terminal, String puertaEmb, String fecha, Double precio) {
        this.idVuelo = idVuelo;
        this.avion = avion;
        this.compania = avion.getCompañia();
        this.salida = salida;
        this.destino = destino;
        this.terminal = terminal;
        this.puertaEmb = puertaEmb;
        this.fecha = fecha;
        this.precio = precio;
        this.disponibles = avion.gettAvion().getCapacidad();
    }

    public String getIdVuelo() {
        return idVuelo;
    }
    public void setIdVuelo(String idVuelo) {
        this.idVuelo = idVuelo;
    }
    public Avion getAvion() {
        return avion;
    }
    public void setAvion(Avion avion) {
        this.avion = avion;
    }
    public String getsalida() {
        return salida;
    }
    public void setsalida(String salida) {
        this.salida = salida;
    }
    public String getdestino() {
        return destino;
    }
    public void setdestino(String destino) {
        this.destino = destino;
    }

    public String getPuertaEmb() {
        return puertaEmb;
    }

    public void setPuertaEmb(String puertaEmb) {
        this.puertaEmb = puertaEmb;
    }

    public String getTerminal() {
        return terminal;
    }

    public void setTerminal(String terminal) {
        this.terminal = terminal;
    }

    public String getfecha() {
        return fecha;
    }
    public void setfecha(String fecha) {
        this.fecha = fecha;
    }

    public Double getprecio() {
        return precio;
    }
    public void setprecio(Double precio) {
        this.precio = precio;
    }
    public Integer getdisponibles() {
        return disponibles;
    }
    public void setdisponibles(Integer disponibles) {
        this.disponibles = disponibles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vuelo vuelo)) return false;
        return Objects.equals(idVuelo, vuelo.idVuelo);
    }


    @Override
    public String toString() {
        return "Vuelo{" +
                "idVuelo='" + idVuelo + '\'' +
                ", compania='" + compania + '\'' +
                ", salida='" + salida + '\'' +
                ", destino='" + destino + '\'' +
                ", fecha='" + fecha + '\'' +
                ", precio = " + precio +
                "€, disponibles=" + disponibles +
                '}';
    }


    @Override
    public int hashCode() { return Objects.hash(idVuelo); }


}
