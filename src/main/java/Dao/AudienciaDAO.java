package Dao;

import Model.AudienciaModel;

import java.util.ArrayList;
import java.util.List;

public class AudienciaDAO {
    private static List<AudienciaModel> listaAudiencias = new ArrayList<>();

    public void addAudiencia(AudienciaModel audiencia) {
        listaAudiencias.add(audiencia);
    }

    public void excluirAudiencia(int id) {
        listaAudiencias.removeIf(audiencia -> audiencia.getID() == id);
    }

    public List<AudienciaModel> getListaAudiencias() {
        return listaAudiencias;
    }
}
