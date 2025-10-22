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

    public boolean addUsuario(Usuario usuario){
        if(usuario.getIdUser() != null && usuario.getDni() != null && usuario.getNombre() != null && usuario.getEmail() != null && usuario.getPassword() != null && usuario.getTelefono() != null){
            usuarios.add(usuario);
            return true;
        }else return false;
    }

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

    public boolean añadirVueloReservado(String idUsuario, Vuelo vuelo){
        Usuario usuario = buscarUsuarioId(idUsuario);

        if(usuario == null || vuelo == null) {
            return false;
        }

        if(vuelo.getdisponibles() <= 0) {
            return false;
        }

        if(usuario.getReservados().contains(vuelo)) {
            return false;
        }
        usuario.getReservados().add(vuelo);
        vuelo.setdisponibles(vuelo.getdisponibles() - 1);

        return true;
    }


}
