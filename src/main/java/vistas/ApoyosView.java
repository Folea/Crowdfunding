package vistas;

import controlador.Controlador;
import model.Apoyo;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Created by foleac on 7/13/2015.
 */

public class ApoyosView {
    private JFrame frame;
    private JTable tablaApoyos;
    private JScrollPane scrollApoyos;
    private JButton botonInfoProyecto;

    public ApoyosView() {
        frame = new JFrame();

        configFrame();

        tablaApoyos = crearTablaApoyos();
        scrollApoyos = new JScrollPane(
                tablaApoyos,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        frame.add(scrollApoyos);

        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new FlowLayout(FlowLayout.RIGHT));
        frame.add(panelBotones);
        botonInfoProyecto = new JButton("Informacion proyecto");
        panelBotones.add(botonInfoProyecto);
        botonInfoProyecto.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                InformacionProyectoApoyoView informacionProyectoApoyoView = new InformacionProyectoApoyoView(tablaApoyos.getValueAt(tablaApoyos.getSelectedRow(), 0).toString());
                informacionProyectoApoyoView.setVisible(true);
            }
        });
    }

    private JTable crearTablaApoyos() {

        // Nombres columnas
        String[] columnas = new String[]{"Titulo proyecto", "Titulo recompensa", "Cantidad financiada"};

        // Tipos de datos columnas
        final Class[] tiposColumnas = new Class[]{String.class, String.class, String.class};

        DefaultTableModel modeloTablaApoyos = new DefaultTableModel(new Object[][]{}, columnas) {

            public Class getColumnClass(int columnIndex) {
                return tiposColumnas[columnIndex];

            }

            boolean[] columnEditables = new boolean[]{false, false, false};

            public boolean isCellEditable(int row, int column) {
                return columnEditables[column];
            }
        };


        final List<Apoyo> apoyos = Controlador.getUnicaInstancia().getApoyosUsuarioConectado();

        for (Apoyo a : apoyos) {
            modeloTablaApoyos.addRow(new Object[]{a.getRecompensa().getProyecto().getNombre(), a.getRecompensa().getNombre(),
                    a.getRecompensa().getProyecto().getEstado()});
        }

        final JTable tableApoyos = new JTable();
        tableApoyos.setBackground(Color.WHITE);
        tableApoyos.setModel(modeloTablaApoyos);
        tableApoyos.setDefaultRenderer(JButton.class, new TableCellRenderer() {

            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                           boolean hasFocus, int row, int column) {
                return (Component) value;
            }
        });

        return tableApoyos;
    }

    private void configFrame() {
        frame.setTitle("Apoyos");
        frame.setResizable(false);
        frame.setSize(900, 400);

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

    }

    public void setVisible(Boolean visible) {
        frame.setVisible(visible);
    }
}

