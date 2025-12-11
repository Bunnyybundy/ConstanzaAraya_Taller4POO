package Presentacion;

import javax.swing.*;

import Dominio.*;
import Logica.Sistema;

public class VentanaAdmin extends JFrame{
	public VentanaAdmin() {
		setTitle("Menu Administrados");
		setSize(400,300);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLayout(new java.awt.GridLayout(4,1));
		
		JButton btnCrear = new JButton("Crear Usuario");
		JButton btnModificar = new JButton("Modificar Usuario");
		JButton btnEliminar = new JButton("Eliminar Usuario");
		JButton btnReset = new JButton("Restablecer Contraseña");
		
		add(btnCrear);
		add(btnModificar);
		add(btnEliminar);
		add(btnReset);
		
		btnCrear.addActionListener(e -> {
			String nombre = JOptionPane.showInputDialog("Nombre: ");
			String contraseña = JOptionPane.showInputDialog("Contraseña: ");
			String rol = JOptionPane.showInputDialog("Rol (Estudiante/Coordinador):");
			if(rol.equalsIgnoreCase("Estudiante")) {
				String rut = JOptionPane.showInputDialog("RUT: ");
				String carrera = JOptionPane.showInputDialog("Carrera: ");
				String correoE = JOptionPane.showInputDialog("Correo: ");
				Estudiante estudiante = new Estudiante(rut,nombre,carrera,correoE,contraseña);
				Sistema.getUsuarios().add(estudiante);
				Sistema.getEstudiantes().add(estudiante);
			}else {
				String area = JOptionPane.showInputDialog("Area: ");
				Coordinador c = new Coordinador(nombre, contraseña,area);
				Sistema.getUsuarios().add(c);
			}
			JOptionPane.showMessageDialog(this, "Usuario creado.");
		});
	}
	
	public void setVisible(boolean b) {
		// TODO Auto-generated method stub
		
	}

}
