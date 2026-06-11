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
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jLabel2)
                                                .addGap(68, 68, 68))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(325, 325, 325)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(cnpjClienteTXT3)
                                                                .addGap(0, 14, Short.MAX_VALUE))
                                                        .addComponent(cnjpClienteInput3)))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(324, 324, 324)
                                                .addComponent(observacaoClienteInput))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGroup(layout.createSequentialGroup()
                                                                                .addGap(7, 7, 7)
                                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                        .addComponent(cnpjClienteTXT)
                                                                                        .addComponent(emailClienteTXT)))
                                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                                .addGroup(layout.createSequentialGroup()
                                                                                        .addContainerGap()
                                                                                        .addComponent(emailClienteInput, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                        .addComponent(cpfClienteInput, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                        .addComponent(cnjpClienteInput, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                        .addGroup(layout.createSequentialGroup()
                                                                                                .addGap(7, 7, 7)
                                                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                                                        .addComponent(telefoneClienteInput, javax.swing.GroupLayout.DEFAULT_SIZE, 295, Short.MAX_VALUE)
                                                                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                                                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                                                .addComponent(nomeClienteTXT)
                                                                                                                                .addComponent(telefoneClienteTXT))
                                                                                                                        .addGap(218, 218, 218))
                                                                                                                .addComponent(cpfClienteTXT))
                                                                                                        .addComponent(nomeClienteInput))))))
                                                                .addGap(23, 23, 23)
                                                                .addComponent(observacaoClienteTXT))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGap(345, 345, 345)
                                                                .addComponent(jLabel1)))
                                                .addGap(0, 0, Short.MAX_VALUE)))
                                .addContainerGap())
                        .addGroup(layout.createSequentialGroup()
                                .addGap(300, 300, 300)
                                .addComponent(addClienteBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(82, 82, 82)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(cnpjClienteTXT3)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(cnjpClienteInput3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(2, 2, 2)
                                                .addComponent(jLabel2)
                                                .addGap(38, 38, 38)
                                                .addComponent(observacaoClienteTXT)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(observacaoClienteInput, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(nomeClienteTXT)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(nomeClienteInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(8, 8, 8)
                                                .addComponent(cpfClienteTXT)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(cpfClienteInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(cnpjClienteTXT)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(cnjpClienteInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(telefoneClienteTXT)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(telefoneClienteInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(emailClienteTXT)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(emailClienteInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(55, 55, 55)
                                .addComponent(addClienteBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(76, Short.MAX_VALUE))
        );

        pack();
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
