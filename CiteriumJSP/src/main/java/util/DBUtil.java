
package util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;

//POOL DE CONEXIONES A LA BD
public class DBUtil {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/citerium?useSSL=false&useTimezone=true&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "";
    private static BasicDataSource dataSource;

    //Metodo que maneja el pool de conexiones, en lugar de usar DriverManager
    public static DataSource getDataSource() {
        if (dataSource == null) {
            dataSource = new BasicDataSource();
            //Ingresamos los datos de la conexion
            dataSource.setUrl(JDBC_URL);
            dataSource.setUsername(JDBC_USER);
            dataSource.setPassword(JDBC_PASSWORD);
            //Definimos el tamaño inicial del pool
            dataSource.setInitialSize(5); //Un tamaño muy grande usa muchos recursos
        }
        return dataSource;
    }

    public static Connection getConecction() throws SQLException {
        return getDataSource().getConnection();
    }

    public static void close(ResultSet rs) throws SQLException {
        rs.close();
    }

    public static void close(Statement stm) throws SQLException {
        stm.close();
    }

    public static void close(PreparedStatement pstm) throws SQLException { //Tiene un mejor perfomance que Statement
        pstm.close();
    }

    public static void close(Connection conn) throws SQLException {
        conn.close();
    }
}
