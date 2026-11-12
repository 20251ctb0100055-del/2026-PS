/*
 * Disciplina: 2026-PS
 * Projeto   : bibliotech
 * Arquivo   : TelaLivros.java
 * Autor     : João Pedro Mauda
 * Descricao : Interface moderna do Catálogo BiblioTech com design aprimorado, hover nos cards e busca por Título/ID.
 */
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.Collections;
import java.util.List;

public class TelaLivros extends JFrame {

    private final LivroDAO dao = new LivroDAO();
    private final JPanel painelListaCards = new JPanel();
    private final JTextField txtPesquisa = new JTextField(16);
    private final JTextField txtBuscaId = new JTextField(4);

    // Paleta de Cores Moderna
    private static final Color COR_FUNDO = new Color(241, 245, 249);
    private static final Color COR_HEADER = new Color(30, 41, 59);
    private static final Color COR_PRIMARIA = new Color(37, 99, 235);
    private static final Color COR_PRIMARIA_HOVER = new Color(29, 78, 216);
    private static final Color COR_SECUNDARIA = new Color(71, 85, 105);
    private static final Color COR_CARD = Color.WHITE;
    private static final Color COR_CARD_HOVER = new Color(248, 250, 252);
    private static final Color COR_BORDA = new Color(226, 232, 240);

    public TelaLivros() {
        setTitle("Catálogo BiblioTech");
        setSize(780, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel painelPrincipal = new JPanel(new BorderLayout(0, 15));
        painelPrincipal.setBackground(COR_FUNDO);

        // --- BANNER SUPERIOR ESTILIZADO ---
        JPanel bannerHeader = new JPanel(new BorderLayout());
        bannerHeader.setBackground(COR_HEADER);
        bannerHeader.setBorder(new EmptyBorder(22, 28, 22, 28));

        JPanel textosHeader = new JPanel();
        textosHeader.setLayout(new BoxLayout(textosHeader, BoxLayout.Y_AXIS));
        textosHeader.setOpaque(false);

        JLabel lblTitulo = new JLabel("📖 Catálogo BiblioTech");
        lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 24));
        lblTitulo.setForeground(Color.WHITE);

        JLabel lblSubtitulo = new JLabel("Acervo digital • Pesquise por título, filtre por ID ou atualize capas");
        lblSubtitulo.setFont(new Font("SansSerif", Font.PLAIN, 13));
        lblSubtitulo.setForeground(new Color(148, 163, 184));

        textosHeader.add(lblTitulo);
        textosHeader.add(Box.createVerticalStrut(5));
        textosHeader.add(lblSubtitulo);
        bannerHeader.add(textosHeader, BorderLayout.WEST);

        painelPrincipal.add(bannerHeader, BorderLayout.NORTH);

        // --- ÁREA DE CONTEÚDO ---
        JPanel painelConteudo = new JPanel(new BorderLayout(0, 15));
        painelConteudo.setOpaque(false);
        painelConteudo.setBorder(new EmptyBorder(0, 24, 20, 24));

