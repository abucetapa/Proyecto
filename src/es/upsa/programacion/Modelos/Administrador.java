package es.upsa.programacion.Modelos;

public class Administrador extends Usuario {

    public Administrador(String idUser, String password) {
        super(idUser, password);
    }

    @Override
    public String toString(){
        return "Administrador " + super.toString();
    }
}
