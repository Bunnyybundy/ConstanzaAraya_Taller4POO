package Logica;

/**
 * Interfaz para elementos que pueden ser visitados
 * por un objeto que implemente {visitor}.
 * Forma parte del patrón Visitor.
 */
public interface visitable {
	/**
     * Acepta un visitor para aplicar una operación sobre el objeto.
     * @param v visitor que realiza la operación
     */
    void accept(visitor v);
}