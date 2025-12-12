package Dominio;

import java.util.List;

import Logica.visitable;
import Logica.visitor;
/**
 * Representa un curso académico con NRC, nombre, semestre, créditos y prerrequisitos.
 */
public class Curso implements visitable {
	private String nrc;
	private String nombre;
	private int semestre;
	private int creditos;
	private String area;
	private List<String> prerrequisitos;
	/**
     * Constructor de curso.
     * @param nrc NRC del curso
     * @param nombre Nombre del curso
     * @param semestre Semestre recomendado
     * @param creditos Créditos del curso
     * @param area Área de conocimiento
     * @param prerrequisitos Lista de prerrequisitos
     */
	public Curso(String nrc, String nombre, int semestre, int creditos, String area, List<String> prerrequisitos) {
		this.nrc = nrc;
		this.nombre = nombre;
		this.semestre = semestre;
		this.creditos = creditos;
		this.area = area;
		this.prerrequisitos = prerrequisitos;
	}
    /** @return nombre del curso */
   public String getNombre() {
		return nombre;
	}
   /** @param nombre nuevo nombre del curso */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
    /** @return semestre recomendado */
	public int getSemestre() {
		return semestre;
	}
    /** @param semestre nuevo semestre recomendado */
	public void setSemestre(int semestre) {
		this.semestre = semestre;
	}
    /** @return créditos del curso */
	public int getCreditos() {
		return creditos;
	}
    /** @param creditos nuevos créditos del curso */
	public void setCreditos(int creditos) {
		this.creditos = creditos;
	}
    /** @return área del curso */
	public String getArea() {
		return area;
	}
    /** @param area nueva área del curso */
	public void setArea(String area) {
		this.area = area;
	}
    /** @return lista de prerrequisitos */
	public List<String> getPrerrequisitos() {
		return prerrequisitos;
	}
    /** @param prerrequisitos nueva lista de prerrequisitos */
	public void setPrerrequisitos(List<String> prerrequisitos) {
		this.prerrequisitos = prerrequisitos;
	}
    /** @return NRC del curso */
	public String getNrc() {
		return nrc;
	}
    /** Aplica el patrón Visitor sobre el curso */
   @Override
    public void accept(visitor v) {
        v.visit(this);
    }
	
	
}
