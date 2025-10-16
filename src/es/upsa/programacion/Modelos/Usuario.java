package es.upsa.programacion.Modelos;

public class Usuario {
    private String idUser;
    private String nombre;
    private String dni;
    private String password;
    private String email;
    private Boolean admin;

    public Usuario(String idUser, String nombre, String dni, String password, String email, Boolean admin) {
        this.idUser = idUser;
        this.nombre = nombre;
        this.dni = dni;
        this.password = password;
        this.email = email;
        this.admin = admin;//Se asigna directamente a no con nuevos usuarios, solo se le permite cambiar a un admin.
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
    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getAdmin() {
        return admin;
    }
    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

}
