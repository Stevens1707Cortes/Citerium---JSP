package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Producto;
import util.DBUtil;

public class JdbcProductoDAO implements ProductoDAO {

    //Conexiones desde fuera de esta clase
    private Connection conexionTransaccional;

    private static final String SQL_SELECTID = "SELECT * FROM producto WHERE id_producto = ?";
    private static final String SQL_SELECT = "SELECT * FROM producto";
    private static final String SQL_INSERT = "INSERT INTO producto(nombre, codigo, categoria, fecha, unidades) VALUES(?,?,?,?,?)";
    private static final String SQL_UPDATE = "UPDATE producto SET nombre = ?, codigo = ?, categoria = ?, fecha = ?, unidades = ? WHERE id_producto = ?";
    private static final String SQL_DELETE = "DELETE FROM producto WHERE id_producto = ?";

    //Contructor
    public JdbcProductoDAO() {
    }

    public JdbcProductoDAO(Connection conexionTransaccional) {
        this.conexionTransaccional = conexionTransaccional;
    }

    @Override
    public void ingresarProducto(Producto producto) throws SQLException {
        int registrosInsertados = 0;
        Connection conn = null;
        PreparedStatement psmt = null;

        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : DBUtil.getConecction();
            psmt = conn.prepareStatement(SQL_INSERT);

            psmt.setString(1, producto.getNombre());
            psmt.setInt(2, producto.getCodigo());
            psmt.setString(3, producto.getCategoria());
            psmt.setString(4, producto.getFecha());
            psmt.setInt(5, producto.getUnidades());

            
            registrosInsertados = psmt.executeUpdate(); 

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
    public Producto obtenerProducto(int id) throws SQLException {
        Connection conn = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        Producto producto;

        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : DBUtil.getConecction();
            psmt = conn.prepareStatement(SQL_SELECTID);
            psmt.setInt(1, id);
            rs = psmt.executeQuery(); //Este ejecuta los SELECT

            if (rs.next()) {
                int idUsuario = rs.getInt("id_producto");
                String nombre = rs.getString("nombre");
                int codigo = rs.getInt("codigo");
                String categoria = rs.getString("categoria");
                String fecha = rs.getString("fecha");
                int unidades = rs.getInt("unidades");

                producto = new Producto(idUsuario, nombre, codigo, categoria, fecha, unidades);

                return producto;
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
    public List<Producto> obtenerProductos() throws SQLException {
        Connection conn = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
         Producto producto;
        List<Producto> productos = new ArrayList<>();
        
        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : DBUtil.getConecction();
            psmt = conn.prepareStatement(SQL_SELECT);
            rs = psmt.executeQuery();

            while (rs.next()) {
                int idUsuario = rs.getInt("id_producto");
                String nombre = rs.getString("nombre");
                int codigo = rs.getInt("codigo");
                String categoria = rs.getString("categoria");
                String fecha = rs.getString("fecha");
                int unidades = rs.getInt("unidades");
                

                producto = new Producto(idUsuario, nombre, codigo, categoria, fecha, unidades);

                productos.add(producto);
            }
        } finally {
            DBUtil.close(rs);
            DBUtil.close(psmt);
            if (this.conexionTransaccional == null) {
                DBUtil.close(conn);
            }
        }
        System.out.println("Ejecuntando query: " + SQL_SELECT);
        return productos;
    }

    @Override
    public void actualizarProducto(Producto producto) throws SQLException {
        int registrosActualizados = 0;

        Connection conn = null;
        PreparedStatement psmt = null;

        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : DBUtil.getConecction();
            psmt = conn.prepareStatement(SQL_UPDATE);

            psmt.setString(1, producto.getNombre());
            psmt.setInt(2, producto.getCodigo());
            psmt.setString(3, producto.getCategoria());
            psmt.setString(4, producto.getFecha());
            psmt.setInt(5, producto.getUnidades());
            psmt.setInt(6, producto.getId());

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
    public void eliminarProducto(int id) throws SQLException {
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

}
