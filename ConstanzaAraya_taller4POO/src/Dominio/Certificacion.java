package Dominio;

import Logica.visitable;
import Logica.visitor;
import Patrones.*;
/**
 * Representa una certificación académica con requisitos de créditos y validez.
 */
public class Certificacion implements visitable  {
	private String id;
	private String nombre;
	private String descripcion;
	private int requisitosCreditos;
	private int validez;
	/**
     * Constructor de certificación.
     * @param id Identificador de la certificación
     * @param nombre Nombre de la certificación
     * @param descripcion Descripción de la certificación
     * @param requisitosCreditos Créditos requeridos
     * @param validez Tiempo de validez en semestres o años
     */
	public Certificacion(String id, String nombre, String descripcion, int requisitosCreditos, int validez) {
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.requisitosCreditos = requisitosCreditos;
		this.validez = validez;
	}
    /** @return ID de la certificación */
	public String getId() {
		return id;
	}
    /** @param id nuevo ID de la certificación */
	public void setId(String id) {
			this.id = id;
		}
    /** @return nombre de la certificación */
	public String getNombre() {
		return nombre;
	}
    /** @param nombre nuevo nombre de la certificación */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
    /** @return descripción de la certificación */
    public String getDescripcion() {
		return descripcion;
	}
    
    /** @return créditos requeridos */	
    public int getRequisitosCreditos() {
		return requisitosCreditos;
	}
    /** @return validez de la certificación */
	public int getValidez() {
		return validez;
	}
    /** @param descripcion nueva descripción */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
    /** @param requisitosCreditos nuevos créditos requeridos */
	public void setRequisitosCreditos(int requisitosCreditos) {
		this.requisitosCreditos = requisitosCreditos;
	}
    /** @param validez nueva validez */
	public void setValidez(int validez) {
		this.validez = validez;
	}
    /** Aplica el patrón Visitor sobre la certificación */
	@Override
    public void accept(visitor v) {
        v.visit(this);
    }
    
    
	
	
}
