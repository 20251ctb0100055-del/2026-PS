public class AtividadeMetodos {

    public static void main(String[] args) {
        System.out.println("=== EXECUTANDO CASOS DE TESTE ===\n");
        System.out.println("--- Problema 1: Calculadora de Desconto ---");
        System.out.println("Esperado: 90.0  | Resultado: " + calcularDesconto(100, 10));
        System.out.println("Esperado: 200.0 | Resultado: " + calcularDesconto(250, 20));
        System.out.println("Esperado: 425.0 | Resultado: " + calcularDesconto(500, 15));
        System.out.println();

        System.out.println("--- Problema 2: Verificador de Maior Valor ---");
        System.out.println("Esperado: 20 | Resultado: " + maiorNumero(10, 20));
        System.out.println("Esperado: 50 | Resultado: " + maiorNumero(50, 5));
        System.out.println("Esperado: 30 | Resultado: " + maiorNumero(30, 30));
        System.out.println();

        System.out.println("--- Problema 3: Sistema de Frete ---");
        System.out.println("Esperado: 10.0 | Resultado: " + calcularFrete(0.5));
        System.out.println("Esperado: 20.0 | Resultado: " + calcularFrete(3));
        System.out.println("Esperado: 35.0 | Resultado: " + calcularFrete(8));
        System.out.println();

        System.out.println("--- Problema 4: Sobrecarga de Soma ---");
        System.out.println("Esperado: 8   | Resultado: " + somar(5, 3));
        System.out.println("Esperado: 6.0 | Resultado: " + somar(2.5, 3.5));
        System.out.println("Esperado: 150 | Resultado: " + somar(100, 50));
        System.out.println();

        System.out.println("--- Problema 5: Sistema de Cardápio com Sobrecarga ---");
        exibirProduto("Refrigerante");
        exibirProduto("Pizza", 39.90);
        exibirProduto("Hambúrguer", 22.50);
    }

    public static double calcularDesconto(double valor, double percentual) {
        return valor - (valor * (percentual / 100));
    }
    public static int maiorNumero(int a, int b) {
        if (a >= b) {
            return a;
        } else {
            return b;
        }
    }
    public static double calcularFrete(double peso) {
        if (peso <= 1.0) {
            return 10.0;
        } else if (peso <= 5.0) {
            return 20.0;
        } else {
            return 35.0;
        }
    }
      public static int somar(int a, int b) {
        return a + b;
    }

    // Problema 4 — Sobrecarga de Soma (Método 2: Decimais)
    public static double somar(double a, double b) {
        return a + b;
    }
    public static void exibirProduto(String nome) {
        System.out.println("Produto: " + nome);
    }
    public static void exibirProduto(String nome, double preco) {
        System.out.println("Produto: " + nome + " Preço: R$ " + preco);
    }
}
