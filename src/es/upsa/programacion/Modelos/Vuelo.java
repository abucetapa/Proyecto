package es.upsa.programacion.Modelos;

import java.util.Objects;

public class Vuelo {

    private String idVuelo;
    private String salida;
    private String destino;
    private String fecha;
    private Float precio;
    private Integer disponibles;


    public Vuelo(String idVuelo, String salida, String destino, String fecha, Float precio, Integer disponibles) {
        this.idVuelo = idVuelo;
        this.salida = salida;
        this.destino = destino;
        this.fecha = fecha;
        this.precio = precio;
        this.disponibles = disponibles;
    }

    public String getIdVuelo() {
        return idVuelo;
    }
    public void setIdVuelo(String idVuelo) {
        this.idVuelo = idVuelo;
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

    public Float getprecio() {
        return precio;
    }
    public void setprecio(Float precio) {
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
                ", salida='" + salida + '\'' +
                ", destino='" + destino + '\'' +
                ", fecha='" + fecha + '\'' +
                ", precio=" + precio +
                ", disponibles=" + disponibles +
                '}';
    }


    @Override
    public int hashCode() { return Objects.hash(idVuelo); }


}
