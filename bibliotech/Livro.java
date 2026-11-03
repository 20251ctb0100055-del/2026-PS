/*
 * Disciplina: 2026-PS
 * Projeto   : bibliotech
 * Arquivo   : Livro.java
 * Autor     : João Pedro Mauda
 * Descricao : Classe de modelo do Livro atualizada para a Parte 15 com 'id'.
 */
public class Livro {
    // ATRIBUTOS
    private int id; // Atributo necessário para representar o ID do MariaDB
    private String titulo;
    private String autor;
    private int ano;
    private boolean disponivel;

    // CONSTRUTOR COMPLETO: para quando os dados vêm do MariaDB (com ID)
    public Livro(int id, String titulo, String autor, int ano) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.ano = ano;
        this.disponivel = true;
    }

    // CONSTRUTOR SEM ID: para quando criamos o objeto antes de salvar no banco
    public Livro(String titulo, String autor, int ano) {
        this.titulo = titulo;
        this.autor = autor;
        this.ano = ano;
        this.disponivel = true;
    }

    // GETTERS E SETTERS
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    // OPERAÇÕES DE ESTADO
    public boolean estaDisponivel() {
        return disponivel;
    }

    public void emprestar() {
        this.disponivel = false;
    }

    public void devolver() {
        this.disponivel = true;
    }

    // REPRESENTAÇÃO EM TEXTO
    @Override
    public String toString() {
        String situacao = disponivel ? "disponivel" : "emprestado";
        return id + " - " + titulo + " (" + autor + ", " + ano + ") - " + situacao;
    }
}