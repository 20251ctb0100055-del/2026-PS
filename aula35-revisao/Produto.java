public class Produto {

    // Tarefa 1: Atributos privados (Encapsulamento)
    private int codigo;
    private String nome;
    private double preco;

    public Produto(int codigo, String nome, double preco) {
        this.codigo = codigo;
        this.nome = nome;
        this.preco = preco;
    }

    // Getters e Setters
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    // Tarefa 5: Sobrecarga do método alterarPreco
    public void alterarPreco(double preco) {
        this.preco = preco;
    }

    public void alterarPreco(double preco, double desconto) {
        this.preco = preco - (preco * desconto / 100);
    }

    // Tarefa 4: Método toString para exibição formatada
    @Override
    public String toString() {
        return String.format("%d - %s - R$ %.2f", codigo, nome, preco);
    }
}