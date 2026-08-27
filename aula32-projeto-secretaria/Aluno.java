/*
 * Disciplina: 2026-PS
 * Estudante : João Pedro Mauda
 * Data      : 2026.08.20
 * Projeto   : aula32-projeto-secretaria
 * Arquivo   : Aluno.java
 */

/*
 * A CLASSE É O MOLDE DA FICHA.
 * Ela não guarda os dados de ninguém: descreve o que TODA ficha de aluno
 * tem (nome, matrícula, curso) e o que ela sabe fazer. Cada "new Aluno(...)"
 * no Main carimba uma ficha nova a partir deste molde.
 * Regra do Java: o arquivo tem o mesmo nome da classe pública - Aluno.java.
 */
public class Aluno {

    // ATRIBUTOS: os campos impressos na ficha.
    // "private" = só o código DESTA classe mexe neles. De fora ninguém
    // escreve direto; tem que passar pelos métodos públicos lá embaixo.
    private String nome;
    private String matricula;
    private String curso;

    // CONSTRUTOR: roda no momento do "new" e preenche a ficha.
    // É o __init__ de vocês, em Java. Tem o mesmo nome da classe e não
    // declara tipo de retorno. Os valores chegam de fora, entre parênteses.
    public Aluno(String nome, String matricula, String curso) {
        // "this" = ESTA ficha aqui (o self do Java).
        // this.nome é o atributo da ficha; nome, sozinho, é o parâmetro
        // que acabou de chegar. Sem o this, os dois seriam o parâmetro.
        this.nome = nome;
        this.matricula = matricula;
        this.curso = curso;
    }

    // GETTERS: as janelas de leitura da ficha.
    // Devolvem o valor guardado sem deixar ninguém de fora alterar.
    // Padrão do nome: get + Atributo, com a primeira letra maiúscula.
    public String getNome() {
        return nome;
    }

    public String getMatricula() {
        return matricula;
    }

    public String getCurso() {
        return curso;
    }

    // SETTERS: a única porta de entrada para mudar um dado da ficha.
    // Hoje eles só trocam o valor, mas é aqui que um dia entra a regra
    // ("nome vazio não vale", "curso tem que existir").
    // Repare que não existe setMatricula: matrícula não muda, por decisão
    // do projeto. Sem setter, ninguém altera - nem por engano.
    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }
}