package Dominio;
/**
 * Relación entre una certificación y un curso (NRC).
 */
public class AsignaturasCertificacion {
	private String idCertificacion;
	private String nrcCursos;
	/**
     * Constructor de la relación certificación-curso.
     * @param idCertificacion ID de la certificación
     * @param nrcCursos NRC del curso
     */
	public AsignaturasCertificacion(String idCertificacion, String nrcCursos) {
		this.idCertificacion = idCertificacion;
		this.nrcCursos = nrcCursos;
	}
	/** @return ID de la certificación */
	public String getIdCertificacion() {
		return idCertificacion;
	}
	/** @param idCertificacion nuevo ID de la certificación */
	public void setIdCertificacion(String idCertificacion) {
		this.idCertificacion = idCertificacion;
	}
	/** @return NRC del curso */
	public String getNrcCursos() {
		return nrcCursos;
	}
    /** @param nrcCursos nuevo NRC del curso */
	public void setNrcCursos(String nrcCursos) {
		this.nrcCursos = nrcCursos;
	}
    /** @return representación en texto de la relación */
	@Override
	public String toString() {
		return "idCertificacion=" + idCertificacion + ", nrcCursos=" + nrcCursos ;
	}
	
	
}
