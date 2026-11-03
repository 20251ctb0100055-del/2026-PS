/*
 * Disciplina: 2026-PS
 * Projeto   : bibliotech
 * Arquivo   : TesteBuscarLivro.java
 * Descricao : Classe de teste atualizada para a Parte 15 (busca por ID com objeto Livro).
 */
import java.util.Scanner;

public class TesteBuscarLivro {

    public static void main(String[] args) {

        Scanner entrada = new Scanner(System.in);
        LivroDAO dao = new LivroDAO();

        System.out.println("=== BUSCAR LIVRO ===");

        int id = -1;

        while (id != 0) {

            System.out.print("\nInforme o ID (0 para sair): ");

            id = entrada.nextInt();

            if (id == 0) {
                break;
            }

            // Agora o retorno do método é um objeto da classe Livro
            Livro livro = dao.buscarLivroPorId(id);

            if (livro != null) {

                System.out.println("Livro encontrado:");
                System.out.println(
                    livro.getId() + " - " +
                    livro.getTitulo() + " - " +
                    livro.getAutor() + " (" +
                    livro.getAno() + ")"
                );

            } else {

                System.out.println("Livro não encontrado.");
            }
        }

        System.out.println("Programa encerrado.");
        entrada.close();
    }
}