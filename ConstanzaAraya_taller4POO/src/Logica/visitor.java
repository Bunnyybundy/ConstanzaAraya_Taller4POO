package Logica;

import Dominio.Certificacion;
import Dominio.Curso;
import Dominio.Estudiante;
/**
 * Interfaz del patrón Visitor.
 * Define operaciones de visita para distintos elementos del dominio:
 * certificaciones, cursos y estudiantes.
 */
public interface visitor {
    /**
     * Visita una certificación.
     * @param c certificación a visitar
     */
    void visit(Certificacion c);
    /**
     * Visita un curso.
     * @param c curso a visitar
     */
    void visit(Curso c);
    /**
     * Visita un estudiante.
     * @param e estudiante a visitar
     */
    void visit(Estudiante e);
}