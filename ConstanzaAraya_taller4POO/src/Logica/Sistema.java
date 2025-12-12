package Logica;

import java.io.*;
import java.util.*;
import javax.swing.JOptionPane;
import Dominio.*;

/**
 * Clase principal de lógica del sistema académico.
 * Implementa el patrón Singleton y gestiona usuarios, estudiantes,
 * cursos, certificaciones, registros y notas.
 * <p>
 * Contiene métodos para leer datos desde archivos, realizar búsquedas,
 * aplicar patrones de diseño (Visitor) y generar certificados.
 * </p>
 */
public class Sistema {
	private static Scanner s;
	
	private static ArrayList<Usuario> usuarios = new ArrayList<>();
	private static ArrayList<Curso> cursos = new ArrayList<>();
	private static ArrayList<Estudiante> estudiantes = new ArrayList<>();
	private static ArrayList<Certificacion> certificaciones = new ArrayList<>();
	private static ArrayList<AsignaturasCertificacion> asignaturas = new ArrayList<>();
	private static ArrayList<RegistroCertificacion> registros = new ArrayList<>();
	private static ArrayList<Nota> nota = new ArrayList<>();
	private static Sistema instancia;
    /** Constructor privado para aplicar el patrón Singleton */
	private Sistema() {
		
	}
	/**
     * Obtiene la instancia única del sistema.
     * @return instancia de Sistema
     */
	public static Sistema getInstancia() {
		if(instancia == null) {
			instancia =  new Sistema();
		}
		return instancia;
	}
	
