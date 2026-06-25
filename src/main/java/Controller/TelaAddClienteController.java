package Controller;

import Dao.ClienteDAO;
import Model.ClienteModel;
import Model.EnderecoModel;
import View.TelaAddClienteView;

import javax.swing.*;

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
        String nome = telaAddClienteView.getNomeClienteInput().getText();
        String cpf = telaAddClienteView.getCpfClienteInput().getText();

        //tem q fazer nao permitir os 2(cpf e cnpj)
        String cnpj = telaAddClienteView.getCnpjClienteInput().getText();
        String telefone = telaAddClienteView.getTelefoneClienteInput().getText();
        String email = telaAddClienteView.getEmailClienteInput().getText();

        boolean dadosClienteValido = verificaDadosClientes(nome, cpf,cnpj, telefone, email);

        if(clienteDAO.verificaCpfCnpjRepetido(cpf)){
            exibirMensagem("CPF/CNPJ ja Cadastrado, por favor insira outro");
            return;
        }

        String rua = telaAddClienteView.getRuaInput().getText();
        String numero = telaAddClienteView.getNumeroInput().getText();
        String bairro = telaAddClienteView.getBairroInput().getText();
        String cidade = telaAddClienteView.getCidadeInput().getText();
        String uf = telaAddClienteView.getUfInput().getText();
        String cep = telaAddClienteView.getCepInput().getText();

        boolean dadosEnderecoValido = verificaDadosEndereco(rua, numero, bairro, cidade, uf, cep);

        if (dadosEnderecoValido && dadosClienteValido) {

            EnderecoModel endereco = new EnderecoModel(rua, numero, bairro, cidade, uf, cep);

            ClienteModel cliente = new ClienteModel(nome, cpf, telefone, email, endereco);

            clienteDAO.addCliente(cliente);

            telaClienteController.atualizarTabela();
        }
    }

    public void exibirMensagem(String msg) {
        JOptionPane.showMessageDialog(null, msg);
    }

    public boolean verificaDadosClientes(String nome, String cpf,String cnpj, String telefone, String email) {
        if (nome.isEmpty() || cpf.isEmpty() || telefone.isEmpty() || email.isEmpty()) {
            exibirMensagem("Campo(s) de clientes invalidos");
            return false;
        }
        return true;
    }

    public boolean verificaDadosEndereco(String rua, String numero, String bairro, String cidade, String uf, String cep) {
        if (rua.isEmpty() || numero.isEmpty() || bairro.isEmpty() || cidade.isEmpty() || uf.isEmpty() || cep.isEmpty()) {
            exibirMensagem("Campo(s) de endereço invalido");
            return false;
        }
        return true;
    }


}
