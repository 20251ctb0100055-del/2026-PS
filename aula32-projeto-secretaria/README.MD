# 5. 🎯 Autoavaliação conceitual

**Link do repositório:** https://github.com/20251ctb0100055-del/2026-PS/tree/66d2ead2f73ae372435aa4d117bb1ebb5e8c1ba7/aula32-projeto-secretaria

**Conceito pretendido:** A

**Por que este conceito:**
- **Critério 1 (classe e encapsulamento):** `Aluno.java`, linha 2 — Atributos privados (`private String matricula;`, `nome`, `curso`, `cidade`) protegidos e acessados via métodos públicos getters e setters.
- **Critério 2 (coleção e menu):** `Main.java`, linha 10 — Uso da coleção dinâmica `ArrayList<Aluno>` gerenciada por um menu interativo em laço `while`.
- **Critério 3 (busca e CRUD):** `Main.java`, linha 46 — Método `buscarPorMatricula` centralizado que possibilita todas as operações do CRUD (cadastrar, listar, buscar, atualizar e remover).
- **Critério 4 (relatório, melhoria e identidade):** `Main.java`, linha 155 — Método `relatorio` estruturado no padrão *preparar, percorrer e usar*, além de validação de matrícula única no cadastro e o campo personalizado `cidade`.

---

### Explicações Conceituais

**1. Alteração de objeto vindo da busca e por que a lista muda junto:**
- **Linha do código:** `if (!novoCurso.isEmpty()) a.setCurso(novoCurso);` (`Main.java`, linha 128)
- **Explicação:** A variável `a` não guarda uma cópia dos dados do aluno, mas sim a referência de memória (o endereço) do objeto que está guardado dentro do `ArrayList`. Quando executamos `a.setCurso(novoCurso)`, estamos alterando o objeto original diretamente na memória. Por isso, a alteração reflete imediatamente na lista sem a necessidade de reenserir o objeto.

**2. Por que a busca devolve `null` em vez de imprimir a mensagem de erro ela mesma:**
- **Explicação:** Trata-se do princípio de separação de responsabilidades. O método `buscarPorMatricula` tem como única função procurar e retornar o objeto no gaveteiro. Ele devolve `null` para sinalizar ao chamador que a busca falhou. Quem decide o que fazer com essa informação — seja exibir uma mensagem amigável no terminal, pedir os dados novamente ou cancelar uma operação — é o método que chamou a busca (como o `buscar`, `cadastrar` ou `atualizar`). Se a busca imprimisse o erro por conta própria, ela ficaria presa ao terminal e não poderia ser reutilizada para validações internas, como conferir se uma matrícula já existe antes de cadastrar.

---

**Uso de IA:** Auxílio na estruturação das etapas do projeto, resolução de incompatibilidades no construtor da classe e suporte nos comandos de versionamento com Git.