package Presentacion;

import javax.swing.*;
import java.awt.*;
import Logica.Sistema;
import Dominio.*;

public class MenuCoordinador extends JFrame {
    public MenuCoordinador() {
        setTitle("Menú Coordinador");
        setSize(800,500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JTabbedPane tabs = new JTabbedPane();
        tabs.addTab("Certificaciones", crearPanelCertificaciones());
        tabs.addTab("Estadísticas", crearPanelEstadisticas());
        tabs.addTab("Estudiantes", crearPanelEstudiantes());

        add(tabs);
    }

    private JPanel crearPanelCertificaciones() {
        JPanel panel = new JPanel(new BorderLayout());
        DefaultListModel<String> modelo = new DefaultListModel<>();
        for (Certificacion cert : Sistema.getCertificaciones()) {
            modelo.addElement(cert.getNombre());
        }
        JList<String> lista = new JList<>(modelo);
        panel.add(new JScrollPane(lista), BorderLayout.CENTER);
        JButton btnModificar = new JButton("Modificar Certificación");
        panel.add(btnModificar, BorderLayout.SOUTH);
        return panel;
    }

    private JPanel crearPanelEstadisticas() {
        JPanel panel = new JPanel(new GridLayout(0,1));
        panel.add(new JLabel("Total certificaciones: " + Sistema.getCertificaciones().size()));
        panel.add(new JLabel("Total estudiantes: " + Sistema.getEstudiantes().size()));
        return panel;
    }

    private JPanel crearPanelEstudiantes() {
        JPanel panel = new JPanel(new BorderLayout());
        JTextField txtRut = new JTextField();
        JButton btnBuscar = new JButton("Buscar Estudiante");
        JTextArea resultado = new JTextArea();
        resultado.setEditable(false);

        btnBuscar.addActionListener(e -> {
            String rut = txtRut.getText();
            Estudiante est = Sistema.buscarEstudiantePorRut(rut);
            if (est != null) {
                resultado.setText("Nombre: " + est.getNombre() + "\nCarrera: " + est.getCarrera());
            } else {
                resultado.setText("Estudiante no encontrado.");
            }
        });

        panel.add(txtRut, BorderLayout.NORTH);
        panel.add(btnBuscar, BorderLayout.CENTER);
        panel.add(new JScrollPane(resultado), BorderLayout.SOUTH);
        return panel;
    }
}