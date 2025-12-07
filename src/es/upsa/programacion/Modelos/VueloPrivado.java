package es.upsa.programacion.Modelos;

public class VueloPrivado extends Vuelo {

    private String terminal;
    private String puertaEmb;
    private Double precio;

    public VueloPrivado(String idVuelo, Avion avion, String salida, String destino, String fecha) {
        super(idVuelo, avion, salida, destino, fecha); // Llamada al padre

        this.precio = calculoAsientos(this.getAsientos());
    }


    private Double calculoAsientos(Integer asientos) {
        return precio = 650.0 * asientos + 100000;
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
    public Double getPrecio() {
        return precio;
    }
    public void setPrecio(Double precio) {
        this.precio = precio;
    }
    @Override
    public String toString() {
        return super.toString() + " [PRIVADO] T:" + terminal + " P:" + puertaEmb + " | Precio: " + precio + "€";
    }
}
