package Dominio;

import Visitors.visitable;
import Visitors.visitor;

public class Estudiante implements visitable {
	private String rut;
	private String nombre;
	private String carrera; 
	private String correoE;
	private String contraseña;
	
	public Estudiante(String rut, String nombre, String carrera, String correoE, String contraseña) {
		this.rut = rut;
		this.nombre = nombre;
		this.carrera = carrera;
		this.correoE = correoE;
		this.contraseña = contraseña;
	}

	public String getRut() {
		return rut;
	}

	public String getNombre() {
		return nombre;
	}

	public String getCarrera() {
		return carrera;
	}

	public String getCorreoE() {
		return correoE;
	}

	public String getContraseña() {
		return contraseña;
	}

	public void setRut(String rut) {
		this.rut = rut;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setCarrera(String carrera) {
		this.carrera = carrera;
	}

	public void setCorreoE(String correoE) {
		this.correoE = correoE;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}

    @Override
    public void accept(visitor v) {
        v.visit(this);
    }
	
	
	
	
}
