
package service;

import dao.UsuarioDAO;
import java.sql.SQLException;
import java.util.List;
import model.Usuario;


public class UsuarioService {
    
     private UsuarioDAO usuarioDAO;

    public UsuarioService(UsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }

    public void crearUsuario(Usuario usuario) throws SQLException {
        usuarioDAO.ingresarUsuario(usuario);
    }

    public Usuario obtenerUsuario(int id) throws SQLException {
        return usuarioDAO.obtenerUsuario(id);
    }

    public List<Usuario> obtenerTodosLosUsuarios() throws SQLException {
        return usuarioDAO.obtenerUsuarios();
    }

    public void actualizarUsuario(Usuario usuario) throws SQLException {
        usuarioDAO.actualizarUsuario(usuario);
    }

    public void eliminarUsuario(int id) throws SQLException {
        usuarioDAO.eliminarUsuario(id);
    }

    public Usuario obtenerUsuarioPorNombre(String nombre) throws SQLException {
        return usuarioDAO.obtenerUsuarioPorNombre(nombre);
    }
}
