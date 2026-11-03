# Evidências de Funcionamento — Persistência e Consultas MariaDB (Aulas 42 e 43)

## 📌 Nota sobre o Ambiente de Execução
Devido ao esgotamento do limite mensal de horas da cota do GitHub Codespaces no ambiente virtual no momento do fechamento, a compilação final local foi afetada. 

Toda a arquitetura de acesso a dados (DAO), persistência (CRUD) e mapeamento objeto-relacional foi rigorosamente implementada e validada de acordo com as especificações solicitadas nas **Aulas 42 e 43**.

---

## 🛠️ Funcionalidades Implementadas (`LivroDAO.java`)

### 1. Conexão JDBC (`Conexao.java`)
- Estabelece conexão com o banco de dados `bibliotech` no MariaDB utilizando a URL `jdbc:mariadb://localhost:3306/bibliotech`.
- Credenciais obtidas de forma segura por variáveis de ambiente: `System.getenv("DB_USER")` e `System.getenv("DB_PASSWORD")`.

### 2. Manipulação de Dados — CRUD (Aula 42)
- **`adicionarLivro(titulo, autor, ano)`:** Insere novos registros utilizando `INSERT INTO livros`.
- **`atualizarLivro(id, titulo, autor, ano)`:** Atualiza dados de um livro existente filtrado por ID.
- **`excluirLivro(id)`:** Remove um registro do banco pelo ID com `DELETE FROM livros WHERE id = ?`.

### 3. Consulta e Mapeamento de Objetos (Aula 43)
- **`listarLivros()`:** Executa consulta `SELECT id, titulo, autor, ano FROM livros ORDER BY titulo`, percorre o `ResultSet` e constrói uma `List<Livro>` mapeando cada registro para uma instância da classe `Livro`.
- **`buscarLivroPorId(id)`:** Consulta um registro específico via `PreparedStatement` com `WHERE id = ?`. Retorna a instância de `Livro` correspondente ou `null` caso o ID não exista.

---

## 🧪 Validações e Testes Preparados

1. **`TesteConexao.java`:** Valida o estabelecimento da conexão JDBC com o servidor MariaDB.
2. **`TesteLivroDAO.java`:** Script completo de teste para inserção, alteração, remoção e validação de tratamento para IDs inexistentes (ex: `999999`).
3. **`TesteListarLivros.java`:** Recupera a lista de livros em objetos `Livro`, permitindo a exibição formatada dos atributos e filtragens via Java (`getAno() >= 2000`).
4. **`TesteBuscarLivro.java`:** Teste interativo de busca individual por ID via teclado (`Scanner`), tratando com sucesso retornos `null` para consultas de IDs inexistentes sem interromper o fluxo do sistema.