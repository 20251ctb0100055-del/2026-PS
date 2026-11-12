/*
 * Disciplina: 2026-PS
 * Projeto   : bibliotech
 * Arquivo   : TelaLivros.java
 * Autor     : João Pedro Mauda
 * Descricao : Interface do Catálogo BiblioTech com capas estilizadas em código e suporte a imagens personalizadas.
 */
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.List;

public class TelaLivros extends JFrame {

    private final LivroDAO dao = new LivroDAO();
    private final JPanel painelListaCards = new JPanel();
    private final JTextField txtPesquisa = new JTextField(18);

    public TelaLivros() {
        setTitle("Catálogo BiblioTech");
        setSize(700, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Painel Principal com fundo claro
        JPanel painelPrincipal = new JPanel(new BorderLayout(15, 15));
        painelPrincipal.setBackground(new Color(245, 247, 250));
        painelPrincipal.setBorder(new EmptyBorder(20, 20, 20, 20));

        // --- CABEÇALHO ---
        JPanel painelTopo = new JPanel();
        painelTopo.setLayout(new BoxLayout(painelTopo, BoxLayout.Y_AXIS));
        painelTopo.setOpaque(false);

        JLabel lblTitulo = new JLabel("📖 Catálogo BiblioTech");
        lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 22));
        lblTitulo.setForeground(new Color(30, 41, 59));

        JLabel lblSubtitulo = new JLabel("Explore os livros disponíveis na biblioteca");
        lblSubtitulo.setFont(new Font("SansSerif", Font.PLAIN, 13));
        lblSubtitulo.setForeground(new Color(100, 116, 139));

        painelTopo.add(lblTitulo);
        painelTopo.add(Box.createVerticalStrut(4));
        painelTopo.add(lblSubtitulo);
        painelTopo.add(Box.createVerticalStrut(15));

