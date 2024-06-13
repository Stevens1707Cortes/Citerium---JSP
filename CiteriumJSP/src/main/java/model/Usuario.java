
package model;


public class Usuario {
    private int id;
    private String nombre;
    private String apellido;
    private int identificacion;
    private String email;
    private String contrasena;
    
    //Constructores

    public Usuario() {
    }

    public Usuario(int id) {
        this.id = id;
    }

    public Usuario(String nombre, String apellido, int identificacion, String email, String contrasena) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.identificacion = identificacion;
        this.email = email;
        this.contrasena = contrasena;
    }

    public Usuario(int id, String nombre, String apellido, int identificacion, String email, String contrasena) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.identificacion = identificacion;
        this.email = email;
        this.contrasena = contrasena;
    }
    
    //Getter - Setter

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(int identificacion) {
        this.identificacion = identificacion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
    
    //ToString

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Usuario{");
        sb.append("id=").append(id);
        sb.append(", nombre=").append(nombre);
        sb.append(", apellido=").append(apellido);
        sb.append(", identificacion=").append(identificacion);
        sb.append(", email=").append(email);
        sb.append(", contrasena=").append(contrasena);
        sb.append('}');
        return sb.toString();
    }
    
}
