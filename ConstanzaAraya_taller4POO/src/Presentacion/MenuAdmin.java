package Presentacion;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import Dominio.*;
import Logica.Sistema;
/**
 * Ventana del menú administrador.
 * Permite gestionar usuarios: crear, modificar, eliminar y restablecer contraseñas.
 */
public class MenuAdmin extends JFrame {
	private static final long serialVersionUID = 1L;

    private JTable tablaUsuarios;
    private DefaultTableModel modelo;
    /**
     * Construye la ventana del menú administrador.
     * Configura la tabla de usuarios y los botones de gestión.
     */
    public MenuAdmin() {
        setTitle("Menú Administrador");
        setSize(800,500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        modelo = new DefaultTableModel(new Object[]{"Nombre","Rol"},0);
        tablaUsuarios = new JTable(modelo);
        cargarUsuarios();
        add(new JScrollPane(tablaUsuarios), BorderLayout.CENTER);

        JPanel panelBotones = new JPanel();
        JButton btnCrear = new JButton("Crear Cuentas");
        JButton btnModificar = new JButton("Modificar Cuentas");
        JButton btnEliminar = new JButton("Eliminar Cuentas");
        JButton btnReset = new JButton("Restablecer Contraseña");

        panelBotones.add(btnCrear);
        panelBotones.add(btnModificar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnReset);
        add(panelBotones, BorderLayout.SOUTH);

        btnCrear.addActionListener(e -> crearUsuario());
        btnModificar.addActionListener(e -> modificarSeleccionado());
        btnEliminar.addActionListener(e -> eliminarSeleccionado());
        btnReset.addActionListener(e -> restablecerSeleccionado());
    }
    /**
     * Carga los usuarios desde el sistema en la tabla.
     */
    private void cargarUsuarios() {
        modelo.setRowCount(0);
        for (Usuario u : Sistema.getUsuarios()) {
            modelo.addRow(new Object[]{u.getNombre(), u.getRol()});
        }
    }
    /**
     * Crea un nuevo usuario (Estudiante o Coordinador) mediante diálogos.
     */
    private void crearUsuario() {
        String rol = JOptionPane.showInputDialog(this, "Rol (Estudiante/Coordinador):");
        if (rol == null) return;

        String nombre = JOptionPane.showInputDialog(this, "Nombre:");
        String pass = JOptionPane.showInputDialog(this, "Contraseña:");
        if (nombre == null || pass == null) return;
        if (rol.equalsIgnoreCase("Estudiante")) {
            String rut = JOptionPane.showInputDialog(this, "RUT:");
            String carrera = JOptionPane.showInputDialog(this, "Carrera:");
            String semestreStr = JOptionPane.showInputDialog(this, "Semestre:");
            String correo = JOptionPane.showInputDialog(this, "Correo:");
            int semestre = 1;
            try { semestre = Integer.parseInt(semestreStr); } catch (Exception ex) { semestre = 1; }
            Estudiante eNuevo = new Estudiante(rut, nombre, carrera, semestre, correo, pass);
            Sistema.getEstudiantes().add(eNuevo);
            Sistema.getUsuarios().add(eNuevo);
        } else if (rol.equalsIgnoreCase("Coordinador")) {
            String area = JOptionPane.showInputDialog(this, "Área:");
            Coordinador cNuevo = new Coordinador(nombre, pass, area);
            Sistema.getUsuarios().add(cNuevo);
        }
        cargarUsuarios();
    }
    /**
     * Modifica el usuario seleccionado en la tabla.
     */
    private void modificarSeleccionado() {
        int fila = tablaUsuarios.getSelectedRow();
        if (fila < 0) { JOptionPane.showMessageDialog(this, "Seleccione un usuario."); return; }
        String nombreActual = (String) modelo.getValueAt(fila, 0);
        Usuario u = Sistema.buscarUsuarioPorNombre(nombreActual);
        if (u == null) return;

        String nuevoNombre = JOptionPane.showInputDialog(this, "Nuevo nombre:", u.getNombre());
        String nuevaClave = JOptionPane.showInputDialog(this, "Nueva contraseña:", u.getContraseña());
        if (nuevoNombre != null) u.setNombre(nuevoNombre);
        if (nuevaClave != null) u.setContraseña(nuevaClave);
        cargarUsuarios();
    }
    /**
     * Elimina el usuario seleccionado y sus datos asociados.
     */
    private void eliminarSeleccionado() {
        int fila = tablaUsuarios.getSelectedRow();
        if (fila < 0) { JOptionPane.showMessageDialog(this, "Seleccione un usuario."); return; }
        String nombre = (String) modelo.getValueAt(fila, 0);

        Usuario u = Sistema.buscarUsuarioPorNombre(nombre);
        if(u != null) {
        	Sistema.getUsuarios().remove(u);
        	if(u instanceof Estudiante) {		
        		Estudiante est = (Estudiante) u;
        		Sistema.getEstudiantes().remove(est);
        		Sistema.getNota().removeIf(n -> n.getRut().equals(est.getRut()));
        		Sistema.getRegistros().removeIf(r -> r.getRut().equals(est.getRut()));  
        		}
                if(u instanceof Coordinador) {
                	Sistema.getCertificaciones().removeIf(c -> c.getDescripcion().contains(u.getNombre()));
            }
        }
        cargarUsuarios();
    }
    /**
     * Restablece la contraseña del usuario seleccionado a "1234".
     */
    private void restablecerSeleccionado() {
        int fila = tablaUsuarios.getSelectedRow();
        if (fila < 0) { 
            JOptionPane.showMessageDialog(this, "Seleccione un usuario."); 
            return; 
        }
        String nombre = (String) modelo.getValueAt(fila, 0);
        Usuario u = Sistema.buscarUsuarioPorNombre(nombre);
        if (u != null) {
            String nuevaClave = JOptionPane.showInputDialog(this, "Ingrese nueva contraseña:");
            if (nuevaClave != null && !nuevaClave.isEmpty()) {
                u.setContraseña(nuevaClave);
                JOptionPane.showMessageDialog(this, "Contraseña actualizada correctamente.");
            }
        }
    }
}