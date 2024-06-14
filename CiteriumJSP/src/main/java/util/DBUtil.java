package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//POOL DE CONEXIONES A LA BD
public class DBUtil {

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/citerium?useSSL=false&useTimezone=true&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "";
    
    //Cargar por medio de un bloque statico el Driver para JDBC
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Error al cargar el driver JDBC de MySQL", e);
        }
    }

    // Método para obtener una conexión directamente con DriverManager
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
    }

    // Métodos para cerrar recursos
    public static void close(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
            }
        }
    }

    public static void close(Statement stm) {
        if (stm != null) {
            try {
                stm.close();
            } catch (SQLException e) {
            }
        }
    }

    public static void close(PreparedStatement pstm) {
        if (pstm != null) {
            try {
                pstm.close();
            } catch (SQLException e) {
            }
        }
    }

    public static void close(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
            }
        }
    }
}
