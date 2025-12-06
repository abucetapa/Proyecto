package es.upsa.programacion.Controladores;

import es.upsa.programacion.Modelos.*;

import java.util.List;


public class UsuarioController {
    private List<Usuario> usuarios;

    public UsuarioController(Agencia agencia){
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
    // Devuelven cliente o administrador, dependiendo de la funcion
    public Cliente buscarClienteId(String idUsuario){
        for(Usuario usuario : usuarios){ //Itera en lista de usuarios
            if(usuario instanceof Cliente cliente) { // Comprueba que usuario es un cliente
                if (usuario.getIdUser().equals(idUsuario)) {
                    return cliente;
                }
            }
        }
        return null;
    }

    public Cliente buscarClienteEmail(String email){
        for(Usuario usuario : usuarios){ //Itera en lista de usuarios
            if(usuario instanceof Cliente cliente) {// Comprueba que usuario es un cliente
                if (cliente.getEmail().equals(email)){
                    return cliente;
                }
            }
        }
        return null;
    }

    public Cliente buscarClienteTelefono(String telefono){
        for(Usuario usuario : usuarios){ //Itera en lista de usuarios
            if(usuario instanceof Cliente cliente) {// Comprueba que usuario es un cliente
                if (cliente.getTelefono().equals(telefono)){
                    return cliente;
                }
            }
        }
        return null;
    }

    public Cliente buscarClienteDni(String dni){
        for(Usuario usuario : usuarios){ //Itera en lista de usuarios
            if(usuario instanceof Cliente cliente) { // Comprueba que usuario es un cliente
                if (cliente.getDni().equals(dni)){
                    return cliente;
                }
            }
        }
        return null;
    }

    public Administrador buscarAdminId(String idUsuario){
        for(Usuario usuario : usuarios){ //Itera en lista de usuarios
            if(usuario instanceof Administrador administrador) { //Comprueba que usuario es administrador
                if (administrador.getIdUser().equals(idUsuario)){
                    return administrador;
                }
            }
        }
        return null;
    }

    // Añadrir vuelo a las reservas del usuario
    public boolean addVueloReservado(Usuario idUsuario, Vuelo vuelo, int intNReservas){
        Cliente cliente = buscarClienteId(idUsuario.getIdUser()); // Busca cliente por su id

        if(cliente == null || vuelo == null) { // Vuelo o usuario no existen
            return false;
        }

        //Comprueba que el numero de asientos disponibles es mayor o igual que el numero de asientos que quiere reservar
        if(vuelo.getAsientos() < intNReservas) {
            return false;
        }

        for(int i = 0; i < intNReservas; i++) { // Itera el numero de vuelos que quiere reservar
            cliente.getReservados().add(vuelo); //Se añade el vuelo al Array de reservados del usuario
        }
        vuelo.setAsientos(vuelo.getAsientos() - intNReservas); //Reduce el número de asientos disponibles
                                                                     // para el vuelo
        return true;
    }




}
