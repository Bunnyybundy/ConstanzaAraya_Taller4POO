package Logica;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import Dominio.Usuario;

public class Sistema {
	private static Scanner s;
	
	private static ArrayList<Usuario> usuarios = new ArrayList<>();
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

	public static void leerRegistros(String string) {
		
	}

	public static void leerNotas(String string) {
		
	}

	public static void leerEstudiantes(String string) {
		
	}

	public static void leerCursos(String string) {
		
	}

	public static void leerCertificaciones(String string) {
		
	}

	public static void leerAsigCertificaciones(String string) {
		
	}
}
