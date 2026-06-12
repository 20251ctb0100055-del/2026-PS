import java.util.Scanner;
import java.util.Random;

public class CardapioRestaurante {

    public static void main(String[] args) {

        Scanner entrada = new Scanner(System.in);
        Random random = new Random();

        double total = 0;

        int qtdXBurguer = 0;
        int qtdPizza = 0;
        int qtdBatata = 0;
        int qtdRefri = 0;
        int qtdSorvete = 0;

        final double PRECO_XBURGUER = 18.00;
        final double PRECO_PIZZA = 35.00;
        final double PRECO_BATATA = 12.00;
        final double PRECO_REFRI = 8.00;
        final double PRECO_SORVETE = 10.00;

        boolean continuar = true;

        while (continuar) {

            System.out.println("\n===========================");
            System.out.println("        FAST FOOD FMS");
            System.out.println("     CARDÁPIO ELETRÔNICO");
            System.out.println("===========================");
            System.out.println("1 - X-Burguer");
            System.out.println("2 - Pizza");
            System.out.println("3 - Batata Frita");
            System.out.println("4 - Refrigerante");
            System.out.println("5 - Sorvete");
            System.out.println("6 - Finalizar Pedido");
            System.out.println();
            System.out.print("Escolha: ");

            int opcao = entrada.nextInt();

            if (opcao == 6) {
                break;
            }

            System.out.println();
            System.out.print("Quantidade: ");
            int qtd = entrada.nextInt();

            switch (opcao) {

                case 1:
                    qtdXBurguer += qtd;
                    total += qtd * PRECO_XBURGUER;
                    break;

                case 2:
                    qtdPizza += qtd;
                    total += qtd * PRECO_PIZZA;
                    break;

                case 3:
                    qtdBatata += qtd;
                    total += qtd * PRECO_BATATA;
                    break;

                case 4:
                    qtdRefri += qtd;
                    total += qtd * PRECO_REFRI;
                    break;

                case 5:
                    qtdSorvete += qtd;
                    total += qtd * PRECO_SORVETE;
                    break;

                default:
                    System.out.println("Opção inválida!");
                    continue;
            }

            System.out.println("\nItem adicionado ao pedido!");

            System.out.println("\nDeseja continuar comprando?");
            System.out.println("1 - Sim");
            System.out.println("2 - Finalizar");
            System.out.print("\nEscolha: ");

            int continuarCompra = entrada.nextInt();

            if (continuarCompra == 2) {
                continuar = false;
            }
        }

        System.out.println("\n===========================");
        System.out.println("RESUMO DO PEDIDO");
        System.out.println("===========================\n");

        if (qtdXBurguer > 0)
            System.out.printf("%dx X-Burguer ........ R$ %.2f%n",
                    qtdXBurguer, qtdXBurguer * PRECO_XBURGUER);

        if (qtdPizza > 0)
            System.out.printf("%dx Pizza ............ R$ %.2f%n",
                    qtdPizza, qtdPizza * PRECO_PIZZA);

        if (qtdBatata > 0)
            System.out.printf("%dx Batata Frita ..... R$ %.2f%n",
                    qtdBatata, qtdBatata * PRECO_BATATA);

        if (qtdRefri > 0)
            System.out.printf("%dx Refrigerante ..... R$ %.2f%n",
                    qtdRefri, qtdRefri * PRECO_REFRI);

        if (qtdSorvete > 0)
            System.out.printf("%dx Sorvete .......... R$ %.2f%n",
                    qtdSorvete, qtdSorvete * PRECO_SORVETE);

        System.out.printf("%nTOTAL: R$ %.2f%n", total);

        System.out.println("\nForma de pagamento:");
        System.out.println();
        System.out.println("1 - Dinheiro");
        System.out.println("2 - Cartão");
        System.out.println("3 - PIX");
        System.out.println();
        System.out.print("Escolha: ");

        int pagamento = entrada.nextInt();

        if (pagamento >= 1 && pagamento <= 3) {

            int pedido = random.nextInt(900) + 100;

            System.out.println("\nPagamento realizado com sucesso!");
            System.out.println();
            System.out.println("Pedido Nº " + pedido);
            System.out.println();
            System.out.println("Aguarde a chamada do seu pedido.");

        } else {
            System.out.println("Forma de pagamento inválida!");
        }

        entrada.close();
    }
}