package Dominio;
/**
 * Usuario con rol de administrador.
 * Permite gestionar cuentas dentro del sistema.
 */
public class Administrador extends Usuario {

	/**
     * Constructor de administrador.
     * @param nombre Nombre del administrador
     * @param contraseña Contraseña del administrador
	 * @param rol 
     */
	public Administrador(String nombre, String contraseña, String rol) {
		super(nombre, contraseña, "Admin");
	}

	

}
