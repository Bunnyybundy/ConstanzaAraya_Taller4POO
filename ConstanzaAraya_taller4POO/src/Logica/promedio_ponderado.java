package Logica;

/**
 * Calcula el promedio ponderado de un conjunto de notas.
 * Implementa la interfaz {estrategia_promedio}.
 */
public class promedio_ponderado implements estrategia_promedio {
    private double[] pesos;
    /**
     * Constructor que recibe los pesos asociados a cada nota.
     * @param pesos arreglo de pesos
     */
    public promedio_ponderado(double[] pesos) { this.pesos = pesos; }
    /**
     * Calcula el promedio ponderado de las notas usando los pesos definidos.
     * @param notas arreglo de notas
     * @return promedio ponderado
     */
    public double calcular(double[] notas) {
        double suma = 0;
        double totalPesos = 0;
        for (int i = 0; i < notas.length; i++) {
            suma += notas[i] * pesos[i];
            totalPesos += pesos[i];
        }
        return suma / totalPesos;
    }
}