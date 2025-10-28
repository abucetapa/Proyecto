package es.upsa.programacion.Controladores;

import es.upsa.programacion.Modelos.*;

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
        Usuario u = buscarClienteId(usuario.getIdUser());
        if(u == null) {
            usuarios.add(usuario);
            return true;
        }return false;
    }

    // Busqueda usuario por id, dni, email y telefono

    public Cliente buscarClienteId(String idUsuario){
        for(Usuario usuario : usuarios){
            if(usuario.getIdUser().equals(idUsuario)){
                return (Cliente) usuario;
            }
        }
        return null;
    }
    public Cliente buscarClienteEmail(String email){
        for(Usuario usuario : usuarios){
            if(usuario instanceof Cliente cliente) {
                if (cliente.getEmail().equals(email)){
                    return cliente;
                }
            }
        }
        return null;
    }

    public Cliente buscarClienteTelefono(String telefono){
        for(Usuario usuario : usuarios){
            if(usuario instanceof Cliente cliente) {
                if (cliente.getTelefono().equals(telefono)){
                    return cliente;
                }
            }
        }
        return null;
    }

    public Cliente buscarClienteDni(String dni){
        for(Usuario usuario : usuarios){
            if(usuario instanceof Cliente cliente) {
                if (cliente.getDni().equals(dni)){
                    return cliente;
                }
            }
        }
        return null;
    }

    public Administrador buscarAdminId(String idUsuario){
        for(Usuario usuario : usuarios){
            if(usuario instanceof Administrador administrador) {
                if (administrador.getIdUser().equals(idUsuario)){
                    return administrador;
                }
            }
        }
        return null;
    }

    // Añadrir vuelo a las reservas del usuario

    public boolean añadirVueloReservado(String idUsuario, Vuelo vuelo){
        Cliente cliente = buscarClienteId(idUsuario);

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
