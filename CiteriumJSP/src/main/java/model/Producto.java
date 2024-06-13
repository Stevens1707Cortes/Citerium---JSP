
package model;



public class Producto {
    private int id;
    private String nombre;
    private int codigo;
    private String categoria;
    private String fecha;
    private int unidades;
    
    //Constructor
    
    public Producto() {
    }
    
    public Producto(int id) {
        this.id = id;
    }
    
    public Producto(String nombre, int codigo, String categoria, String fecha, int unidades) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.categoria = categoria;
        this.fecha = fecha;
        this.unidades = unidades;
    }

    public Producto(int id, String nombre, int codigo, String categoria, String fecha, int unidades) {
        this.id = id;
        this.nombre = nombre;
        this.codigo = codigo;
        this.categoria = categoria;
        this.fecha = fecha;
        this.unidades = unidades;
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

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getUnidades() {
        return unidades;
    }

    public void setUnidades(int unidades) {
        this.unidades = unidades;
    }
    
    // ToString

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Producto{");
        sb.append("id=").append(id);
        sb.append(", nombre=").append(nombre);
        sb.append(", codigo=").append(codigo);
        sb.append(", categoria=").append(categoria);
        sb.append(", fecha=").append(fecha);
        sb.append(", unidades=").append(unidades);
        sb.append('}');
        return sb.toString();
    }
    
}
