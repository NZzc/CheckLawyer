package View;

import javax.swing.*;
import java.awt.*;

public class TelaPrincipalView extends JFrame {

    private JButton AudicenciaBTN;
    private JButton ClienteBTN;
    private JButton ProcessosBTN;
    private JButton financeiroBTN;
    private JLabel jLabel1;

    public TelaPrincipalView() {
        initComponents();

        setTitle("CheckLawyer");
        setSize(800, 600);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void initComponents() {

        jLabel1 = new JLabel("CheckLawyer");

        ClienteBTN = new JButton("Clientes");
        AudicenciaBTN = new JButton("Audiências");
        ProcessosBTN = new JButton("Processos");
        financeiroBTN = new JButton("Financeiro");

        // Fonte do título
        jLabel1.setFont(new Font("Arial", Font.BOLD, 32));

        // Fonte dos botões
        Font fonteBotoes = new Font("Arial", Font.BOLD, 18);

        ClienteBTN.setFont(fonteBotoes);
        AudicenciaBTN.setFont(fonteBotoes);
        ProcessosBTN.setFont(fonteBotoes);
        financeiroBTN.setFont(fonteBotoes);

        // Tamanho dos botões
        Dimension tamanhoBotao = new Dimension(220, 100);

        ClienteBTN.setPreferredSize(tamanhoBotao);
        AudicenciaBTN.setPreferredSize(tamanhoBotao);
        ProcessosBTN.setPreferredSize(tamanhoBotao);
        financeiroBTN.setPreferredSize(tamanhoBotao);

        // Painel principal
        JPanel painelPrincipal = new JPanel();
        painelPrincipal.setLayout(new BorderLayout());

        // Painel do título
        JPanel painelTitulo = new JPanel();
        painelTitulo.add(jLabel1);

        // Painel dos botões (2x2 centralizado)
        JPanel painelBotoes = new JPanel();
        painelBotoes.setLayout(new GridLayout(2, 2, 40, 40));

        painelBotoes.add(ClienteBTN);
        painelBotoes.add(AudicenciaBTN);
        painelBotoes.add(ProcessosBTN);
        painelBotoes.add(financeiroBTN);

        // Painel central para centralizar os botões
        JPanel painelCentro = new JPanel(new GridBagLayout());
        painelCentro.add(painelBotoes);

        painelPrincipal.add(painelTitulo, BorderLayout.NORTH);
        painelPrincipal.add(painelCentro, BorderLayout.CENTER);

        setContentPane(painelPrincipal);
    }

    public JButton getAudicenciaBTN() {
        return AudicenciaBTN;
    }

    public JButton getProcessosBTN() {
        return ProcessosBTN;
    }

    public JButton getFinanceiroBTN() {
        return financeiroBTN;
    }

    public JButton getClienteBTN() {
        return ClienteBTN;
    }
}