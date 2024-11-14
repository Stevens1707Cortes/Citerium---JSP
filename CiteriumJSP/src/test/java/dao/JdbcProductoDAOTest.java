/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Producto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author steve
 */
public class JdbcProductoDAOTest {

    private JdbcProductoDAO instance;

    @BeforeEach
    public void setUp() {
        instance = new JdbcProductoDAO();
    }

    @Test
    public void testIngresarProducto() throws Exception {
        System.out.println("ingresarProducto");

        // Crear un producto para insertar
        Producto producto = new Producto("Producto Test", 1234, "Categoria", "2024-11-13", 10, 100.0);

        JdbcProductoDAO instance = new JdbcProductoDAO();
        instance.ingresarProducto(producto);  // Inserta el producto en la base de datos

        // Obtener el ID generado para el producto insertado
        assertNotNull(producto.getId()); // Asegúrate de que el ID generado no sea nulo
        assertTrue(producto.getId() > 0); // Verifica que el ID sea mayor que 0, lo cual indica que fue insertado correctamente
    }

    @Test
    public void testObtenerProducto() throws Exception {
        System.out.println("obtenerProducto");
        // Crear un producto para insertar
        Producto producto = new Producto("Producto Test", 1234, "Categoria", "2024-11-13", 10, 100.0);

        // Insertar el producto usando el método ingresarProducto
        JdbcProductoDAO instance = new JdbcProductoDAO();
        instance.ingresarProducto(producto);

        // Obtener el producto recién insertado
        Producto result = instance.obtenerProducto(producto.getId()); // Asegúrate de obtener el producto con el ID generado
        assertNotNull(result); // Verifica que no sea nulo
        assertEquals(producto.getId(), result.getId()); // Verifica que el ID coincida
        assertEquals(producto.getNombre(), result.getNombre()); // Verifica que el nombre coincida
        // Puedes agregar más verificaciones para los otros campos
    }

    @Test
    public void testObtenerProductoCodigo() throws SQLException {
        Producto producto = new Producto("ProductoTest", 1234, "CategoriaTest", "2024-11-01", 100, 20.5);
        instance.ingresarProducto(producto);

        Producto productoRecuperado = instance.obtenerProductoCodigo(1234);
        assertNotNull(productoRecuperado);
        assertEquals(1234, productoRecuperado.getCodigo());
    }

    @Test
    public void testObtenerProductos() throws SQLException {
        Producto producto1 = new Producto("ProductoTest1", 1234, "CategoriaTest1", "2024-11-01", 100, 20.5);
        Producto producto2 = new Producto("ProductoTest2", 1235, "CategoriaTest2", "2024-11-02", 150, 25.0);
        instance.ingresarProducto(producto1);
        instance.ingresarProducto(producto2);

        List<Producto> productos = instance.obtenerProductos();
        assertNotNull(productos);
        assertTrue(!productos.isEmpty());
    }

    @Test
    public void testActualizarProducto() throws SQLException {
        Producto producto = new Producto("ProductoTest", 1234, "CategoriaTest", "2024-11-01", 100, 20.5);
        instance.ingresarProducto(producto);

        producto.setNombre("ProductoActualizado");
        producto.setPrecio(22.0);
        instance.actualizarProducto(producto);

        Producto productoRecuperado = instance.obtenerProductoCodigo(1234);
        assertNotNull(productoRecuperado);
        assertEquals("ProductoActualizado", productoRecuperado.getNombre());
        assertEquals(22.0, productoRecuperado.getPrecio());
    }

    @Test
    public void testEliminarProducto() throws SQLException {
        Producto producto = new Producto("ProductoTest", 1234, "CategoriaTest", "2024-11-01", 100, 20.5);
        instance.ingresarProducto(producto);
        instance.eliminarProducto(1234);

        Producto productoRecuperado = instance.obtenerProductoCodigo(1234);
        assertNull(productoRecuperado);  // Producto ya no debería existir
    }

    @Test
    public void testDescontarInventario() throws SQLException {
        Producto producto = new Producto("ProductoTest", 1234, "CategoriaTest", "2024-11-01", 100, 20.5);
        instance.ingresarProducto(producto);

        List<Producto> productosSeleccionados = new ArrayList<>();
        producto.setUnidades(10);  // Se van a descontar 10 unidades
        productosSeleccionados.add(producto);

        instance.descontarInventario(productosSeleccionados);

        Producto productoRecuperado = instance.obtenerProductoCodigo(1234);
        assertNotNull(productoRecuperado);
        assertEquals(90, productoRecuperado.getUnidades());  // El inventario debe haberse descontado
    }
}
