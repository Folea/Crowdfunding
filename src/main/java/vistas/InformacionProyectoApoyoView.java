package vistas;

import com.toedter.calendar.JDateChooser;
import controlador.Controlador;
import exceptions.ProyectoInexistenteException;
import model.Proyecto;
import model.Recompensa;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

/**
 * Created by foleac on 7/15/2015.
 */
public class InformacionProyectoApoyoView {

    private JTextField textFieldImporte;
    private JFrame frame;
    private JTextField textFieldTitulo;
    private JTextArea textAreaDescripcion;
    private JTextField textFieldCategoria;
    private JDateChooser dateChooserFechaFin;
    private JTextField textFieldDiasRestantes;
    private JTextField textFieldFinanciacion;
    private JTextField textFieldPorcentaje;
    private JTable tablaRecompensas;
    private JScrollPane scrollRecompensas;
    private JScrollPane scrollTextAreaDescripcion;

    public InformacionProyectoApoyoView(String nombreProyecto) {

        frame = new JFrame();
        Proyecto proyecto = null;
        try {
            proyecto = Controlador.getUnicaInstancia().getProyecto(nombreProyecto);
        } catch (ProyectoInexistenteException e) {
            e.printStackTrace();
        }

        configFrame();

        //Panel izquierda
        JPanel panelInformacion = new JPanel();
        panelInformacion.setLayout(new BoxLayout(panelInformacion, BoxLayout.Y_AXIS));
        frame.add(panelInformacion);

        JPanel panelTitulo = new JPanel();
        panelTitulo.setLayout(new FlowLayout());
        panelInformacion.add(panelTitulo);
        JLabel labelTitulo = new JLabel("Titulo");
        labelTitulo.setPreferredSize(new Dimension(130, 25));
        panelTitulo.add(labelTitulo);
        textFieldTitulo = new JTextField();
        textFieldTitulo.setPreferredSize(new Dimension(200, 25));
        textFieldTitulo.setEditable(false);
        textFieldTitulo.setText(proyecto.getNombre());
        panelTitulo.add(textFieldTitulo);

        JPanel panelDescripcion = new JPanel();
        panelDescripcion.setLayout(new FlowLayout());
        panelInformacion.add(panelDescripcion);
        JLabel labelDescripcion = new JLabel("Descripcion");
        labelDescripcion.setPreferredSize(new Dimension(130, 25));
        panelDescripcion.add(labelDescripcion);
        textAreaDescripcion = new JTextArea();
        textAreaDescripcion.setEditable(false);
        textAreaDescripcion.setText(proyecto.getDescripcion());
        scrollTextAreaDescripcion = new JScrollPane(
                textAreaDescripcion,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollTextAreaDescripcion.setPreferredSize(new Dimension(200, 120));
        panelDescripcion.add(scrollTextAreaDescripcion);

        JPanel panelCategoria = new JPanel();
        panelCategoria.setLayout(new FlowLayout());
        panelInformacion.add(panelCategoria);
        JLabel labelCategoria = new JLabel("Categoria");
        labelCategoria.setPreferredSize(new Dimension(130, 25));
        panelCategoria.add(labelCategoria);
        textFieldCategoria = new JTextField();
        textFieldCategoria.setPreferredSize(new Dimension(200, 25));
        textFieldCategoria.setEditable(false);
        textFieldCategoria.setText(proyecto.getCategoria().getName());
        panelCategoria.add(textFieldCategoria);

        JPanel panelImporte = new JPanel();
        panelImporte.setLayout(new FlowLayout());
        panelInformacion.add(panelImporte);
        JLabel labelImporte = new JLabel("Importe");
        labelImporte.setPreferredSize(new Dimension(130, 25));
        panelImporte.add(labelImporte);
        textFieldImporte = new JTextField();
        textFieldImporte.setPreferredSize(new Dimension(200, 25));
        textFieldImporte.setEditable(false);
        textFieldImporte.setText(String.valueOf(proyecto.getFinancionEsperada()));
        panelImporte.add(textFieldImporte);

        JPanel panelFechaFin = new JPanel();
        panelFechaFin.setLayout(new FlowLayout());
        panelInformacion.add(panelFechaFin);
        JLabel labelFechaFin = new JLabel("Fecha finalizacion");
        labelFechaFin.setPreferredSize(new Dimension(130, 25));
        panelFechaFin.add(labelFechaFin);
        dateChooserFechaFin = new JDateChooser();
        dateChooserFechaFin.setPreferredSize(new Dimension(200, 25));
        dateChooserFechaFin.setEnabled(false);
        dateChooserFechaFin.setDate(proyecto.getFechaFinalizacion());
        panelFechaFin.add(dateChooserFechaFin);

        JPanel panelDiasRestantes = new JPanel();
        panelDiasRestantes.setLayout(new FlowLayout());
        panelInformacion.add(panelDiasRestantes);
        JLabel labelDiasRestantes = new JLabel("Dias restantes");
        labelDiasRestantes.setPreferredSize(new Dimension(130, 25));
        panelDiasRestantes.add(labelDiasRestantes);
        textFieldDiasRestantes = new JTextField();
        textFieldDiasRestantes.setPreferredSize(new Dimension(200, 25));
        textFieldDiasRestantes.setEditable(false);
        textFieldDiasRestantes.setText(String.valueOf(proyecto.getDiasRestantes()));
        panelDiasRestantes.add(textFieldDiasRestantes);

        JPanel panelFinanciacionRecibida = new JPanel();
        panelFinanciacionRecibida.setLayout(new FlowLayout());
        panelInformacion.add(panelFinanciacionRecibida);
        JLabel labelFinanciacionRecibida = new JLabel("Financiacion");
        labelFinanciacionRecibida.setPreferredSize(new Dimension(130, 25));
        panelFinanciacionRecibida.add(labelFinanciacionRecibida);
        textFieldFinanciacion = new JTextField();
        textFieldFinanciacion.setPreferredSize(new Dimension(200, 25));
        textFieldFinanciacion.setEditable(false);
        textFieldFinanciacion.setText(String.valueOf(proyecto.getFinanciacion()));
        panelFinanciacionRecibida.add(textFieldFinanciacion);

        JPanel panelPorcentaje = new JPanel();
        panelPorcentaje.setLayout(new FlowLayout());
        panelInformacion.add(panelPorcentaje);
        JLabel labelPorcentaje = new JLabel("Porcentaje");
        labelPorcentaje.setPreferredSize(new Dimension(130, 25));
        panelPorcentaje.add(labelPorcentaje);
        textFieldPorcentaje = new JTextField();
        textFieldPorcentaje.setPreferredSize(new Dimension(200, 25));
        textFieldPorcentaje.setEditable(false);
        textFieldPorcentaje.setText(String.valueOf(proyecto.getPorcentajeFinanciado()));
        panelPorcentaje.add(textFieldPorcentaje);

        //Panel Derecha
        JPanel panelGeneralDerecha = new JPanel();
        panelGeneralDerecha.setLayout(new BoxLayout(panelGeneralDerecha, BoxLayout.Y_AXIS));
        frame.add(panelGeneralDerecha);

        JPanel panelTextoRecompensa = new JPanel();
        panelTextoRecompensa.setLayout(new FlowLayout());
        panelGeneralDerecha.add(panelTextoRecompensa);
        JLabel labelTextoRecompensa = new JLabel("Recompensas");
        panelTextoRecompensa.add(labelTextoRecompensa);


        JPanel panelRecompensas = new JPanel();
        panelRecompensas.setLayout(new FlowLayout());
        panelGeneralDerecha.add(panelRecompensas);
        tablaRecompensas = crearTablaRecompensas();
        scrollRecompensas = new JScrollPane(
                tablaRecompensas,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        panelRecompensas.add(scrollRecompensas);
        scrollRecompensas.setViewportView(tablaRecompensas);
        scrollRecompensas.setPreferredSize(new Dimension(420, 340));
    }

    private void configFrame() {
        frame.setTitle("Informacion de proyecto en financiacion");
        frame.setResizable(false);
        frame.setSize(800, 400);

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.X_AXIS));
    }

