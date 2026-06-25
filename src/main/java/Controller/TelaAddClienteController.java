package Controller;

import Dao.ClienteDAO;
import Model.ClienteModel;
import Model.EnderecoModel;
import View.TelaAddClienteView;

public class TelaAddClienteController {
    private TelaAddClienteView telaAddClienteView;
    private ClienteDAO clienteDAO;
    private TelaClienteController telaClienteController;


    public TelaAddClienteController(TelaClienteController telaClienteController) {
        telaAddClienteView = new TelaAddClienteView();
        clienteDAO = new ClienteDAO();
        this.telaClienteController = telaClienteController;

        configurarEventos();
    }

    public void configurarEventos() {
        telaAddClienteView.getAddClienteBTN().addActionListener(e -> {
            cadastrarCliente();
        });
    }

    public void cadastrarCliente() {
        try{
        String nome = telaAddClienteView.getNomeClienteInput().getText();
        String cpf = telaAddClienteView.getCpfClienteInput().getText();
        String telefone = telaAddClienteView.getTelefoneClienteInput().getText();
        String email = telaAddClienteView.getEmailClienteInput().getText();

        String rua = telaAddClienteView.getRuaInput().getText();
        String numero = telaAddClienteView.getNumeroInput().getText();
        String bairro = telaAddClienteView.getBairroInput().getText();
        String cidade = telaAddClienteView.getCidadeInput().getText();
        String uf = telaAddClienteView.getUfInput().getText();
        String cep = telaAddClienteView.getCepInput().getText();

        EnderecoModel endereco = new EnderecoModel(rua, numero, bairro, cidade, uf, cep);

        ClienteModel cliente = new ClienteModel(nome, cpf, telefone, email, endereco);

        clienteDAO.addCliente(cliente);

        telaClienteController.atualizarTabela();
        }
        catch(Exception e){}
    }


}
