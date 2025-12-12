package Logica;
/**
 * Estrategia para calcular el promedio de notas.
 */
public interface estrategia_promedio {
	/**
     * Calcula el promedio de un conjunto de notas.
     * @param notas arreglo de notas
     * @return promedio calculado
     */
    double calcular(double[] notas);
}