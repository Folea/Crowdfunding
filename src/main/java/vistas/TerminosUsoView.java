package vistas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class TerminosUsoView extends JDialog {
	private JPanel contentPane;
	private final String texto = "Términos y Condiciones\r\n\r\n"+
								"Los presentes términos y condiciones se han\r\n"+
								"redactado en un lugar de La Mancha de cuyo nombre\r\n"+
								"no quiero acordarme, donde no hay mucho que vivía\r\n"+
								"un hidalgo de los de lanza en astillero, adarga\r\n"+
								"antigua, rocín flaco y galgo corredor.\r\n\r\n"+
								"El usuario registrado en la asignatura TDS\r\n"+
								"se compromete a estudiar regularmente la\r\n"+
								"asignatura y a planificarse en el estudio\r\n"+
								"de manera que pueda alcanzar progresivamente\r\n"+
								"el nivel exigido en la asignatura antes\r\n"+
								"de la fecha del examen.\r\n\r\n"+
								"De ninguna forma podrá copiar, soplar o realizar\r\n"+
								"comentarios de las preguntas durante la\r\n"+
								"realización del examen,\r\n"+
								"quedando advertido que dichas acciones\r\n"+
								"podrían ser causa de fuertes sanciones\r\n"+
								"disciplinarias, incluyendo la retirada del\r\n"+
								"carnet de conducir.\r\n";
	
	public TerminosUsoView(JFrame ventana) {
		setTitle("Términos de Uso");
		setModalityType(ModalityType.APPLICATION_MODAL);
		setResizable(false);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setSize(350, 350);
		setLocationRelativeTo(null);
		contentPane = (JPanel) this.getContentPane();

		contentPane.setLayout(new BoxLayout(contentPane,BoxLayout.Y_AXIS));
		
		JButton btnCerrar = new JButton("Aceptar");
		btnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				dispose();
			}
		});
		JTextArea txtAreaTerminos = new JTextArea();
		txtAreaTerminos.setMinimumSize(new Dimension(320,450));
		txtAreaTerminos.setPreferredSize(new Dimension(320,450));
		txtAreaTerminos.setMaximumSize(new Dimension(320,450));
		txtAreaTerminos.setText(texto);
		txtAreaTerminos.setEditable(false);
		txtAreaTerminos.setCaretPosition(1);
		JScrollPane PanelScroll = new JScrollPane();

		PanelScroll.setSize(350,230);
		PanelScroll.setAlignmentX(CENTER_ALIGNMENT);

		contentPane.add(PanelScroll);
		PanelScroll.setViewportView(txtAreaTerminos);
		btnCerrar.setAlignmentX(CENTER_ALIGNMENT);
		contentPane.add(btnCerrar);
	}
}