    public void setVisible(Boolean visible) {
        frame.setVisible(true);
    }

    private JTable crearTablaRecompensas() {

        // Nombres columnas
        String[] columnas = new String[]{"Titulo", "Descripcion"};

        // Tipos de datos columnas
        final Class[] tiposColumnas = new Class[]{java.lang.String.class, java.lang.String.class};

        final DefaultTableModel modeloTablaRecompensas = new DefaultTableModel(new Object[][]{}, columnas) {

            public Class getColumnClass(int columnIndex) {
                return tiposColumnas[columnIndex];

            }

            boolean[] columnEditables = new boolean[]{false, false};

            public boolean isCellEditable(int row, int column) {
                return columnEditables[column];
            }
        };


        final java.util.List<Recompensa> recompensas = Controlador.getUnicaInstancia().getRecompensasParaProyecto(textFieldTitulo.getText());

        for (Recompensa r : recompensas) {
            modeloTablaRecompensas.addRow(new Object[]{r.getNombre(), r.getDescripcion()});
        }

        final JTable tableRecompensas = new JTable();
        tableRecompensas.setBackground(Color.WHITE);
        tableRecompensas.setModel(modeloTablaRecompensas);
        tableRecompensas.setDefaultRenderer(JButton.class, new TableCellRenderer() {

            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                           boolean hasFocus, int row, int column) {
                return (Component) value;
            }
        });

        return tableRecompensas;
    }
}
