package es.upsa.programacion.Modelos;

import java.util.ArrayList;
import java.util.Objects;

public class Usuario {
    private String idUser;
    private String nombre;
    private String dni;
    private String password;
    private String email;
    private String telefono;
    private Boolean admin;
    private ArrayList<Vuelo> reservados;

    public Usuario(String idUser, String nombre, String dni, String password, String email, String telefono, Boolean admin) {
        this.idUser = idUser;
        this.nombre = nombre;
        this.dni = dni;
        this.password = password;
        this.email = email;
        this.telefono = telefono;
        this.admin = admin;//Se asigna directamente a no con nuevos usuarios, solo se le permite cambiar a un admin.
        this.reservados = new ArrayList<>();
    }

    public String getIdUser() {
        return idUser;
    }
    public void setIdUser(String idUser) {
        this.idUser = idUser;
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
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getEmail() {
        return email;
    }
    public String getTelefono() {
        return telefono;
    }
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getAdmin() {
        return admin;
    }
    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }
    public ArrayList<Vuelo> getReservados() {return reservados;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Usuario)) return false;
        return Objects.equals(idUser, ((Usuario) o).idUser);
    }
    @Override
    public int hashCode() {return Objects.hashCode(idUser);}

    @Override
    public String toString() {
        //Caso 1: El usuario con dni x no tiene ningún vuelo asociado
        if (reservados.isEmpty()) {
            return nombre + " id:" + idUser + " (" + dni + "): con ningún vuelo asociado";
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
