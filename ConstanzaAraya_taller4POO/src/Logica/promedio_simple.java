package Logica;

/**
 * Calcula el promedio simple de un conjunto de notas.
 * Implementa la interfaz {estrategia_promedio}.
 */
public class promedio_simple implements estrategia_promedio {
	/**
     * Calcula el promedio aritmético de las notas.
     * @param notas arreglo de notas
     * @return promedio simple
     */
	public double calcular(double[] notas) {
        double suma = 0;
        for (double n : notas) suma += n;
        return suma / notas.length;
    }
}