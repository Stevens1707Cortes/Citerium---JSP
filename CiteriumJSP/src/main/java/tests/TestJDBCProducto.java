
package tests;

import dao.JdbcProductoDAO;
import dao.ProductoDAO;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import model.Producto;
import util.DBUtil;


public class TestJDBCProducto {
    public static void main(String[] args) throws SQLException {
        Connection conexion = null;
        
        Producto producto1 = new Producto("Arroz", 1545, "Comida", "12-05-2024", 3);
        Producto producto2 = new Producto("Sopa", 0245, "Comida", "11-05-2024", 1);
        
        try {
            conexion = DBUtil.getConecction();
            if (conexion.getAutoCommit()) {
                conexion.setAutoCommit(false);
            }
            
            ProductoDAO productoDao = new JdbcProductoDAO(conexion);
            
            //Ingresar registros
//            productoDao.ingresarProducto(producto1);
//            productoDao.ingresarProducto(producto2);

            //Actualizar
//            var producto2Act = productoDao.obtenerProducto(2);
//            producto2Act.setNombre("Chocolates");
//            productoDao.actualizarProducto(producto2Act);
            
            //Eliminar
            productoDao.eliminarProducto(productoDao.obtenerProducto(2).getId());
            
            
            //Leer registros
            List<Producto> productos = productoDao.obtenerProductos();
            productos.forEach(p -> {System.out.println(p);});
            
            conexion.commit();
            System.out.println("Se ha realizado el commit");
            
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
            System.out.println("Entramos al rollback");
            conexion.rollback();
        }
        
        
        

        
        
    }
}
