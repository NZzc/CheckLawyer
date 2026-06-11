package View;

public class TelaPrincipalView extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(TelaPrincipalView.class.getName());

    public TelaPrincipalView() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        ClienteBTN = new javax.swing.JButton();
        AudicenciaBTN = new javax.swing.JButton();
        ProcessosBTN = new javax.swing.JButton();
        financeiroBTN = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("CheckLawyer");

        ClienteBTN.setText("Clientes");

        AudicenciaBTN.setText("Audiências");

        ProcessosBTN.setText("Processos");

        financeiroBTN.setText("Financeiro");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(149, 149, 149)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(ProcessosBTN)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 302, Short.MAX_VALUE)
                                                                .addComponent(financeiroBTN))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(ClienteBTN)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(AudicenciaBTN))))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(341, 341, 341)
                                                .addComponent(jLabel1)))
                                .addGap(174, 174, 174))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addComponent(jLabel1)
                                .addGap(156, 156, 156)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(ClienteBTN)
                                        .addComponent(AudicenciaBTN))
                                .addGap(146, 146, 146)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(financeiroBTN)
                                        .addComponent(ProcessosBTN))
                                .addContainerGap(209, Short.MAX_VALUE))
        );

        pack();
    }


    private javax.swing.JButton AudicenciaBTN;
    private javax.swing.JButton ClienteBTN;
    private javax.swing.JButton ProcessosBTN;
    private javax.swing.JButton financeiroBTN;
    private javax.swing.JLabel jLabel1;
}
