/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package dao;

import java.sql.SQLException;
import java.util.List;
import model.Usuario;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author steve
 */
public class JdbcUsuarioDAOTest {

    private JdbcUsuarioDAO instance;
    private Usuario testUsuario;

    @BeforeAll
    public static void setUpClass() {
        // Inicializar cualquier recurso compartido si es necesario.
    }

    @AfterAll
    public static void tearDownClass() {
        // Limpiar cualquier recurso compartido si es necesario.
    }

    @BeforeEach
    public void setUp() throws SQLException {
        instance = new JdbcUsuarioDAO();
        testUsuario = new Usuario("Test", "User", 123456789, "test@example.com", "password123");
        // Insertar un usuario de prueba antes de cada test
        instance.ingresarUsuario(testUsuario);
    }

    @AfterEach
    public void tearDown() throws SQLException {
        // Eliminar el usuario de prueba después de cada test
        Usuario usuario = instance.obtenerUsuarioPorNombre(testUsuario.getNombre());
        if (usuario != null) {
            instance.eliminarUsuario(usuario.getId());
        }
    }

    @Test
    public void testIngresarUsuario() throws SQLException {
        Usuario nuevoUsuario = new Usuario("John", "Doe", 987654321, "john.doe@example.com", "password456");
        instance.ingresarUsuario(nuevoUsuario);

        // Verificar que el usuario se ha ingresado correctamente
        Usuario usuarioRecuperado = instance.obtenerUsuarioPorNombre(nuevoUsuario.getNombre());
        assertNotNull(usuarioRecuperado);
        assertEquals(nuevoUsuario.getNombre(), usuarioRecuperado.getNombre());
    }

    @Test
    public void testObtenerUsuario() throws SQLException {
        Usuario usuarioRecuperado = instance.obtenerUsuario(testUsuario.getId());
        System.out.println(usuarioRecuperado);
        System.out.println(testUsuario);
        assertNotNull(usuarioRecuperado);
        assertEquals(testUsuario.getNombre(), usuarioRecuperado.getNombre());
    }

    @Test
    public void testObtenerUsuarios() throws SQLException {
        List<Usuario> usuarios = instance.obtenerUsuarios();
        assertNotNull(usuarios);
        assertTrue(!usuarios.isEmpty());  // Asumimos que hay usuarios en la base de datos
    }

    @Test
    public void testActualizarUsuario() throws SQLException {
        testUsuario.setNombre("Updated Name");
        instance.actualizarUsuario(testUsuario);

        Usuario usuarioActualizado = instance.obtenerUsuario(testUsuario.getId());
        assertNotNull(usuarioActualizado);
        assertEquals("Updated Name", usuarioActualizado.getNombre());
    }

    @Test
    public void testEliminarUsuario() throws SQLException {
        int id = testUsuario.getId();
        instance.eliminarUsuario(id);

        Usuario usuarioEliminado = instance.obtenerUsuario(id);
        assertNull(usuarioEliminado);  // El usuario debe haber sido eliminado
    }

    @Test
    public void testObtenerUsuarioPorNombre() throws SQLException {
        Usuario usuarioRecuperado = instance.obtenerUsuarioPorNombre(testUsuario.getNombre());
        assertNotNull(usuarioRecuperado);
        assertEquals(testUsuario.getNombre(), usuarioRecuperado.getNombre());
    }
}
