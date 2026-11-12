/*
 * Disciplina: 2026-PS
 * Projeto   : bibliotech
 * Arquivo   : TelaCadastroLivro.java
 * Autor     : João Pedro Mauda
 * Descricao : Interface de Cadastro de Livros com layout equilibrado, prévia da capa e paleta unificada.
 */
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.List;

public class TelaCadastroLivro extends JFrame {

    private final LivroDAO dao = new LivroDAO();
    private final JTextField txtTitulo = new JTextField(22);
    private final JTextField txtAutor = new JTextField(22);
    private final JTextField txtAno = new JTextField(10);
    private final JLabel lblPrevia = new JLabel("Sem prévia", SwingConstants.CENTER);
    private final JLabel lblNomeArquivo = new JLabel("Nenhuma imagem", SwingConstants.CENTER);
    private File arquivoImagemSelecionado = null;

    // Mesma paleta de cores da TelaLivros
    private static final Color COR_FUNDO = new Color(241, 245, 249);
    private static final Color COR_HEADER = new Color(30, 41, 59);
    private static final Color COR_PRIMARIA = new Color(37, 99, 235);
    private static final Color COR_PRIMARIA_HOVER = new Color(29, 78, 216);
    private static final Color COR_SECUNDARIA = new Color(71, 85, 105);
    private static final Color COR_BORDA = new Color(226, 232, 240);

    public TelaCadastroLivro() {
        setTitle("BiblioTech - Cadastro de Livros");
        setSize(660, 470);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel painelPrincipal = new JPanel(new BorderLayout());
        painelPrincipal.setBackground(COR_FUNDO);

        // --- CABEÇALHO UNIFICADO ---
        JPanel bannerHeader = new JPanel(new BorderLayout());
        bannerHeader.setBackground(COR_HEADER);
        bannerHeader.setBorder(new EmptyBorder(18, 24, 18, 24));

        JPanel textosHeader = new JPanel();
        textosHeader.setLayout(new BoxLayout(textosHeader, BoxLayout.Y_AXIS));
        textosHeader.setOpaque(false);

        JLabel lblTituloTopo = new JLabel("Cadastro de Novo Livro");
        lblTituloTopo.setFont(new Font("SansSerif", Font.BOLD, 20));
        lblTituloTopo.setForeground(Color.WHITE);

        JLabel lblSubtituloTopo = new JLabel("Informe os dados da obra e selecione uma capa para o catálogo");
        lblSubtituloTopo.setFont(new Font("SansSerif", Font.PLAIN, 12));
        lblSubtituloTopo.setForeground(new Color(148, 163, 184));

        textosHeader.add(lblTituloTopo);
        textosHeader.add(Box.createVerticalStrut(3));
        textosHeader.add(lblSubtituloTopo);
        bannerHeader.add(textosHeader, BorderLayout.WEST);

        painelPrincipal.add(bannerHeader, BorderLayout.NORTH);

        // --- CONTEÚDO CENTRAL ---
        JPanel painelCentral = new JPanel(new BorderLayout(18, 0));
        painelCentral.setOpaque(false);
        painelCentral.setBorder(new EmptyBorder(20, 24, 20, 24));

        // Card Esquerdo: Formulário limpo e bem alinhado
        JPanel cardFormulario = new JPanel(new GridBagLayout());
        cardFormulario.setBackground(Color.WHITE);
        cardFormulario.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(1, 4, 1, 1, COR_PRIMARIA),
            new EmptyBorder(18, 20, 18, 20)
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.weightx = 1.0;

        estilizarCampoTexto(txtTitulo);
        estilizarCampoTexto(txtAutor);
        estilizarCampoTexto(txtAno);

        // Linha 1: Título
        gbc.gridx = 0; gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 4, 0);
        cardFormulario.add(criarLabelCampo("Título"), gbc);

        gbc.gridy = 1;
        gbc.insets = new Insets(0, 0, 12, 0);
        cardFormulario.add(txtTitulo, gbc);

