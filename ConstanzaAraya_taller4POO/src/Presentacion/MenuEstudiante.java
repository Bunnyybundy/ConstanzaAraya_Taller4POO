package Presentacion;

import javax.swing.*;
import java.awt.*;
import Dominio.*;
import Logica.Sistema;
import java.util.List;
/**
 * Ventana del menú estudiante.
 * Permite visualizar el perfil, la malla curricular,
 * las certificaciones disponibles y el progreso.
 */
public class MenuEstudiante extends JFrame {
	private static final long serialVersionUID = 1L;

    private Estudiante estudiante;
    /**
     * Construye la ventana del menú estudiante con pestañas de información.
     * @param estudiante estudiante asociado a la sesión
     */
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
    /**
     * Panel que muestra el perfil del estudiante.
     * @return panel con datos personales
     */
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
    /**
     * Panel que muestra la malla curricular con los cursos disponibles.
     * @return panel de malla curricular
     */
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
    /**
     * Panel que lista las certificaciones y permite inscribirse.
     * @return panel de certificaciones
     */
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
    /**
     * Panel que muestra el progreso del estudiante en certificaciones.
     * @return panel de progreso
     */
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