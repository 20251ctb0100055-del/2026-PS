public class Main {
    public static void main(String[] args) {
        int[] valores = {8, 3, 10, 5, 12};

        System.out.println(calculaSoma.executar(valores));
        System.out.println(calculaMedia.executar(valores));
        System.out.println(menorValor.executar(valores));
        System.out.println(maiorValor.executar(valores));
        System.out.println(contarAcima.executar(valores, 6));
    }
}