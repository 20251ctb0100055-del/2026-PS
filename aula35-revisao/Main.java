import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static Scanner teclado = new Scanner(System.in);
    static ArrayList<Produto> produtos = new ArrayList<>();

    // Tarefa 3: O main apenas orquestra o menu
    public static void main(String[] args) {

        int opcao = 0;

        while (opcao != 5) {

            System.out.println("\n=== SISTEMA DE PRODUTOS ===");
            System.out.println("1 - Cadastrar");
            System.out.println("2 - Listar");
            System.out.println("3 - Alterar preço");
            System.out.println("4 - Remover");
            System.out.println("5 - Sair");
            System.out.print("Opção: ");

            opcao = teclado.nextInt();
            teclado.nextLine();

            if (opcao == 1) {
                cadastrar();
            } else if (opcao == 2) {
                listar();
            } else if (opcao == 3) {
                alterarPreco();
            } else if (opcao == 4) {
                remover();
            }
        }

        System.out.println("Sistema encerrado.");
    }

    // Tarefa 2: Método de busca centralizado
    static Produto buscarPorCodigo(int codigo) {
        for (Produto p : produtos) {
            if (p.getCodigo() == codigo) {
                return p;
            }
        }
        return null;
    }

    // Tarefa 3 e 6: Método para cadastrar produto
    static void cadastrar() {
        System.out.print("Código: ");
        int codigo = teclado.nextInt();
        teclado.nextLine();

        // Tarefa 6: Impede código duplicado
        if (buscarPorCodigo(codigo) != null) {
            System.out.println("Erro: Já existe um produto com este código!");
            return;
        }

        System.out.print("Nome: ");
        String nome = teclado.nextLine();

        System.out.print("Preço: ");
        double preco = teclado.nextDouble();

        produtos.add(new Produto(codigo, nome, preco));
        System.out.println("Produto cadastrado com sucesso!");
    }

    // Tarefa 3 e 4: Listagem usando System.out.println(p)
    static void listar() {
        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
            return;
        }

        System.out.println("\n--- Lista de Produtos ---");
        for (Produto p : produtos) {
            System.out.println(p); // Executa o toString() de Produto
        }
    }

    // Tarefa 3, 5 e 6: Alteração de preço com opção de desconto
    static void alterarPreco() {
        System.out.print("Código do produto: ");
        int codigo = teclado.nextInt();

        Produto p = buscarPorCodigo(codigo);

        // Tarefa 6: Mensagem caso produto não exista
        if (p == null) {
            System.out.println("Erro: Produto não encontrado!");
            return;
        }

        System.out.println("1 - Alterar para novo preço fixo");
        System.out.println("2 - Aplicar desconto no preço atual");
        System.out.print("Opção: ");
        int tipo = teclado.nextInt();

        if (tipo == 1) {
            System.out.print("Novo preço: ");
            double novoPreco = teclado.nextDouble();
            p.alterarPreco(novoPreco); // Versão simples
            System.out.println("Preço atualizado com sucesso!");
        } else if (tipo == 2) {
            System.out.print("Porcentagem de desconto (%): ");
            double desconto = teclado.nextDouble();
            p.alterarPreco(p.getPreco(), desconto); // Versão com sobrecarga
            System.out.println("Desconto aplicado com sucesso!");
        } else {
            System.out.println("Opção inválida.");
        }
    }

    // Tarefa 3 e 6: Remoção sem erro de ConcurrentModificationException
    static void remover() {
        System.out.print("Código do produto: ");
        int codigo = teclado.nextInt();

        Produto p = buscarPorCodigo(codigo);

        // Tarefa 6: Mensagem caso produto não exista e remoção segura
        if (p != null) {
            produtos.remove(p); // Remove diretamente o objeto localizado fora do loop
            System.out.println("Produto removido com sucesso!");
        } else {
            System.out.println("Erro: Produto não encontrado!");
        }
    }
}