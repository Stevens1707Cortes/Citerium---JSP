
package tests;

import dao.JdbcProductoDAO;
import dao.ProductoDAO;
import java.sql.SQLException;
import java.util.List;
import model.Producto;


public class TestJDBCProducto {
    public static void main(String[] args) throws SQLException {
        ProductoDAO productoDao = new JdbcProductoDAO();
        
        Producto producto1 = new Producto("Arroz", 1545, "Comida", "12-05-2024", 3, 2500);
        Producto producto2 = new Producto("Sopa", 0245, "Comida", "11-05-2024", 1, 4560);
        Producto producto1Act = new Producto("PerroCaliente", 1545, "Comida", "12-05-2024", 5, 4878);
        
//        productoDao.ingresarProducto(producto2);
        
        productoDao.actualizarProducto(producto1Act);
        
        List<Producto> productos = productoDao.obtenerProductos();
        productos.forEach(p -> { System.out.println(p); });
    }    
}
