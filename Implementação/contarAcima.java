public class contarAcima {
    public static int executar(int[] numeros, int limite) {
        int contador = 0;
        for (int n : numeros) {
            if (n > limite) {
                contador++;
            }
        }
        return contador;
    }
}