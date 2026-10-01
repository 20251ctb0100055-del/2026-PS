/*
 * Disciplina: 2026-PS
 * Projeto   : bibliotech
 * Arquivo   : Leitor.java
 * Autor     : João Pedro Mauda
 * Descricao : Leitor E UM TIPO DE Usuario: herda nome, matricula e entrar().
 */
public class Leitor extends Usuario {

    // So o que a caixa Leitor acrescenta. Nome e matricula ja vem de Usuario.
    private int limiteEmprestimos;
    private int livrosEmMaos;  // nao estava na caixa: o codigo pediu

    public Leitor(String nome, String matricula, int limiteEmprestimos) {
        super(nome, matricula);     // primeiro a parte de Usuario, depois a de leitor
        this.limiteEmprestimos = limiteEmprestimos;
        this.livrosEmMaos = 0;
    }

    public int getLimiteEmprestimos() {
        return limiteEmprestimos;
    }

    public int getLivrosEmMaos() {
        return livrosEmMaos;
    }

    // OPERACAO DA CAIXA: podePegarEmprestimo().
    public boolean podePegarEmprestimo() {
        return livrosEmMaos < limiteEmprestimos;
    }

    // Os dois metodos que o emprestimo vai usar na Aula 38.
    public void pegouLivro() {
        this.livrosEmMaos = this.livrosEmMaos + 1;
    }

    public void devolveuLivro() {
        this.livrosEmMaos = this.livrosEmMaos - 1;
    }

    @Override
    public String toString() {
        return "Leitor " + super.toString() + " - "
                + livrosEmMaos + " de " + limiteEmprestimos + " livros";
    }
}