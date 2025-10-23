package es.upsa.programacion.Controladores;

import es.upsa.programacion.Modelos.Usuario;
import es.upsa.programacion.Modelos.Agencia;
import es.upsa.programacion.Modelos.Vuelo;

import java.util.ArrayList;
import java.util.List;


public class UsuarioController {
    private List<Usuario> usuarios;
    private Agencia agencia;

    public UsuarioController(Agencia agencia){
        this.agencia=agencia;
        this.usuarios = agencia.getUsuarios();
    }

    // Añadir usuario

    public boolean addUsuario(Usuario usuario){
        if(usuario.getIdUser() != null && usuario.getDni() != null && usuario.getNombre() != null && usuario.getEmail() != null && usuario.getPassword() != null && usuario.getTelefono() != null){
            usuarios.add(usuario);
            return true;
        }else return false;
    }

    // Busqueda usuario por id, dni, email y telefono

    public Usuario buscarUsuarioId(String idUsuario){
        for(Usuario usuario : usuarios){
            if(usuario.getIdUser().equals(idUsuario)){
                return usuario;
            }
        }
        return null;
    }
    public Usuario buscarUsuarioEmail(String email){
        for(Usuario usuario : usuarios){
            if(usuario.getEmail().equals(email)){
                return usuario;
            }
        }
        return null;
    }

    public Usuario buscarUsuarioTelefono(String telefono){
        for(Usuario usuario : usuarios){
            if(usuario.getTelefono().equals(telefono)){
                return usuario;
            }
        }
        return null;
    }

    public Usuario buscarUsuarioDni(String dni){
        for(Usuario usuario : usuarios){
            if(usuario.getDni().equals(dni)){
                return usuario;
            }
        }
        return null;
    }

    // Añadrir vuelo a las reservas del usuario

    public boolean añadirVueloReservado(String idUsuario, Vuelo vuelo){
        Usuario cliente = buscarUsuarioId(idUsuario);

        if(cliente == null || vuelo == null) {
            return false;
        }

        if(vuelo.getdisponibles() <= 0) {
            return false;
        }

        if(cliente.getReservados().contains(vuelo)) {
            return false;
        }
        cliente.getReservados().add(vuelo);
        vuelo.setdisponibles(vuelo.getdisponibles() - 1);

        return true;
    }


}
