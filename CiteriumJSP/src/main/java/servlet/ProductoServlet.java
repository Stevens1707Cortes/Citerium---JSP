package servlet;

import com.google.gson.Gson;
import dao.JdbcProductoDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Producto;
import service.ProductoService;

@WebServlet("/productosServlet")
public class ProductoServlet extends HttpServlet {

    //Instancia de Usuario service
    private ProductoService productoService;

    @Override
    //Inicializar ProductoService para conexion con la DB
    public void init() throws ServletException {
        productoService = new ProductoService(new JdbcProductoDAO());
    }

    //Obtener productos
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Producto> productos;
        try {
            // Obtener la lista de productos desde la capa de servicio
            productos = productoService.obtenerTodosLosProductos();

            // Convertir la lista de productos a JSON
            Gson gson = new Gson();
            String jsonProductos = gson.toJson(productos);
            System.out.println("JSON de productos: " + jsonProductos); // Registro para verificar el JSON generado

            // Establecer el tipo de contenido de la respuesta como JSON
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            // Escribir la respuesta JSON de los productos al cliente
            response.getWriter().write(jsonProductos);

        } catch (SQLException ex) {
            Logger.getLogger(ProductoServlet.class.getName()).log(Level.SEVERE, null, ex);
            // Manejo de excepciones
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("Error al obtener los productos: " + ex.getMessage());
        }
    }

    //Ingresar, Actualizar, Descontar, Eliminar
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (null == action) {
        } else {
            switch (action) {
                case "add": {
                    ingresarProducto(request);
                    break;
                }
                case "update": {
                    actualizarProducto(request);
                    break;
                }
                case "discount": {
                    try {
                        descontarUnidades(request, response);
                    } catch (SQLException ex) {
                        Logger.getLogger(ProductoServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
                }
                case "delete": {
                    eliminarProducto(request);
                    break;
                }
                default:
                    break;
            }
        }
        response.sendRedirect(request.getContextPath() + "/jsp/home_Producto.jsp");
    }

    private void ingresarProducto(HttpServletRequest request) {
        String nombre = request.getParameter("nombre").toLowerCase();
        String codigo = request.getParameter("codigo");
        String categoria = request.getParameter("categoria").toLowerCase();
        String fecha = request.getParameter("fecha");
        String unidades = request.getParameter("unidades");
        String precio = request.getParameter("precio");


        Producto productoNuevo = new Producto(nombre, Integer.parseInt(codigo), categoria, fecha, Integer.parseInt(unidades), Double.parseDouble(precio));
        try {
            productoService.crearProducto(productoNuevo);
        } catch (SQLException ex) {
            Logger.getLogger(ProductoServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void actualizarProducto(HttpServletRequest request) {
        int codigo = Integer.parseInt(request.getParameter("codigo"));
        String nombre = request.getParameter("nombre").toLowerCase();
        String categoria = request.getParameter("categoria").toLowerCase();
        String fecha = request.getParameter("fecha");
        String unidades = request.getParameter("unidades");
        String precio = request.getParameter("precio");

        Producto productoAct = new Producto(nombre, codigo, categoria, fecha, Integer.parseInt(unidades), Double.parseDouble(precio));
        try {
            productoService.actualizarProducto(productoAct);
        } catch (SQLException ex) {
            Logger.getLogger(ProductoServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void descontarUnidades(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int codigo = Integer.parseInt(request.getParameter("codigo"));
        int unidadesDescontar = Integer.parseInt(request.getParameter("unidades"));

        //Obtener producto
        Producto producto = productoService.obtenerProductoCodigo(codigo);

        //Verificar si existe
        if (producto == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Producto no encontrado");
            return;
        }

        // Verificar si hay suficientes unidades para descontar
        if (producto.getUnidades() < unidadesDescontar) {
            // Enviar respuesta de error indicando que no hay suficientes unidades
            response.sendRedirect(request.getContextPath() + "/home_Inventario.jsp?alerta=unidadesInsuficientes");
            return;
        }

        //Descontar Unidades
        producto.setUnidades(producto.getUnidades() - unidadesDescontar);

        //Actualizar
        try {
            productoService.actualizarProducto(producto);
        } catch (SQLException ex) {
            Logger.getLogger(ProductoServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void eliminarProducto(HttpServletRequest request) {
        int codigo = Integer.parseInt(request.getParameter("codigo"));
        try {
            productoService.eliminarProducto(codigo);
        } catch (SQLException ex) {
            Logger.getLogger(ProductoServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