        // --- BARRA DE PESQUISA MODERNA ---
        JPanel painelBusca = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        painelBusca.setBackground(Color.WHITE);
        painelBusca.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(COR_BORDA, 1, true),
            new EmptyBorder(6, 12, 6, 12)
        ));

        JLabel lblIconeBusca = new JLabel("🔎");
        lblIconeBusca.setFont(new Font("SansSerif", Font.PLAIN, 15));

        estilizarCampoTexto(txtPesquisa, "Digite o título do livro...");
        JButton btnPesquisar = criarBotaoEstilizado("Pesquisar", COR_PRIMARIA, COR_PRIMARIA_HOVER);

        JSeparator separador = new JSeparator(SwingConstants.VERTICAL);
        separador.setPreferredSize(new Dimension(2, 26));
        separador.setForeground(COR_BORDA);

        JLabel lblId = new JLabel("ID:");
        lblId.setFont(new Font("SansSerif", Font.BOLD, 13));
        lblId.setForeground(COR_SECUNDARIA);

        estilizarCampoTexto(txtBuscaId, "Nº");
        JButton btnBuscarId = criarBotaoEstilizado("Buscar ID", COR_SECUNDARIA, new Color(51, 65, 85));

        JButton btnLimpar = criarBotaoEstilizado("Todos", new Color(226, 232, 240), new Color(203, 213, 225));
        btnLimpar.setForeground(COR_HEADER);

        painelBusca.add(lblIconeBusca);
        painelBusca.add(txtPesquisa);
        painelBusca.add(btnPesquisar);
        painelBusca.add(Box.createHorizontalStrut(4));
        painelBusca.add(separador);
        painelBusca.add(Box.createHorizontalStrut(4));
        painelBusca.add(lblId);
        painelBusca.add(txtBuscaId);
        painelBusca.add(btnBuscarId);
        painelBusca.add(btnLimpar);

        painelConteudo.add(painelBusca, BorderLayout.NORTH);

        // --- LISTA DE CARDS COM SCROLL ---
        painelListaCards.setLayout(new BoxLayout(painelListaCards, BoxLayout.Y_AXIS));
        painelListaCards.setBackground(COR_FUNDO);

        JScrollPane scrollPane = new JScrollPane(painelListaCards);
        scrollPane.setBorder(null);
        scrollPane.getViewport().setBackground(COR_FUNDO);
        scrollPane.getVerticalScrollBar().setUnitIncrement(14);
        painelConteudo.add(scrollPane, BorderLayout.CENTER);

        painelPrincipal.add(painelConteudo, BorderLayout.CENTER);

        // Ações
        btnPesquisar.addActionListener(e -> buscarLivros());
        txtPesquisa.addActionListener(e -> buscarLivros());
        btnBuscarId.addActionListener(e -> buscarLivroPorId());
        txtBuscaId.addActionListener(e -> buscarLivroPorId());
        btnLimpar.addActionListener(e -> {
            txtPesquisa.setText("");
            txtBuscaId.setText("");
            carregarLivros(dao.listarLivros());
        });

        add(painelPrincipal);
        carregarLivros(dao.listarLivros());
    }

    private void estilizarCampoTexto(JTextField campo, String tooltip) {
        campo.setFont(new Font("SansSerif", Font.PLAIN, 14));
        campo.setToolTipText(tooltip);
        campo.setBackground(new Color(248, 250, 252));
        campo.setForeground(COR_HEADER);
        campo.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(203, 213, 225), 1, true),
            new EmptyBorder(6, 10, 6, 10)
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
        btn.setBorder(new EmptyBorder(8, 14, 8, 14));

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

    private void carregarLivros(List<Livro> livros) {
        painelListaCards.removeAll();

        if (livros == null || livros.isEmpty()) {
            JPanel painelVazio = new JPanel(new GridBagLayout());
            painelVazio.setBackground(COR_FUNDO);
            JLabel lblVazio = new JLabel("Nenhum livro encontrado no catálogo.");
            lblVazio.setFont(new Font("SansSerif", Font.BOLD, 15));
            lblVazio.setForeground(new Color(100, 116, 139));
            painelVazio.add(lblVazio);
            painelListaCards.add(painelVazio);
        } else {
            for (Livro livro : livros) {
                painelListaCards.add(criarCardLivro(livro));
                painelListaCards.add(Box.createVerticalStrut(14));
            }
        }

        painelListaCards.revalidate();
        painelListaCards.repaint();
    }

    private JPanel criarCardLivro(Livro livro) {
        // Card com borda esquerda de destaque e efeito hover
        JPanel card = new JPanel(new BorderLayout(18, 0));
        card.setBackground(COR_CARD);
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(1, 5, 1, 1, COR_PRIMARIA),
            new EmptyBorder(15, 16, 15, 18)
        ));
        card.setPreferredSize(new Dimension(680, 165));
        card.setMaximumSize(new Dimension(Integer.MAX_VALUE, 165));

        card.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                card.setBackground(COR_CARD_HOVER);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                card.setBackground(COR_CARD);
            }
        });

        // --- CAPA À ESQUERDA COM MOLDURA ---
        JLabel lblCapa = new JLabel();
        lblCapa.setPreferredSize(new Dimension(88, 128));
        lblCapa.setHorizontalAlignment(SwingConstants.CENTER);
        lblCapa.setBorder(BorderFactory.createLineBorder(COR_BORDA, 1));

        ImageIcon iconeCapa = carregarImagem(livro.getCapa());
        if (iconeCapa != null) {
            lblCapa.setIcon(iconeCapa);
        } else {
            lblCapa.setIcon(new ImageIcon(gerarCapaIlustrativa(livro.getTitulo(), livro.getAutor(), 88, 128)));
        }
        card.add(lblCapa, BorderLayout.WEST);

        // --- INFORMAÇÕES AO CENTRO ---
        JPanel painelInfo = new JPanel();
        painelInfo.setLayout(new BoxLayout(painelInfo, BoxLayout.Y_AXIS));
        painelInfo.setOpaque(false);

        JLabel lblTitulo = new JLabel(livro.getTitulo());
        lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 18));
        lblTitulo.setForeground(COR_HEADER);
        lblTitulo.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel lblAutor = new JLabel("por " + livro.getAutor());
        lblAutor.setFont(new Font("SansSerif", Font.PLAIN, 14));
        lblAutor.setForeground(new Color(100, 116, 139));
        lblAutor.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Badges Estilizados (Ano + ID)
        JPanel painelBadges = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        painelBadges.setOpaque(false);
        painelBadges.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel lblAno = criarBadge("📅 " + livro.getAno(), new Color(239, 246, 255), new Color(29, 78, 216));
        JLabel lblBadgeId = criarBadge("ID #" + livro.getId(), new Color(241, 245, 249), new Color(71, 85, 105));

        painelBadges.add(lblAno);
        painelBadges.add(Box.createHorizontalStrut(8));
        painelBadges.add(lblBadgeId);

        // Botão de ação
        JButton btnDetalhes = criarBotaoEstilizado("Alterar / Ver Capa", COR_PRIMARIA, COR_PRIMARIA_HOVER);
        btnDetalhes.setAlignmentX(Component.LEFT_ALIGNMENT);
        btnDetalhes.addActionListener(e -> selecionarCapaParaLivro(livro));

        painelInfo.add(lblTitulo);
        painelInfo.add(Box.createVerticalStrut(3));
        painelInfo.add(lblAutor);
        painelInfo.add(Box.createVerticalStrut(10));
        painelInfo.add(painelBadges);
        painelInfo.add(Box.createVerticalStrut(12));
        painelInfo.add(btnDetalhes);

        card.add(painelInfo, BorderLayout.CENTER);

        return card;
    }

    private JLabel criarBadge(String texto, Color fundo, Color textoCor) {
        JLabel badge = new JLabel("  " + texto + "  ");
        badge.setFont(new Font("SansSerif", Font.BOLD, 11));
        badge.setForeground(textoCor);
        badge.setBackground(fundo);
        badge.setOpaque(true);
        badge.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(203, 213, 225), 1, true),
            new EmptyBorder(3, 6, 3, 6)
        ));
        return badge;
    }

    private Image gerarCapaIlustrativa(String titulo, String autor, int largura, int altura) {
        BufferedImage img = new BufferedImage(largura, altura, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = img.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        Color corFundo = new Color(30, 58, 95);
        if (titulo.toLowerCase().contains("cortiço") || titulo.toLowerCase().contains("cortico")) {
            corFundo = new Color(145, 80, 50);
        } else if (autor.toLowerCase().contains("machado")) {
            corFundo = new Color(40, 75, 60);
        }

        g2.setColor(corFundo);
        g2.fillRect(0, 0, largura, altura);

        g2.setColor(new Color(225, 205, 140, 180));
        g2.drawRect(5, 5, largura - 11, altura - 11);

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

        int centroX = largura / 2;
        int centroY = altura / 2 + 10;
        Polygon losango = new Polygon();
        losango.addPoint(centroX, centroY - 8);
        losango.addPoint(centroX + 7, centroY);
        losango.addPoint(centroX, centroY + 8);
        losango.addPoint(centroX - 7, centroY);
        g2.fill(losango);

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
            Image reduzida = img.getScaledInstance(88, 128, Image.SCALE_SMOOTH);
            return new ImageIcon(reduzida);
        } catch (IOException e) {
            return null;
        }
    }

    private void buscarLivros() {
        txtBuscaId.setText("");
        String termo = txtPesquisa.getText().trim();
        if (termo.isEmpty()) {
            carregarLivros(dao.listarLivros());
        } else {
            carregarLivros(dao.buscarLivrosPorTitulo(termo));
        }
    }

    private void buscarLivroPorId() {
        txtPesquisa.setText("");
        String textoId = txtBuscaId.getText().trim();
        if (textoId.isEmpty()) {
            carregarLivros(dao.listarLivros());
            return;
        }

        try {
            int id = Integer.parseInt(textoId);
            Livro encontrado = null;
            for (Livro l : dao.listarLivros()) {
                if (l.getId() == id) {
                    encontrado = l;
                    break;
                }
            }

            if (encontrado != null) {
                carregarLivros(Collections.singletonList(encontrado));
            } else {
                carregarLivros(Collections.emptyList());
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Digite um número de ID válido.");
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
                carregarLivros(dao.listarLivros());
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