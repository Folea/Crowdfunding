package vistas;

import controlador.Controlador;
import model.Notificacion;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.util.List;
/**
 * Created by foleac on 7/13/2015.
 */
public class NotificacionesView {

    private JFrame frame;
    private JTable tablaNotificaciones;
    private JScrollPane scrollNotificaciones;

    public NotificacionesView() {
        frame = new JFrame();

        configFrame();

        tablaNotificaciones = crearTablaNotificaciones();
        scrollNotificaciones = new JScrollPane(
                tablaNotificaciones,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        frame.add(scrollNotificaciones);
    }

    private JTable crearTablaNotificaciones() {

        // Nombres columnas
        String[] columnas = new String[]{"Titulo", "Descripcion", "Estado proyecto"};

        // Tipos de datos columnas
        final Class[] tiposColumnas = new Class[]{String.class, String.class, String.class};

        DefaultTableModel modeloTablaNotificaciones = new DefaultTableModel(new Object[][]{}, columnas) {

            public Class getColumnClass(int columnIndex) {
                return tiposColumnas[columnIndex];

            }

            boolean[] columnEditables = new boolean[]{false, false, false};

            public boolean isCellEditable(int row, int column) {
                return columnEditables[column];
            }
        };


        final List<Notificacion> notificaciones = Controlador.getUnicaInstancia().getNotificaciones();

        for (Notificacion n : notificaciones) {
            modeloTablaNotificaciones.addRow(new Object[]{n.getProyecto().getNombre(), n.getDescripcion(), n.getProyecto().getEstado()});
        }

        final JTable tableNotificaciones = new JTable();
        tableNotificaciones.setBackground(Color.WHITE);
        tableNotificaciones.setModel(modeloTablaNotificaciones);
        tableNotificaciones.setDefaultRenderer(JButton.class, new TableCellRenderer() {

            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                           boolean hasFocus, int row, int column) {
                return (Component) value;
            }
        });

        return tableNotificaciones;
    }

    private void configFrame() {
        frame.setTitle("Notificaciones");
        frame.setResizable(false);
        frame.setSize(900, 400);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);

    }

    public void setVisible(Boolean visible) {
        frame.setVisible(visible);
    }
}