        // Linha 2: Autor
        gbc.gridy = 2;
        gbc.insets = new Insets(0, 0, 4, 0);
        cardFormulario.add(criarLabelCampo("Autor"), gbc);

        gbc.gridy = 3;
        gbc.insets = new Insets(0, 0, 12, 0);
        cardFormulario.add(txtAutor, gbc);

        // Linha 3: Ano + Botão Escolher Capa lado a lado
        JPanel painelLinhaInferior = new JPanel(new GridBagLayout());
        painelLinhaInferior.setOpaque(false);
        GridBagConstraints gbcInf = new GridBagConstraints();
        gbcInf.fill = GridBagConstraints.HORIZONTAL;
        gbcInf.anchor = GridBagConstraints.SOUTH;

        JPanel painelAno = new JPanel(new BorderLayout(0, 4));
        painelAno.setOpaque(false);
        painelAno.add(criarLabelCampo("Ano de Publicação"), BorderLayout.NORTH);
        painelAno.add(txtAno, BorderLayout.CENTER);

        JButton btnSelecionarImagem = criarBotaoEstilizado("Escolher Capa...", COR_SECUNDARIA, new Color(51, 65, 85));

        gbcInf.gridx = 0; gbcInf.gridy = 0; gbcInf.weightx = 0.45;
        gbcInf.insets = new Insets(0, 0, 0, 12);
        painelLinhaInferior.add(painelAno, gbcInf);

        gbcInf.gridx = 1; gbcInf.weightx = 0.55;
        gbcInf.insets = new Insets(0, 0, 0, 0);
        painelLinhaInferior.add(btnSelecionarImagem, gbcInf);

        gbc.gridy = 4;
        gbc.insets = new Insets(4, 0, 0, 0);
        cardFormulario.add(painelLinhaInferior, gbc);

        painelCentral.add(cardFormulario, BorderLayout.CENTER);

