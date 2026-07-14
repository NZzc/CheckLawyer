package Controller;

import Dao.ProcessoDAO;
import Exception.SelecionarItemException;
import Model.ProcessoModel;
import View.TelaProcessosView;

public class TelaProcessoController {
    private final ProcessoDAO processoDAO;
    private final TelaProcessosView telaProcessosView;

    public TelaProcessoController() {
        telaProcessosView = new TelaProcessosView();
        processoDAO = new ProcessoDAO();
        atualizarTabela();
        BtnAddProcesso();
    }

    private void BtnAddProcesso() {
        telaProcessosView.getAddProcessoBTN().addActionListener(e -> new TelaAddProcessoController(this));

        telaProcessosView.getEditarProcessoBTN().addActionListener(e -> {
            try {
                int ID = telaProcessosView.getIDlinhaSelecionada();
                ProcessoModel processo = processoDAO.buscarPorId(ID);

                if (processo == null) {
                    exibirErro("Processo não encontrado.");
                    return;
                }

                new TelaEditProcessoController(this, processo);

            } catch (SelecionarItemException ex) {
                exibirErro(ex.getMessage());
            } catch (Exception ex) {
                exibirErro("Erro inesperado: " + ex.getMessage());
            }
        });

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

    /*
    public ProcessoDAO getProcessoDAO() {
        return processoDAO;
    }
     */

    public void exibirErro(String msg) {
        telaProcessosView.exibirErro(msg);
    }

    public void exibirSucesso(String msg) {
        telaProcessosView.exibirSucesso(msg);
    }
}
