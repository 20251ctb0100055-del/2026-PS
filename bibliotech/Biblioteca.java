import java.util.ArrayList;

public class Biblioteca {

    // O 0..* do diagrama: cada lista guarda muitas referencias
    private ArrayList<Livro> livros;
    private ArrayList<Leitor> leitores;
    private ArrayList<Emprestimo> emprestimos;

    // Declarar nao cria: as tres listas nascem aqui, no new.
    public Biblioteca() {
        this.livros = new ArrayList<Livro>();
        this.leitores = new ArrayList<Leitor>();
        this.emprestimos = new ArrayList<Emprestimo>();
    }

    public void cadastrarLivro(Livro livro) {
        livros.add(livro);
    }

    public void cadastrarLeitor(Leitor leitor) {
        leitores.add(leitor);
    }

    public void listarAcervo() {
        for (int i = 0; i < livros.size(); i++) {
            System.out.println(livros.get(i));
        }
    }

    // Devolve o livro achado, ou null se o titulo nao esta no acervo.
    public Livro buscarLivro(String titulo) {
        for (int i = 0; i < livros.size(); i++) {
            Livro l = livros.get(i);
            if (l.getTitulo().equals(titulo)) {
                return l;
            }
        }
        return null;
    }

    // Devolve o leitor achado, ou null se a matricula nao existe.
    public Leitor buscarLeitor(String matricula) {
        for (int i = 0; i < leitores.size(); i++) {
            Leitor l = leitores.get(i);
            if (l.getMatricula().equals(matricula)) {
                return l;
            }
        }
        return null;
    }

    public boolean emprestar(String titulo, String matricula) {
        Livro livro = buscarLivro(titulo);
        Leitor leitor = buscarLeitor(matricula);
        if (livro == null || leitor == null) {
            return false;
        }

        Emprestimo novo = new Emprestimo(livro, leitor);
        if (!novo.realizarEmprestimo()) {
            return false;
        }

        emprestimos.add(novo);
        return true;
    }

    // Procura o emprestimo ATIVO daquele titulo. O registro continua na lista
    public boolean devolver(String titulo) {
        for (int i = 0; i < emprestimos.size(); i++) {
            Emprestimo e = emprestimos.get(i);
            if (e.estaAtivo() && e.getLivro().getTitulo().equals(titulo)) {
                return e.registrarDevolucao();
            }
        }
        return false;
    }

    public void listarEmprestimos() {
        for (int i = 0; i < emprestimos.size(); i++) {
            System.out.println(emprestimos.get(i));
        }
    }

    // Fornece uma descricao do acervo para a interface (Fase 2).
    public String obterAcervoComoTexto() {
        if (livros.isEmpty()) {
            return "Nenhum livro cadastrado.";
        }

        String texto = "";
        for (int i = 0; i < livros.size(); i++) {
            texto = texto + livros.get(i) + "\n";
        }
        return texto;
    }
}