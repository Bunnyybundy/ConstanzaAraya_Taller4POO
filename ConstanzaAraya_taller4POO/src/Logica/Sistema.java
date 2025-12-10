package Logica;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import Dominio.*;

public class Sistema {
	private static Scanner s;
	
	private static ArrayList<Usuario> usuarios = new ArrayList<>();
	private static ArrayList<Curso> cursos = new ArrayList<>();
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
			//falta como leer la informacion extra que tienen solo algunas lineas
		}
	}

	public static void leerCertificaciones(String archivo) throws FileNotFoundException {
		s = new Scanner(new File(archivo));
		while(s.hasNextLine()) {
			
		}
	}

	public static void leerAsigCertificaciones(String archivo) throws FileNotFoundException {
		s = new Scanner(new File(archivo));
		while(s.hasNextLine()) {
			
		}
	}
	
	public static ArrayList<Usuario> getUsuarios() {
		return usuarios;
	}
}
