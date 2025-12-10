package Estrategia;

public class promedio_simple implements estrategia_promedio {
    public double calcular(double[] notas) {
        double suma = 0;
        for (double n : notas) suma += n;
        return suma / notas.length;
    }
}