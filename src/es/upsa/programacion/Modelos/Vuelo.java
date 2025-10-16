package es.upsa.programacion.Modelos;

public class Vuelo {

    private String idVuelo;
    private String Salida;
    private String Destino;
    private String Fecha;
    private Integer Asientos;
    private Integer Disponibles;


    public Vuelo(String idVuelo, String salida, String destino, String fecha, Integer asientos) {
        this.idVuelo = idVuelo;
        this.Salida = salida;
        this.Destino = destino;
        this.Fecha = fecha;
        this.Asientos = asientos ;
    }

    public String getIdVuelo() {
        return idVuelo;
    }
    public void setIdVuelo(String idVuelo) {
        this.idVuelo = idVuelo;
    }
    public String getSalida() {
        return Salida;
    }
    public void setSalida(String salida) {
        this.Salida = salida;
    }
    public String getDestino() {
        return Destino;
    }
    public void setDestino(String destino) {
        this.Destino = destino;
    }
    public String getFecha() {
        return Fecha;
    }
    public void setFecha(String fecha) {
        this.Fecha = fecha;
    }
    public Integer getAsientos() {
        return Asientos;
    }
    public void setAsientos(Integer asientos) {
        this.Asientos = asientos;
    }


    @Override
    public String toString() {
        return "Vuelo{" + "idVuelo=" + idVuelo +"}" ;
    }



}
