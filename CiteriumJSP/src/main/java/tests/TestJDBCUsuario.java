package tests;

import dao.JdbcUsuarioDAO;
import dao.UsuarioDAO;
import java.sql.SQLException;
import java.util.List;
import model.Usuario;


public class TestJDBCUsuario {
    public static void main(String[] args) throws SQLException {
        UsuarioDAO usuarioDao = new JdbcUsuarioDAO();
        
        Usuario usuario1 = new Usuario("Jeisson", "Apellido", 1234567, "correo@.com", "12345abc");
        
        usuarioDao.ingresarUsuario(usuario1);
        
        List<Usuario> usuarios = usuarioDao.obtenerUsuarios();
        
        usuarios.forEach(u -> {
            System.out.println(u);
        });
        
        int id = usuario1.getId();
        System.out.println(usuarioDao.obtenerUsuario(id));
        
    }
}
