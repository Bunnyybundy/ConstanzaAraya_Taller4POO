package Dominio;

import Logica.visitable;
import Logica.visitor;
/**
 * Usuario con rol de estudiante dentro del sistema académico.
 * Contiene información personal y académica como rut, carrera, semestre y correo.
 */
public class Estudiante extends Usuario implements visitable {
	private String rut;
	private String carrera; 
	private int semestre;
	private String correoE;
	 /**
     * Constructor de estudiante.
     * @param rut RUT del estudiante
     * @param nombre Nombre del estudiante
     * @param carrera Carrera que estudia
     * @param semestre Semestre actual
     * @param correoE Correo electrónico
     * @param contraseña Contraseña de acceso
     */
	public Estudiante(String rut, String nombre, String carrera, int semestre, String correoE, String contraseña) {
		super(nombre,contraseña,"Estudiante");
		this.rut = rut;
		this.carrera = carrera;
		this.semestre = semestre;
		this.correoE = correoE;
	}
    /** @return RUT del estudiante */
	public String getRut() {
		return rut;
	}
    /** @return carrera del estudiante */
    public String getCarrera() {
		return carrera;
	}
    /** @param carrera nueva carrera del estudiante */
	public void setCarrera(String carrera) {
		this.carrera = carrera;
	}
    /** @return semestre actual */
	public int getSemestre() {
		return semestre;
	}
    /** @param semestre nuevo semestre */
	public void setSemestre(int semestre) {
		this.semestre = semestre;
	}
    /** @return correo electrónico */
	public String getCorreoE() {
		return correoE;
	}
    /** @param correoE nuevo correo electrónico */
	public void setCorreoE(String correoE) {
		this.correoE = correoE;
	}
    /** Aplica el patrón Visitor sobre el estudiante */
	@Override
    public void accept(visitor v) {
        v.visit(this);
    }
	
	
	
	
}
