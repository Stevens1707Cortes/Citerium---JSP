
package dao;

import java.sql.SQLException;
import java.util.List;
import model.Usuario;



public interface UsuarioDAO {
    void ingresarUsuario(Usuario usuario) throws SQLException;
    Usuario obtenerUsuario(int id)throws SQLException;
    List<Usuario> obtenerUsuarios()throws SQLException;
    void actualizarUsuario(Usuario usuario)throws SQLException;
    void eliminarUsuario(int id)throws SQLException;
    Usuario obtenerUsuarioPorNombre(String nombre)throws SQLException;
}
