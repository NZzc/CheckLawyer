package Dao;

import Model.AudienciaModel;

import java.util.ArrayList;
import java.util.List;

public class AudienciaDAO implements PersistivelInterface <AudienciaModel> {
    private static List<AudienciaModel> listaAudiencias = new ArrayList<>();

    public void inserir(AudienciaModel audiencia) {
        listaAudiencias.add(audiencia);
    }

    public void excluir(int id) {
        listaAudiencias.removeIf(audiencia -> audiencia.getID() == id);
    }

    public List<AudienciaModel> getLista() {
        return listaAudiencias;
    }
}
