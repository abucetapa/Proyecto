package es.upsa.programacion.Controladores;

import es.upsa.programacion.Modelos.*;

import java.util.HashMap;
import java.util.Map;


public class UsuarioController {
    private Map<String,Usuario> usuariosMap;
    private Map<String, Cliente> dniClientesMap;
    private Map<String, Cliente> emailClientesMap;
    private Map<String,Cliente> telefonoClientesMap;

    public UsuarioController(Agencia agencia){
        this.usuariosMap = agencia.getUsuariosMap();
        this.dniClientesMap = agencia.getDniClientesMap();
        this.emailClientesMap = agencia.getEmailClientesMap();
        this.telefonoClientesMap = agencia.getTelefonoClientesMap();

    }



    // Añadir usuario

    public boolean addUsuario(Usuario usuario){
        Usuario u = buscarClienteId(usuario.getIdUser());
        if(u == null) {
            usuariosMap.put(usuario.getIdUser(),usuario);
            return true;
        }return false;
    }

    // Busqueda usuario por id, dni, email y telefono
    // Devuelven cliente o administrador, dependiendo de la funcion
    public Cliente buscarClienteId(String idUsuario){
        return (Cliente) usuariosMap.get(idUsuario);
    }

    public Cliente buscarClienteEmail(String email){
        return emailClientesMap.get(email);
    }

    public Cliente buscarClienteTelefono(String telefono){
        return telefonoClientesMap.get(telefono);
    }

    public Cliente buscarClienteDni(String dni){
        return dniClientesMap.get(dni);
    }

    public Administrador buscarAdminId(String idUsuario){
        Usuario u = usuariosMap.get(idUsuario);
        if(u instanceof Administrador){
            return (Administrador) u;
        }
        return null;
    }

    // Añadrir vuelo a las reservas del usuario
    public boolean addVueloReservado(Usuario usuario, Vuelo vuelo, int intNReservas){
        Cliente cliente = buscarClienteId(usuario.getIdUser()); // Busca cliente por su id

        if(cliente == null || vuelo == null) { // Vuelo o usuario no existen
            return false;
        }

        //Comprueba que el numero de asientos disponibles es mayor o igual que el numero de asientos que quiere reservar
        if (!vuelo.verificarDisponibilidad(intNReservas)) {
            return false; // No hay sitio
        }

        for(int i = 0; i < intNReservas; i++) { // Itera el numero de vuelos que quiere reservar
            cliente.getReservados().add(vuelo); //Se añade el vuelo al Array de reservados del usuario
        }
        vuelo.setAsientos(vuelo.getAsientos() - intNReservas); //Reduce el número de asientos disponibles
                                                                     // para el vuelo
        return true;
    }

    public boolean addReservaVueloPrivado(Usuario idUsuario, Vuelo vuelo){
        Cliente cliente = buscarClienteId(idUsuario.getIdUser()); // Busca cliente por su id

        if(cliente == null || vuelo == null) { // Vuelo o usuario no existen
            return false;
        }
        cliente.getReservados().add(vuelo); //Se añade el vuelo al Array de reservados del usuario

        return true;
    }




}
