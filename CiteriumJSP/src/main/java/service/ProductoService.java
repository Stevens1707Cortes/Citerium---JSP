
package service;

import dao.ProductoDAO;
import java.sql.SQLException;
import java.util.List;
import model.Producto;


public class ProductoService {
    private ProductoDAO productoDAO;

    public ProductoService(ProductoDAO productoDAO) {
        this.productoDAO = productoDAO;
    }

    public void crearProducto(Producto producto) throws SQLException {
        productoDAO.ingresarProducto(producto);
    }

    public Producto obtenerProducto(int id) throws SQLException {
        return productoDAO.obtenerProducto(id);
    }

    public List<Producto> obtenerTodosLosProductos() throws SQLException {
        return productoDAO.obtenerProductos();
    }

    public void actualizarProducto(Producto producto) throws SQLException {
        productoDAO.actualizarProducto(producto);
    }

    public void eliminarProducto(int id) throws SQLException {
        productoDAO.eliminarProducto(id);
    }
}
