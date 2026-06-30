package Dao;

import java.util.List;

public interface PersistivelInterface <T>{
    public void inserir(T entidade);
    public void excluir(int id);
    public List<T> getLista();
}
