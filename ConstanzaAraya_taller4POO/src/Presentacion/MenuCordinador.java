package Presentacion;

import Logica.Sistema;
import Dominio.Certificacion;
import Dominio.Estudiante;
import java.util.Scanner;
import Patrones.*;

public class MenuCordinador {
    private static Scanner s = new Scanner(System.in);

    public static void mostrar() {
        int opcion;
        do {
            System.out.println("\n=== Menú Coordinador ===");
            System.out.println("1. Listar certificaciones");
            System.out.println("2. Listar estudiantes");
            System.out.println("3. Buscar estudiante por RUT");
            System.out.println("4. Validar certificaciones de un estudiante (Visitor)");
            System.out.println("5. Salir");
            System.out.print("Seleccione opción: ");
            opcion = s.nextInt();
            s.nextLine(); // limpiar buffer

            switch (opcion) {
                case 1:
                    for (Certificacion c : Sistema.getCertificaciones()) {
                        System.out.println(c.getId() + " - " + c.getNombre());
                    }
                    break;

                case 2:
                    for (Estudiante e : Sistema.getEstudiantes()) {
                        System.out.println(e.getRut() + " - " + e.getNombre() + " (" + e.getCarrera() + ")");
                    }
                    break;

                case 3:
                    System.out.print("Ingrese RUT: ");
                    String rut = s.nextLine();
                    Estudiante est = Sistema.buscarEstudiantePorRut(rut);
                    if (est != null) {
                        System.out.println("Encontrado: " + est.getNombre() + " - " + est.getCarrera());
                    } else {
                        System.out.println("No existe estudiante con ese RUT.");
                    }
                    break;

                case 4:
                    System.out.print("Ingrese RUT del estudiante: ");
                    String rutVisitor = s.nextLine();
                    Estudiante estudiante = Sistema.buscarEstudiantePorRut(rutVisitor);

                    if (estudiante != null) {
                        visitor v = new Validar_CertiVisitor(estudiante);
                        Sistema.aplicarVisitorCertificaciones(v); 
                    } else {
                        System.out.println("No existe estudiante con ese RUT.");
                    }
                    break;
            }
        } while (opcion != 5);
    }
}
