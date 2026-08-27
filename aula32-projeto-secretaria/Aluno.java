/*
 * Disciplina: 2026-PS
 * Estudante : João Pedro Mauda
 * Data      : 2026.08.27
 * Projeto   : aula32-projeto-secretaria
 * Arquivo   : Aluno.java
 */

public class Aluno {
    private String matricula;
    private String nome;
    private String curso;
    private int idade;

    public Aluno(String matricula, String nome, String curso, int idade) {
        this.matricula = matricula;
        this.nome = nome;
        this.curso = curso;
        this.idade = idade;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    // A receita de apresentação da ficha
    @Override
    public String toString() {
        return matricula + " | " + nome + " | " + curso + " | " + idade + " anos";
    }
}