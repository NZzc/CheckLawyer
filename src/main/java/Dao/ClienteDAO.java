package Dao;

import Model.ClienteModel;

import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {
    private static List<ClienteModel> listaClientes = new ArrayList<ClienteModel>();

    public void addCliente(ClienteModel cliente){
        listaClientes.add(cliente);
    }

    public void removeCliente(ClienteModel cliente){
        listaClientes.remove(cliente);
    }

    public List<ClienteModel> getListaClientes() {
        return listaClientes;
    }


}
