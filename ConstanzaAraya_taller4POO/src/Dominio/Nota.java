package Dominio;
/**
 * Representa una nota académica asociada a un estudiante en un curso específico.
 * Contiene información como rut del estudiante, código de asignatura, calificación,
 * estado (aprobada/reprobada) y semestre.
 */
public class Nota {
	private String rut;
	private String codigoAsignatura;
	private double calificacion ;
	private String estado;
	private String semestre;
	/**
     * Constructor de nota.
     * @param rut RUT del estudiante
     * @param codigoAsignatura Código de la asignatura
     * @param calificacion Calificación obtenida
     * @param estado Estado de la asignatura (ej. "Aprobada", "Reprobada")
     * @param semestre Semestre en que se cursó
     */
	public Nota(String rut, String codigoAsignatura, double calificacion, String estado, String semestre) {
		this.rut = rut;
		this.codigoAsignatura = codigoAsignatura;
		this.calificacion = calificacion;
		this.estado = estado;
		this.semestre = semestre;
	}
    /** @return RUT del estudiante */
	public String getRut() {
		return rut;
	}
    /** @param rut nuevo RUT del estudiante */
	public void setRut(String rut) {
		this.rut = rut;
	}
    /** @return código de la asignatura */
	public String getCodigoAsignatura() {
		return codigoAsignatura;
	}
    /** @param codigoAsignatura nuevo código de la asignatura */
	public void setCodigoAsignatura(String codigoAsignatura) {
		this.codigoAsignatura = codigoAsignatura;
	}
    /** @return calificación obtenida */
	public double getCalificacion() {
		return calificacion;
	}
    /** @param calificacion nueva calificación */
	public void setCalificacion(double calificacion) {
		this.calificacion = calificacion;
	}
    /** @return estado de la asignatura */
	public String getEstado() {
		return estado;
	}
    /** @param estado nuevo estado de la asignatura */
	public void setEstado(String estado) {
		this.estado = estado;
	}
    /** @return semestre en que se cursó */
	public String getSemestre() {
		return semestre;
	}
    /** @param semestre nuevo semestre */
	public void setSemestre(String semestre) {
		this.semestre = semestre;
	}
    /** @return representación en texto de la nota */
	@Override
	public String toString() {
		return "Nota{rut=" + rut + ", codigoAsignatura=" + codigoAsignatura + ", calificacion=" + calificacion
				+ ", estado=" + estado + ", semestre=" + semestre + "}";
	}
	
	
}
