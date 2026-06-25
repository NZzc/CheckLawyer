package Controller;

import Dao.ClienteDAO;
import Model.ClienteModel;
import Model.EnderecoModel;
import View.TelaAddClienteView;

public class TelaAddClienteController {
    private TelaAddClienteView telaAddClienteView;
    private ClienteDAO clienteDAO;


    public TelaAddClienteController() {
        telaAddClienteView = new TelaAddClienteView();
        clienteDAO = new ClienteDAO();

        configurarEventos();
    }

    public void configurarEventos() {

    }

    public void cadastrarCliente() {
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
    }


}
