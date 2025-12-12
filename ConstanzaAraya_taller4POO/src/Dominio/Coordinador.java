package Dominio;
/**
 * Usuario con rol de coordinador, encargado de gestionar certificaciones y cursos.
 */
public class Coordinador extends Usuario {
	private String area;
	/**
     * Constructor de coordinador.
     * @param nombre Nombre del coordinador
     * @param contraseña Contraseña del coordinador
     * @param area Área de especialización
	 * @param area 
     */
	public Coordinador(String nombre, String contraseña, String rol, String area) {
		super(nombre, contraseña, "Coordinador");
		this.area = area;
	}
    /** @return área del coordinador */
	public String getArea() {
		return area;
	}
    /** @param area nueva área del coordinador */
	public void setArea(String area) {
		this.area = area;
	}
	

	
}
