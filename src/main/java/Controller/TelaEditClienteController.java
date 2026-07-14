package Controller;

import Dao.ClienteDAO;
import Exception.CampoVazioException;
import Exception.FormatoInvalidoException;
import Model.ClienteFisicoModel;
import Model.ClienteJuridicoModel;
import Model.ClienteModel;
import Model.EnderecoModel;
import View.TelaAddClienteView;

public class TelaEditClienteController {

    private TelaAddClienteView telaEditClienteView;
    private ClienteDAO clienteDAO;
    private ClienteModel cliente;
    private TelaClienteController telaClienteController;

    public TelaEditClienteController(TelaClienteController telaClienteController, ClienteModel cliente) {
        this.telaClienteController = telaClienteController;
        this.cliente = cliente;
        this.clienteDAO = new ClienteDAO();
        this.telaEditClienteView = new TelaAddClienteView();

        telaEditClienteView.configurarModoEdicao();
        telaEditClienteView.preencherCliente(cliente);
        configurarEventos();
    }

    private void configurarEventos() {
        telaEditClienteView.getAddClienteBTN().addActionListener(e -> editarCliente());
    }

    private void editarCliente() {
        try {
            atualizarDadosCliente();
            atualizarEnderecoCliente();

            clienteDAO.editar(cliente);
            telaClienteController.atualizarTabela();
            telaEditClienteView.exibirSucesso("Cliente atualizado com sucesso!");
            telaEditClienteView.dispose();

        } catch (CampoVazioException | FormatoInvalidoException ex) {
            telaEditClienteView.exibirErro(ex.getMessage());
        } catch (Exception ex) {
            telaEditClienteView.exibirErro("Erro inesperado: " + ex.getMessage());
        }
    }

    private void atualizarDadosCliente() throws CampoVazioException, FormatoInvalidoException {
        if (cliente instanceof ClienteFisicoModel) {
            cliente.setNome(telaEditClienteView.getNomeInput());
            cliente.setTelefone(telaEditClienteView.getTelefonePFInput());
            cliente.setEmail(telaEditClienteView.getEmailPFInput());
            cliente.setObservacao(telaEditClienteView.getObservacaoPFInput());
        } else if (cliente instanceof ClienteJuridicoModel) {
            cliente.setNome(telaEditClienteView.getNomeEmpresaInput());
            cliente.setTelefone(telaEditClienteView.getTelefonePJInput());
            cliente.setEmail(telaEditClienteView.getEmailPJInput());
            cliente.setObservacao(telaEditClienteView.getObservacaoPJInput());
        }
    }

    private void atualizarEnderecoCliente() throws CampoVazioException, FormatoInvalidoException {
        EnderecoModel endereco = new EnderecoModel(
                telaEditClienteView.getRuaInput(),
                telaEditClienteView.getNumeroInput(),
                telaEditClienteView.getBairroInput(),
                telaEditClienteView.getCidadeInput(),
                telaEditClienteView.getUfInput(),
                telaEditClienteView.getCepInput()
        );

        cliente.setEndereco(endereco);
    }
}
