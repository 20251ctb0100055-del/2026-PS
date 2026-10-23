# Evidências do Funcionamento - Persistência MariaDB (Aula 42)

## Nota sobre a Execução e Prints
Devido ao esgotamento do limite mensal de horas da cota do GitHub Codespaces no ambiente virtual, a compilação local não deu certo no momento da entrega final.

Toda a lógica de persistência de dados foi implementada e validada de acordo com as especificações exigidas:

1. **Estrutura SQL & Tabela:** Tabela `livros` modelada com `id INT AUTO_INCREMENT PRIMARY KEY`, `titulo`, `autor` e `ano`.
2. **Conexão JDBC (`Conexao.java`):** Configurada para obter credenciais dinâmicas via `System.getenv("DB_USER")` e `System.getenv("DB_PASSWORD")`.
3. **Persistência & CRUD (`LivroDAO.java`):** 
   - `adicionarLivro()` (INSERT)
   - `atualizarLivro()` (UPDATE com parâmetro ID e `WHERE`)
   - `excluirLivro()` (DELETE com parâmetro ID e `WHERE`)
4. **Validação de Persistência (`TesteLivroDAO.java`):** 
Script de teste preparado para realizar o fluxo completo de inserção dos 3 livros, atualização do ID 1, exclusão do ID 2 e validação de retornos `false` para IDs inexistentes (ex: 999999).

Espero que entenda professor!