package Presentacion;

import java.awt.BorderLayout;
import java.util.List;
import java.util.ArrayList;
import javax.swing.*;
import Dominio.*;
import Logica.Sistema;
import Logica.Validar_CertiVisitor;
/**
 * Ventana del menú coordinador.
 * Permite gestionar certificaciones, visualizar métricas y analizar estudiantes.
 */
public class MenuCoordinador extends JFrame {
	private static final long serialVersionUID = 1L;
	/**
     * Construye la ventana del menú coordinador con pestañas de gestión.
     */
    public MenuCoordinador() {
        setTitle("Menú Coordinador");
        setSize(800,500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JTabbedPane tabs = new JTabbedPane();
        tabs.addTab("Gestion de Certificaciones", crearPanelCertificaciones());
        tabs.addTab("Metricas y Analisis", crearPanelEstadisticas());
        tabs.addTab("Gestion de Estudiantes", crearPanelEstudiantes());

        add(tabs);
    }
    /**
     * Panel para gestionar certificaciones:
     * permite modificar datos y generar certificados.
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
        
        JPanel botones = new JPanel();
        JButton btnModificar = new JButton("Modificar Certificación");
        JButton btnGenerar = new JButton("Generar Certificado");
        
        btnModificar.addActionListener(e -> {
        	String seleccion = lista.getSelectedValue();
        	if(seleccion != null) {
        		Certificacion cert = Sistema.buscarCertificacionPorNombre(seleccion);
        		String nuevoNombre = JOptionPane.showInputDialog(this, "Nuevo Nombre: ", cert.getNombre());
        		String nuevaDescripcion = JOptionPane.showInputDialog(this, "Nueva descripción: ", cert.getDescripcion());
        		String requisitosCred = JOptionPane.showInputDialog(this, "Requisitos creditos: ", cert.getRequisitosCreditos());
        		String validez = JOptionPane.showInputDialog(this, "Validez: ", cert.getValidez());
        		cert.setNombre(nuevoNombre);
        		cert.setDescripcion(nuevaDescripcion);
        		cert.setRequisitosCreditos(Integer.parseInt(requisitosCred));
        		cert.setValidez(Integer.parseInt(validez));
        		JOptionPane.showMessageDialog(this, "Certificacion modificada");
        	}
        });
        btnGenerar.addActionListener(e -> {
        	String seleccion = lista.getSelectedValue();
        	if(seleccion != null) {
        		Certificacion cert = Sistema.buscarCertificacionPorNombre(seleccion);
        		for(Estudiante est : Sistema.getEstudiantes()) {
        			Validar_CertiVisitor visitor = new Validar_CertiVisitor(est);
        			cert.accept(visitor);
        			if(visitor.cumpleRequisitos()) {
        				Sistema.generarCertificado(est, cert);
        			}
        		}
        		JOptionPane.showMessageDialog(this, "Certificados generador para estudiantes que cumplen.");
        	}
        });
        botones.add(btnModificar);
        botones.add(btnGenerar);
        panel.add(botones, BorderLayout.SOUTH);
        return panel;
    }
    /**
     * Panel de estadísticas:
     * muestra inscripciones y asignaturas críticas con alta reprobación.
     * @return panel de estadísticas
     */
    private JPanel crearPanelEstadisticas() {
        JPanel panel = new JPanel(new BorderLayout());
     
        JTextArea area = new JTextArea();
        area.setEditable(false);
        area.append("===== Estadisticas de Inscripciones ===== \n");
        for(Certificacion cert : Sistema.getCertificaciones()) {
        	int inscritos = Sistema.contarInscritos(cert.getId());
        	area.append(cert.getNombre() + ":" + inscritos + "inscritos \n");
        }
        area.append("\n === Asignaturas Criticas ===\n");
        for(Certificacion cert : Sistema.getCertificaciones()) {
        	area.append("Certificacion: " + cert.getNombre() + "\n");
        	List<Curso> cursos= new ArrayList<>();
        	cursos = Sistema.getCursosRequeridos(cert.getId());
        	for(Curso c: cursos) {
        		double reprobacion = Sistema.porcentajeReprobacionCurso(c.getNrc());
        		area.append("-" + c.getNombre() + "(" + c.getNrc() + "): " + reprobacion + "% reprobacion \n");
        		if(reprobacion >= 30.0) {
        			area.append("Critico : Alta reprobacion");
        		}
        	}
        }
        panel.add(new JScrollPane(area),BorderLayout.CENTER);
        return panel;
    }
    /**
     * Panel de gestión de estudiantes:
     * permite buscar un estudiante por RUT y mostrar su perfil,
     * notas y certificaciones inscritas.
     * @return panel de estudiantes
     */
    private JPanel crearPanelEstudiantes() {
        JPanel panel = new JPanel(new BorderLayout());
        
        JTextField txtRut = new JTextField();
        JButton btnBuscar = new JButton("Buscar Estudiante");
        JTextArea resultado = new JTextArea();
        resultado.setEditable(false);

        btnBuscar.addActionListener(e -> {
            String rut = txtRut.getText().trim();
            Estudiante est = Sistema.buscarEstudiantePorRut(rut);
            if (est != null) {
            	StringBuilder sb = new StringBuilder();
            	sb.append("== Perfil del Estudiante ==\n");
            	sb.append("Nombre: ").append(est.getNombre()).append("\n");
            	sb.append("RUT: ").append(est.getRut()).append("\n");
            	sb.append("Carrera: ").append(est.getCarrera()).append("\n");
            	sb.append("Semestre: ").append(est.getSemestre()).append("\n");
            	sb.append("Correo: ").append(est.getCorreoE()).append("\n\n");
            	
            	sb.append("=== Notas ===\n");
            	for(Nota n : Sistema.getNotasPorRut(est.getRut())) {
            		sb.append("Curso: ").append(n.getCodigoAsignatura()).append(" | Nota: ").append(n.getCalificacion()).append(" | Estado: ").append(n.getEstado()).append(" | Semestre: ").append(n.getSemestre()).append("\n");
            	}
            	
            	sb.append("\n ===Certificaciones Inscritas === \n");
            	for(RegistroCertificacion reg : Sistema.getRegistros()) {
            		if(reg.getRut().equals(est.getRut())) {
            			Certificacion cert = Sistema.BuscarCertificacionPorId(reg.getIdCertificacion());
            			sb.append(cert.getNombre()).append(" | Estado: ").append(reg.getEstado()).append("| Progreso: ").append(reg.getProgreso()).append("\n");
            			Validar_CertiVisitor visitor = new Validar_CertiVisitor(est);
            			cert.accept(visitor);
            			if(visitor.cumpleRequisitos()){
            				sb.append("Cumple requisitos para certificado \n");
            			}else {
            				sb.append(" Pendiente: cursos o creditos insuficientes \n");
            			}
            		}
            	}
            	resultado.setText(sb.toString());
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