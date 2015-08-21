package vistas;

import controlador.Controlador;
import model.Proyecto;
import pulsador.IEncendidoListener;
import pulsador.Luz;
import umu.tds.cargador.ComponenteCargadorFinanciacion;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.EventObject;

public class PrincipalView {
	
	private JFrame frame;
	private JButton botonNotificaciones;
	private JButton botonMensajes;
	private JButton botonApoyos;
	private JButton botonCreacionProyecto;
	private JButton botonCerrarSesion;
	private JTable tablaVotacion;
	private JTable tablaFinanciacion;
	private JScrollPane scrollVotacion;
	private JScrollPane scrollFinanciacion;
	private Component rigidAreaBotones;
	private Component rigidAreaUsuario;
	private boolean xmlFinanciacionCargado = false;
	
	public PrincipalView() {
		frame = new JFrame();
		
		configFrame();
		
		//Panel Norte
		JTabbedPane pestanas = new JTabbedPane(JTabbedPane.TOP);
		frame.getContentPane().add(pestanas, BorderLayout.NORTH);
		
		JPanel panelVotacion = new JPanel();
		pestanas.addTab("Proyectos en votacion", null, panelVotacion, null);
		panelVotacion.setLayout(new BorderLayout(0, 0));
		scrollVotacion = new JScrollPane();
		panelVotacion.add(scrollVotacion);
		
		JPanel panelFinanciacion = new JPanel();
		pestanas.addTab("Proyectos en financiacion", null, panelFinanciacion, null);
		panelFinanciacion.setLayout(new BorderLayout(0, 0));
		scrollFinanciacion = new JScrollPane();
		panelFinanciacion.add(scrollFinanciacion);

		Controlador.getUnicaInstancia().actualizarEstadoProyectos();
		crearTablas();

		pestanas.addMouseListener(new MouseListener() {

			public void mouseReleased(MouseEvent e) {
			}

			public void mousePressed(MouseEvent e) {
			}

			public void mouseExited(MouseEvent e) {
			}

			public void mouseEntered(MouseEvent e) {
			}

			public void mouseClicked(MouseEvent e) {
				Controlador.getUnicaInstancia().actualizarEstadoProyectos();
				crearTablas();
			}
		});	
		
		//Panel Sur
		JPanel panelSur = new JPanel();
		frame.getContentPane().add(panelSur, BorderLayout.SOUTH);
		panelSur.setLayout(new BoxLayout(panelSur, BoxLayout.Y_AXIS));

//		JPanel panelLuz = new JPanel();
//		panelLuz.setLayout(new FlowLayout());
//		panelSur.add(panelLuz);
//		JLabel labelUsuario = new JLabel("Usuario conectado: " + Controlador.getUnicaInstancia().getUsuarioConectado());
//		panelLuz.add(labelUsuario);
//
//		rigidAreaUsuario = Box.createRigidArea(new Dimension(20, 20));
//		panelLuz.add(rigidAreaUsuario);

//		final Luz luz = new Luz();
//		luz.addEncendidoListener(new IEncendidoListener() {
//
//			public void enteradoCambioEncendido(EventObject eventObject) {
//				JFrame frame2 = new JFrame();
//				frame2.setAlwaysOnTop(true);
//				JFileChooser chooser = new JFileChooser();
//				chooser.showOpenDialog(frame2);
//				File currentFile = chooser.getSelectedFile();
//				if (currentFile != null) {
//					ComponenteCargadorFinanciacion cargador = new ComponenteCargadorFinanciacion();
//					List<Proyecto> proyectos = Controlador.getUnicaInstancia().getProyectosFinanciacion();
//					for (Proyecto p : proyectos) {
//						cargador.addOyente(p);
//					}
//					cargador.asignarArchivo(currentFile.getAbsolutePath());
//					Controlador.getUnicaInstancia().actualizarEstadoProyectos();
//					crearTablas();
//				}
//			}
//		});
//
//		panelLuz.add(luz);

		JPanel panelBotones = new JPanel();
		panelBotones.setLayout(new BoxLayout(panelBotones, BoxLayout.X_AXIS));
		panelSur.add(panelBotones);

		rigidAreaBotones = Box.createRigidArea(new Dimension(130, 20));
		panelBotones.add(rigidAreaBotones);

		botonNotificaciones = new JButton(Controlador.getUnicaInstancia().getNotificaciones().size() + " notificaciones");
		panelBotones.add(botonNotificaciones);
		botonNotificaciones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NotificacionesView view = new NotificacionesView();
				view.setVisible(true);
			}
		});
		
		botonMensajes = new JButton("Mensajes");
		panelBotones.add(botonMensajes);
		botonMensajes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MensajesView mensajesView = new MensajesView();
				mensajesView.setVisible(true);
			}
		});
		
		botonApoyos = new JButton("Apoyos");
		panelBotones.add(botonApoyos);
		botonApoyos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ApoyosView apoyosView = new ApoyosView();
				apoyosView.setVisible(true);
			}
		});
		
		botonCreacionProyecto = new JButton("Crear proyecto");
		panelBotones.add(botonCreacionProyecto);
		botonCreacionProyecto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CrearProyectoView crearProyectoView = new CrearProyectoView(frame);
				crearProyectoView.setVisible(true);
			}
		});
		
		botonCerrarSesion = new JButton("Cerrar sesion");
		panelBotones.add(botonCerrarSesion);
		botonCerrarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Controlador.getUnicaInstancia().logout();
				LoginView view = new LoginView();
				view.mostrarVentana();
				frame.dispose();
			}
		});

	}
	
	private JTable crearTablaVotacion() {

		// Nombres columnas
		String[] columnas = new String[] { "Id", "Titulo", "Descripcion", "Dias Restantes", "Votos", "" };

		// Tipos de datos columnas
		final Class[] tiposColumnas = new Class[] { Integer.class, String.class, String.class, int.class,
				int.class, JButton.class };

		final DefaultTableModel modeloTablaVotacion = new DefaultTableModel(new Object[][] {}, columnas) {

			public Class getColumnClass(int columnIndex) {
				return tiposColumnas[columnIndex];

			}

			boolean[] columnEditables = new boolean[] { false, false, false, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};
		
		
		final List<Proyecto> proyectosVotacion = Controlador.getUnicaInstancia().getProyectosVotacion();

		Collections.sort(proyectosVotacion, new Comparator<Proyecto>() {

			public int compare(Proyecto p1, Proyecto p2) {
				if (p1.getCuentaVotos() > p2.getCuentaVotos())
					return -1;
				return +1;
			}
		});
		for (Proyecto p : proyectosVotacion) {
			int diasRestantes = p.getDiasRestantes();
			modeloTablaVotacion.addRow(new Object[] {p.getId(), p.getNombre(), p.getDescripcion(), diasRestantes,
					p.getCuentaVotos(), new JButton("Info") });
		}

		final JTable table_votacion = new JTable();
		table_votacion.setBackground(Color.WHITE);
		table_votacion.setModel(modeloTablaVotacion);
		table_votacion.setDefaultRenderer(JButton.class, new TableCellRenderer() {

			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int column) {

				return (Component) value;
			}
		});

		table_votacion.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int fila = table_votacion.rowAtPoint(e.getPoint());
				int columna = table_votacion.columnAtPoint(e.getPoint());

				if (table_votacion.getModel().getColumnClass(columna).equals(JButton.class)) {
					InformacionProyectoVotacionView informacionProyectoVotacionView = new InformacionProyectoVotacionView(modeloTablaVotacion.getValueAt(fila, 1).toString());
					informacionProyectoVotacionView.setVisible(true);
				}
			}
		});

		return table_votacion;
	}

	private JTable crearTablaFinanciacion() {

		// Nombres columnas
		String[] columnas = new String[] { "Id", "Titulo", "Descripcion", "Estado", "Dias Restantes", "Dinero recaudado",
				"% recaudado", "" };

		// Tipos de datos columnas
		final Class[] tiposColumnas = new Class[] { Integer.class, String.class, String.class, String.class, int.class,
				double.class, double.class, JButton.class };

		final DefaultTableModel modeloTablaFinanciacion = new DefaultTableModel(new Object[][] {}, columnas) {

			public Class getColumnClass(int columnIndex) {
				return tiposColumnas[columnIndex];

			}

			boolean[] columnEditables = new boolean[] {false, false, false, false, false, false, false, false};

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};

		final List<Proyecto> proyectos = Controlador.getUnicaInstancia().getProyectosFinanciacion();
		proyectos.addAll(Controlador.getUnicaInstancia().getProyectosFinanciados());
		proyectos.addAll(Controlador.getUnicaInstancia().getProyectosCancelados());

		for (Proyecto p : proyectos) {
			int diasRestantes = p.getDiasRestantes();
			modeloTablaFinanciacion.addRow(new Object[] { p.getId(), p.getNombre(), p.getDescripcion(), p.getEstado(), diasRestantes,
					p.getFinanciacion(), p.getPorcentajeFinanciado(),
					new JButton("Info") });
		}

		final JTable table_financiacion = new JTable();
		table_financiacion.setModel(modeloTablaFinanciacion);
		table_financiacion.setDefaultRenderer(JButton.class, new TableCellRenderer() {

			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
														   boolean hasFocus, int row, int column) {
				return (Component) value;
			}
		});

		table_financiacion.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int fila = table_financiacion.rowAtPoint(e.getPoint());
				int columna = table_financiacion.columnAtPoint(e.getPoint());

				if (table_financiacion.getModel().getColumnClass(columna).equals(JButton.class)) {
					InformacionProyectoFinanciacionView informacionProyectoFinanciacionView = new InformacionProyectoFinanciacionView(modeloTablaFinanciacion.getValueAt(fila, 1).toString());
					informacionProyectoFinanciacionView.setVisible(true);
				}
			}
		});

		return table_financiacion;
	}

	public void configFrame() {
		frame.setTitle("Principal");
		frame.setResizable(false);
		frame.setSize(1000, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);

		frame.getContentPane().setLayout(new BorderLayout(0, 0));
	}

	public void setVisible(Boolean visible) {
		frame.setVisible(visible);
	}

	public void crearTablas() {
		tablaVotacion = crearTablaVotacion();
		tablaFinanciacion = crearTablaFinanciacion();
		scrollVotacion.setViewportView(tablaVotacion);
		scrollFinanciacion.setViewportView(tablaFinanciacion);

		this.frame.revalidate();
	}
}
