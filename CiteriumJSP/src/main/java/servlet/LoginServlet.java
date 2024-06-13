/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;

import dao.JdbcUsuarioDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Usuario;
import service.UsuarioService;
import util.SessionUtil;

/**
 *
 * @author steve
 */
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
        String nombre = request.getParameter("nombre");
        String contrasena = request.getParameter("contrasena");

        try {
            Usuario usuario = usuarioService.obtenerUsuarioPorNombre(nombre);
            if (usuario != null && usuario.getContrasena().equals(contrasena)) {
                SessionUtil.crearSesionUsuario(request, usuario);
                //Redireccionar
                response.sendRedirect("home.jsp");
           }else{
            response.sendRedirect("index.jsp?error=true");
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet LoginServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LoginServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

}
