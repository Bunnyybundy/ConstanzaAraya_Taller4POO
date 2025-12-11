package Dominio;

import java.util.List;
import Visitors.visitable;
import Visitors.visitor;

public class Curso implements visitable {
	private String nrc;
	private String nombre;
	private int semestre;
	private int creditos;
	private String area;
	private List<String> prerrequisitos;
	
	public Curso(String nrc, String nombre, int semestre, int creditos, String area, List<String> prerrequisitos) {
		this.nrc = nrc;
		this.nombre = nombre;
		this.semestre = semestre;
		this.creditos = creditos;
		this.area = area;
		this.prerrequisitos = prerrequisitos;
	}

	public String getNrc() {
		return nrc;
	}

	public String getNombre() {
		return nombre;
	}
	
   @Override
    public void accept(visitor v) {
        v.visit(this);
    }
	
	
}