        // Card Direito: Prévia da Capa centralizada
        JPanel cardPrevia = new JPanel();
        cardPrevia.setLayout(new BoxLayout(cardPrevia, BoxLayout.Y_AXIS));
        cardPrevia.setBackground(Color.WHITE);
        cardPrevia.setPreferredSize(new Dimension(185, 270));
        cardPrevia.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(COR_BORDA, 1, true),
            new EmptyBorder(14, 14, 14, 14)
        ));

        JLabel lblTituloPrevia = new JLabel("PRÉVIA DA CAPA");
        lblTituloPrevia.setFont(new Font("SansSerif", Font.BOLD, 11));
        lblTituloPrevia.setForeground(COR_SECUNDARIA);
        lblTituloPrevia.setAlignmentX(Component.CENTER_ALIGNMENT);

        lblPrevia.setPreferredSize(new Dimension(120, 165));
        lblPrevia.setMaximumSize(new Dimension(120, 165));
        lblPrevia.setMinimumSize(new Dimension(120, 165));
        lblPrevia.setOpaque(true);
        lblPrevia.setBackground(new Color(248, 250, 252));
        lblPrevia.setForeground(new Color(148, 163, 184));
        lblPrevia.setFont(new Font("SansSerif", Font.PLAIN, 12));
        lblPrevia.setBorder(BorderFactory.createDashedBorder(new Color(203, 213, 225), 3, 3));
        lblPrevia.setAlignmentX(Component.CENTER_ALIGNMENT);

        lblNomeArquivo.setFont(new Font("SansSerif", Font.PLAIN, 11));
        lblNomeArquivo.setForeground(new Color(100, 116, 139));
        lblNomeArquivo.setAlignmentX(Component.CENTER_ALIGNMENT);

        cardPrevia.add(lblTituloPrevia);
        cardPrevia.add(Box.createVerticalStrut(10));
        cardPrevia.add(lblPrevia);
        cardPrevia.add(Box.createVerticalStrut(8));
        cardPrevia.add(lblNomeArquivo);

        painelCentral.add(cardPrevia, BorderLayout.EAST);
        painelPrincipal.add(painelCentral, BorderLayout.CENTER);

        // --- RODAPÉ COM AÇÕES ---
        JPanel painelRodape = new JPanel(new FlowLayout(FlowLayout.RIGHT, 12, 12));
        painelRodape.setBackground(Color.WHITE);
        painelRodape.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, COR_BORDA));

        JButton btnLimpar = criarBotaoEstilizado("Limpar", new Color(226, 232, 240), new Color(203, 213, 225));
        btnLimpar.setForeground(COR_HEADER);

        JButton btnSalvar = criarBotaoEstilizado("Cadastrar Livro", COR_PRIMARIA, COR_PRIMARIA_HOVER);

        painelRodape.add(btnLimpar);
        painelRodape.add(btnSalvar);
        painelRodape.add(Box.createHorizontalStrut(12));

        painelPrincipal.add(painelRodape, BorderLayout.SOUTH);

        // Eventos
        btnSelecionarImagem.addActionListener(e -> escolherImagem());
        btnLimpar.addActionListener(e -> limparFormulario());
        btnSalvar.addActionListener(e -> salvarLivro());

        add(painelPrincipal);
    }

    private JLabel criarLabelCampo(String texto) {
        JLabel lbl = new JLabel(texto);
        lbl.setFont(new Font("SansSerif", Font.BOLD, 12));
        lbl.setForeground(COR_HEADER);
        return lbl;
    }

    private void estilizarCampoTexto(JTextField campo) {
        campo.setFont(new Font("SansSerif", Font.PLAIN, 13));
        campo.setBackground(new Color(248, 250, 252));
        campo.setForeground(COR_HEADER);
        campo.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(203, 213, 225), 1, true),
            new EmptyBorder(7, 10, 7, 10)
        ));
    }

    private JButton criarBotaoEstilizado(String texto, Color corNormal, Color corHover) {
        JButton btn = new JButton(texto);
        btn.setFont(new Font("SansSerif", Font.BOLD, 12));
        btn.setBackground(corNormal);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setBorder(new EmptyBorder(9, 16, 9, 16));

        btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btn.setBackground(corHover);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                btn.setBackground(corNormal);
            }
        });
        return btn;
    }

    private void escolherImagem() {
        JFileChooser seletor = new JFileChooser();
        seletor.setDialogTitle("Selecione a imagem de capa");
        seletor.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Imagens JPG e PNG", "jpg", "jpeg", "png"));

        if (seletor.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            arquivoImagemSelecionado = seletor.getSelectedFile();
            ImageIcon icon = new ImageIcon(arquivoImagemSelecionado.getAbsolutePath());
            Image img = icon.getImage().getScaledInstance(120, 165, Image.SCALE_SMOOTH);
            lblPrevia.setIcon(new ImageIcon(img));
            lblPrevia.setText("");
            lblPrevia.setBorder(BorderFactory.createLineBorder(COR_PRIMARIA, 2));

            String nome = arquivoImagemSelecionado.getName();
            if (nome.length() > 20) nome = nome.substring(0, 17) + "...";
            lblNomeArquivo.setText(nome);
        }
    }

    private void limparFormulario() {
        txtTitulo.setText("");
        txtAutor.setText("");
        txtAno.setText("");
        arquivoImagemSelecionado = null;
        lblPrevia.setIcon(null);
        lblPrevia.setText("Sem prévia");
        lblPrevia.setBorder(BorderFactory.createDashedBorder(new Color(203, 213, 225), 3, 3));
        lblNomeArquivo.setText("Nenhuma imagem");
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

            boolean inserido = dao.adicionarLivro(titulo, autor, ano);
            if (inserido) {
                if (arquivoImagemSelecionado != null) {
                    List<Livro> livros = dao.buscarLivrosPorTitulo(titulo);
                    if (!livros.isEmpty()) {
                        Livro cadastrado = livros.get(livros.size() - 1);
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