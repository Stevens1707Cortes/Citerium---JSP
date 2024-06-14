package servlet;

import dao.JdbcUsuarioDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Usuario;
import service.UsuarioService;
import util.SessionUtil;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private UsuarioService usuarioService;

    //Inicializacion del Servlet
    @Override
    public void init() throws ServletException {
        //Inicializar el service de usuario
        usuarioService = new UsuarioService(new JdbcUsuarioDAO());
    }

    //Peticiones doPost para manejar el formulario de inicio de sesion
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //Obtener datos del formulario
        String nombre = request.getParameter("nombre").toLowerCase();
        String contrasena = request.getParameter("contrasena");

        try {
            Usuario usuario = usuarioService.obtenerUsuarioPorNombre(nombre);
            if (usuario != null && usuario.getContrasena().equals(contrasena)) {
                SessionUtil.crearSesionUsuario(request, usuario);
                //Redireccionar
                response.sendRedirect(request.getContextPath() + "/jsp/home.jsp");
            } else {
                response.sendRedirect("index.jsp?alerta=usuarioInvalido");
            }

        } catch (SQLException ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
