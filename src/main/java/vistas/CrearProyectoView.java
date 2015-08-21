package vistas;

import com.toedter.calendar.JDateChooser;
import controlador.Controlador;
import exceptions.ProyectoExistenteException;
import exceptions.UsuarioNoLogeadoException;
import model.Proyecto;
import model.Recompensa;
import org.joda.time.DateTime;
import org.joda.time.Days;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;

public class CrearProyectoView {

	private JFrame frame;
	private JTextField textFieldTitulo;
	private JTextArea textAreaDescripcion;
	private JScrollPane scrollDescripcionRecompensas;
	private JScrollPane scrollDescripcion;
	private JDateChooser dateChooserFechaFinalizacion;
	private JTextField textFieldImporte;
	private JTextField textFieldCategoria;
	private JTextField textFieldTituloRecompensa;
	private JTextField textFieldApoyos;
	private JTextArea textAreaFieldDescripcionRecompensa;
	private JButton botonInsertar;
	private JButton botonLimpiar;
	private JButton botonEditar;
	private JButton botonBorrar;
	private JList listRecompensas;
	private JButton botonCrearProyecto;
	private JButton botonCancelarProyecto;
	private DefaultListModel<String> modeloListaRecompensa;
	private List<Recompensa> recompensas;
	private JButton botonActualizar;
	private int indexOfList = 0;
	private String tituloParaActualizar = "";
	private JTextField textFieldCantidad;
	private JFrame frameAnterior;
	private JScrollPane scrollListRecompensas;


