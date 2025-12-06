package es.upsa.programacion.Modelos;


public class VueloComercial extends Vuelo {

    private String terminal;
    private String puertaEmb;
    private Double precio;

    public VueloComercial(String idVuelo, Avion avion, String salida, String destino,String terminal, String puertaEmb, String fecha, Double precio) {
        super(idVuelo, avion, salida, destino, fecha);
        this.terminal = terminal;
        this.puertaEmb = puertaEmb;
        this.precio = precio;
    }



    public String getTerminal() {
        return terminal;
    }

    public void setTerminal(String terminal) {
        this.terminal = terminal;
    }

    public String getPuertaEmb() {
        return puertaEmb;
    }

    public void setPuertaEmb(String puertaEmb) {
        this.puertaEmb = puertaEmb;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Double getPrecio() {
        return precio;
    }

    @Override
    public String toString() {
        return super.toString() + " [COMERCIAL] T:" + terminal + " P:" + puertaEmb + " | Precio: " + precio + "€";
    }
}
