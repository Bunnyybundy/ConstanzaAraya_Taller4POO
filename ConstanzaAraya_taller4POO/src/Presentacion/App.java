package Presentacion;

import java.io.FileNotFoundException;
import java.util.Scanner;

import Logica.Sistema;

public class App {
	
	private static Scanner s;
	
	public static void main(String[] args) throws FileNotFoundException {
		new VentanaLogin().setVisible(true);
		s = new Scanner(System.in);
		Sistema sistema = Sistema.getInstancia();
		
		sistema.leerUsuarios("usuarios.txt");
		sistema.leerRegistros("registros.txt");
		sistema.leerNotas("notas.txt");
		sistema.leerEstudiantes("estudiantes.txt");
		sistema.leerCursos("cursos.txt");
		sistema.leerCertificaciones("certificaciones.txt");
		sistema.leerAsigCertificaciones("asignaturas_certificaciones.txt");
	}

}
