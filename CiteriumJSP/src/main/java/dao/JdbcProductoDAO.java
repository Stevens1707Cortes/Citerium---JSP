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

    private static final String SQL_SELECTCODE = "SELECT * FROM producto WHERE codigo = ?";
    private static final String SQL_SELECTID = "SELECT * FROM producto WHERE id_producto = ?";
    private static final String SQL_SELECT = "SELECT * FROM producto";
    private static final String SQL_INSERT = "INSERT INTO producto(nombre, codigo, categoria, fecha, unidades, precio) VALUES(?,?,?,?,?,?)";
    private static final String SQL_UPDATE = "UPDATE producto SET nombre = ?, categoria = ?, fecha = ?, unidades = ?, precio = ? WHERE codigo = ?";
    private static final String SQL_DELETE = "DELETE FROM producto WHERE codigo = ?";

    //Contructor
    public JdbcProductoDAO() {
    }

    @Override
    public void ingresarProducto(Producto producto) throws SQLException {
        int registrosInsertados = 0;
        Connection conn = null;
        PreparedStatement psmt = null;

        try {
            conn = DBUtil.getConnection();
            psmt = conn.prepareStatement(SQL_INSERT, PreparedStatement.RETURN_GENERATED_KEYS);

            psmt.setString(1, producto.getNombre());
            psmt.setInt(2, producto.getCodigo());
            psmt.setString(3, producto.getCategoria());
            psmt.setString(4, producto.getFecha());
            psmt.setInt(5, producto.getUnidades());
            psmt.setDouble(6, producto.getPrecio());

            registrosInsertados = psmt.executeUpdate();

            // Obtener el ID generado
            ResultSet rs = psmt.getGeneratedKeys();
            if (rs.next()) {
                int idGenerado = rs.getInt(1); // El primer valor es el ID generado
                producto.setId(idGenerado); // Establecemos el ID generado en el objeto Producto
            }

        } finally {
            DBUtil.close(psmt);
            DBUtil.close(conn);
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
            conn = DBUtil.getConnection();
            psmt = conn.prepareStatement(SQL_SELECTID);
            psmt.setInt(1, id);
            rs = psmt.executeQuery(); //Este ejecuta los SELECT

            if (rs.next()) {
                int idProducto = rs.getInt("id_producto");
                String nombre = rs.getString("nombre");
                int codigo = rs.getInt("codigo");
                String categoria = rs.getString("categoria");
                String fecha = rs.getString("fecha");
                int unidades = rs.getInt("unidades");
                double precio = rs.getDouble("precio");

                producto = new Producto(idProducto, nombre, codigo, categoria, fecha, unidades, precio);

                return producto;
            }
        } finally {
            DBUtil.close(rs);
            DBUtil.close(psmt);
            DBUtil.close(conn);
        }
        System.out.println("Ejecuntando query: " + SQL_SELECTID);
        return null;
    }

    @Override
    public Producto obtenerProductoCodigo(int codigo) throws SQLException {
        Connection conn = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        Producto producto;

        try {
            conn = DBUtil.getConnection();
            psmt = conn.prepareStatement(SQL_SELECTCODE);
            psmt.setInt(1, codigo);
            rs = psmt.executeQuery(); //Este ejecuta los SELECT

            if (rs.next()) {
                int id = rs.getInt("id_producto");
                String nombre = rs.getString("nombre");
                String categoria = rs.getString("categoria");
                String fecha = rs.getString("fecha");
                int unidades = rs.getInt("unidades");
                double precio = rs.getDouble("precio");

                producto = new Producto(id, nombre, codigo, categoria, fecha, unidades, precio);

                return producto;
            }
        } finally {
            DBUtil.close(rs);
            DBUtil.close(psmt);
            DBUtil.close(conn);
        }
        System.out.println("Ejecuntando query: " + SQL_SELECTCODE);
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
            conn = DBUtil.getConnection();
            psmt = conn.prepareStatement(SQL_SELECT);
            rs = psmt.executeQuery();

            while (rs.next()) {
                int idUsuario = rs.getInt("id_producto");
                String nombre = rs.getString("nombre");
                int codigo = rs.getInt("codigo");
                String categoria = rs.getString("categoria");
                String fecha = rs.getString("fecha");
                int unidades = rs.getInt("unidades");
                double precio = rs.getDouble("precio");

                producto = new Producto(idUsuario, nombre, codigo, categoria, fecha, unidades, precio);

                productos.add(producto);
            }
        } finally {
            DBUtil.close(rs);
            DBUtil.close(psmt);
            DBUtil.close(conn);
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
            conn = DBUtil.getConnection();
            psmt = conn.prepareStatement(SQL_UPDATE);

            psmt.setString(1, producto.getNombre());
            psmt.setString(2, producto.getCategoria());
            psmt.setString(3, producto.getFecha());
            psmt.setInt(4, producto.getUnidades());
            psmt.setDouble(5, producto.getPrecio());
            psmt.setInt(6, producto.getCodigo());

            registrosActualizados = psmt.executeUpdate();

        } finally {
            DBUtil.close(psmt);
            DBUtil.close(conn);
        }
        System.out.println("Ejecuntando query: " + SQL_UPDATE);
        System.out.println("Registros actualizados: " + registrosActualizados);
    }

    @Override
    public void eliminarProducto(int codigo) throws SQLException {
        int registrosEliminados = 0;
        Connection conn = null;
        PreparedStatement psmt = null;

        try {
            conn = DBUtil.getConnection();
            psmt = conn.prepareStatement(SQL_DELETE);
            psmt.setInt(1, codigo);
            registrosEliminados = psmt.executeUpdate();

        } finally {
            DBUtil.close(psmt);
            DBUtil.close(conn);
        }
        System.out.println("Ejecuntando query: " + SQL_DELETE);
        System.out.println("Registros eliminados: " + registrosEliminados);
    }

    @Override
    public void descontarInventario(List<Producto> productosSeleccionados) throws SQLException {
        Connection conn = null;
        PreparedStatement psmt = null;

        try {
            conn = DBUtil.getConnection();
            conn.setAutoCommit(false);  // Inicia la transacción

            psmt = conn.prepareStatement("UPDATE producto SET unidades = unidades - ? WHERE codigo = ?");

            for (Producto producto : productosSeleccionados) {
                psmt.setInt(1, producto.getUnidades());  // Cantidad a descontar
                psmt.setInt(2, producto.getCodigo());
                psmt.addBatch();
            }

            psmt.executeBatch();
            conn.commit();  // Confirma los cambios si todo sale bien

        } catch (SQLException e) {
            if (conn != null) {
                conn.rollback();  // Revierte en caso de error
            }
            throw e;
        } finally {
            DBUtil.close(psmt);
            DBUtil.close(conn);
        }
    }
}
