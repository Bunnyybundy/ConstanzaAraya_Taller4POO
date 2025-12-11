package Logica;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import Dominio.*;
import Patrones.*;


public class Sistema {
	private static Scanner s;
	
	private static ArrayList<Usuario> usuarios = new ArrayList<>();
	private static ArrayList<Curso> cursos = new ArrayList<>();
	private static ArrayList<Estudiante> estudiantes = new ArrayList<>();
	private static ArrayList<Certificacion> certificaciones = new ArrayList<>();
	private static ArrayList<AsignaturasCertificacion> asignaturas = new ArrayList<>();
	
	private static Sistema instancia;
	
	private Sistema() {
		
	}
	
	public static Sistema getInstancia() {
		if(instancia == null) {
			instancia =  new Sistema();
		}
		return instancia;
	}
	
	
    public static void leerUsuarios(String archivo) throws FileNotFoundException {
		s = new Scanner(new File(archivo));
		while(s.hasNextLine()) {
			String[] parte = s.nextLine().split(";");
			usuarios.add(UsuarioFactory.crearUsuario(parte));
		
		}
	}
    public static Usuario login(String usuario, String contraseña) {
		
		return null;
	}

	public static void leerRegistros(String archivo) throws FileNotFoundException {
		s = new Scanner(new File(archivo));
		while(s.hasNextLine()) {
			String linea = s.nextLine();
			String parte[] = linea.split(";");
			String rut = parte[0];
			String idCertificacion = parte[1];
			String fechaRegistro = parte[2];
			String estado = parte[3];
			int progreso = Integer.parseInt(parte[4]);
			
			RegistroCertificacion registro = new RegistroCertificacion(rut,idCertificacion,fechaRegistro, estado, progreso);
		}
	}

	public static void leerNotas(String archivo) throws FileNotFoundException {
		s = new Scanner(new File(archivo));
		while(s.hasNextLine()) {
			String linea = s.nextLine();
			String parte[] = linea.split(";");
			String rut = parte[0];
			String codigoAsignatura = parte[1];
			double calificacion = Double.parseDouble(parte[2]);
			String estado = parte[3];
			String semestre = parte[4];
			
			Nota notas = new Nota(rut, codigoAsignatura,calificacion, estado, semestre);
		}
	}

	public static void leerEstudiantes(String archivo) throws FileNotFoundException {
		s = new Scanner(new File(archivo));
		while(s.hasNextLine()) {
			String linea = s.nextLine();
			String parte[] = linea.split(";");
			String rut = parte[0];
			String nombre = parte[1];
			String carrera = parte[2]; 
			String correoE = parte[3];
			String contraseña = parte[4];
			
			Estudiante estudiante = new Estudiante(rut,nombre,carrera,correoE, contraseña);
			
		}
	}

	public static void leerCursos(String archivo) throws FileNotFoundException {
		s = new Scanner(new File(archivo));
		while(s.hasNextLine()) {
			String linea = s.nextLine();
			String parte[] = linea.split(";");
			String nrc = parte[0];
			String nombre = parte[1];
			int semestre = Integer.parseInt(parte[2]); 
			int creditos = Integer.parseInt(parte[3]);
			String area = parte[4];
			ArrayList<String> prerrequisitos = new ArrayList<>();
			if(parte.length > 5 && !parte[5].isEmpty()) {
				for(String p : parte[5].split(";")) {
					prerrequisitos.add(p.trim());
				}
			}
			Curso curso = new Curso(nrc,nombre, semestre,creditos,area,prerrequisitos);
			cursos.add(curso);
			
		}
	}

	public static void leerCertificaciones(String archivo) throws FileNotFoundException {
		s = new Scanner(new File(archivo));
		while(s.hasNextLine()) {
			String linea = s.nextLine();
			String parte[] = linea.split(";");
			String id = parte[0];
			String nombre = parte[1];
			String descripcion = parte[2]; 
			int requisitos = Integer.parseInt(parte[3]);
			int validez = Integer.parseInt(parte[4]);
			
			Certificacion certificacion = new Certificacion(id,nombre,descripcion,requisitos,validez);
		}
	}

	public static void leerAsigCertificaciones(String archivo) throws FileNotFoundException {
		s = new Scanner(new File(archivo));
		while(s.hasNextLine()) {
			String linea = s.nextLine();
			String parte[] = linea.split(";");
			String id = parte[0];
			String nrc = parte[1];
			
			AsignaturasCertificacion asigCertificacion = new AsignaturasCertificacion(id, nrc);
		}
	}
	
	public static ArrayList<Usuario> getUsuarios() {
		return usuarios;
	}
	public static ArrayList<Estudiante> getEstudiantes(){
		return estudiantes;
	}
	
	public static ArrayList<Certificacion> getCertificaciones(){
		return certificaciones;
		
	}
	public static Estudiante buscarEstudiantePorRut(String rut) {
	    for (Estudiante e : estudiantes) {
	        if (e.getRut().equals(rut)) {
	            return e;
	        }
	    }
	    return null;
	}
	public static Curso buscarCursoPorNRC(String nrc) {
	    for (Curso c : cursos) {
	        if (c.getNrc().equals(nrc)) {
	            return c;
	        }
	    }
	    return null;
	}
	
	public static void aplicarVisitorCertificaciones(visitor v) {
	    for (Certificacion c : certificaciones) {
	        c.accept(v);
	    }
	}

	
}
