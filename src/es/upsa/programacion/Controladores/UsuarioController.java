package es.upsa.programacion.Controladores;

import es.upsa.programacion.Modelos.Usuario;
import es.upsa.programacion.Modelos.Agencia;

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

}
