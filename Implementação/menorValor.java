public class menorValor {
    public static int executar(int[] numeros) {
        int menor = numeros[0];
        for (int n : numeros) {
            if (n < menor) {
                menor = n;
            }
        }
        return menor;
    }
}