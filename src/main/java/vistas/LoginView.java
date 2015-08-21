package vistas;
/*Ventana de login*/

import controlador.Controlador;
import exceptions.ContrasenaIncorectaException;
import exceptions.UsuarioInexistenteException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class LoginView {

	static final int ANCHOW=400;
	static final int ALTOW=300;
	
	private JFrame frame;
	private JLabel lblLogin = new JLabel("Login:");
	private JLabel lblPassword = new JLabel("Password:");
	private JTextField txtLogin = new JTextField();
	private JPasswordField txtPassword = new JPasswordField();
	private JButton btnLogin = new JButton("Login");
	private JButton btnRegistro = new JButton("Registro");
	private JButton btnSalir = new JButton("Salir");

	
	/*manejador eventos de botón*/
	 /* Constructor */
	public LoginView() { crearFrame(); /*crea la ventana*/ }

	/**
	 * Mostrar la ventana
	 */

	public void mostrarVentana() {
		 frame.setVisible(true);
	}
	/**
	 * Crear la ventana
	 */
	private void crearFrame() {
		frame = new JFrame();
		frame.setTitle("Acceso");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setSize(ANCHOW, ALTOW);

		
		JPanel contenido=(JPanel) frame.getContentPane();
		contenido.setLayout(new FlowLayout(FlowLayout.LEFT));

		
		lblLogin = new JLabel("Login:",JLabel.RIGHT);
		fixedSize(lblLogin,125,30);
		txtLogin = new JTextField();
		fixedSize(txtLogin,150,25);
		
		lblPassword = new JLabel("Password:",JLabel.RIGHT);
		fixedSize(lblPassword,125,30);
		txtPassword = new JPasswordField();
		fixedSize(txtPassword,150,25);
		
		btnLogin = new JButton("Login");
		fixedSize(btnLogin,100,30);
		btnLogin.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				try {
					Controlador.getUnicaInstancia().login(
							txtLogin.getText(), new String(txtPassword.getPassword()));
					PrincipalView window = new PrincipalView();
					window.setVisible(true);
					frame.dispose();
				} catch (UsuarioInexistenteException e1) {
					JOptionPane.showMessageDialog(frame,
							"Nombre de usuario o contrasena no valido",
							"Error", JOptionPane.ERROR_MESSAGE);
				} catch (ContrasenaIncorectaException e1) {
					JOptionPane.showMessageDialog(frame,
							"Nombre de usuario o contraseña no valido",
							"Error", JOptionPane.ERROR_MESSAGE);
				}
			} 
	    }); 

		btnRegistro = new JButton("Registro"); fixedSize(btnRegistro,100,30);
		btnRegistro.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
					JPanel panelRegistro = new RegistroView(frame);
			}
		});
		btnSalir = new JButton("Salir"); fixedSize(btnSalir,75,30);
		btnSalir.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
					frame.dispose(); /*cuando se destruye la última ventana termina la maquina virtual*/
					System.exit(0);  /*no sería necesario en este caso*/
			}
		});
		
		contenido.add(Box.createRigidArea(new Dimension(ANCHOW,75)));
		contenido.add(lblLogin); contenido.add(txtLogin);
		contenido.add(lblPassword); contenido.add(txtPassword);
		contenido.add(Box.createRigidArea(new Dimension(ANCHOW,75)));
		contenido.add(btnLogin); contenido.add(btnRegistro); 
		contenido.add(Box.createRigidArea(new Dimension(94,30)));contenido.add(btnSalir);
		
	}
	
	/**
	 * Fija el tamaño de un componente
	 */
	private void fixedSize(JComponent o, int x, int y) {
		Dimension d= new Dimension(x,y);
		o.setMinimumSize(d);
		o.setMaximumSize(d);
		o.setPreferredSize(d);
	}
}