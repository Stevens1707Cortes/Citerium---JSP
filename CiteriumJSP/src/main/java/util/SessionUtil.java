
package util;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import model.Usuario;


public class SessionUtil {
    // Nombre del atributo de sesión para almacenar al usuario
    private static final String SESSION_USER_ATTRIBUTE = "usuario";

    // Método para crear la sesión del usuario
    public static void crearSesionUsuario(HttpServletRequest request, Usuario usuario) {
        HttpSession session = request.getSession(true); // Crea una nueva sesión si no existe
        session.setAttribute(SESSION_USER_ATTRIBUTE, usuario);
    }

    // Método para obtener al usuario de la sesión
    public static Usuario obtenerUsuarioSesion(HttpServletRequest request) {
        HttpSession session = request.getSession(false); // No crea una nueva sesión si no existe
        if (session != null) {
            return (Usuario) session.getAttribute(SESSION_USER_ATTRIBUTE);
        }
        return null;
    }

    // Método para cerrar la sesión del usuario
    public static void cerrarSesionUsuario(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate(); // Invalida la sesión
        }
    }

    // Método para verificar si hay una sesión de usuario activa
    public static boolean sesionUsuarioActiva(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        return session != null && session.getAttribute(SESSION_USER_ATTRIBUTE) != null;
    }
}
