package Logica;

import Dominio.Certificacion;
import Dominio.Curso;
import Dominio.Estudiante;
import Dominio.Nota;

/**
 * Visitor que valida si un estudiante cumple los requisitos
 * para optar a una certificación.
 */
public class Validar_CertiVisitor implements visitor {
    private Estudiante estudiante;
    private Certificacion certificacion;
    /**
     * Crea un visitor para validar certificaciones de un estudiante.
     * @param estudiante estudiante a evaluar
     */
    public Validar_CertiVisitor(Estudiante estudiante) {
        this.estudiante = estudiante;
    }
    /**
     * Verifica si la certificación corresponde al área del estudiante.
     * @param c certificación visitada
     */
    @Override
    public void visit(Certificacion c) {
        if (c.getDescripcion().contains(estudiante.getCarrera())) {
            System.out.println("✔ El estudiante " + estudiante.getNombre() +
                               " puede optar a la certificación " + c.getNombre());
        } else {
            System.out.println("✘ El estudiante " + estudiante.getNombre() +
                               " no cumple el área para " + c.getNombre());
        }
    }
    /**
     * Muestra información del curso visitado.
     * @param c curso visitado
     */
    @Override
    public void visit(Curso c) {
        System.out.println("Visitando curso: " + c.getNombre());
    }
    /**
     * Muestra información del estudiante visitado.
     * @param e estudiante visitado
     */
    @Override
    public void visit(Estudiante e) {
        System.out.println("Visitando estudiante: " + e.getNombre() +
                           " (" + e.getCarrera() + ")");
    }
    /**
     * Evalúa si el estudiante cumple los requisitos de la certificación:
     * créditos mínimos y cursos requeridos aprobados.
     * @return true si cumple requisitos, false en caso contrario
     */
    public boolean cumpleRequisitos() {
        int creditosAprobados = 0;
        for (Nota n : Sistema.getNotasPorRut(estudiante.getRut())) {
            if (n.getEstado().equalsIgnoreCase("Aprobada")) {
                Curso c = Sistema.buscarCursoPorNRC(n.getCodigoAsignatura());
                if (c != null) {
                    creditosAprobados += c.getCreditos();
                }
            }
        }
        if (creditosAprobados < certificacion.getRequisitosCreditos()) {
            return false;
        }
        for (Curso c : Sistema.getCursosRequeridos(certificacion.getId())) {
            boolean aprobado = false;
            for (Nota n : Sistema.getNotasPorRut(estudiante.getRut())) {
                if (n.getCodigoAsignatura().equals(c.getNrc()) &&
                    n.getEstado().equalsIgnoreCase("Aprobada")) {
                    aprobado = true;
                    break;
                }
            }
            if (!aprobado) {
                return false;
            }
        }
        return true; 
    }

}