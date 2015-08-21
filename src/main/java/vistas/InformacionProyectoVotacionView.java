package vistas;

import com.toedter.calendar.JDateChooser;
import controlador.Controlador;
import exceptions.MismoUsuarioException;
import exceptions.ProyectoInexistenteException;
import exceptions.ProyectoVotadoException;
import model.Proyecto;
import model.Recompensa;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class InformacionProyectoVotacionView {

	private JFrame frame;
    private JTextField textFieldTitulo;
    private JTextArea textAreaDescripcion;
    private JTextField textFieldCategoria;
    private JTextField textFieldImporte;
    private JDateChooser dateChooserFechaFinalizacion;
    private JTextField textFieldDiasRestantes;
    private JTextField textFieldNumeroVotos;
    private JList listRecompensas;
    private DefaultListModel<String> modeloListaRecompensa;
    private JButton botonVotar;
    private JScrollPane scrollPaneDescripcion;
    private JScrollPane scrollPaneListRcompensas;
	
	public InformacionProyectoVotacionView(String nombreProyecto) {
		frame = new JFrame();

        Proyecto proyecto1 = null;
        try {
            proyecto1 = Controlador.getUnicaInstancia().getProyecto(nombreProyecto);
        } catch (ProyectoInexistenteException e) {
            JOptionPane.showMessageDialog(frame,
                    "El proyecto " + nombreProyecto + " no existe.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }

        final Proyecto proyecto = proyecto1;

        configFrame();

        JPanel panelGeneral = new JPanel();
        panelGeneral.setLayout(new BoxLayout(panelGeneral, BoxLayout.X_AXIS));
        frame.add(panelGeneral);

        //Panel izquierda
        JPanel panelInformacion = new JPanel();
        panelInformacion.setLayout(new BoxLayout(panelInformacion, BoxLayout.Y_AXIS));
        panelGeneral.add(panelInformacion);

        JPanel panelTitulo = new JPanel();
        panelTitulo.setLayout(new FlowLayout());
        panelInformacion.add(panelTitulo);
        JLabel labelTitulo = new JLabel("Titulo");
        labelTitulo.setPreferredSize(new Dimension(90, 25));
        panelTitulo.add(labelTitulo);
        textFieldTitulo = new JTextField();
        textFieldTitulo.setPreferredSize(new Dimension(150, 25));
        textFieldTitulo.setEditable(false);
        textFieldTitulo.setText(proyecto.getNombre());
        panelTitulo.add(textFieldTitulo);

        JPanel panelDescripcion = new JPanel();
        panelDescripcion.setLayout(new FlowLayout());
        panelInformacion.add(panelDescripcion);
        JLabel labelDescripcion = new JLabel("Descripcion");
        labelDescripcion.setPreferredSize(new Dimension(90, 25));
        panelDescripcion.add(labelDescripcion);
        textAreaDescripcion = new JTextArea();
        textAreaDescripcion.setEditable(false);
        textAreaDescripcion.setLineWrap(true);
        textAreaDescripcion.setWrapStyleWord(true);
        textAreaDescripcion.setText(proyecto.getDescripcion());
        scrollPaneDescripcion = new JScrollPane(
                textAreaDescripcion,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPaneDescripcion.setPreferredSize(new Dimension(150, 100));
        panelDescripcion.add(scrollPaneDescripcion);

        JPanel panelCategoria = new JPanel();
        panelCategoria.setLayout(new FlowLayout());
        panelInformacion.add(panelCategoria);
        JLabel labelCategoria = new JLabel("Categoria");
        labelCategoria.setPreferredSize(new Dimension(90, 25));
        panelCategoria.add(labelCategoria);
        textFieldCategoria = new JTextField();
        textFieldCategoria.setPreferredSize(new Dimension(150, 25));
        textFieldCategoria.setEditable(false);
        textFieldCategoria.setText(proyecto.getCategoria().getName());
        panelCategoria.add(textFieldCategoria);

        JPanel panelImporte = new JPanel();
        panelImporte.setLayout(new FlowLayout());
        panelInformacion.add(panelImporte);
        JLabel labelImporte = new JLabel("Importe");
        labelImporte.setPreferredSize(new Dimension(90, 25));
        panelImporte.add(labelImporte);
        textFieldImporte = new JTextField();
        textFieldImporte.setPreferredSize(new Dimension(150, 25));
        textFieldImporte.setEditable(false);
        textFieldImporte.setText(String.valueOf(proyecto.getFinancionEsperada()));
        panelImporte.add(textFieldImporte);

        JPanel panelFechaFinalizacion = new JPanel();
        panelFechaFinalizacion.setLayout(new FlowLayout());
        panelInformacion.add(panelFechaFinalizacion);
        JLabel labelFechaFinalizacion = new JLabel("Fecha finalizacion");
        labelFechaFinalizacion.setPreferredSize(new Dimension(90, 25));
        panelFechaFinalizacion.add(labelFechaFinalizacion);
        dateChooserFechaFinalizacion = new JDateChooser();
        dateChooserFechaFinalizacion.setPreferredSize(new Dimension(150, 25));
        dateChooserFechaFinalizacion.setEnabled(false);
        dateChooserFechaFinalizacion.setDate(proyecto.getFechaFinalizacion());
        panelFechaFinalizacion.add(dateChooserFechaFinalizacion);

        JPanel panelDiasRestantes = new JPanel();
        panelDiasRestantes.setLayout(new FlowLayout());
        panelInformacion.add(panelDiasRestantes);
        JLabel labelDiasRestantes = new JLabel("Dias restantes");
        labelDiasRestantes.setPreferredSize(new Dimension(90, 25));
        panelDiasRestantes.add(labelDiasRestantes);
        textFieldDiasRestantes = new JTextField();
        textFieldDiasRestantes.setPreferredSize(new Dimension(150, 25));
        textFieldDiasRestantes.setEditable(false);
        textFieldDiasRestantes.setText(String.valueOf(proyecto.getDiasRestantes()));
        panelDiasRestantes.add(textFieldDiasRestantes);

        JPanel panelVotos = new JPanel();
        panelVotos.setLayout(new FlowLayout());
        panelInformacion.add(panelVotos);
        JLabel labelVotos = new JLabel("Numero votos");
        labelVotos.setPreferredSize(new Dimension(90, 25));
        panelVotos.add(labelVotos);
        textFieldNumeroVotos = new JTextField();
        textFieldNumeroVotos.setPreferredSize(new Dimension(150, 25));
        textFieldNumeroVotos.setEditable(false);
        textFieldNumeroVotos.setText(String.valueOf(proyecto.getCuentaVotos()));
        panelVotos.add(textFieldNumeroVotos);


        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new BorderLayout(0, 0));
        frame.add(panelBotones);
        botonVotar = new JButton("Votar");
        panelBotones.add(botonVotar, BorderLayout.EAST);
        botonVotar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Controlador.getUnicaInstancia().votarProyecto(proyecto);
                    frame.dispose();
                } catch (ProyectoVotadoException e1) {
                    JOptionPane.showMessageDialog(frame,
                            "Ya has votado este proyecto",
                            "Informacion", JOptionPane.INFORMATION_MESSAGE);
                } catch (MismoUsuarioException e1) {
                    JOptionPane.showMessageDialog(frame,
                            "No puedes votar un proyecto creado por ti",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        //Panel Derecha
        JPanel panelDerecha = new JPanel();
        panelDerecha.setLayout(new BoxLayout(panelDerecha, BoxLayout.Y_AXIS));
        panelGeneral.add(panelDerecha);

        JPanel panelTextRecompensas = new JPanel();
        panelTextRecompensas.setLayout(new FlowLayout());
        panelDerecha.add(panelTextRecompensas);
        JLabel labelRecompensas = new JLabel("Recompensas");
        panelTextRecompensas.add(labelRecompensas);

        JPanel panelListaRecompensas = new JPanel();
        panelDerecha.add(panelListaRecompensas);
        listRecompensas = new JList();
        modeloListaRecompensa = new DefaultListModel<String>(){};
        listRecompensas.setModel(modeloListaRecompensa);
        scrollPaneListRcompensas = new JScrollPane(
                listRecompensas,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPaneListRcompensas.setPreferredSize(new Dimension(250, 300));
        panelListaRecompensas.add(scrollPaneListRcompensas);

        for (Recompensa r : proyecto.getRecompensas()) {
            modeloListaRecompensa.addElement(r.getNombre());
        }

	}

    private void configFrame() {
        frame.setTitle("Informacion de proyecto en votacion");
        frame.setResizable(false);
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
    }
    
    public void setVisible(Boolean visible) {
    	frame.setVisible(visible);
    }

}
