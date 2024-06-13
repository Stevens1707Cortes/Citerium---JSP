
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Usuario;
import util.DBUtil;


public class JdbcUsuarioDAO implements UsuarioDAO{
    
    //Conexiones desde fuera de esta clase
    private Connection conexionTransaccional;
    
    private static final String SQL_SELECTID = "SELECT * FROM usuario WHERE id_usuario = ?";
    private static final String SQL_SELECT = "SELECT * FROM usuario";
    private static final String SQL_INSERT = "INSERT INTO usuario(nombre, apellido, identificacion, email, contrasena) VALUES(?,?,?,?,?)";
    private static final String SQL_UPDATE = "UPDATE usuario SET nombre = ?, apellido = ?, identificacion = ?, email = ?, contrasena = ? WHERE id_usuario = ?";
    private static final String SQL_DELETE = "DELETE FROM usuario WHERE id_usuario = ?";
    
    //Contructor

    public JdbcUsuarioDAO() {
    }
    
    public JdbcUsuarioDAO(Connection conexionTransaccional) {
        this.conexionTransaccional = conexionTransaccional;
    }
    
    /*Ingresar un usuario*/
    @Override
    public void ingresarUsuario(Usuario usuario) throws SQLException {
        int registrosInsertados = 0;
        Connection conn = null;
        PreparedStatement psmt = null;

        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : DBUtil.getConecction();
            psmt = conn.prepareStatement(SQL_INSERT);

            psmt.setString(1, usuario.getNombre());
            psmt.setString(2, usuario.getApellido());
            psmt.setInt(3, usuario.getIdentificacion());
            psmt.setString(4, usuario.getEmail());
            psmt.setString(5, usuario.getContrasena());

            //Estamos alterando el estado de la base de datos
            registrosInsertados = psmt.executeUpdate(); // Este ejecute INSERT INTO, UPDATE, y DELETE

        } finally {
            try {
                DBUtil.close(psmt);
                if (this.conexionTransaccional == null) {
                    DBUtil.close(conn);
                }
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
        System.out.println("Ejecuntando query: " + SQL_INSERT);
        System.out.println("Registros insertados: " + registrosInsertados);
    }

    @Override
    public Usuario obtenerUsuario(int id) throws SQLException {
        Connection conn = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        Usuario usuario;

        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : DBUtil.getConecction();
            psmt = conn.prepareStatement(SQL_SELECTID);
            psmt.setInt(1, id);
            rs = psmt.executeQuery(); //Este ejecuta los SELECT

            if (rs.next()) {
                int idUsuario = rs.getInt("id_usuario");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                int identificacion = rs.getInt("identificacion");
                String email = rs.getString("email");
                String contrasena = rs.getString("contrasena");

                usuario = new Usuario(idUsuario, nombre, apellido, identificacion, email, contrasena);

                return usuario;
            }
        } finally {
            DBUtil.close(rs);
            DBUtil.close(psmt);
            if (this.conexionTransaccional == null) {
                DBUtil.close(conn);
            }

        }
        System.out.println("Ejecuntando query: " + SQL_SELECTID);
        return null;
    }

    @Override
    public List<Usuario> obtenerUsuarios() throws SQLException {
        Connection conn = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
         Usuario usuario;
        List<Usuario> usuarios = new ArrayList<>();
        /*
            Este codigo hace referencia a que si hay una conexionTransaccional abierta, esta se mantendra siempre y cuando no sea nula.
            Si llegase a ser nula, entonces ahi terminaria la transaccion y se cerraria la conexion a la BD.
             */
        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : DBUtil.getConecction();
            psmt = conn.prepareStatement(SQL_SELECT);
            rs = psmt.executeQuery();

            while (rs.next()) {
                int idUsuario = rs.getInt("id_usuario");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                int identificacion = rs.getInt("identificacion");
                String email = rs.getString("email");
                String contrasena = rs.getString("contrasena");
                

                usuario = new Usuario(idUsuario, nombre, apellido, identificacion, email, contrasena);

                usuarios.add(usuario);
            }
        } finally {
            DBUtil.close(rs);
            DBUtil.close(psmt);
            if (this.conexionTransaccional == null) {
                DBUtil.close(conn);
            }
        }
        System.out.println("Ejecuntando query: " + SQL_SELECT);
        return usuarios;
    }

    @Override
    public void actualizarUsuario(Usuario usuario) throws SQLException {
        int registrosActualizados = 0;

        Connection conn = null;
        PreparedStatement psmt = null;

        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : DBUtil.getConecction();
            psmt = conn.prepareStatement(SQL_UPDATE);

            psmt.setString(1, usuario.getNombre());
            psmt.setString(2, usuario.getApellido());
            psmt.setInt(3, usuario.getIdentificacion());
            psmt.setString(4, usuario.getEmail());
            psmt.setString(5, usuario.getContrasena());
            psmt.setInt(6, usuario.getId());

            registrosActualizados = psmt.executeUpdate();

        } finally {
            try {
                DBUtil.close(psmt);
                if (this.conexionTransaccional == null) {
                    DBUtil.close(conn);
                }
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
        System.out.println("Ejecuntando query: " + SQL_UPDATE);
        System.out.println("Registros actualizados: " + registrosActualizados);
    }

    @Override
    public void eliminarUsuario(int id) throws SQLException {
        int registrosEliminados = 0;
        Connection conn = null;
        PreparedStatement psmt = null;

        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : DBUtil.getConecction();
            psmt = conn.prepareStatement(SQL_DELETE);
            psmt.setInt(1, id);
            registrosEliminados = psmt.executeUpdate();

        } finally {
            try {
                DBUtil.close(psmt);
                if (this.conexionTransaccional == null) {
                    DBUtil.close(conn);
                }
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
        System.out.println("Ejecuntando query: " + SQL_DELETE);
        System.out.println("Registros eliminados: " + registrosEliminados);
    }

    @Override
    public Usuario obtenerUsuarioPorNombre(String nombre) throws SQLException {
        Connection conn = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        Usuario usuario;

        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : DBUtil.getConecction();
            psmt = conn.prepareStatement(SQL_SELECTID);
            psmt.setString(1, nombre);
            rs = psmt.executeQuery(); //Este ejecuta los SELECT

            if (rs.next()) {
                int idUsuario = rs.getInt("id_usuario");
                String apellido = rs.getString("apellido");
                int identificacion = rs.getInt("identificacion");
                String email = rs.getString("email");
                String contrasena = rs.getString("contrasena");

                usuario = new Usuario(idUsuario, nombre, apellido, identificacion, email, contrasena);

                return usuario;
            }
        } finally {
            DBUtil.close(rs);
            DBUtil.close(psmt);
            if (this.conexionTransaccional == null) {
                DBUtil.close(conn);
            }

        }
        System.out.println("Ejecuntando query: " + SQL_SELECT);
        return null;
    }
    
}
