package Dao;

import Model.ClienteModel;

import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {
    private static List<ClienteModel> listaClientes = new ArrayList<>();

    public void addCliente(ClienteModel cliente) {
        listaClientes.add(cliente);
    }

    public void excluirCliente(int id) {
        listaClientes.removeIf(cliente -> cliente.getID() == id);
    }

    public List<ClienteModel> getListaClientes() {
        return listaClientes;
    }

    public boolean verificaCpfCnpjRepetido(String cpfCnpj){
        /*
        if(listaClientes.isEmpty()){
            return false;
        }
        */

        for (ClienteModel cliente : listaClientes){
            if (cpfCnpj.equals(cliente.getCpf_cnpj())){
                return true;
            }
        }
        return false;
    }

}
