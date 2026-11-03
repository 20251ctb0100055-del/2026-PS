/*
 * Disciplina: 2026-PS
 * Projeto   : bibliotech
 * Arquivo   : TesteListarLivros.java
 * Descricao : Classe atualizada para a Parte 15 para testar a listagem com objetos Livro.
 */
import java.util.List;

public class TesteListarLivros {

    public static void main(String[] args) {

        LivroDAO dao = new LivroDAO();

        // Agora o método retorna uma lista de objetos Livro
        List<Livro> livros = dao.listarLivros();

        System.out.println("=== LIVROS CADASTRADOS ===");

        if (livros.isEmpty()) {

            System.out.println("Nenhum livro encontrado.");

        } else {

            // Laço for percorrendo objetos do tipo Livro
            for (Livro livro : livros) {
                System.out.println(
                    livro.getId() + " - " +
                    livro.getTitulo() + " - " +
                    livro.getAutor() + " (" +
                    livro.getAno() + ")"
                );
            }

            // Desafio Prático da Parte 15: apresentar livros publicados a partir do ano 2000
            System.out.println("\n=== LIVROS PUBLICADOS A PARTIR DE 2000 ===");
            boolean encontrou = false;
            for (Livro livro : livros) {
                if (livro.getAno() >= 2000) {
                    System.out.println(
                        livro.getTitulo() + " (" + livro.getAno() + ")"
                    );
                    encontrou = true;
                }
            }

            if (!encontrou) {
                System.out.println("Nenhum livro encontrado publicado a partir de 2000.");
            }
        }
    }
}