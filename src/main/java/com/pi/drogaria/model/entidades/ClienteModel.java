package com.pi.drogaria.model.entidades;

import java.util.ArrayList;
import java.util.List;

import com.pi.drogaria.DAO.ClienteDAO;

public class ClienteModel {

	private List<Cliente> getListCliente(List<Object> clientes) {
		List<Cliente> client = new ArrayList<>();

		for (Object obj : clientes) {
			client.add((Cliente) obj);
		}
		return client;
	}

	public List<Cliente> listar() {

		ClienteDAO clienteDAO = new ClienteDAO();

		List<Object> clientes = clienteDAO.listar("dataCadastro", Cliente.class);

		List<Cliente> client = getListCliente(clientes);

		return client;

	}

	public List<Cliente> excluir(Cliente cliente) {

		ClienteDAO clienteDAO = new ClienteDAO();
		clienteDAO.excluir(cliente);

		List<Object> clientes = clienteDAO.listar(Cliente.class);

		List<Cliente> client = getListCliente(clientes);
		return client;

	}

	public List<Cliente> salvar(Cliente cliente) {

		ClienteDAO clienteDAO = new ClienteDAO();

		clienteDAO.merge(cliente);


		List<Object> clientes = clienteDAO.listar("dataCadastro", Cliente.class);

		List<Cliente> client = getListCliente(clientes);
	
		return client;
		
	}
}
