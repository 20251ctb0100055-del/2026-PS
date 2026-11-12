/*
 * Disciplina: 2026-PS
 * Projeto   : bibliotech
 * Arquivo   : TelaCadastroLivro.java
 * Autor     : João Pedro Mauda
 * Descricao : Segunda tela personalizada - Formulario de cadastro com previa da capa.
 */
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.List;

public class TelaCadastroLivro extends JFrame {

    private final LivroDAO dao = new LivroDAO();
    private final JTextField txtTitulo = new JTextField(20);
    private final JTextField txtAutor = new JTextField(20);
    private final JTextField txtAno = new JTextField(6);
    private final JLabel lblPrevia = new JLabel("Sem prévia", SwingConstants.CENTER);
    private File arquivoImagemSelecionado = null;

    public TelaCadastroLivro() {
        setTitle("BiblioTech - Cadastro de Livros com Prévia");
        setSize(520, 420);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel painelPrincipal = new JPanel(new BorderLayout(10, 10));
        painelPrincipal.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        painelPrincipal.setBackground(new Color(248, 250, 252));

        JPanel painelCampos = new JPanel(new GridLayout(4, 2, 8, 8));
        painelCampos.setOpaque(false);

        painelCampos.add(new JLabel("Título:"));
        painelCampos.add(txtTitulo);
        painelCampos.add(new JLabel("Autor:"));
        painelCampos.add(txtAutor);
        painelCampos.add(new JLabel("Ano de Publicação:"));
        painelCampos.add(txtAno);

        JButton btnSelecionarImagem = new JButton("Escolher Capa...");
        painelCampos.add(btnSelecionarImagem);
        painelCampos.add(new JLabel());

        lblPrevia.setPreferredSize(new Dimension(120, 160));
        lblPrevia.setBorder(BorderFactory.createLineBorder(new Color(203, 213, 225), 2));
        lblPrevia.setOpaque(true);
        lblPrevia.setBackground(Color.WHITE);

        JPanel painelCentral = new JPanel(new BorderLayout(10, 10));
        painelCentral.setOpaque(false);
        painelCentral.add(painelCampos, BorderLayout.CENTER);

        JPanel painelPrevia = new JPanel(new FlowLayout(FlowLayout.CENTER));
        painelPrevia.setOpaque(false);
        painelPrevia.add(lblPrevia);
        painelCentral.add(painelPrevia, BorderLayout.EAST);

        JButton btnSalvar = new JButton("Cadastrar Livro");
        btnSalvar.setFont(new Font("SansSerif", Font.BOLD, 14));
        btnSalvar.setBackground(new Color(37, 99, 235));
        btnSalvar.setForeground(Color.WHITE);

        painelPrincipal.add(painelCentral, BorderLayout.CENTER);
        painelPrincipal.add(btnSalvar, BorderLayout.SOUTH);

        btnSelecionarImagem.addActionListener(e -> escolherImagem());
        btnSalvar.addActionListener(e -> salvarLivro());

        add(painelPrincipal);
    }

    private void escolherImagem() {
        JFileChooser seletor = new JFileChooser();
        seletor.setDialogTitle("Selecione a imagem de capa");
        seletor.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Imagens JPG e PNG", "jpg", "jpeg", "png"));

        if (seletor.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            arquivoImagemSelecionado = seletor.getSelectedFile();
            ImageIcon icon = new ImageIcon(arquivoImagemSelecionado.getAbsolutePath());
            Image img = icon.getImage().getScaledInstance(120, 160, Image.SCALE_SMOOTH);
            lblPrevia.setIcon(new ImageIcon(img));
            lblPrevia.setText("");
        }
    }

    private void salvarLivro() {
        String titulo = txtTitulo.getText().trim();
        String autor = txtAutor.getText().trim();
        String anoStr = txtAno.getText().trim();

        if (titulo.isEmpty() || autor.isEmpty() || anoStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            int ano = Integer.parseInt(anoStr);

            // Chamada ajustada para bater com a assinatura do seu LivroDAO
            boolean inserido = dao.adicionarLivro(titulo, autor, ano);
            if (inserido) {
                if (arquivoImagemSelecionado != null) {
                    List<Livro> livros = dao.buscarLivrosPorTitulo(titulo);
                    if (!livros.isEmpty()) {
                        Livro cadastrado = livros.get(0);
                        String caminhoCapa = ImagemLivro.salvar(arquivoImagemSelecionado.toPath(), cadastrado.getId());
                        dao.atualizarCapa(cadastrado.getId(), caminhoCapa);
                    }
                }
                JOptionPane.showMessageDialog(this, "Livro cadastrado com sucesso!");
                dispose();
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Ano inválido.", "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao salvar imagem: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TelaCadastroLivro().setVisible(true));
    }
}