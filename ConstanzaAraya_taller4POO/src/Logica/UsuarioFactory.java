package Logica;

import Dominio.Administrador;
import Dominio.Coordinador;
import Dominio.Usuario;

public class UsuarioFactory {

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