	/**
     * Lee usuarios desde archivo y los agrega a la lista.
     * @param archivo ruta del archivo
     * @throws FileNotFoundException si el archivo no existe
     */
    public static void leerUsuarios(String archivo) throws FileNotFoundException {
		s = new Scanner(new File(archivo));
		while(s.hasNextLine()) {
			String[] parte = s.nextLine().split(";");
			usuarios.add(UsuarioFactory.crearUsuario(parte));
		
		}
	}
    /**
     * Valida el inicio de sesión de un usuario.
     * @param nombre nombre del usuario
     * @param contraseña contraseña del usuario
     * @return usuario válido o null si no coincide
     */
    public static Usuario login(String nombre, String contraseña) {
		for(Usuario u: usuarios) {
			if(u.getNombre().equals(nombre) && u.validarContraseña(contraseña)) {
				return u;
			}
		}
		return null;
	}
    /** Lee registros de certificaciones desde archivo */
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
			registros.add(registro);
		}
	}
	/**
	 * Guarda la lista actual de usuarios en el archivo especificado.
	 * 
	 * Cada usuario se escribe en una línea con el formato:
	 * nombre;contraseña;rol
	 * 
	 * @param archivo nombre del archivo donde se guardarán los usuarios (ej. "usuarios.txt")
	 */
	public static void guardarUsuarios(String archivo) {
	    try (PrintWriter pw = new PrintWriter(new FileWriter(archivo))) {
	        for (Usuario u : usuarios) {
	            pw.println(u.getNombre() + ";" + u.getContraseña() + ";" + u.getRol());
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
    /** Lee notas desde archivo */
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
			nota.add(notas);
		}
	}
    /** Lee estudiantes desde archivo */
	public static void leerEstudiantes(String archivo) throws FileNotFoundException {
		s = new Scanner(new File(archivo));
		while(s.hasNextLine()) {
			String linea = s.nextLine();
			String parte[] = linea.split(";");
			String rut = parte[0];
			String nombre = parte[1];
			String carrera = parte[2]; 
			int semestre = Integer.parseInt(parte[3]);
			String correoE = parte[4];
			String contraseña = parte[5];
			
			Estudiante estudiante = new Estudiante(rut,nombre,carrera, semestre,correoE, contraseña);
			estudiantes.add(estudiante);
		}
	}
    /** Lee cursos desde archivo */
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
    /** Lee certificaciones desde archivo */
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
			certificaciones.add(certificacion);
		}
	}
    /** Lee asignaturas de certificaciones desde archivo */
	public static void leerAsigCertificaciones(String archivo) throws FileNotFoundException {
		s = new Scanner(new File(archivo));
		while(s.hasNextLine()) {
			String linea = s.nextLine();
			String parte[] = linea.split(";");
			String id = parte[0];
			String nrc = parte[1];
			
			AsignaturasCertificacion asigCertificacion = new AsignaturasCertificacion(id, nrc);
			asignaturas.add(asigCertificacion);
		}
	}
    /** @return lista de usuarios */
	public static ArrayList<Usuario> getUsuarios() {
		return usuarios;
	}
    /** @return lista de estudiantes */
	public static ArrayList<Estudiante> getEstudiantes(){
		return estudiantes;
	}
    /** @return lista de certificaciones */
	public static ArrayList<Certificacion> getCertificaciones(){
		return certificaciones;
	}
    /** @return lista de cursos */
	public static ArrayList<Curso> getCursos(){
		return cursos;	
	}
    /** @return lista de registros de certificaciones */
	public static ArrayList<RegistroCertificacion> getRegistros(){
		return registros;
	}
    /** @return lista de asignaturas de certificaciones */
	public static ArrayList<AsignaturasCertificacion> getAsignaturas(){
		return asignaturas;	
	}
    /** @return lista de notas */
	public static ArrayList<Nota> getNota(){
		return nota;
	}
    /** Busca un usuario por nombre */
	public static Usuario buscarUsuarioPorNombre(String nombre) {
		for(Usuario u : usuarios) {
			if(u.getNombre().equals(nombre)) {
				return u;
			}
		}
		return null;
	}
    /** Busca un estudiante por RUT */
	public static Estudiante buscarEstudiantePorRut(String rut) {
	    for (Estudiante e : estudiantes) {
	        if (e.getRut().equals(rut)) {
	            return e;
	        }
	    }
	    return null;
	}
    /** Busca un curso por NRC */
	public static Curso buscarCursoPorNRC(String nrc) {
	    for (Curso c : cursos) {
	        if (c.getNrc().equals(nrc)) {
	            return c;
	        }
	    }
	    return null;
	}
    /** Aplica Visitor sobre todas las certificaciones */
	public static void aplicarVisitorCertificaciones(visitor v) {
	    for (Certificacion c : certificaciones) {
	        c.accept(v);
	    }
	}
    /** Busca certificación por nombre */
	public static Certificacion buscarCertificacionPorNombre(String seleccion) {
		for(Certificacion c : certificaciones) {
			if(c.getNombre().equalsIgnoreCase(seleccion)) {
				return c;
			}
		}
		return null;
	}
	/**
     * Genera un certificado para un estudiante si cumple requisitos.
     * @param est estudiante
     * @param cert certificación
     */
	public static void generarCertificado(Estudiante est, Certificacion cert) {
		Validar_CertiVisitor visitor = new Validar_CertiVisitor(est);
		cert.accept(visitor);
		
		if(visitor.cumpleRequisitos()) {
			
            String linea = est.getRut() + ";" + cert.getId() + ";Completado";

            try (BufferedWriter bw = new BufferedWriter(new FileWriter("data/certificados.txt", true))) {
                bw.write(linea);
                bw.newLine();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Error al guardar certificado: " + ex.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            JOptionPane.showMessageDialog(null,"Certificado generado para " + est.getNombre() +" en la certificación " + cert.getNombre());
        } else {
            JOptionPane.showMessageDialog(null,
                "El estudiante " + est.getNombre() +" aún no cumple los requisitos de " + cert.getNombre());
        }
	}
    /** Cuenta inscritos en una certificación */
	public static int contarInscritos(String id) {
		int count = 0;
		for(RegistroCertificacion r :  registros) {
			if(r.getIdCertificacion().equals(id)) {
				count++;
			}
		}
		return count;
	}
    /** Obtiene cursos requeridos para una certificación */
	public static List<Curso> getCursosRequeridos(String id) {
		List<Curso> lista = new ArrayList<>();
		for(AsignaturasCertificacion ac : asignaturas) {
			if(ac.getIdCertificacion().equals(ac.getNrcCursos())) {
				Curso c = buscarCursoPorNRC(ac.getNrcCursos());
				if(c != null) {
					lista.add(c);
				}
			}
			
		}
		return lista;
	}
    /** Calcula porcentaje de reprobación de un curso */
	public static double porcentajeReprobacionCurso(String nrc) {
		int total = 0;
		int reprobados = 0;
		
		for(Nota n :  nota) {
			if(n.getCodigoAsignatura().equals(nrc)) {
				total++;
				if(n.getEstado().equals("Reprobado")) {
					reprobados++;
				}
			}
		}
		if(total == 0) {
			return 0;
		}
		return (reprobados * 100)/total;
		}
    /** Obtiene notas por RUT (pendiente de implementación) */
	public static List<Nota> getNotasPorRut(String rut) {
		List<Nota> lista = new ArrayList<>();
		for(Nota n : nota) {
			if(n.getRut().equals(rut)) {
				lista.add(n);
			}
		}
		return lista;
	}
    /** Busca certificación por ID */
	public static Certificacion BuscarCertificacionPorId(String idCertificacion) {
		for(Certificacion c : certificaciones) {
			if(c.getId().equals(idCertificacion)) {
				return c;
			}
		}
		return null;
	}
	/**
	 * Guarda todas las notas en el archivo indicado.
	 * Formato: rut;codigoAsignatura;calificacion;estado;semestre
	 *
	 * @param archivo nombre del archivo de salida (ej. "notas.txt")
	 */
	public static void guardarNotas(String archivo) {
	    try (PrintWriter pw = new PrintWriter(new FileWriter(archivo))) {
	        for (Nota n : nota) {
	            pw.println(n.getRut() + ";" + n.getCodigoAsignatura() + ";" +
	                       n.getCalificacion() + ";" + n.getEstado() + ";" + n.getSemestre());
	        }
	    } catch (IOException ex) {
	        ex.printStackTrace();
	    }
	}
	/**
	 * Guarda todos los registros de certificaciones en el archivo indicado.
	 * Formato: rut;idCertificacion;estado;progreso
	 *
	 * @param archivo nombre del archivo de salida (ej. "registros.txt")
	 */
	public static void guardarRegistros(String archivo) {
	    try (PrintWriter pw = new PrintWriter(new FileWriter(archivo))) {
	        for (RegistroCertificacion r : registros) {
	            pw.println(r.getRut() + ";" + r.getIdCertificacion() + ";" +
	                       r.getEstado() + ";" + r.getProgreso());
	        }
	    } catch (IOException ex) {
	        ex.printStackTrace();
	    }
	}
	/**
	 * Guarda todos los estudiantes en el archivo indicado.
	 * Formato: rut;nombre;carrera;semestre;correo;contraseña
	 *
	 * @param archivo nombre del archivo de salida (ej. "estudiantes.txt")
	 */
	public static void guardarEstudiantes(String archivo) {
	    try (PrintWriter pw = new PrintWriter(new FileWriter(archivo))) {
	        for (Estudiante e : estudiantes) {
	            pw.println(e.getRut() + ";" + e.getNombre() + ";" + e.getCarrera() + ";" +
	                       e.getSemestre() + ";" + e.getCorreoE() + ";" + e.getContraseña());
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	/**
	 * Guarda todas las certificaciones en el archivo indicado.
	 * Formato: id;nombre;descripcion
	 *
	 * @param archivo nombre del archivo de salida (ej. "certificaciones.txt")
	 */
	public static void guardarCertificaciones(String archivo) {
	    try (PrintWriter pw = new PrintWriter(new FileWriter(archivo))) {
	        for (Certificacion c : certificaciones) {
	            pw.println(c.getId() + ";" + c.getNombre() + ";" + c.getDescripcion());
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	/**
	 * Guarda todos los cursos en el archivo indicado.
	 * Formato: nrc;nombre;creditos
	 *
	 * @param archivo nombre del archivo de salida (ej. "cursos.txt")
	 */
	public static void guardarCursos(String archivo) {
	    try (PrintWriter pw = new PrintWriter(new FileWriter(archivo))) {
	        for (Curso c : cursos) {
	            pw.println(c.getNrc() + ";" + c.getNombre() + ";" + c.getCreditos());
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	/**
	 * Verifica si un estudiante cumple los prerrequisitos de una certificación.
	 * 
	 * Se revisan los cursos requeridos por la certificación y se valida que el estudiante
	 * tenga una nota aprobada en cada uno de ellos.
	 *
	 * @param estudiante estudiante a validar
	 * @param cert certificación que se desea inscribir
	 * @return true si cumple todos los prerrequisitos, false en caso contrario
	 */
	public static boolean verificarPrerrequisitos(Estudiante estudiante, Certificacion cert) {
	    // Obtener los cursos requeridos por la certificación
	    List<Curso> cursosRequeridos = getCursosRequeridos(cert.getId());
	    for (Curso c : cursosRequeridos) {
	        Nota nota = buscarNota(estudiante.getRut(), c.getNrc());
	        if (nota == null || !"Aprobado".equalsIgnoreCase(nota.getEstado())) {
	            // Si falta la nota o no está aprobado, no cumple
	            return false;
	        }
	    }
	    return true;
	}

	/**
	 * Busca la nota de un estudiante en un curso específico.
	 *
	 * @param rut rut del estudiante
	 * @param nrc código del curso (NRC)
	 * @return Nota encontrada o null si no existe
	 */
	public static Nota buscarNota(String rut, String nrc) {
	    for (Nota n : nota) {
	        if (n.getRut().equals(rut) && n.getCodigoAsignatura().equals(nrc)) {
	            return n;
	        }
	    }
	    return null;
	}
}
