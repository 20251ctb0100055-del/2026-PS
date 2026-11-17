import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/*
 * Disciplina: 2026-PS
 * Projeto   : bibliotech
 * Arquivo   : LivroDAO.java
 * Autor     : João Pedro Mauda
 * Descricao : DAO do Livro atualizado para a Aula 46 com propagacao de PersistenciaException.
 */
public class LivroDAO {

    // Atualiza apenas o caminho da capa
    public boolean atualizarCapa(int id, String caminho) {
        String sql = "UPDATE livros SET capa = ? WHERE id = ?";
        try (Connection conexao = Conexao.conectar();
             PreparedStatement comando = conexao.prepareStatement(sql)) {
            comando.setString(1, caminho);
            comando.setInt(2, id);
            return comando.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new PersistenciaException("Não foi possível atualizar a capa do livro.", e);
        }
    }

    // Sobrecarga para adicionar livro informando capa
    public boolean adicionarLivro(String titulo, String autor, int ano, String capa) {
        String sql = "INSERT INTO livros (titulo, autor, ano, capa) VALUES (?, ?, ?, ?)";
        try (Connection conexao = Conexao.conectar();
             PreparedStatement comando = conexao.prepareStatement(sql)) {
            comando.setString(1, titulo);
            comando.setString(2, autor);
            comando.setInt(3, ano);
            comando.setString(4, capa);
            return comando.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new PersistenciaException("Não foi possível cadastrar o livro.", e);
        }
    }

    // Adicionar livro sem capa (mantém compatibilidade)
    public boolean adicionarLivro(String titulo, String autor, int ano) {
        return adicionarLivro(titulo, autor, ano, null);
    }

    public boolean atualizarLivro(int id, String titulo, String autor, int ano) {
        String sql = "UPDATE livros SET titulo = ?, autor = ?, ano = ? WHERE id = ?";
        try (Connection conexao = Conexao.conectar();
             PreparedStatement comando = conexao.prepareStatement(sql)) {
            comando.setString(1, titulo);
            comando.setString(2, autor);
            comando.setInt(3, ano);
            comando.setInt(4, id);
            return comando.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new PersistenciaException("Não foi possível atualizar os dados do livro.", e);
        }
    }

    public boolean excluirLivro(int id) {
        String sql = "DELETE FROM livros WHERE id = ?";
        try (Connection conexao = Conexao.conectar();
             PreparedStatement comando = conexao.prepareStatement(sql)) {
            comando.setInt(1, id);
            return comando.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new PersistenciaException("Não foi possível excluir o livro.", e);
        }
    }

    public List<Livro> listarLivros() {
        List<Livro> livros = new ArrayList<>();
        String sql = "SELECT id, titulo, autor, ano, capa FROM livros ORDER BY titulo";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement comando = conexao.prepareStatement(sql);
             ResultSet resultado = comando.executeQuery()) {

            while (resultado.next()) {
                Livro livro = new Livro(
                    resultado.getInt("id"),
                    resultado.getString("titulo"),
                    resultado.getString("autor"),
                    resultado.getInt("ano"),
                    resultado.getString("capa")
                );
                livros.add(livro);
            }
        } catch (SQLException e) {
            throw new PersistenciaException("Não foi possível consultar os livros.", e);
        }
        return livros;
    }

    public Livro buscarLivroPorId(int id) {
        String sql = "SELECT id, titulo, autor, ano, capa FROM livros WHERE id = ?";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement comando = conexao.prepareStatement(sql)) {

            comando.setInt(1, id);

            try (ResultSet resultado = comando.executeQuery()) {
                if (resultado.next()) {
                    return new Livro(
                        resultado.getInt("id"),
                        resultado.getString("titulo"),
                        resultado.getString("autor"),
                        resultado.getInt("ano"),
                        resultado.getString("capa")
                    );
                }
            }
        } catch (SQLException e) {
            throw new PersistenciaException("Não foi possível buscar o livro pelo identificador.", e);
        }
        return null;
    }

    public List<Livro> buscarLivrosPorTitulo(String titulo) {
        List<Livro> livros = new ArrayList<>();
        String sql = "SELECT id, titulo, autor, ano, capa FROM livros WHERE titulo LIKE ? ORDER BY titulo";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement comando = conexao.prepareStatement(sql)) {

            comando.setString(1, "%" + titulo + "%");

            try (ResultSet resultado = comando.executeQuery()) {
                while (resultado.next()) {
                    Livro livro = new Livro(
                        resultado.getInt("id"),
                        resultado.getString("titulo"),
                        resultado.getString("autor"),
                        resultado.getInt("ano"),
                        resultado.getString("capa")
                    );
                    livros.add(livro);
                }
            }
        } catch (SQLException e) {
            throw new PersistenciaException("Não foi possível buscar os livros pelo título.", e);
        }
        return livros;
    }
}