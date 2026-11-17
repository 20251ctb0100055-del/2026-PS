import java.time.Year;

public class ValidadorLivro {

    public static void validar(String titulo, String autor, int ano)
            throws LivroInvalidoException {

        if (titulo == null || titulo.isBlank()) {
            throw new LivroInvalidoException("O título é obrigatório.");
        }

        if (titulo.trim().length() < 3) {
            throw new LivroInvalidoException("O título deve ter pelo menos 3 caracteres.");
        }

        if (autor == null || autor.isBlank()) {
            throw new LivroInvalidoException("O autor é obrigatório.");
        }

        if (ano < 1450 || ano > Year.now().getValue() + 1) {
            throw new LivroInvalidoException("Informe um ano válido.");
        }
    }
}