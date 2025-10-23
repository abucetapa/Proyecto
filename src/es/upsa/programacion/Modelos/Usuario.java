package es.upsa.programacion.Modelos;

import java.util.ArrayList;
import java.util.Objects;

public abstract class Usuario {

    private String idUser;
    private String password;

    public Usuario(String idUser, String password) {
        this.idUser = idUser;
        this.password = password;
    }

    public String getIdUser() {
        return idUser;
    }
    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
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
        return "{idUser: " +idUser +", password: "+ password +"}";
    }


}
