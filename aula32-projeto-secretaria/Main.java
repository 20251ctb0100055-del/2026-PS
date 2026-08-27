/*
 * Disciplina: 2026-PS
 * Estudante : João Pedro Mauda
 * Data      : 2026.08.27
 * Projeto   : aula32-projeto-secretaria
 * Arquivo   : Main.java
 */

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        ArrayList<Aluno> lista = new ArrayList<Aluno>();

        while (true) {
            System.out.println("----------------------------------------");
            System.out.println("  SECRETARIA DO MAUDA");
            System.out.println("----------------------------------------");
            System.out.println("[1] Cadastrar aluno");
            System.out.println("[2] Listar alunos");
            System.out.println("[3] Buscar por matricula");
            System.out.println("[4] Atualizar aluno");
            System.out.println("[5] Remover aluno");
            System.out.println("[6] Relatorio");
            System.out.println("[0] Sair");
            System.out.print("Sua escolha: ");

            String opcao = teclado.nextLine().trim();

            if (opcao.equals("0")) {
                System.out.println("Secretaria fechada. Ate a proxima!");
                break;
            } else if (opcao.equals("1")) {
                cadastrar(lista, teclado);
            } else if (opcao.equals("2")) {
                listar(lista);
            } else if (opcao.equals("3")) {
                buscar(lista, teclado);
            } else if (opcao.equals("4")) {
                atualizar(lista, teclado);
            } else if (opcao.equals("5")) {
                remover(lista, teclado);
            } else if (opcao.equals("6")) {
                relatorio(lista, teclado);
            } else {
                System.out.println("Opcao invalida! Vale 0, 1, 2, 3, 4, 5 ou 6.");
            }
        }
    }

    static Aluno buscarPorMatricula(ArrayList<Aluno> lista, String matricula) {
        for (Aluno a : lista) {
            if (a != null) {
                if (a.getMatricula().equals(matricula)) {
                    return a;
                }
            }
        }
        return null;
    }

    static void cadastrar(ArrayList<Aluno> lista, Scanner teclado) {
        System.out.print("Matricula: ");
        String matricula = teclado.nextLine().trim();

        if (buscarPorMatricula(lista, matricula) != null) {
            System.out.println("Erro: Matricula ja cadastrada!");
            return;
        }

        if (matricula.isEmpty()) {
            System.out.println("Erro: A matricula nao pode ser vazia!");
            return;
        }

        System.out.print("Nome: ");
        String nome = teclado.nextLine().trim();
        if (nome.isEmpty()) {
            System.out.println("Erro: O nome nao pode ser vazio!");
            return;
        }

        System.out.print("Curso: ");
        String curso = teclado.nextLine().trim();
        if (curso.isEmpty()) {
            System.out.println("Erro: O curso nao pode ser vazio!");
            return;
        }

        System.out.print("Cidade: ");
        String cidade = teclado.nextLine().trim();
        if (cidade.isEmpty()) {
            System.out.println("Erro: A cidade nao pode ser vazia!");
            return;
        }

        Aluno novo = new Aluno(matricula, nome, curso, cidade);
        lista.add(novo);
        System.out.println("Ficha de " + nome + " arquivada!");
    }

    static void listar(ArrayList<Aluno> lista) {
        if (lista.size() == 0) {
            System.out.println("Nenhuma ficha no gaveteiro.");
        } else {
            System.out.println("--- FICHAS NO GAVETEIRO: " + lista.size() + " ---");
            for (Aluno a : lista) {
                System.out.println(a);
            }
        }
    }

    static void buscar(ArrayList<Aluno> lista, Scanner teclado) {
        System.out.print("Matricula procurada: ");
        String mat = teclado.nextLine().trim();
        Aluno a = buscarPorMatricula(lista, mat);
        
        if (a == null) {
            System.out.println("Nenhuma ficha com a matricula " + mat + ".");
        } else {
            System.out.println("Achei: " + a);
        }
    }

    static void atualizar(ArrayList<Aluno> lista, Scanner teclado) {
        System.out.print("Matricula da ficha a atualizar: ");
        String mat = teclado.nextLine().trim();
        Aluno a = buscarPorMatricula(lista, mat);

        if (a != null) {
            System.out.print("Novo curso de " + a.getNome() + ": ");
            String novoCurso = teclado.nextLine().trim();
            if (!novoCurso.isEmpty()) a.setCurso(novoCurso);

            System.out.println("Ficha atualizada: " + a);
        } else {
            System.out.println("Nenhuma ficha com a matricula " + mat + ".");
        }
    }

    static void remover(ArrayList<Aluno> lista, Scanner teclado) {
        System.out.print("Matricula da ficha a remover: ");
        String mat = teclado.nextLine().trim();
        Aluno a = buscarPorMatricula(lista, mat);

        if (a != null) {
            System.out.print("Tem certeza que deseja remover " + a.getNome() + "? (s/n): ");
            String confirma = teclado.nextLine().trim();
            if (confirma.equalsIgnoreCase("s")) {
                lista.remove(a);
                System.out.println("Ficha removida com sucesso!");
            } else {
                System.out.println("Remocao cancelada.");
            }
        } else {
            System.out.println("Nenhuma ficha com a matricula " + mat + ".");
        }
    }

    static void relatorio(ArrayList<Aluno> lista, Scanner teclado) {
        System.out.println("--- RELATORIO DA SECRETARIA ---");
        System.out.println("Total de fichas: " + lista.size());

        System.out.print("Contar alunos de qual curso? ");
        String cursoProcurado = teclado.nextLine().trim();

        int contador = 0;

        for (Aluno a : lista) {
            if (a.getCurso().equalsIgnoreCase(cursoProcurado)) {
                contador++;
            }
        }

        System.out.println("Alunos de " + cursoProcurado + ": " + contador);
    }
}