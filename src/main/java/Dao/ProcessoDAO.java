package Dao;

import Model.ProcessoModel;

import java.util.ArrayList;
import java.util.List;

public class ProcessoDAO {
    private static List<ProcessoModel> listaProcessos = new ArrayList<>();

    public void addProcesso(ProcessoModel processo) {
        listaProcessos.add(processo);
    }

    public void excluirProcesso(int id) {
        listaProcessos.removeIf(processo -> processo.getID() == id);
    }

    public List<ProcessoModel> getListaProcessos() {
        return listaProcessos;
    }

    public boolean verificaNumeroRepetido(String numero) {
        for (ProcessoModel p : listaProcessos) {
            if (numero.equals(p.getNumero())) {
                return true;
            }
        }
        return false;
    }
}
