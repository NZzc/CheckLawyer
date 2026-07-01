package Controller;

import Dao.ClienteDAO;
import Model.ClienteFisicoModel;
import Model.ClienteJuridicoModel;
import Model.EnderecoModel;
import View.TelaAddClienteView;
import Exception.FormatoInvalidoException;

import javax.swing.*;

public class TelaAddClienteController {

    private TelaAddClienteView telaAddClienteView;
    private ClienteDAO clienteDAO;
    private TelaClienteController telaClienteController;

    public TelaAddClienteController(TelaClienteController telaClienteController) {
        telaAddClienteView = new TelaAddClienteView();
        clienteDAO = new ClienteDAO();
        this.telaClienteController = telaClienteController;

        BtnAddCliente();
    }

    public void BtnAddCliente() {
        telaAddClienteView.getAddClienteBTN().addActionListener(e -> cadastrarCliente());
    }

    public void cadastrarCliente() {

        //Endereço (comum aos dois tipos)
        String rua = telaAddClienteView.getRuaInput().getText().trim();
        String numero = telaAddClienteView.getNumeroInput().getText().trim();
        String bairro = telaAddClienteView.getBairroInput().getText().trim();
        String cidade = telaAddClienteView.getCidadeInput().getText().trim();
        String uf = telaAddClienteView.getUfInput().getText().trim();
        String cep = telaAddClienteView.getCepInput().getText().trim();

        if (!verificaDadosEndereco(rua, numero, bairro, cidade, uf, cep)) return;

        EnderecoModel endereco = new EnderecoModel(rua, numero, bairro, cidade, uf, cep);

        //Ramifica conforme o tipo selecionado
        if (telaAddClienteView.isPessoaFisica()) {
            cadastrarPessoaFisica(endereco);
        } else {
            cadastrarPessoaJuridica(endereco);
        }
    }

    private void cadastrarPessoaFisica(EnderecoModel endereco){
        String nome = telaAddClienteView.getNomeInput().getText().trim();
        String cpf = telaAddClienteView.getCpfInput().getText().trim();
        String telefone = telaAddClienteView.getTelefonePFInput().getText().trim();
        String email = telaAddClienteView.getEmailPFInput().getText().trim();
        String observacao = telaAddClienteView.getObservacaoPFInput().getText().trim();

        if (!verificaDadosPessoaFisica(nome, cpf, telefone, email)) {
            return;
        }

        if (clienteDAO.verificaCpfCnpjRepetido(cpf)) {
            exibirMensagem("CPF já cadastrado. Por favor, insira outro.");
            return;
        }

        ClienteFisicoModel cliente = new ClienteFisicoModel(nome, telefone, email, observacao, endereco, cpf);
        clienteDAO.inserir(cliente);

        telaClienteController.atualizarTabela();
        exibirMensagem("Cliente (Pessoa Física) cadastrado com sucesso!");
    }

    private void cadastrarPessoaJuridica(EnderecoModel endereco) {
        String nomeFantasia = telaAddClienteView.getNomeEmpresaInput().getText().trim();
        String cnpj = telaAddClienteView.getCnpjInput().getText().trim();
        String telefone = telaAddClienteView.getTelefonePJInput().getText().trim();
        String email = telaAddClienteView.getEmailPJInput().getText().trim();
        String observacao = telaAddClienteView.getObservacaoPJInput().getText().trim();


        if (!verificaDadosPessoaJuridica(cnpj, telefone, email)) return;


        if (clienteDAO.verificaCpfCnpjRepetido(cnpj)) {
            exibirMensagem("CNPJ já cadastrado. Por favor, insira outro.");
            return;
        }

        ClienteJuridicoModel cliente = new ClienteJuridicoModel(nomeFantasia, telefone, email, observacao, endereco, cnpj);
        clienteDAO.inserir(cliente);

        telaClienteController.atualizarTabela();
        exibirMensagem("Cliente (Pessoa Jurídica) cadastrado com sucesso!");
    }

    public boolean verificaDadosPessoaFisica(String nome, String cpf, String telefone, String email) {
        if (nome.isEmpty() || cpf.isEmpty() || telefone.isEmpty() || email.isEmpty()) {
            exibirMensagem("Preencha todos os campos de Pessoa Física.");
            return false;

        }
        if (!cpf.matches("\\d{11}$")) {
            exibirMensagem("CPF invalido! \n Padrão: 01234567890 11 digitos.");
            return false;
        }
        if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
            exibirMensagem("Email invalido! \n Padrão: xxxxx@xxxxxxx.com");
            return false;
        }
        if (!telefone.matches("^\\d{10,11}$")) {
            exibirMensagem("Telefone invalido! \n Padrão: 01234567890 10/11 digitos");
            return false;
        }
        return true;
    }

    public boolean verificaDadosPessoaJuridica(String cnpj, String telefone, String email) {
        if (cnpj.isEmpty() || telefone.isEmpty() || email.isEmpty()) {
            exibirMensagem("Preencha todos os campos de Pessoa Jurídica.");
            return false;
        }
        if (!cnpj.matches("^\\d{14}$")) {
            exibirMensagem("CNPJ inválido! \n Padrão: 01234567891011 14 digitos");
            return false;
        }
        if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
            exibirMensagem("Email inválido! \n Padrão: xxxxx@xxxxxxx.com");
            return false;
        }
        if (!telefone.matches("^\\d{10,11}$")) {
            exibirMensagem("Telefone inválido! \n Padrão: 01234567890 10/11 digitos");
            return false;
        }
        return true;
    }

    public boolean verificaDadosEndereco(String rua, String numero, String bairro, String cidade, String uf, String cep) {
        if (rua.isEmpty() || numero.isEmpty() || bairro.isEmpty()
                || cidade.isEmpty() || uf.isEmpty() || cep.isEmpty()) {
            exibirMensagem("Preencha todos os campos de endereço.");
            return false;
        }
        if (!(cep.matches("\\d{8}$"))) {
            exibirMensagem("CEP inválido \n Padrão: 00000000 8 digitos");
            return false;
        }
        return true;
    }

    public void exibirMensagem(String msg) {
        JOptionPane.showMessageDialog(null, msg);
    }
}