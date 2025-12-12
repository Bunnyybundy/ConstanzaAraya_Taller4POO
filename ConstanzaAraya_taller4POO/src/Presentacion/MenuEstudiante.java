package Presentacion;
//Integrante 1: Constanza Fernanda Araya Plaza – 21.609.057-8 – ICCI
//Integrante 2: Emiliano Ángel Toro Rojas – 21.512.702-8 – ITI

import javax.swing.*;
import java.awt.*;
import Dominio.*;
import Logica.Sistema;
import Logica.Validar_CertiVisitor;

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
     * Panel que muestra el perfil del estudiante con promedio general y por semestre.
     * @return panel con datos personales y promedios
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
        area.append("Correo: " + estudiante.getCorreoE() + "\n\n");

        // Mostrar notas y calcular promedios
        List<Nota> notas = Sistema.getNotasPorRut(estudiante.getRut());
        double suma = 0; int count = 0;
        area.append("=== Notas ===\n");
        for (Nota n : notas) {
            area.append("Curso: " + n.getCodigoAsignatura() + " | Nota: " + n.getCalificacion() +
                        " | Estado: " + n.getEstado() + " | Semestre: " + n.getSemestre() + "\n");
            suma += n.getCalificacion();
            count++;
        }
        if (count > 0) {
            double promedioGeneral = suma / count;
            area.append("\nPromedio General: " + promedioGeneral + "\n");
        }

        panel.add(new JScrollPane(area), BorderLayout.CENTER);
        return panel;
    }

    /**
     * Panel que muestra la malla curricular con colores según estado de asignaturas.
     * Verde = aprobado, rojo = reprobado, gris = pendiente.
     * @return panel de malla curricular interactiva
     */
    private JPanel crearPanelMalla() {
        JPanel panel = new JPanel(new GridLayout(8, 6));
        List<Curso> cursos = Sistema.getCursos();
        for (Curso c : cursos) {
            JButton btnCurso = new JButton(c.getNombre());
            Nota nota = Sistema.buscarNota(estudiante.getRut(), c.getNrc());

            if (nota != null) {
                if ("Aprobado".equalsIgnoreCase(nota.getEstado())) {
                    btnCurso.setBackground(Color.GREEN);
                } else if ("Reprobado".equalsIgnoreCase(nota.getEstado())) {
                    btnCurso.setBackground(Color.RED);
                } else {
                    btnCurso.setBackground(Color.LIGHT_GRAY);
                }
            } else {
                btnCurso.setBackground(Color.LIGHT_GRAY);
            }

            btnCurso.addActionListener(e -> JOptionPane.showMessageDialog(this,
                "Curso: " + c.getNombre() + "\nNRC: " + c.getNrc() +
                "\nCréditos: " + c.getCreditos()));
            panel.add(btnCurso);
        }
        return panel;
    }

    /**
     * Panel que lista las certificaciones y permite inscribirse con validaciones.
     * @return panel de certificaciones
     */
    private JPanel crearPanelCertificaciones() {
        JPanel panel = new JPanel(new BorderLayout());
        DefaultListModel<String> modelo = new DefaultListModel<>();
        for (Certificacion cert : Sistema.getCertificaciones()) {
            modelo.addElement(cert.getNombre() + " - " + cert.getDescripcion());
        }
        JList<String> lista = new JList<>(modelo);
        panel.add(new JScrollPane(lista), BorderLayout.CENTER);

        JButton btnInscribir = new JButton("Inscribirse");
        btnInscribir.addActionListener(e -> {
            String seleccion = lista.getSelectedValue();
            if (seleccion != null) {
                String nombreCert = seleccion.split(" - ")[0];
                Certificacion cert = Sistema.buscarCertificacionPorNombre(nombreCert);

                // Validar prerrequisitos (ejemplo simple)
                if (Sistema.verificarPrerrequisitos(estudiante, cert)) {
                    RegistroCertificacion reg = new RegistroCertificacion(estudiante.getRut(), cert.getId(), "Inscrito", nombreCert, 0);
                    Sistema.getRegistros().add(reg);
                    Sistema.guardarRegistros("registros.txt");
                    JOptionPane.showMessageDialog(this, "Inscripción realizada y guardada en archivo.");
                } else {
                    JOptionPane.showMessageDialog(this, "No cumple con los prerrequisitos.");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Seleccione una certificación.");
            }
        });
        panel.add(btnInscribir, BorderLayout.SOUTH);
        return panel;
    }

    /**
     * Panel que muestra el progreso del estudiante en certificaciones inscritas.
     * Aplica Visitor para acciones según tipo de certificación.
     * @return panel de progreso
     */
    private JPanel crearPanelProgreso() {
        JPanel panel = new JPanel(new GridLayout(0,1));
        for (RegistroCertificacion reg : Sistema.getRegistros()) {
            if (reg.getRut().equals(estudiante.getRut())) {
                Certificacion cert = Sistema.BuscarCertificacionPorId(reg.getIdCertificacion());
                JProgressBar barra = new JProgressBar(0,100);
                barra.setValue(reg.getProgreso());
                barra.setStringPainted(true);
                panel.add(new JLabel(cert.getNombre() + " - Estado: " + reg.getEstado()));
                panel.add(barra);

                JButton btnActualizar = new JButton("Actualizar Progreso");
                btnActualizar.addActionListener(e -> {
                    int nuevoProgreso = Integer.parseInt(JOptionPane.showInputDialog(this, "Ingrese nuevo progreso (0-100):"));
                    reg.setProgreso(nuevoProgreso);
                    barra.setValue(nuevoProgreso);
                    Sistema.guardarRegistros("registros.txt");

                    // Aplicar Visitor (ejemplo)
                    Validar_CertiVisitor visitor = new Validar_CertiVisitor(estudiante);
                    cert.accept(visitor);
                });
                panel.add(btnActualizar);
            }
        }
        return panel;
    }
}