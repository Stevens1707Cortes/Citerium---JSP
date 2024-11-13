
package dao;

import java.sql.SQLException;
import java.util.List;
import model.Producto;


public interface ProductoDAO {
    void ingresarProducto(Producto producto)throws SQLException;
    Producto obtenerProducto(int id)throws SQLException;
    Producto obtenerProductoCodigo(int codigo)throws SQLException;
    List<Producto> obtenerProductos()throws SQLException;
    void actualizarProducto(Producto producto)throws SQLException;
    void eliminarProducto(int codigo)throws SQLException;
    void descontarInventario(List<Producto> productosSeleccionados) throws SQLException;
}
