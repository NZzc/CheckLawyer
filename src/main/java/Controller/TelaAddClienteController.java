package Controller;

import Dao.ClienteDAO;
import Exception.*;
import Model.ClienteFisicoModel;
import Model.ClienteJuridicoModel;
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

    public void configurarEventos(){
        telaAddClienteView.getAddClienteBTN().addActionListener(e -> {
            cadastrarCliente();
        });
    }

    public void cadastrarCliente() {
        try {
            // Endereço (comum aos dois tipos) — a View valida e lança as exceções
            String rua = telaAddClienteView.getRuaInput();
            String numero = telaAddClienteView.getNumeroInput();
            String bairro = telaAddClienteView.getBairroInput();
            String cidade = telaAddClienteView.getCidadeInput();
            String uf = telaAddClienteView.getUfInput();
            String cep = telaAddClienteView.getCepInput();

            EnderecoModel endereco = new EnderecoModel(rua, numero, bairro, cidade, uf, cep);

            // Ramifica conforme o tipo selecionado
            if (telaAddClienteView.isPessoaFisica()) {
                cadastrarPessoaFisica(endereco);
            } else {
                cadastrarPessoaJuridica(endereco);
            }

        } catch (CampoVazioException | FormatoInvalidoException | RegistroDuplicadoException ex) {
            exibirErro(ex.getMessage());
        } catch (Exception ex) {
            exibirErro("Erro inesperado: " + ex.getMessage());
        }
    }

    private void cadastrarPessoaFisica(EnderecoModel endereco)
            throws CampoVazioException, FormatoInvalidoException, RegistroDuplicadoException {
        String nome = telaAddClienteView.getNomeInput();
        String cpf = telaAddClienteView.getCpfInput();
        String telefone = telaAddClienteView.getTelefonePFInput();
        String email = telaAddClienteView.getEmailPFInput();
        String observacao = telaAddClienteView.getObservacaoPFInput();

        // Regra que depende do banco continua no Controller
        if (clienteDAO.verificaCpfCnpjRepetido(cpf)) throw new RegistroDuplicadoException("CPF", cpf);

        ClienteFisicoModel cliente = new ClienteFisicoModel(nome, telefone, email, observacao, endereco, cpf);
        clienteDAO.inserir(cliente);

        telaClienteController.atualizarTabela();
        exibirSucesso("Cliente (Pessoa Física) cadastrado com sucesso!");
    }

    private void cadastrarPessoaJuridica(EnderecoModel endereco)
            throws CampoVazioException, FormatoInvalidoException, RegistroDuplicadoException {
        String nomeFantasia = telaAddClienteView.getNomeEmpresaInput();
        String cnpj = telaAddClienteView.getCnpjInput();
        String telefone = telaAddClienteView.getTelefonePJInput();
        String email = telaAddClienteView.getEmailPJInput();
        String observacao = telaAddClienteView.getObservacaoPJInput();

        // Regra que depende do banco continua no Controller
        if (clienteDAO.verificaCpfCnpjRepetido(cnpj)) throw new RegistroDuplicadoException("CNPJ", cnpj);

        ClienteJuridicoModel cliente = new ClienteJuridicoModel(nomeFantasia, telefone, email, observacao, endereco, cnpj);
        clienteDAO.inserir(cliente);

        telaClienteController.atualizarTabela();
        exibirSucesso("Cliente (Pessoa Jurídica) cadastrado com sucesso!");
    }

    public void exibirErro(String msg) {
        telaAddClienteView.exibirErro(msg);
    }

    public void exibirSucesso(String msg) {
        telaAddClienteView.exibirSucesso(msg);
    }
}