	public CrearProyectoView(final JFrame frameAnterior) {
		frame = new JFrame();
		this.frameAnterior = frameAnterior;

		recompensas = new LinkedList<Recompensa>();

		configFrame();

		JPanel panelPrincipal = new JPanel();
		panelPrincipal.setLayout(new FlowLayout());
		frame.getContentPane().add(panelPrincipal);

		JTabbedPane pestanas = new JTabbedPane(JTabbedPane.TOP);
		frame.getContentPane().add(pestanas);
		
		//Pestana datos
		JPanel panelDatos = new JPanel();
		pestanas.addTab("Datos", null, panelDatos, null);
		panelDatos.setLayout(new BoxLayout(panelDatos, BoxLayout.Y_AXIS));
		
		JPanel panelTitulo = new JPanel();
		panelTitulo.setLayout(new FlowLayout());
		panelDatos.add(panelTitulo);
		JLabel labelTitulo = new JLabel("Titulo");
		labelTitulo.setPreferredSize(new Dimension(125, 25));
		panelTitulo.add(labelTitulo);
		textFieldTitulo = new JTextField();
		textFieldTitulo.setPreferredSize(new Dimension(400, 25));
		panelTitulo.add(textFieldTitulo);
		
		JPanel panelDescripcion = new JPanel();
		panelDescripcion.setLayout(new FlowLayout());
		panelDatos.add(panelDescripcion);
		JLabel labelDescripcion = new JLabel("Descripcion");
		labelDescripcion.setPreferredSize(new Dimension(125, 25));
		panelDescripcion.add(labelDescripcion);
		textAreaDescripcion = new JTextArea();
		textAreaDescripcion.setLineWrap(true);
		textAreaDescripcion.setWrapStyleWord(true);
		scrollDescripcion = new JScrollPane(
				textAreaDescripcion,
				ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollDescripcion.setPreferredSize(new Dimension(400, 150));
		panelDescripcion.add(scrollDescripcion);

		JPanel panelFechaFinalizacion = new JPanel();
		panelFechaFinalizacion.setLayout(new FlowLayout());
		panelDatos.add(panelFechaFinalizacion);
		JLabel labelFechaFinalizacion = new JLabel("Fecha finalizacion");
		labelFechaFinalizacion.setPreferredSize(new Dimension(125, 25));
		panelFechaFinalizacion.add(labelFechaFinalizacion);
		dateChooserFechaFinalizacion = new JDateChooser();
		dateChooserFechaFinalizacion.setPreferredSize(new Dimension(400, 25));
		panelFechaFinalizacion.add(dateChooserFechaFinalizacion);

		JPanel panelImporte = new JPanel();
		panelImporte.setLayout(new FlowLayout());
		panelDatos.add(panelImporte);
		JLabel labelImporte = new JLabel("Importe");
		labelImporte.setPreferredSize(new Dimension(125, 25));
		panelImporte.add(labelImporte);
		textFieldImporte = new JTextField();
		textFieldImporte.setPreferredSize(new Dimension(400, 25));
		panelImporte.add(textFieldImporte);

		JPanel panelCategoria = new JPanel();
		panelCategoria.setLayout(new FlowLayout());
		panelDatos.add(panelCategoria);
		JLabel labelCategoria = new JLabel("Categoria");
		labelCategoria.setPreferredSize(new Dimension(125, 25));
		panelCategoria.add(labelCategoria);
		textFieldCategoria = new JTextField();
		textFieldCategoria.setPreferredSize(new Dimension(400, 25));
		panelCategoria.add(textFieldCategoria);

		//Pestana recompensas
		JPanel panelRecompensas = new JPanel();
		panelRecompensas.setLayout(new BoxLayout(panelRecompensas, BoxLayout.X_AXIS));
		pestanas.addTab("Recompensas", null, panelRecompensas, null);

		//Panel Izquierda
		JPanel panelInformacionRecompensa = new JPanel();
		panelInformacionRecompensa.setLayout(new BoxLayout(panelInformacionRecompensa, BoxLayout.Y_AXIS));
		panelRecompensas.add(panelInformacionRecompensa, BorderLayout.WEST);

		JPanel panelTituloRecompensa = new JPanel();
		panelTituloRecompensa.setLayout(new FlowLayout());
		panelInformacionRecompensa.add(panelTituloRecompensa);
		final JLabel labelTituloRecompensa = new JLabel("Titulo");
		labelTituloRecompensa.setPreferredSize(new Dimension(75, 25));
		panelTituloRecompensa.add(labelTituloRecompensa);
		textFieldTituloRecompensa = new JTextField();
		textFieldTituloRecompensa.setPreferredSize(new Dimension(150, 25));
		panelTituloRecompensa.add(textFieldTituloRecompensa);

		JPanel panelApoyosRecompensa = new JPanel();
		panelApoyosRecompensa.setLayout(new FlowLayout());
		panelInformacionRecompensa.add(panelApoyosRecompensa);
		JLabel labelApoyosRecompensa = new JLabel("Apoyos");
		labelApoyosRecompensa.setPreferredSize(new Dimension(75, 25));
		panelApoyosRecompensa.add(labelApoyosRecompensa);
		textFieldApoyos = new JTextField();
		textFieldApoyos.setPreferredSize(new Dimension(150, 25));
		panelApoyosRecompensa.add(textFieldApoyos);

		JPanel panelCantidadRecompensa = new JPanel();
		panelCantidadRecompensa.setLayout(new FlowLayout());
		panelInformacionRecompensa.add(panelCantidadRecompensa);
		JLabel labelCantidadRecompensa = new JLabel("Cantidad");
		labelCantidadRecompensa.setPreferredSize(new Dimension(75, 25));
		panelCantidadRecompensa.add(labelCantidadRecompensa);
		textFieldCantidad = new JTextField();
		textFieldCantidad.setPreferredSize(new Dimension(150, 25));
		panelCantidadRecompensa.add(textFieldCantidad);

		JPanel panelDescripcionRecompensa = new JPanel();
		panelDescripcionRecompensa.setLayout(new FlowLayout());
		panelInformacionRecompensa.add(panelDescripcionRecompensa);
		JLabel labelDescripcionRecompensa = new JLabel("Descripcion");
		labelDescripcionRecompensa.setPreferredSize(new Dimension(75, 25));
		panelDescripcionRecompensa.add(labelDescripcionRecompensa);
		textAreaFieldDescripcionRecompensa = new JTextArea();
		textAreaFieldDescripcionRecompensa.setLineWrap(true);
		scrollDescripcionRecompensas = new JScrollPane(
				textAreaFieldDescripcionRecompensa,
				ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollDescripcionRecompensas.setPreferredSize(new Dimension(150, 100));
		panelDescripcionRecompensa.add(scrollDescripcionRecompensas);

		JPanel panelBotonesIzquierda = new JPanel();
		panelBotonesIzquierda.setLayout(new FlowLayout());
		panelInformacionRecompensa.add(panelBotonesIzquierda);

		botonInsertar = new JButton("Insertar");
		panelBotonesIzquierda.add(botonInsertar);
		botonInsertar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Recompensa recompensa = new Recompensa();
					recompensa.setCantidad(Double.parseDouble(textFieldCantidad.getText()));
					recompensa.setMaxApoyos(Integer.parseInt(textFieldApoyos.getText()));
					recompensa.setNombre(textFieldTituloRecompensa.getText());
					recompensa.setDescripcion(textAreaFieldDescripcionRecompensa.getText());
					recompensas.add(recompensa);
					modeloListaRecompensa.addElement(textFieldTituloRecompensa.getText());
					textFieldApoyos.setText("");
					textFieldTituloRecompensa.setText("");
					textAreaFieldDescripcionRecompensa.setText("");
					textFieldCantidad.setText("");
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(frame,
							"Formato de la cantidad o numero de apoyos incorecta.",
							"Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		botonLimpiar = new JButton("Limpiar");
		panelBotonesIzquierda.add(botonLimpiar);
		botonLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textFieldApoyos.setText("");
				textFieldTituloRecompensa.setText("");
				textAreaFieldDescripcionRecompensa.setText("");
				textFieldCantidad.setText("");
			}
		});

		botonActualizar = new JButton("Actualizar");
		botonActualizar.setEnabled(false);
		panelInformacionRecompensa.add(botonActualizar);
		botonActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					modeloListaRecompensa.remove(indexOfList);
					Recompensa recompensa = null;
					for (Recompensa r : recompensas) {
						if (r.getNombre().equals(tituloParaActualizar)) {
							recompensa = r;
							break;
						}
					}
					recompensa.setDescripcion(textAreaFieldDescripcionRecompensa.getText());
					recompensa.setNombre(textFieldTituloRecompensa.getText());
					recompensa.setCantidad(Double.parseDouble(textFieldCantidad.getText()));
					recompensa.setMaxApoyos(Integer.parseInt(textFieldApoyos.getText()));
					modeloListaRecompensa.add(indexOfList, textFieldTituloRecompensa.getText());
					textAreaFieldDescripcionRecompensa.setText("");
					textFieldApoyos.setText("");
					textFieldTituloRecompensa.setText("");
					textFieldCantidad.setText("");
					botonActualizar.setEnabled(false);
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(frame,
							"Formato de la cantidad o numero de apoyos incorecta.",
							"Error", JOptionPane.ERROR_MESSAGE);
				}

			}
		});

		//Pane derecha
		JPanel panelRecompensaDerecha = new JPanel();
		panelRecompensaDerecha.setLayout(new BoxLayout(panelRecompensaDerecha, BoxLayout.Y_AXIS));
		panelRecompensas.add(panelRecompensaDerecha, BorderLayout.EAST);

		JPanel panelListaRecompensas = new JPanel();
		panelRecompensaDerecha.add(panelListaRecompensas);
		listRecompensas = new JList();
		modeloListaRecompensa = new DefaultListModel<String>(){};
		listRecompensas.setModel(modeloListaRecompensa);
		scrollListRecompensas = new JScrollPane(
				listRecompensas,
				ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollListRecompensas.setPreferredSize(new Dimension(300, 250));
		panelListaRecompensas.add(scrollListRecompensas);

		JPanel panelBotones = new JPanel();
		panelBotones.setLayout(new FlowLayout());
		panelRecompensaDerecha.add(panelBotones);

		botonEditar = new JButton("Editar");
		panelBotones.add(botonEditar);
		botonEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					String titulo = modeloListaRecompensa.getElementAt(listRecompensas.getSelectedIndex());
					Recompensa recompensa = null;
					for(Recompensa r : recompensas) {
						if (r.getNombre().equals(titulo)) {
							recompensa = r;
							break;
						}
					}
					textFieldTituloRecompensa.setText(recompensa.getNombre());
					textAreaFieldDescripcionRecompensa.setText(recompensa.getDescripcion());
					textFieldApoyos.setText(String.valueOf(recompensa.getMaxApoyos()));
					textFieldCantidad.setText(String.valueOf(recompensa.getCantidad()));
					indexOfList = listRecompensas.getSelectedIndex();
					tituloParaActualizar = recompensa.getNombre();
					botonActualizar.setEnabled(true);
				} catch (ArrayIndexOutOfBoundsException ex) {
					JOptionPane.showMessageDialog(frame,
							"No ha seleccionado ningun elemento para editar",
							"Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		botonBorrar = new JButton("Borrar");
		panelBotones.add(botonBorrar);
		botonBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String titulo = modeloListaRecompensa.getElementAt(listRecompensas.getSelectedIndex());
				for(Recompensa r : recompensas) {
					if(r.getNombre().equals(titulo)) {
						recompensas.remove(r);
						break;
					}
				}
				modeloListaRecompensa.remove(listRecompensas.getSelectedIndex());
			}
		});

		JPanel panelBotones2 = new JPanel();
		panelBotones.setLayout(new FlowLayout());
		frame.getContentPane().add(panelBotones2);
		botonCrearProyecto = new JButton("Crear proyecto");
		panelBotones2.add(botonCrearProyecto);
		botonCrearProyecto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(recompensas.size() == 0) {
					JOptionPane.showMessageDialog(frame,
							"No hay ninguna recompensa asignada",
							"Error", JOptionPane.ERROR_MESSAGE);
				}
				else if(textFieldTitulo.getText().length() == 0) {
					JOptionPane.showMessageDialog(frame,
							"No hay ningun titulo para este proyecto",
							"Error", JOptionPane.ERROR_MESSAGE);
				}
				else if(textAreaDescripcion.getText().length() == 0) {
					JOptionPane.showMessageDialog(frame,
							"No hay ningun descripcion para este proyecto",
							"Error", JOptionPane.ERROR_MESSAGE);
				}
				else if(dateChooserFechaFinalizacion.getDate() == null) {
					JOptionPane.showMessageDialog(frame,
							"No hay ninguna fecha de finalizacion asignada",
							"Error", JOptionPane.ERROR_MESSAGE);
				}
				else if(textFieldCategoria.getText().length() == 0) {
					JOptionPane.showMessageDialog(frame,
							"No hay ninguna categoria asignada",
							"Error", JOptionPane.ERROR_MESSAGE);
				}
				else {
					if( Days.daysBetween(new DateTime(), new DateTime(dateChooserFechaFinalizacion.getDate())).getDays() <= 0){
						JOptionPane.showMessageDialog(frame,
								"La fecha de finalizacion debe ser posterior a la fecha actual",
								"Error", JOptionPane.ERROR_MESSAGE);
					}
					else if(Days.daysBetween(new DateTime(), new DateTime(dateChooserFechaFinalizacion.getDate())).getDays() > 90){
						JOptionPane.showMessageDialog(frame,
								"La fecha de finalizacion no puede ser mayor de 90 dias",
								"Error", JOptionPane.ERROR_MESSAGE);
					}
					else {
						try {
							Proyecto proyecto = Controlador.getUnicaInstancia().crearProyecto(textFieldTitulo.getText(), textAreaDescripcion.getText(),
									Double.parseDouble(textFieldImporte.getText()), new DateTime(dateChooserFechaFinalizacion.getDate()),
									textFieldCategoria.getText());
							for (Recompensa r : recompensas) {
								r.setProyecto(proyecto);
								Controlador.getUnicaInstancia().crearRecompensa(r.getNombre(), r.getDescripcion(), r.getMaxApoyos(),
										proyecto, r.getCantidad());
							}

							frame.dispose();
							frameAnterior.dispose();
							PrincipalView principalView = new PrincipalView();
							principalView.setVisible(true);

						} catch (ProyectoExistenteException e1) {
							JOptionPane.showMessageDialog(frame,
									"El proyecto con el nombre " + textFieldTitulo.getText() + " ya existe",
									"Error", JOptionPane.ERROR_MESSAGE);
						} catch (UsuarioNoLogeadoException e1) {
							JOptionPane.showMessageDialog(frame,
									"Tiene que estar logeado para poder anadir un proyecto",
									"Error", JOptionPane.ERROR_MESSAGE);
						} catch (NumberFormatException e1) {
							JOptionPane.showMessageDialog(frame,
									"El formato del importe es incorecto.",
									"Error", JOptionPane.ERROR_MESSAGE);
						}
					}
				}
			}
		});

		botonCancelarProyecto = new JButton("Cancelar proyecto");
		panelBotones2.add(botonCancelarProyecto);

		botonCancelarProyecto.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				cerrarVentana();
			}
		});

	}
	
	public void configFrame() {
		frame.setTitle("Crear Proyecto");
		frame.setResizable(false);
		frame.setSize(600, 400);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		
		frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
	}

	public void cerrarVentana() {
		frame.dispose();
	}

	public void setVisible(Boolean visible) {
		frame.setVisible(visible);
	}

}
