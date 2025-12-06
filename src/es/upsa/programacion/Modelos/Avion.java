package es.upsa.programacion.Modelos;


import java.util.Objects;

public class Avion {

    public enum tipoAvion{
        COMERCIAL("Comercial", 200),
        PRIVADO_GRANDE("Privado negocios",100),
        PRIVADO("Privado", 30);


        private String descripcion;
        private int capacidad;

        tipoAvion(String descripcion, int capacidad){
            this.descripcion = descripcion;
            this.capacidad = capacidad;
        }
        public String getDescripcion() {
            return descripcion;
        }
        public int getCapacidad() {
            return capacidad;
        }


    }

    private String idAvion;
    private String compañia;
    private tipoAvion tAvion;
    private boolean disponible;
    private String ciudadActual;

    public Avion(String idAvion, String compañia, tipoAvion tAvion, String ciudadActual) {
        this.idAvion = idAvion;
        this.compañia=compañia;
        this.tAvion = tAvion;
        this.disponible = true;
        this.ciudadActual = ciudadActual;

    }

    public String getIdAvion() {
        return idAvion;
    }

    public void setIdAvion(String idAvion) {
        this.idAvion = idAvion;
    }

    public String getCompañia() {
        return compañia;
    }

    public void setCompañia(String compañia) {
        this.compañia = compañia;
    }

    public tipoAvion gettAvion() {
        return tAvion;
    }

    public void settAvion(tipoAvion tAvion) {
        this.tAvion = tAvion;
    }
    public boolean isDisponible() {
        return disponible;
    }
    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }
    public String getCiudadActual() {
        return ciudadActual;
    }
    public void setCiudadActual(String ciudadActual) {
        this.ciudadActual = ciudadActual;
    }

    @Override
    public String toString() {
        return "Avión: " +
                "idAvion = " + idAvion  +
                ", Compañía = " + compañia +
                ", Tipo de avion =" +  tAvion.getDescripcion()  +
                ", Capacidad = " + tAvion.getCapacidad()  +
                ", Disponible = " + disponible;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Avion avion)) return false;
        return Objects.equals(idAvion, avion.idAvion);
    }

    @Override
    public int hashCode() {
        return idAvion.hashCode();
    }
}

