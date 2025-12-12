package Dominio;
/**
 * Representa el registro de un estudiante en una certificación.
 * Contiene información como rut del estudiante, ID de la certificación,
 * fecha de registro, estado y progreso.
 */
public class RegistroCertificacion {
	private String rut;
	private String idCertificacion;
	private String fechaRegistro;
	private String estado ;
	private int progreso;
	  /**
     * Constructor de registro de certificación.
     * @param rut RUT del estudiante
     * @param idCertificacion ID de la certificación
     * @param fechaRegistro Fecha en que se registró
     * @param estado Estado del registro (ej. "Activo", "Finalizado")
     * @param progreso Porcentaje de progreso en la certificación
     */
	public RegistroCertificacion(String rut, String idCertificacion, String fechaRegistro, String estado,
			int progreso) {
		this.rut = rut;
		this.idCertificacion = idCertificacion;
		this.fechaRegistro = fechaRegistro;
		this.estado = estado;
		this.progreso = progreso;
	}
    /** @return RUT del estudiante */
	public String getRut() {
		return rut;
	}
    /** @param rut nuevo RUT del estudiante */
	public void setRut(String rut) {
		this.rut = rut;
	}
    /** @return ID de la certificación */
	public String getIdCertificacion() {
		return idCertificacion;
	}
    /** @param idCertificacion nuevo ID de la certificación */
	public void setIdCertificacion(String idCertificacion) {
		this.idCertificacion = idCertificacion;
	}
    /** @return fecha de registro */
	public String getFechaRegistro() {
		return fechaRegistro;
	}
    /** @param fechaRegistro nueva fecha de registro */
	public void setFechaRegistro(String fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}
    /** @return estado del registro */
	public String getEstado() {
		return estado;
	}
    /** @param estado nuevo estado del registro */
	public void setEstado(String estado) {
		this.estado = estado;
	}
    /** @return progreso en la certificación */
	public int getProgreso() {
		return progreso;
	}
    /** @param progreso nuevo progreso en la certificación */
	public void setProgreso(int progreso) {
		this.progreso = progreso;
	}
	
	
	
}
