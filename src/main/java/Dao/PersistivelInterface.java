package Dao;

import java.util.List;

public interface PersistivelInterface <T>{
    void inserir(T entidade);
    void excluir(int id);
    List<T> getLista();
    void editar(T entidade);
}
