public class TesteLivroDAO {

    public static void main(String[] args) {
        LivroDAO dao = new LivroDAO();

        System.out.println("=== ETAPA 1: INSERÇÃO ===");
        dao.adicionarLivro("Dom Casmurro", "Machado de Assis", 1899);
        dao.adicionarLivro("O Pequeno Príncipe", "Antoine de Saint-Exupéry", 1943);
        dao.adicionarLivro("Capitães da Areia", "Jorge Amado", 1937);

        System.out.println("\n=== TESTE ADICIONAL: REGISTRO INEXISTENTE ===");
        boolean atualizouInexistente = dao.atualizarLivro(999999, "Inexistente", "Autor", 2026);
        boolean excluiuInexistente = dao.excluirLivro(999999);
        System.out.println("Atualização inexistente: " + atualizouInexistente); // deve ser false
        System.out.println("Exclusão inexistente: " + excluiuInexistente);       // deve ser false

        /* 
           ATENÇÃO: Substitua os IDs abaixo (1 e 2) pelos IDs reais exibidos 
           ao rodar 'SELECT * FROM livros;' no MariaDB!
        */
        System.out.println("\n=== ETAPA 3: ATUALIZAÇÃO ===");
        boolean atualizou = dao.atualizarLivro(1, "Dom Casmurro - Edição Revisada", "Machado de Assis", 1899);
        System.out.println("Livro 1 atualizado: " + atualizou);

        System.out.println("\n=== ETAPA 4: EXCLUSÃO ===");
        boolean excluiu = dao.excluirLivro(2);
        System.out.println("Livro 2 excluído: " + excluiu);
    }
}