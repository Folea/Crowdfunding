package vistas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import controlador.Controlador;
import exceptions.UsuarioExistenteException;


@SuppressWarnings("serial")
public class RegistroView extends JPanel {
	static final int ANCHOW=400;
	static final int ALTOW=300;
	
	private JFrame ventana;
	private JLabel lblNombre;
	private JLabel lblApellidos;
	private JLabel lblDNI;
	private JLabel lblEmail;
	private JLabel lblUsuario;
	private JLabel lblPassword;
	private JLabel lblPasswordChk;
	private JTextField txtNombre;
	private JTextField txtApellidos;
	private JTextField txtDNI;
	private JTextField txtEmail;
	private JTextField txtUsuario;
	private JPasswordField txtPassword;
	private JPasswordField txtPasswordChk;
	private JCheckBox chkTerminos;
	private JButton btnRegistrar;
	private JButton btnVolver;
	
	private JPanel jpanelAnterior;
	private JLabel lblNombreError;
	private JLabel lblApellidosError;
	private JLabel lblDNIError;
	private JLabel lblEmailError;
	private JLabel lblUsuarioError;
	private JLabel lblPasswordError;
	
	private boolean mostrarTerminos=true;
	
	public RegistroView(JFrame frame){
		ventana=frame;
		jpanelAnterior = (JPanel) ventana.getContentPane();
		setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		JPanel linea_1=new JPanel(); /*Nombre*/
		linea_1.setLayout(new FlowLayout(FlowLayout.LEFT)); fixedSize(linea_1,ANCHOW,25);
		linea_1.setAlignmentX(JLabel.LEFT_ALIGNMENT);
		JPanel linea_2=new JPanel(); /*Apellidos*/
		linea_2.setLayout(new FlowLayout(FlowLayout.LEFT)); fixedSize(linea_2,ANCHOW,25);
		linea_2.setAlignmentX(JLabel.LEFT_ALIGNMENT);
		JPanel linea_3=new JPanel(); /*DNI*/
		linea_3.setLayout(new FlowLayout(FlowLayout.LEFT)); fixedSize(linea_3,ANCHOW,25);
		linea_3.setAlignmentX(JLabel.LEFT_ALIGNMENT);
		JPanel linea_4=new JPanel(); /*Email*/ 
		linea_4.setLayout(new FlowLayout(FlowLayout.LEFT)); fixedSize(linea_4,ANCHOW,25);
		linea_4.setAlignmentX(JLabel.LEFT_ALIGNMENT);
		JPanel linea_5=new JPanel(); /*Usuario*/
		linea_5.setLayout(new FlowLayout(FlowLayout.LEFT)); fixedSize(linea_5,ANCHOW,25);
		linea_5.setAlignmentX(JLabel.LEFT_ALIGNMENT);
		JPanel linea_6=new JPanel(); /*Password y passwordchk*/
		linea_6.setLayout(new FlowLayout(FlowLayout.LEFT)); fixedSize(linea_6,ANCHOW,25);
		linea_6.setAlignmentX(JLabel.LEFT_ALIGNMENT);
		JPanel linea_7=new JPanel(); /*Terminos de uso*/
		linea_7.setLayout(new FlowLayout(FlowLayout.CENTER)); fixedSize(linea_7,ANCHOW,25);
		linea_7.setAlignmentX(JLabel.LEFT_ALIGNMENT);
		JPanel linea_8=new JPanel(); /*Botones*/
		linea_8.setLayout(new FlowLayout(FlowLayout.LEFT)); fixedSize(linea_8,ANCHOW,50);
		linea_8.setAlignmentX(JLabel.LEFT_ALIGNMENT);
		
		/*linea 1*/
		lblNombre = new JLabel("Nombre :",JLabel.RIGHT); fixedSize(lblNombre,75,20);
		txtNombre = new JTextField(); fixedSize(txtNombre,270,20);
		lblNombreError=new JLabel("El nombre es obligatorio",JLabel.RIGHT); fixedSize(lblNombreError,224,15);
		lblNombreError.setForeground(Color.RED);
		linea_1.add(lblNombre); linea_1.add(txtNombre);
		
		/*linea 2*/
		lblApellidos = new JLabel("Apellidos :",JLabel.RIGHT); fixedSize(lblApellidos,75,20);
		txtApellidos = new JTextField(); fixedSize(txtApellidos,270,20);
		lblApellidosError=new JLabel("Los apellidos son obligatorios",JLabel.RIGHT); fixedSize(lblApellidosError,255,15);
		lblApellidosError.setForeground(Color.RED);
		linea_2.add(lblApellidos); linea_2.add(txtApellidos);
		
		/*linea 3*/
		lblDNI = new JLabel("DNI :",JLabel.RIGHT); fixedSize(lblDNI,75,20);
		txtDNI = new JTextField(); fixedSize(txtDNI,125,20);
		lblDNIError=new JLabel("El DNI es obligatorio",JLabel.LEFT); fixedSize(lblDNIError,150,15);
		lblDNIError.setForeground(Color.RED);
		linea_3.add(lblDNI); linea_3.add(txtDNI); linea_3.add(lblDNIError);
		
		/*linea 4*/
		lblEmail = new JLabel("Email :",JLabel.RIGHT); fixedSize(lblEmail,75,20);
		txtEmail = new JTextField(); fixedSize(txtEmail,125,20);
		lblEmailError=new JLabel("El Email es obligatorio",JLabel.LEFT); fixedSize(lblEmailError,150,15);
		lblEmailError.setForeground(Color.RED);
		linea_4.add(lblEmail); linea_4.add(txtEmail); linea_4.add(lblEmailError);
		
		/*linea 5*/
		lblUsuario = new JLabel("Usuario :",JLabel.RIGHT); fixedSize(lblUsuario,75,20);
		txtUsuario = new JTextField(); fixedSize(txtUsuario,125,20);
		lblUsuarioError=new JLabel("El usuario ya existe",JLabel.LEFT); fixedSize(lblUsuarioError,150,15);
		lblUsuarioError.setForeground(Color.RED);
		linea_5.add(lblUsuario); linea_5.add(txtUsuario); linea_5.add(lblUsuarioError);
		
		/*linea 6*/
		lblPassword = new JLabel("Password :",JLabel.RIGHT); fixedSize(lblPassword,75,20);
		txtPassword = new JPasswordField(); fixedSize(txtPassword,100,20);
		lblPasswordChk = new JLabel("Otra vez:",JLabel.RIGHT); fixedSize(lblPasswordChk,60,20);
		txtPasswordChk = new JPasswordField(); fixedSize(txtPasswordChk,100,20);
		lblPasswordError=new JLabel("Error al introducir las contraseñas",JLabel.CENTER); fixedSize(lblPasswordError,ANCHOW,15);
		lblPasswordError.setForeground(Color.RED);
		linea_6.add(lblPassword); linea_6.add(txtPassword); linea_6.add(lblPasswordChk); linea_6.add(txtPasswordChk); 
		
		/*linea 7*/
		chkTerminos= new JCheckBox("Marcar la casilla para leer y aceptar los terminos de uso");
		linea_7.add(chkTerminos);
		
		/*linea 8*/
		btnVolver= new JButton("Volver"); fixedSize(btnVolver,90,30);
		btnRegistrar= new JButton("Registrar"); fixedSize(btnRegistrar,90,30);
		btnRegistrar.setEnabled(false);
		linea_8.add(Box.createHorizontalStrut(75)); linea_8.add(btnVolver);
		linea_8.add(Box.createHorizontalStrut(80)); linea_8.add(btnRegistrar);
		
		ocultarErrores();
		
		this.add(linea_1);
		this.add(lblNombreError);
		this.add(linea_2);
		this.add(lblApellidosError);
		this.add(linea_3);
		this.add(linea_4);
		this.add(linea_5);
		this.add(linea_6);
		this.add(lblPasswordError);
		this.add(linea_7);
		this.add(linea_8);
		ventana.setContentPane(this);
		ventana.revalidate(); /*redibujar con el nuevo JPanel*/
		
		/*Manejador botón volver*/
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventana.setContentPane(jpanelAnterior);
				ventana.revalidate();
			}
		});
		
		/*Manejador casilla checkbox*/
		chkTerminos.addItemListener(new ItemListener (){
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange()==ItemEvent.SELECTED) {
					if (mostrarTerminos) {
						TerminosUsoView tm= new TerminosUsoView(ventana);
						tm.setVisible(true);
						mostrarTerminos=false;
					}
					btnRegistrar.setEnabled(true);
					chkTerminos.setSelected(true);
			    } else { btnRegistrar.setEnabled(false); }
			}
		});
		
		/*Manejador botón Registrar*/
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean OK=false;
				OK=checkFields();
				if (OK) {
					//todo bien registrar
					try{
						Controlador.getUnicaInstancia().registrar(
										txtNombre.getText(),
										txtApellidos.getText(),
										txtDNI.getText(),
										txtUsuario.getText(),
										new String(txtPassword.getPassword()),
										txtEmail.getText());
						JOptionPane.showMessageDialog(
								ventana,
								"Se ha registrado correctamente.",
								"Registro",
								JOptionPane.INFORMATION_MESSAGE);
						ventana.setContentPane(jpanelAnterior);
						ventana.revalidate();
					} catch (UsuarioExistenteException e1) {
						JOptionPane.showMessageDialog(ventana,
								"No se ha podido llevar a cabo el registro.\n",
								"Registro",
								JOptionPane.ERROR_MESSAGE);
					}
				} //if OK
			} //Actionperformed
		});
	} /*constructor*/
	
	/**
	 * Comprueba que los campos de registro estén bien
	 */
	private boolean checkFields() {
		boolean salida=true;
		/*borrar todos los errores en pantalla*/
		ocultarErrores();
		if (txtNombre.getText().trim().isEmpty()) {
			lblNombreError.setVisible(true); salida=false;
		}
		if (txtApellidos.getText().trim().isEmpty()) {
			lblApellidosError.setVisible(true); salida=false;
		}
		if (txtDNI.getText().trim().isEmpty()) {
			lblDNIError.setVisible(true); salida=false;
		}
		if (txtEmail.getText().trim().isEmpty()) {
			lblEmailError.setVisible(true); salida=false;
		}
		if (txtUsuario.getText().trim().isEmpty()) {
			lblUsuarioError.setText("El usuario es obligatorio");
			lblUsuarioError.setVisible(true); salida=false;
		}
		String password = new String(txtPassword.getPassword());
		String password2 = new String(txtPasswordChk.getPassword());
		if (password.equals("")) {
			lblPasswordError.setText("El password no puede estar vacio");
			lblPasswordError.setVisible(true); salida=false;
		} else if (!password.equals(password2)) {
			lblPasswordError.setText("Los dos passwords no coinciden");
			lblPasswordError.setVisible(true); salida=false;
		}
		/* Comprobar que no exista otro usuario con igual login */
		if (Controlador.getUnicaInstancia().esRegistrado(txtUsuario.getText())) {
			lblUsuarioError.setText("Ya existe ese usuario");
			lblUsuarioError.setVisible(true); salida=false;
		}
		return salida;
	}
	
	/**
	 * Oculta todos los errores que pueda haber en la pantalla
	 */
	private void ocultarErrores() {
		lblNombreError.setVisible(false);
		lblApellidosError.setVisible(false);
		lblDNIError.setVisible(false);
		lblEmailError.setVisible(false);
		lblUsuarioError.setVisible(false);
		lblPasswordError.setVisible(false);
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