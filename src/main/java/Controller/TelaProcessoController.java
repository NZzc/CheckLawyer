package Controller;

import Dao.ProcessoDAO;
import Exception.SelecionarItemException;
import View.TelaProcessosView;

public class TelaProcessoController {
    private ProcessoDAO processoDAO;
    private TelaProcessosView telaProcessosView;

    public TelaProcessoController() {
        telaProcessosView = new TelaProcessosView();
        processoDAO = new ProcessoDAO();
        atualizarTabela();
        BtnAddProcesso();
    }

    private void BtnAddProcesso() {
        telaProcessosView.getAddProcessoBTN().addActionListener(e -> new TelaAddProcessoController(this));

        telaProcessosView.getExcluirProcessoBTN().addActionListener(e -> {
            try {
                int ID = telaProcessosView.getIDlinhaSelecionada();

                if (!telaProcessosView.confirmarExclusao("Tem certeza que deseja excluir este processo?")) return;

                processoDAO.excluir(ID);
                atualizarTabela();
                exibirSucesso("Processo excluído com sucesso!");

            } catch (SelecionarItemException ex) {
                exibirErro(ex.getMessage());
            } catch (Exception ex) {
                exibirErro("Erro inesperado: " + ex.getMessage());
            }
        });
    }

    public void atualizarTabela() {
        telaProcessosView.popularTabela(processoDAO.getLista());
    }

    public ProcessoDAO getProcessoDAO() {
        return processoDAO;
    }

    public void exibirErro(String msg) {
        telaProcessosView.exibirErro(msg);
    }

    public void exibirSucesso(String msg) {
        telaProcessosView.exibirSucesso(msg);
    }
}
