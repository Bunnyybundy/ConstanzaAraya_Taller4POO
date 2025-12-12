package Presentacion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import Dominio.*;
import Logica.*;
/**
 * Ventana de inicio de sesión del sistema.
 * Permite ingresar usuario y contraseña para acceder
 * al menú correspondiente según el rol.
 */
public class VentanaLogin extends JFrame{
	private static final long serialVersionUID = 1L;

	private JTextField txtUsuario;
	private JPasswordField txtContraseña;
	private JButton btnLogin;
	/**
     * Construye la ventana de login con campos de usuario y contraseña.
     * Configura el botón de ingreso para validar credenciales.
     */
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
		btnLogin = new JButton("Ingresar");
		btnLogin.setBounds(100,100,120,25);
		add(btnLogin);
		
		btnLogin.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String usuario = txtUsuario.getText();
				String contraseña = new String(txtContraseña.getPassword());
				Usuario u = Sistema.login(usuario, contraseña);
				
				if(u instanceof Administrador) {
					new MenuAdmin().setVisible(true);
				}else if(u instanceof Coordinador) {
					new MenuCoordinador().setVisible(true);
				}else if(u instanceof Estudiante) {
					new MenuEstudiante((Estudiante) u).setVisible(true);
				}else {
					JOptionPane.showMessageDialog(null, "Credenciales invalidas");
				}
			}
			
		});
		
	}
	
	
}
