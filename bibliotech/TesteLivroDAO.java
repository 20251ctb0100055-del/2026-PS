public class TesteLivroDAO {

    public static void main(String[] args) {
        LivroDAO dao = new LivroDAO();

        System.out.println("--- ETAPA 1: INSERÇÃO DE 3 LIVROS ---");
        boolean l1 = dao.adicionarLivro("O Pequeno Principe", "Antoine de Saint-Exupery", 1943);
        boolean l2 = dao.adicionarLivro("Dom Casmurro", "Machado de Assis", 1899);
        boolean l3 = dao.adicionarLivro("O Hobbit", "J.R.R. Tolkien", 1937);

        System.out.println("Livro 1 Adicionado: " + l1);
        System.out.println("Livro 2 Adicionado: " + l2);
        System.out.println("Livro 3 Adicionado: " + l3);

        System.out.println("\n--- ETAPA 3: ATUALIZAÇÃO ---");
        boolean atualizou = dao.atualizarLivro(1, "O Pequeno Principe - Edicao Especial", "Antoine de Saint-Exupery", 1943);
        System.out.println("Livro ID 1 Atualizado: " + atualizou);

        System.out.println("\n--- ETAPA 4: EXCLUSÃO ---");
        boolean excluiu = dao.excluirLivro(2);
        System.out.println("Livro ID 2 Excluido: " + excluiu);

        System.out.println("\n--- TESTE ADICIONAL: REGISTRO INEXISTENTE ---");
        boolean atualizouInexistente = dao.atualizarLivro(999999, "Livro Inexistente", "Autor Desconhecido", 2026);
        boolean excluiuInexistente = dao.excluirLivro(999999);

        System.out.println("Atualizacao Inexistente: " + atualizouInexistente);
        System.out.println("Exclusao Inexistente: " + excluiuInexistente);
    }
}