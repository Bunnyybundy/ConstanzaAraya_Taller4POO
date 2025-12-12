package Presentacion;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import Dominio.*;
import Logica.Sistema;

public class MenuAdmin extends JFrame {
    private JTable tablaUsuarios;
    private DefaultTableModel modelo;

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
        JButton btnCrear = new JButton("Crear Usuario");
        JButton btnModificar = new JButton("Modificar Usuario");
        JButton btnEliminar = new JButton("Eliminar Usuario");
        JButton btnReset = new JButton("Resetear Contraseña");

        panelBotones.add(btnCrear);
        panelBotones.add(btnModificar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnReset);
        add(panelBotones, BorderLayout.SOUTH);

        btnCrear.addActionListener(e -> crearUsuario());
        btnModificar.addActionListener(e -> modificarSeleccionado());
        btnEliminar.addActionListener(e -> eliminarSeleccionado());
        btnReset.addActionListener(e -> resetearSeleccionado());
    }

    private void cargarUsuarios() {
        modelo.setRowCount(0);
        for (Usuario u : Sistema.getUsuarios()) {
            modelo.addRow(new Object[]{u.getNombre(), u.getRol()});
        }
    }

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

    private void eliminarSeleccionado() {
        int fila = tablaUsuarios.getSelectedRow();
        if (fila < 0) { JOptionPane.showMessageDialog(this, "Seleccione un usuario."); return; }
        String nombre = (String) modelo.getValueAt(fila, 0);

        for (int i = 0; i < Sistema.getUsuarios().size(); i++) {
            if (Sistema.getUsuarios().get(i).getNombre().equals(nombre)) {
                Usuario u = Sistema.getUsuarios().remove(i);
                if (u instanceof Estudiante) {
                    for (int j = 0; j < Sistema.getEstudiantes().size(); j++) {
                        if (Sistema.getEstudiantes().get(j).getNombre().equals(nombre)) {
                            Sistema.getEstudiantes().remove(j);
                            break;
                        }
                    }
                }
                break;
            }
        }
        cargarUsuarios();
    }

    private void resetearSeleccionado() {
        int fila = tablaUsuarios.getSelectedRow();
        if (fila < 0) { JOptionPane.showMessageDialog(this, "Seleccione un usuario."); return; }
        String nombre = (String) modelo.getValueAt(fila, 0);
        Usuario u = Sistema.buscarUsuarioPorNombre(nombre);
        if (u != null) {
            u.setContraseña("1234");
            JOptionPane.showMessageDialog(this, "Contraseña restablecida a '1234'.");
        }
    }
}