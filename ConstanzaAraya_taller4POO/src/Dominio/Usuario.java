package Dominio;
/**
 * Clase abstracta que representa un usuario del sistema académico.
 * Contiene atributos comunes como nombre, contraseña y rol.
 * Es la clase base para tipos específicos de usuarios (Administrador, Coordinador, Estudiante).
 */
public abstract class Usuario {
	private String nombre;
	private String contraseña;
	private String rol;
	/**
     * Constructor de usuario.
     * @param nombre Nombre del usuario
     * @param contraseña Contraseña del usuario
     * @param rol Rol del usuario dentro del sistema
     */
	public Usuario(String nombre, String contraseña, String rol) {
		this.nombre = nombre;
		this.contraseña = contraseña;
		this.rol = rol;
	}
    /** @return nombre del usuario */
	public String getNombre() {
		return nombre;
	}
    /** @param nombre nuevo nombre del usuario */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
    /** @return contraseña del usuario */
	public String getContraseña() {
		return contraseña;
	}
    /** @param contraseña nueva contraseña del usuario */
	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}
    /** @return rol del usuario */
	public String getRol() {
		return rol;
	}
	/**
     * Valida si la contraseña ingresada coincide con la registrada.
     * @param clave Contraseña a validar
     * @return true si la contraseña coincide, false en caso contrario
     */
	public boolean validarContraseña(String clave) {
		return this.contraseña!= null && this.contraseña.equals(clave);
	}
	
}
