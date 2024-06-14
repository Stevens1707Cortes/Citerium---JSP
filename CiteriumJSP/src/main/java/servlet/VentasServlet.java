package servlet;

import com.google.gson.Gson;
import dao.JdbcProductoDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Producto;
import service.ProductoService;

@WebServlet(name = "ventasServlet", urlPatterns = {"/ventasServlet"})
public class VentasServlet extends HttpServlet {

    //Instancia de Usuario service
    private ProductoService productoService;

    @Override
    //Inicializar ProductoService para conexion con la DB
    public void init() throws ServletException {
        productoService = new ProductoService(new JdbcProductoDAO());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//         Obtener el código del producto desde el formulario
        String codigoStr = request.getParameter("codigo");
        String unidadesStr = request.getParameter("unidades");

        if (codigoStr == null || codigoStr.isEmpty()) {
            // Manejar el caso en que no se proporcionó un código válido
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("Debe proporcionar un parámetro 'codigo' válido");
            return;
        }

//      Convertir el código del producto a entero (suponiendo que el código es numérico)
        int codigo = Integer.parseInt(codigoStr);
        int unidades = Integer.parseInt(unidadesStr);

        try {
            // Simular la obtención del producto (aquí podrías hacer una llamada a algún servicio que simule esto)
            Producto producto = productoService.obtenerProductoCodigo(codigo);
            System.out.println("producto = " + producto);

            if (producto != null) {
                //Actualizar prodcuto
                producto.setUnidades(unidades);
                double precio = producto.getPrecio() * producto.getUnidades();
                producto.setPrecio(precio);
                
                // Convertir el producto a JSON
                Gson gson = new Gson();
                String jsonProducto = gson.toJson(producto);
                System.out.println("JSON del producto: " + jsonProducto); // Registro para verificar el JSON generado

                // Establecer el tipo de contenido de la respuesta como JSON
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");

                // Escribir la respuesta JSON del producto al cliente
                response.getWriter().write(jsonProducto);
            } else {
                // Manejar el caso en que no se encontró el producto
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                response.getWriter().write("Producto no encontrado con código: " + codigo);
            }

        } catch (IOException ex) {
            Logger.getLogger(ProductoServlet.class.getName()).log(Level.SEVERE, null, ex);
            // Manejo de excepciones
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("Error al obtener el producto: " + ex.getMessage());
        } catch (SQLException ex) {
            Logger.getLogger(VentasServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
