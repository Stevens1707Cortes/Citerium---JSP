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

@WebServlet("/UsuarioServlet")
public class UsuarioServlet extends HttpServlet {

    //Instancia de Usuario service
    private UsuarioService usuarioService;

    @Override
    //Inicializar UsuarioService para conexion con la DB
    public void init() throws ServletException {
        usuarioService = new UsuarioService(new JdbcUsuarioDAO());

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Recuperar datos del formulario
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String identificacion = request.getParameter("identificacion");
        String email = request.getParameter("email");
        String contrasena = request.getParameter("contrasena");
        
        try {
            //Verificar si el usuario ya existe
            Usuario usuarioExiste = usuarioService.obtenerUsuarioPorNombre(nombre);
            if (usuarioExiste != null) {
                response.sendRedirect("registro_Usuario?error=usuario_existente");
            }else{
            //Crear un usuario nuevo
            Usuario nuevoUsuario = new Usuario(nombre, apellido, Integer.parseInt(identificacion), email, contrasena);
            //Insertarlo en la BD
            usuarioService.crearUsuario(nuevoUsuario);
            
            //Redireccionar a inicio de sesion
            response.sendRedirect("index.jsp");
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
