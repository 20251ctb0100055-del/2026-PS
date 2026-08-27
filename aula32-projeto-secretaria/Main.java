/*
 * Disciplina: 2026-PS
 * Estudante : João Pedro Mauda
 * Data      : 2026.08.20
 * Projeto   : aula32-projeto-secretaria
 * Arquivo   : Main.java
 */

import java.util.ArrayList;
import java.util.Scanner;

/*
 * O BALCAO DA SECRETARIA: nao guarda ficha nenhuma, ele atende.
 * Mostra o menu, le a escolha e chama o metodo que resolve.
 */
public class Main {

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);

        // O GAVETEIRO TIPADO: o <Aluno> diz que so entra ficha de aluno aqui.
        ArrayList<Aluno> lista = new ArrayList<Aluno>();

        // while (true) = repete para sempre. A unica saida e o break da opcao 0.
        while (true) {
            System.out.println("----------------------------------------");
            System.out.println("  SECRETARIA DO MAUDA");
            System.out.println("----------------------------------------");
            System.out.println("[1] Cadastrar aluno");
            System.out.println("[2] Listar alunos");
            System.out.println("[0] Sair");
            System.out.print("Sua escolha: ");

            String opcao = teclado.nextLine().trim(); // trim: tira espacos das pontas

            // Texto se compara com .equals, nunca com == (isso vale ouro em Java).
            if (opcao.equals("0")) {
                System.out.println("Secretaria fechada. Ate a proxima!");
                break;
            } else if (opcao.equals("1")) {
                cadastrar(lista, teclado);
            } else if (opcao.equals("2")) {
                listar(lista);
            } else {
                System.out.println("Opcao invalida! Vale 0, 1 ou 2.");
            }
        }
    }

    // Le os dados no balcao, carimba a ficha e guarda no gaveteiro.
    static void cadastrar(ArrayList<Aluno> lista, Scanner teclado) {
        System.out.print("Nome: ");
        String nome = teclado.nextLine().trim();

        System.out.print("Matricula: ");
        String matricula = teclado.nextLine().trim();

        System.out.print("Curso: ");
        String curso = teclado.nextLine().trim();

        Aluno novo = new Aluno(matricula, nome, curso);
        lista.add(novo);

        System.out.println("Ficha de " + nome + " arquivada!");
    }

    // Percorre o gaveteiro e imprime ficha por ficha (padrao da Aula 29).
    static void listar(ArrayList<Aluno> lista) {
        if (lista.size() == 0) {
            System.out.println("Nenhuma ficha no gaveteiro.");
        } else {
            System.out.println("--- FICHAS NO GAVETEIRO: " + lista.size() + " ---");
            for (Aluno a : lista) {
                System.out.println(a.getMatricula() + " | " + a.getNome() + " | " + a.getCurso());
            }
        }
    }
}