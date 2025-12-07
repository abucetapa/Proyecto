package es.upsa.programacion.Modelos;

import java.util.Objects;

public abstract class Vuelo implements IReservable{

    private String idVuelo;
    private Avion avion;
    private String compania;
    private String salida;
    private String destino;
    private String fecha;
    private Integer asientos;


    public Vuelo(String idVuelo, Avion avion, String salida, String destino, String fecha) {
        this.idVuelo = idVuelo;
        this.avion = avion;
        this.compania = avion.getCompañia();
        this.salida = salida;
        this.destino = destino;
        this.fecha = fecha;
        this.asientos = avion.gettAvion().getCapacidad();
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


    public String getfecha() {
        return fecha;
    }
    public void setfecha(String fecha) {
        this.fecha = fecha;
    }


    public Integer getAsientos() {
        return asientos;
    }
    public void setAsientos(Integer asientos) {
        this.asientos = asientos;
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
                ", asientos=" + asientos +
                '}';
    }


    @Override
    public int hashCode() { return Objects.hash(idVuelo); }

    @Override
    public boolean verificarDisponibilidad(int asientosRequeridos) {
        // Devuelve true si la capacidad actual es suficiente
        return this.asientos >= asientosRequeridos;
    }

}
