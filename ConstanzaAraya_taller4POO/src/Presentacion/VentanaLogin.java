package Presentacion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Dominio.*;
import Logica.Sistema;

public class VentanaLogin extends JFrame{
	private JTextField txtUsuario;
	private JPasswordField txtContraseña;
	private JButton btnLogin;
	
	public VentanaLogin() {
		setTitle("Login Sistema");
		setSize(350,200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		
		JLabel lblUsuario = new JLabel("Usuario: ");
		lblUsuario.setBounds(20,20,80,25);
		add(lblUsuario);
		
		txtUsuario = new JTextField();
		txtUsuario.setBounds(100,20,200,25);
		add(txtUsuario);
		
		JLabel lblContraseña = new JLabel("Contraseña: ");
		lblContraseña.setBounds(20,60,80,25);
		add(lblContraseña);
		
		txtContraseña = new JPasswordField();
		txtContraseña.setBounds(100,60,200,25);
		add(txtContraseña);
		
		btnLogin.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String usuario = txtUsuario.getText();
				String contraseña = new String(txtContraseña.getPassword());
				Usuario u = Sistema.login(usuario, contraseña);
				
				if(u instanceof Administrador) {
					new VentanaAdmin().setVisible(true);
				}else if(u instanceof Coordinador) {
					new VentanaCoordinador().setVisible(true);
				}else if(u instanceof Estudiante) {
					new VentanaEstudiante((Estudiante) u).setVisible(true);
				}else {
					JOptionPane.showMessageDialog(null, "Credenciales invalidas");
				}
			}
			
		});
		
	}
	
	
}
