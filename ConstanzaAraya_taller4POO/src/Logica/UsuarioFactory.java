package Logica;

import Dominio.Administrador;
import Dominio.Coordinador;
import Dominio.Estudiante;
import Dominio.Usuario;
/**
 * Fábrica de usuarios.
 * Crea instancias de Administrador, Coordinador o Estudiante según el rol indicado.
 */
public class UsuarioFactory {
	/**
     * Crea un usuario a partir de los datos entregados.
     * 
     * @param parte arreglo con los datos del usuario
     *              [0] nombre
     *              [1] contraseña
     *              [2] rol ("Admin", "Coordinador" o "Estudiante")
     *              [3] área (solo si es Coordinador)
     *              [3] rut (si es Estudiante)
     *              [4] carrera (si es Estudiante)
     *              [5] semestre (si es Estudiante)
     *              [6] correo (si es Estudiante)
     * @return Usuario creado
     * @throws IllegalArgumentException si el rol no es válido
     */
	public static Usuario crearUsuario(String[] parte) {
		String nombre = parte[0];
		String contraseña = parte[1];
		String rol = parte[2];
		
		switch(rol) {
		case "Admin":
			return new Administrador(nombre, contraseña,rol);
		case "Coordinador":
			String area = parte[3];
			return new Coordinador(nombre, contraseña,rol, area);
		case "Estudiante":
            String rut = parte[3];
            String carrera = parte[4];
            int semestre = Integer.parseInt(parte[5]);
            String correo = parte[6];
            return new Estudiante(rut, nombre, carrera, semestre, correo, contraseña);
		default:
			throw new IllegalArgumentException("Rol no reconocido: " + rol);
		}

	}
}
