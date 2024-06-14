
package service;

import dao.ProductoDAO;
import java.sql.SQLException;
import java.util.List;
import model.Producto;


public class ProductoService {
    private final ProductoDAO productoDAO;

    public ProductoService(ProductoDAO productoDAO) {
        this.productoDAO = productoDAO;
    }

    public void crearProducto(Producto producto) throws SQLException {
        
        productoDAO.ingresarProducto(producto);
    }

    public Producto obtenerProducto(int id) throws SQLException {
        return productoDAO.obtenerProducto(id);
    }
    
    public Producto obtenerProductoCodigo(int codigo) throws SQLException {
        return productoDAO.obtenerProductoCodigo(codigo);
    }

    public List<Producto> obtenerTodosLosProductos() throws SQLException {
        return productoDAO.obtenerProductos();
    }

    public void actualizarProducto(Producto producto) throws SQLException {
        productoDAO.actualizarProducto(producto);
    }

    public void eliminarProducto(int codigo) throws SQLException {
        productoDAO.eliminarProducto(codigo);
    }
}
