package Dao;

import Model.ClienteFisicoModel;
import Model.ClienteJuridicoModel;
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


    public boolean verificaCpfCnpjRepetido(String cpf){
        for (ClienteModel cliente : listaClientes){
            if (cpf.equals(cliente.getDocumento())){
                return true;
            }
        }
        return false;
    }




}
