package vistas;

import controlador.Controlador;
import exceptions.ProyectoInexistenteException;
import model.Proyecto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by foleac on 7/13/2015.
 */
public class EnviarPreguntaView {

    private JFrame frame;
//    private JTextField textFieldTitulo;
    private JTextField textFieldAsunto;
    private JTextField textFieldProyecto;
    private JTextArea textAreaContenido;
    private JButton botonEnviar;
    private JScrollPane scrollPaneContenido;

    public EnviarPreguntaView() {
        frame = new JFrame();

        configFrame();

        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));
        frame.add(panelPrincipal);

        JPanel panelAsunto = new JPanel();
        panelAsunto.setLayout(new FlowLayout());
        panelPrincipal.add(panelAsunto);
        JLabel labelAsunto = new JLabel("Asunto");
        labelAsunto.setPreferredSize(new Dimension(100, 25));
        panelAsunto.add(labelAsunto);
        textFieldAsunto =  new JTextField();
        textFieldAsunto.setPreferredSize(new Dimension(300, 25));
        panelAsunto.add(textFieldAsunto);

        JPanel panelProyecto = new JPanel();
        panelProyecto.setLayout(new FlowLayout());
        panelPrincipal.add(panelProyecto);
        JLabel labelProyecto = new JLabel("Nombre proyecto");
        labelProyecto.setPreferredSize(new Dimension(100, 25));
        panelProyecto.add(labelProyecto);
        textFieldProyecto = new JTextField();
        textFieldProyecto.setPreferredSize(new Dimension(300, 25));
        panelProyecto.add(textFieldProyecto);

        JPanel panelContenido = new JPanel();
        panelContenido.setLayout(new FlowLayout());
        panelPrincipal.add(panelContenido);
        JLabel labelContenido = new JLabel("Contenido");
        labelContenido.setPreferredSize(new Dimension(100, 25));
        panelContenido.add(labelContenido);
        textAreaContenido = new JTextArea();
        textAreaContenido.setLineWrap(true);
        textAreaContenido.setWrapStyleWord(true);
        scrollPaneContenido = new JScrollPane(
                textAreaContenido,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPaneContenido.setPreferredSize(new Dimension(300, 100));
        panelContenido.add(scrollPaneContenido);

        JPanel panelBotonEnviar = new JPanel();
        panelBotonEnviar.setLayout(new FlowLayout());
        panelPrincipal.add(panelBotonEnviar);
        botonEnviar = new JButton("Enviar");
        panelBotonEnviar.add(botonEnviar, FlowLayout.LEFT);
        botonEnviar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try{
                    Proyecto proyecto = Controlador.getUnicaInstancia().getProyecto(textFieldProyecto.getText());
                    Controlador.getUnicaInstancia().enviarMensaje(textAreaContenido.getText(), textFieldAsunto.getText(),
                            proyecto);
                    MensajesView mensajesView = new MensajesView();
                    mensajesView.setVisible(true);
                    frame.dispose();
                } catch(ProyectoInexistenteException ex) {
                    JOptionPane.showMessageDialog(frame,
                            "El proyecto " + textFieldProyecto.getText()  + " no existe.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    public void setVisible(Boolean visible) {
        frame.setVisible(visible);
    }

    public void configFrame() {
        frame.setTitle("Enviar mensaje");
        frame.setResizable(false);
        frame.setSize(450, 300);
    }
}
