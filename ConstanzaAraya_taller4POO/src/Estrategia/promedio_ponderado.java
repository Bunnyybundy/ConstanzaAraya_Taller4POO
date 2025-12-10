package Estrategia;

public class promedio_ponderado implements estrategia_promedio {
    private double[] pesos;
    public promedio_ponderado(double[] pesos) { this.pesos = pesos; }

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