        // --- BARRA DE PESQUISA ---
        JPanel painelBusca = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        painelBusca.setBackground(new Color(235, 240, 248));
        painelBusca.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(226, 232, 240), 1),
            new EmptyBorder(5, 5, 5, 5)
        ));

        JLabel lblIconeBusca = new JLabel("🔎");
        txtPesquisa.setFont(new Font("SansSerif", Font.PLAIN, 14));
        txtPesquisa.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(203, 213, 225), 1),
            new EmptyBorder(6, 8, 6, 8)
        ));

        JButton btnPesquisar = new JButton("Pesquisar");
        btnPesquisar.setFont(new Font("SansSerif", Font.BOLD, 13));
        btnPesquisar.setBackground(new Color(59, 89, 152));
        btnPesquisar.setForeground(Color.WHITE);
        btnPesquisar.setFocusPainted(false);
        btnPesquisar.setBorder(new EmptyBorder(8, 16, 8, 16));

        painelBusca.add(lblIconeBusca);
        painelBusca.add(txtPesquisa);
        painelBusca.add(btnPesquisar);

        painelTopo.add(painelBusca);
        painelPrincipal.add(painelTopo, BorderLayout.NORTH);

        // --- LISTA DE CARDS (CENTRO COM SCROLL) ---
        painelListaCards.setLayout(new BoxLayout(painelListaCards, BoxLayout.Y_AXIS));
        painelListaCards.setBackground(new Color(245, 247, 250));

        JScrollPane scrollPane = new JScrollPane(painelListaCards);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(12);
        painelPrincipal.add(scrollPane, BorderLayout.CENTER);

        // Ações
        btnPesquisar.addActionListener(e -> buscarLivros());
        txtPesquisa.addActionListener(e -> buscarLivros());

        add(painelPrincipal);

        // Carregar a lista inicial
        carregarLivros(dao.listarLivros());
    }

    private void carregarLivros(List<Livro> livros) {
        painelListaCards.removeAll();

        if (livros.isEmpty()) {
            JLabel lblVazio = new JLabel("Nenhum livro encontrado.", SwingConstants.CENTER);
            lblVazio.setFont(new Font("SansSerif", Font.PLAIN, 14));
            lblVazio.setForeground(new Color(100, 116, 139));
            painelListaCards.add(lblVazio);
        } else {
            for (Livro livro : livros) {
                painelListaCards.add(criarCardLivro(livro));
                painelListaCards.add(Box.createVerticalStrut(12));
            }
        }

        painelListaCards.revalidate();
        painelListaCards.repaint();
    }

    private JPanel criarCardLivro(Livro livro) {
        JPanel card = new JPanel(new BorderLayout(15, 0));
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(226, 232, 240), 1, true),
            new EmptyBorder(12, 15, 12, 15)
        ));
        card.setMaximumSize(new Dimension(Integer.MAX_VALUE, 160));

        // --- CAPA À ESQUERDA ---
        JLabel lblCapa = new JLabel();
        lblCapa.setPreferredSize(new Dimension(85, 125));
        lblCapa.setHorizontalAlignment(SwingConstants.CENTER);

        ImageIcon iconeCapa = carregarImagem(livro.getCapa());
        if (iconeCapa != null) {
            lblCapa.setIcon(iconeCapa);
        } else {
            // Desenha a capa estilizada com fundo colorido igual ao print do professor
            lblCapa.setIcon(new ImageIcon(gerarCapaIlustrativa(livro.getTitulo(), livro.getAutor(), 85, 125)));
        }
        card.add(lblCapa, BorderLayout.WEST);

        // --- INFORMAÇÕES AO CENTRO ---
        JPanel painelInfo = new JPanel();
        painelInfo.setLayout(new BoxLayout(painelInfo, BoxLayout.Y_AXIS));
        painelInfo.setOpaque(false);

        JLabel lblTitulo = new JLabel(livro.getTitulo());
        lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 17));
        lblTitulo.setForeground(new Color(30, 41, 59));

        JLabel lblAutor = new JLabel(livro.getAutor());
        lblAutor.setFont(new Font("SansSerif", Font.PLAIN, 14));
        lblAutor.setForeground(new Color(100, 116, 139));

        // Badge de Ano
        JLabel lblAno = new JLabel("  " + livro.getAno() + "  ");
        lblAno.setFont(new Font("SansSerif", Font.PLAIN, 12));
        lblAno.setForeground(new Color(71, 85, 105));
        lblAno.setBackground(new Color(241, 245, 249));
        lblAno.setOpaque(true);
        lblAno.setBorder(BorderFactory.createLineBorder(new Color(226, 232, 240), 1));

        // Botão "Ver detalhes" (também permite alterar capa ao clicar)
        JButton btnDetalhes = new JButton("Ver detalhes");
        btnDetalhes.setFont(new Font("SansSerif", Font.BOLD, 12));
        btnDetalhes.setBackground(new Color(59, 89, 152));
        btnDetalhes.setForeground(Color.WHITE);
        btnDetalhes.setFocusPainted(false);
        btnDetalhes.addActionListener(e -> selecionarCapaParaLivro(livro));

        painelInfo.add(lblTitulo);
        painelInfo.add(Box.createVerticalStrut(4));
        painelInfo.add(lblAutor);
        painelInfo.add(Box.createVerticalStrut(8));
        painelInfo.add(lblAno);
        painelInfo.add(Box.createVerticalStrut(10));
        painelInfo.add(btnDetalhes);

        card.add(painelInfo, BorderLayout.CENTER);

        return card;
    }

    // Gera a capa no estilo artesanal do exemplo (Verde para Machado de Assis / Marrom para outros)
    private Image gerarCapaIlustrativa(String titulo, String autor, int largura, int altura) {
        BufferedImage img = new BufferedImage(largura, altura, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = img.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Seleção de cor do fundo
        Color corFundo = new Color(55, 90, 70); // Verde escuro elegante por padrão
        if (titulo.toLowerCase().contains("cortiço") || titulo.toLowerCase().contains("cortico")) {
            corFundo = new Color(145, 80, 50); // Marrom terracota
        } else if (autor.toLowerCase().contains("machado")) {
            corFundo = new Color(40, 75, 60);
        }

        g2.setColor(corFundo);
        g2.fillRect(0, 0, largura, altura);

        // Borda dourada sutil interna
        g2.setColor(new Color(225, 205, 140, 180));
        g2.drawRect(4, 4, largura - 9, altura - 9);

        // Texto do Título centralizado
        g2.setColor(new Color(245, 235, 200));
        g2.setFont(new Font("Serif", Font.BOLD, 11));

        FontMetrics fm = g2.getFontMetrics();
        String[] palavras = titulo.toUpperCase().split(" ");
        int y = 35;

        for (String pal : palavras) {
            int x = (largura - fm.stringWidth(pal)) / 2;
            g2.drawString(pal, Math.max(x, 6), y);
            y += 14;
        }

        // Desenha o detalhe geométrico (losango/estrela) no centro
        int centroX = largura / 2;
        int centroY = altura / 2 + 10;
        Polygon losango = new Polygon();
        losango.addPoint(centroX, centroY - 8);
        losango.addPoint(centroX + 7, centroY);
        losango.addPoint(centroX, centroY + 8);
        losango.addPoint(centroX - 7, centroY);
        g2.fill(losango);

        // Nome do autor no rodapé
        g2.setFont(new Font("SansSerif", Font.PLAIN, 8));
        FontMetrics fmAutor = g2.getFontMetrics();
        String autorReduzido = autor.toUpperCase();
        if (autorReduzido.length() > 14) autorReduzido = autorReduzido.substring(0, 14);
        int xAutor = (largura - fmAutor.stringWidth(autorReduzido)) / 2;
        g2.drawString(autorReduzido, Math.max(xAutor, 4), altura - 10);

        g2.dispose();
        return img;
    }

    private ImageIcon carregarImagem(String caminho) {
        if (caminho == null || caminho.isBlank()) return null;
        File arquivo = new File(caminho);
        if (!arquivo.isFile()) return null;

        try {
            BufferedImage img = ImageIO.read(arquivo);
            if (img == null) return null;
            Image reduzida = img.getScaledInstance(85, 125, Image.SCALE_SMOOTH);
            return new ImageIcon(reduzida);
        } catch (IOException e) {
            return null;
        }
    }

    private void buscarLivros() {
        String termo = txtPesquisa.getText().trim();
        if (termo.isEmpty()) {
            carregarLivros(dao.listarLivros());
        } else {
            carregarLivros(dao.buscarLivrosPorTitulo(termo));
        }
    }

    private void selecionarCapaParaLivro(Livro livro) {
        JFileChooser seletor = new JFileChooser();
        seletor.setDialogTitle("Selecione a capa para: " + livro.getTitulo());
        seletor.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Imagens JPG e PNG", "jpg", "jpeg", "png"));

        if (seletor.showOpenDialog(this) != JFileChooser.APPROVE_OPTION) return;

        java.nio.file.Path destino = null;
        try {
            destino = java.nio.file.Path.of(ImagemLivro.salvar(seletor.getSelectedFile().toPath(), livro.getId()));
            boolean ok = dao.atualizarCapa(livro.getId(), destino.toString().replace('\\', '/'));

            if (ok) {
                JOptionPane.showMessageDialog(this, "Capa atualizada com sucesso!");
                buscarLivros();
            } else {
                java.nio.file.Files.deleteIfExists(destino);
                JOptionPane.showMessageDialog(this, "Erro ao atualizar banco.");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TelaLivros().setVisible(true));
    }
}