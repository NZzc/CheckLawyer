package Dao;

import Model.ClienteModel;

import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {
    private static List<ClienteModel> listaClientes = new ArrayList<>();

    public void addCliente(ClienteModel cliente) {
        listaClientes.add(cliente);
    }

    public void excluirCliente(int ID) {
        for (ClienteModel cliente : listaClientes) {
            if (cliente.getID() == ID) {
                listaClientes.remove(cliente);
            }
        }
    }

    public List<ClienteModel> getListaClientes() {
        return listaClientes;
    }


}
