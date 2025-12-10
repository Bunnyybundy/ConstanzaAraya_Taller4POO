package Presentacion;

import java.util.*;
import Logica.Sistema;
import Logica.UsuarioFactory;
import Dominio.Usuario;


public class MenuAdmin {
    private static Scanner s = new Scanner(System.in);

    public static void mostrar() {
        int opcion;
        do {
            System.out.println("=== Menú Administrador ===");
            System.out.println("1. Listar usuarios");
            System.out.println("2. Crear usuario");
            System.out.println("3. Eliminar usuario");
            System.out.println("4. Salir");
            System.out.print("Seleccione opción: ");
            opcion = s.nextInt();
            s.nextLine(); 

            switch (opcion) {
                case 1:
                    for (Usuario u : Sistema.getUsuarios()) {
                        System.out.println(u.getNombre() + " - " + u.getRol());
                    }
                    break;
                case 2:
                    System.out.print("Nombre: ");
                    String nombre = s.nextLine();
                    System.out.print("Contraseña: ");
                    String pass = s.nextLine();
                    System.out.print("Rol (Admin/Coordinador): ");
                    String rol = s.nextLine();
                    String extra = "";
                    if (rol.equals("Coordinador")) {
                        System.out.print("Área: ");
                        extra = s.nextLine();
                    }
                    Usuario nuevo = UsuarioFactory.crearUsuario(new String[]{nombre, pass, rol, extra});
                    Sistema.getUsuarios().add(nuevo);
                    System.out.println("Usuario creado.");
                    break;
                case 3:
                    System.out.print("Nombre de usuario a eliminar: ");
                    String eliminar = s.nextLine();
                    Sistema.getUsuarios().removeIf(u -> u.getNombre().equals(eliminar));
                    System.out.println("Usuario eliminado.");
                    break;
            }
        } while (opcion != 4);
    }
}

