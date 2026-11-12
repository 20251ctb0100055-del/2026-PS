/*
 * Disciplina: 2026-PS
 * Projeto   : bibliotech
 * Arquivo   : Livro.java
 * Autor     : João Pedro Mauda
 * Descricao : Classe de modelo do Livro atualizada para a Aula 45 com suporte a capas (imagens).
 */
public class Livro {
    // ATRIBUTOS
    private int id;
    private String titulo;
    private String autor;
    private int ano;
    private boolean disponivel;
    private String capa; // Novo atributo para armazenar o caminho da imagem

    // CONSTRUTOR COMPLETO COM CAPA: para dados vindos do MariaDB (com ID e capa)
    public Livro(int id, String titulo, String autor, int ano, String capa) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.ano = ano;
        this.disponivel = true;
        this.capa = capa;
    }

    // CONSTRUTOR SEM CAPA (com ID): para compatibilidade com partes anteriores
    public Livro(int id, String titulo, String autor, int ano) {
        this(id, titulo, autor, ano, null);
    }

    // CONSTRUTOR COMPLETO SEM ID (com capa): para novos cadastros
    public Livro(String titulo, String autor, int ano, String capa) {
        this.titulo = titulo;
        this.autor = autor;
        this.ano = ano;
        this.disponivel = true;
        this.capa = capa;
    }

    // CONSTRUTOR SEM ID E SEM CAPA: para criarmos objetos simples
    public Livro(String titulo, String autor, int ano) {
        this(titulo, autor, ano, null);
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

    public String getCapa() {
        return capa;
    }

    public void setCapa(String capa) {
        this.capa = capa;
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
        String infoCapa = (capa != null && !capa.isEmpty()) ? " [Capa: " + capa + "]" : " [Sem capa]";
        return id + " - " + titulo + " (" + autor + ", " + ano + ") - " + situacao + infoCapa;
    }
}