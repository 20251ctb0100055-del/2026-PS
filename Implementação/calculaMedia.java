public class calculaMedia {
    public static int executar(int[] numeros) {
        int soma = 0;
        for (int n : numeros) {
            soma += n;
        }
        return soma / numeros.length;
    }
}