package Logica;

import Dominio.Administrador;
import Dominio.Coordinador;
import Dominio.Usuario;
/**
 * Fábrica de usuarios.
 * Crea instancias de Administrador o Coordinador según el rol indicado.
 */
public class UsuarioFactory {
	/**
     * Crea un usuario a partir de los datos entregados.
     * @param parte arreglo con los datos del usuario
     *              [0] nombre
     *              [1] contraseña
     *              [2] rol ("Admin" o "Coordinador")
     *              [3] área (solo si es Coordinador)
     * @return Usuario creado
     * @throws IllegalArgumentException si el rol no es válido
     */
	public static Usuario crearUsuario(String[] parte) {
		String nombre = parte[0];
		String contraseña = parte[1];
		String rol = parte[2];
		
		switch(rol) {
		case "Admin":
			return new Administrador(nombre, contraseña);
		case "Coordinador":
			String area = parte[3];
			return new Coordinador(nombre, contraseña, area);
		default:
			throw new IllegalArgumentException("Rol no reconocido: " + rol);
		}

	}
}
