package vistas;

import controlador.Controlador;
import model.Mensaje;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by foleac on 7/13/2015.
 */
public class MensajesView {

    private JFrame frame;
    private JList listaMensajes;
    private JList listMensajesRecibidos;
    private JTextArea textAreaTextEnviado;
    private JTextArea textAreaTextEnviado2;
    private JTextArea textAreaTextRespuesta;
    private JTextArea textAreaTextRespuesta2;
    private JButton botonNuevaPregunta;
    private JButton botonEnviarRespuesta;
    private JScrollPane scrollPaneListMensajes;
    private JScrollPane scrollPaneListMensajesRecibidos;
    private JScrollPane scrollPaneEnviado;
    private JScrollPane scrollPaneEnviado2;
    private JScrollPane scrollPaneRespuesta;
    private JScrollPane scrollPaneRespuesta2;
    private DefaultListModel<String> modeloListMensajesEnviados;
    private DefaultListModel<String> modeloListMensajesRecibidos;

    public MensajesView() {
        frame = new JFrame();

        configFrame();

        //Pestanas
        JTabbedPane pestanas = new JTabbedPane(JTabbedPane.TOP);
        frame.getContentPane().add(pestanas);

        //Pestana Enviados
        JPanel panelEnviados = new JPanel();
        pestanas.addTab("Enviados", null, panelEnviados, null);
        panelEnviados.setLayout(new BoxLayout(panelEnviados, BoxLayout.Y_AXIS));

        JPanel panelInformacionEnviados = new JPanel();
        panelInformacionEnviados.setLayout(new BoxLayout(panelInformacionEnviados, BoxLayout.X_AXIS));
        panelEnviados.add(panelInformacionEnviados);

        JPanel panelLista = new JPanel();
        panelLista.setLayout(new FlowLayout());
        panelInformacionEnviados.add(panelLista);
        listaMensajes = new JList();
        modeloListMensajesEnviados = new DefaultListModel<String>(){};
        listaMensajes.setModel(modeloListMensajesEnviados);
        scrollPaneListMensajes = new JScrollPane(
                listaMensajes,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPaneListMensajes.setPreferredSize(new Dimension(290, 343));
        panelLista.add(scrollPaneListMensajes);
        listaMensajes.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                Mensaje mensaje = Controlador.getUnicaInstancia().getMensajeEnviadoPorAsunto(modeloListMensajesEnviados.getElementAt(listaMensajes.getSelectedIndex()));
                textAreaTextEnviado.setText(mensaje.getCuerpo());
                textAreaTextRespuesta.setText(mensaje.getRespuesta());
            }
        });

        JPanel panelTextoRespuestas = new JPanel();
        panelTextoRespuestas.setLayout(new BoxLayout(panelTextoRespuestas, BoxLayout.Y_AXIS));
        panelInformacionEnviados.add(panelTextoRespuestas);

        JLabel labelTexto = new JLabel("Texto");
        panelTextoRespuestas.add(labelTexto);

        JPanel panelTextoEnviado = new JPanel();
        panelTextoEnviado.setLayout(new FlowLayout());
        panelTextoRespuestas.add(panelTextoEnviado);
        textAreaTextEnviado = new JTextArea();
        textAreaTextEnviado.setLineWrap(true);
        textAreaTextEnviado.setWrapStyleWord(true);
        textAreaTextEnviado.setEditable(false);
        scrollPaneEnviado = new JScrollPane(
                textAreaTextEnviado,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPaneEnviado.setPreferredSize(new Dimension(290, 150));
        panelTextoEnviado.add(scrollPaneEnviado);

        JLabel labelRespuestas = new JLabel("Respuesta");
        panelTextoRespuestas.add(labelRespuestas);

        JPanel panelTextoRespuestasE = new JPanel();
        panelTextoRespuestasE.setLayout(new FlowLayout());
        panelTextoRespuestas.add(panelTextoRespuestasE);
        textAreaTextRespuesta = new JTextArea();
        textAreaTextRespuesta.setLineWrap(true);
        textAreaTextRespuesta.setWrapStyleWord(true);
        textAreaTextRespuesta.setEditable(false);
        scrollPaneRespuesta = new JScrollPane(
                textAreaTextRespuesta,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPaneRespuesta.setPreferredSize(new Dimension(290, 150));
        panelTextoRespuestasE.add(scrollPaneRespuesta);

        JPanel panelBotonEnviar = new JPanel();
        panelBotonEnviar.setLayout(new FlowLayout());
        panelEnviados.add(panelBotonEnviar);
        botonNuevaPregunta = new JButton("Nueva pregunta");
        panelBotonEnviar.add(botonNuevaPregunta, FlowLayout.LEFT);
        botonNuevaPregunta.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                EnviarPreguntaView enviarPreguntaView = new EnviarPreguntaView();
                enviarPreguntaView.setVisible(true);
                frame.dispose();
            }
        });

        //Pestana Respuestas
        JPanel panelRecibidos = new JPanel();
        pestanas.addTab("Recibidos", null, panelRecibidos, null);
        panelRecibidos.setLayout(new BoxLayout(panelRecibidos, BoxLayout.Y_AXIS));

        JPanel panelInformacionEnviados2 = new JPanel();
        panelInformacionEnviados2.setLayout(new BoxLayout(panelInformacionEnviados2, BoxLayout.X_AXIS));
        panelRecibidos.add(panelInformacionEnviados2);

        JPanel panelLista2 = new JPanel();
        panelLista2.setLayout(new FlowLayout());
        panelInformacionEnviados2.add(panelLista2);
        listMensajesRecibidos = new JList();
        modeloListMensajesRecibidos = new DefaultListModel<String>(){};
        listMensajesRecibidos.setModel(modeloListMensajesRecibidos);
        scrollPaneListMensajesRecibidos = new JScrollPane(
                listMensajesRecibidos,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPaneListMensajesRecibidos.setPreferredSize(new Dimension(290, 343));
        panelLista2.add(scrollPaneListMensajesRecibidos);
        listMensajesRecibidos.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                Mensaje mensaje = Controlador.getUnicaInstancia().getMensajeRecibidoPorAsunto(modeloListMensajesRecibidos.getElementAt(listMensajesRecibidos.getSelectedIndex()));
                textAreaTextEnviado2.setText(mensaje.getCuerpo());
                textAreaTextRespuesta2.setText(mensaje.getRespuesta());
                if(mensaje.getRespuesta() == null) {
                    botonEnviarRespuesta.setEnabled(true);
                    textAreaTextRespuesta2.setEditable(true);
                }
                else {
                    botonEnviarRespuesta.setEnabled(false);
                    textAreaTextRespuesta2.setEditable(false);
                }
            }
        });

        JPanel panelTextoRespuestas2 = new JPanel();
        panelTextoRespuestas2.setLayout(new BoxLayout(panelTextoRespuestas2, BoxLayout.Y_AXIS));
        panelInformacionEnviados2.add(panelTextoRespuestas2);

        JLabel labelTexto2 = new JLabel("Texto");
        panelTextoRespuestas2.add(labelTexto2);

        JPanel panelTextoEnviado2 = new JPanel();
        panelTextoEnviado2.setLayout(new FlowLayout());
        panelTextoRespuestas2.add(panelTextoEnviado2);
        textAreaTextEnviado2 = new JTextArea();
        textAreaTextEnviado2.setLineWrap(true);
        textAreaTextEnviado2.setWrapStyleWord(true);
        textAreaTextEnviado2.setEditable(false);
        scrollPaneEnviado2  = new JScrollPane(
                textAreaTextEnviado2,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPaneEnviado2.setPreferredSize(new Dimension(290, 150));
        panelTextoEnviado2.add(scrollPaneEnviado2);

        JLabel labelRespuestas2 = new JLabel("Respuesta");
        panelTextoRespuestas2.add(labelRespuestas2);

        JPanel panelTextoRespuestasE2 = new JPanel();
        panelTextoRespuestasE2.setLayout(new FlowLayout());
        panelTextoRespuestas2.add(panelTextoRespuestasE2);
        textAreaTextRespuesta2 = new JTextArea();
        textAreaTextRespuesta2.setLineWrap(true);
        textAreaTextRespuesta2.setWrapStyleWord(true);
        scrollPaneRespuesta2 = new JScrollPane(
                textAreaTextRespuesta2,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPaneRespuesta2.setPreferredSize(new Dimension(290, 150));
        panelTextoRespuestasE2.add(scrollPaneRespuesta2);

        JPanel panelBotonEnviarRespuesta = new JPanel();
        panelBotonEnviarRespuesta.setLayout(new FlowLayout());
        panelRecibidos.add(panelBotonEnviarRespuesta);
        botonEnviarRespuesta = new JButton("Enviar respuesta");
        panelBotonEnviarRespuesta.add(botonEnviarRespuesta, FlowLayout.LEFT);
        botonEnviarRespuesta.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Mensaje mensaje = Controlador.getUnicaInstancia().getMensajeRecibidoPorAsunto(modeloListMensajesRecibidos.getElementAt(listMensajesRecibidos.getSelectedIndex()));
                mensaje.setRespuesta(textAreaTextRespuesta2.getText());
                Controlador.getUnicaInstancia().actualizarMensaje(mensaje);
                JOptionPane.showMessageDialog(frame,
                        "Respuesta enviada correctamente.",
                        "Informacion", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        for(Mensaje m : Controlador.getUnicaInstancia().getMensajesEnviados()) {
            modeloListMensajesEnviados.addElement(m.getAsunto());
        }

        for(Mensaje m : Controlador.getUnicaInstancia().getMensajesRecibidos()) {
            modeloListMensajesRecibidos.addElement(m.getAsunto());
        }
    }

    public void configFrame() {
        frame.setTitle("Mensajes");
        frame.setResizable(false);
        frame.setSize(600, 450);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
    }

    public void setVisible(Boolean visible) {
        frame.setVisible(visible);
    }
}
