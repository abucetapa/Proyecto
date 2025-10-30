package es.upsa.programacion.Modelos;

import java.util.ArrayList;

public class Cliente extends Usuario {


    private String nombre;
    private String dni;
    private String email;
    private String telefono;
    private ArrayList<Vuelo> reservados;

    public Cliente(String idUser,String nombre, String dni, String password, String email, String telefono) {
        super(idUser,password);
        this.nombre = nombre;
        this.dni = dni;
        this.email = email;
        this.telefono = telefono;
        this.reservados = new ArrayList<>();
    }



    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getDni() {
        return dni;
    }
    public void setDni(String dni) {
        this.dni = dni;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getTelefono() {
        return telefono;
    }
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    public ArrayList<Vuelo> getReservados() {
        return reservados;
    }

    @Override
    public String toString() {
        //Caso 1: El usuario con dni xq no tiene ningún vuelo asociado
        if (reservados.isEmpty()) {
            return nombre + " id:" + getIdUser() + " (" + dni + "): con ningún vuelo asociado";
        } else {
            //Caso 2: El usuario tiene uno o más vuelos asociados
            String resultado = nombre + " (" + dni + "): ";
            for (int i = 0; i < reservados.size(); i++) {
                Vuelo vuelo = reservados.get(i);
                resultado = resultado + vuelo.getIdVuelo() + " (" +
                        vuelo.getsalida() + "→" + vuelo.getdisponibles() +
                        ", fecha " + vuelo.getfecha() + ")";

                if (i < reservados.size() - 1) {
                    resultado = resultado + ", ";
                }
            }
            return resultado;
        }
    }


}
