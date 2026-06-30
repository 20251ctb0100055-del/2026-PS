import java.util.ArrayList;

public class Aula29 {

    // Exercício 1
    public static double calcularMedia(double[] notas) {
        double soma = 0;

        for (double nota : notas) {
            soma += nota;
        }

        return soma / notas.length;
    }

    // Exercício 2
    public static int contarAprovados(double[] notas) {
        int aprovados = 0;

        for (double nota : notas) {
            if (nota >= 6.0) {
                aprovados++;
            }
        }

        return aprovados;
    }

    // Exercício 3
    public static void adicionarProduto(ArrayList<String> lista, String nome) {
        lista.add(nome);
    }

    public static void listarProdutos(ArrayList<String> lista) {
        for (int i = 0; i < lista.size(); i++) {
            System.out.println((i + 1) + " - " + lista.get(i));
        }
    }

    // Exercício 4
    public static int maiorValor(int[] valores) {
        int maior = valores[0];

        for (int valor : valores) {
            if (valor > maior) {
                maior = valor;
            }
        }

        return maior;
    }

    public static int maiorValor(int a, int b) {
        if (a > b) {
            return a;
        }
        return b;
    }

    // Desafio (nível A)
    public static int contarAcimaDaMedia(double[] notas) {
        double media = calcularMedia(notas);
        int contador = 0;

        for (double nota : notas) {
            if (nota > media) {
                contador++;
            }
        }

        return contador;
    }

    // Exercício 5
    public static void exibirBoletim(double[] notas) {
        double media = calcularMedia(notas);
        int aprovados = contarAprovados(notas);

        System.out.println("Média: " + media);
        System.out.println("Aprovados: " + aprovados);

        if (media >= 6.0) {
            System.out.println("Situação: APROVADA");
        } else {
            System.out.println("Situação: EM RECUPERACAO");
        }

        // Desafio
        System.out.println("Acima da média: " + contarAcimaDaMedia(notas));
    }

    public static void main(String[] args) {

        // Teste Ex. 1
        double[] notas = {7.0, 8.0, 9.0};
        System.out.println("Media: " + calcularMedia(notas));

        // Teste Ex. 2
        double[] notas2 = {7.0, 4.0, 9.0, 6.0};
        System.out.println("Aprovados: " + contarAprovados(notas2));

        // Teste Ex. 3
        ArrayList<String> produtos = new ArrayList<>();
        adicionarProduto(produtos, "Pizza");
        adicionarProduto(produtos, "Suco");
        listarProdutos(produtos);

        // Teste Ex. 4
        int[] valores = {3, 9, 5};
        System.out.println("Maior do array: " + maiorValor(valores));
        System.out.println("Maior entre dois numeros: " + maiorValor(12, 7));

        // Teste Ex. 5
        double[] notas3 = {7.0, 5.0, 9.0, 6.0};
        exibirBoletim(notas3);
    }
}