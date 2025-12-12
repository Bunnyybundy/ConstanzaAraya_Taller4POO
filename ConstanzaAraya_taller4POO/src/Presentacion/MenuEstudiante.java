package Presentacion;

import javax.swing.*;
import java.awt.*;
import Dominio.*;
import Logica.Sistema;
import java.util.List;

public class MenuEstudiante extends JFrame {
    private Estudiante estudiante;

    public MenuEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
        setTitle("Menú Estudiante");
        setSize(900,600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JTabbedPane tabs = new JTabbedPane();
        tabs.addTab("Perfil", crearPanelPerfil());
        tabs.addTab("Malla Curricular", crearPanelMalla());
        tabs.addTab("Certificaciones", crearPanelCertificaciones());
        tabs.addTab("Progreso", crearPanelProgreso());

        add(tabs);
    }

    private JPanel crearPanelPerfil() {
        JPanel panel = new JPanel(new BorderLayout());
        JTextArea area = new JTextArea();
        area.setEditable(false);
        area.append("Perfil del Estudiante:\n");
        area.append("Nombre: " + estudiante.getNombre() + "\n");
        area.append("Rut: " + estudiante.getRut() + "\n");
        area.append("Carrera: " + estudiante.getCarrera() + "\n");
        area.append("Semestre: " + estudiante.getSemestre() + "\n");
        area.append("Correo: " + estudiante.getCorreoE() + "\n");
        panel.add(new JScrollPane(area), BorderLayout.CENTER);
        return panel;
    }

    private JPanel crearPanelMalla() {
        JPanel panel = new JPanel(new GridLayout(8, 6));
        List<Curso> cursos = Sistema.getCursos();
        for (Curso c : cursos) {
            JButton btnCurso = new JButton(c.getNombre());
            btnCurso.setBackground(Color.LIGHT_GRAY);
            btnCurso.addActionListener(e -> JOptionPane.showMessageDialog(this,
                "Curso: " + c.getNombre() + "\nNRC: " + c.getNrc()));
            panel.add(btnCurso);
        }
        return panel;
    }

    private JPanel crearPanelCertificaciones() {
        JPanel panel = new JPanel(new BorderLayout());
        DefaultListModel<String> modelo = new DefaultListModel<>();
        for (Certificacion cert : Sistema.getCertificaciones()) {
            modelo.addElement(cert.getNombre());
        }
        JList<String> lista = new JList<>(modelo);
        panel.add(new JScrollPane(lista), BorderLayout.CENTER);
        JButton btnInscribir = new JButton("Inscribirse");
        btnInscribir.addActionListener(e -> JOptionPane.showMessageDialog(this, "Validar inscripción aquí..."));
        panel.add(btnInscribir, BorderLayout.SOUTH);
        return panel;
    }

    private JPanel crearPanelProgreso() {
        JPanel panel = new JPanel(new GridLayout(0,1));
        JProgressBar barra = new JProgressBar(0,100);
        barra.setValue(50);
        barra.setStringPainted(true);
        panel.add(new JLabel("Certificación A"));
        panel.add(barra);
        return panel;
    }
}