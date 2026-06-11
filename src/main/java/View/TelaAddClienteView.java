package View;

public class TelaAddClienteView extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(TelaAddClienteView.class.getName());

    public TelaAddClienteView() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        addClienteBTN = new javax.swing.JButton();
        nomeClienteInput = new javax.swing.JTextField();
        nomeClienteTXT = new javax.swing.JLabel();
        cpfClienteTXT = new javax.swing.JLabel();
        cpfClienteInput = new javax.swing.JTextField();
        cnpjClienteTXT = new javax.swing.JLabel();
        cnjpClienteInput = new javax.swing.JTextField();
        telefoneClienteTXT = new javax.swing.JLabel();
        telefoneClienteInput = new javax.swing.JTextField();
        emailClienteTXT = new javax.swing.JLabel();
        emailClienteInput = new javax.swing.JTextField();
        cnpjClienteTXT3 = new javax.swing.JLabel();
        cnjpClienteInput3 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        observacaoClienteTXT = new javax.swing.JLabel();
        observacaoClienteInput = new javax.swing.JTextField();

        //setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Adicionar Cliente");

        addClienteBTN.setText("Adicionar");

        nomeClienteTXT.setText("Nome:");

        cpfClienteTXT.setText("CPF:");

        cnpjClienteTXT.setText("CNPJ (Pessoa jurídica)");

        telefoneClienteTXT.setText("Telefone");

        emailClienteTXT.setText("Email");

        cnpjClienteTXT3.setText("Endereço (Rua/avenida xxx, número: xxx, Bairro xxx, Cidade xxx - UF xxx, CEP 00000-000)");


        observacaoClienteTXT.setText("Observação");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGap(49, 49, 49).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(nomeClienteTXT).addComponent(telefoneClienteTXT).addComponent(nomeClienteInput, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(telefoneClienteInput, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(jLabel2).addGap(68, 68, 68)).addGroup(layout.createSequentialGroup().addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addComponent(cnpjClienteTXT3).addGap(0, 21, Short.MAX_VALUE)).addComponent(cnjpClienteInput3))))).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(cpfClienteTXT).addGroup(layout.createSequentialGroup().addComponent(cpfClienteInput, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED).addComponent(observacaoClienteTXT)).addGroup(layout.createSequentialGroup().addGap(276, 276, 276).addComponent(jLabel1))).addGap(0, 0, Short.MAX_VALUE)))).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false).addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addContainerGap().addComponent(emailClienteInput, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)).addGroup(layout.createSequentialGroup().addGap(49, 49, 49).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addComponent(cnjpClienteInput).addGap(11, 11, 11)).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(cnpjClienteTXT).addComponent(emailClienteTXT)).addGap(158, 158, 158))))).addComponent(observacaoClienteInput))).addContainerGap()).addGroup(layout.createSequentialGroup().addGap(283, 283, 283).addComponent(addClienteBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE).addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addComponent(jLabel1).addGap(82, 82, 82).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(cnpjClienteTXT3).addComponent(nomeClienteTXT)).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(nomeClienteInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(cnjpClienteInput3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)).addGap(2, 2, 2).addComponent(jLabel2).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(cpfClienteTXT).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING).addComponent(cpfClienteInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(observacaoClienteTXT)).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addComponent(cnpjClienteTXT).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(cnjpClienteInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED).addComponent(telefoneClienteTXT).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(telefoneClienteInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(emailClienteTXT).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(emailClienteInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)).addComponent(observacaoClienteInput, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)).addGap(52, 52, 52).addComponent(addClienteBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE).addContainerGap(79, Short.MAX_VALUE)));

        pack();
    }

    public static void main(String args[]) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(() -> new TelaAddClienteView().setVisible(true));
    }

    private javax.swing.JButton addClienteBTN;
    private javax.swing.JTextField cnjpClienteInput;
    private javax.swing.JTextField cnjpClienteInput3;
    private javax.swing.JLabel cnpjClienteTXT;
    private javax.swing.JLabel cnpjClienteTXT3;
    private javax.swing.JTextField cpfClienteInput;
    private javax.swing.JLabel cpfClienteTXT;
    private javax.swing.JTextField emailClienteInput;
    private javax.swing.JLabel emailClienteTXT;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField nomeClienteInput;
    private javax.swing.JLabel nomeClienteTXT;
    private javax.swing.JTextField observacaoClienteInput;
    private javax.swing.JLabel observacaoClienteTXT;
    private javax.swing.JTextField telefoneClienteInput;
    private javax.swing.JLabel telefoneClienteTXT;
}

