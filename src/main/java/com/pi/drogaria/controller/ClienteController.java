package com.pi.drogaria.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import com.pi.drogaria.DAO.ClienteDAO;
import com.pi.drogaria.DAO.PessoaDAO;
import com.pi.drogaria.model.entidades.Cliente;
import com.pi.drogaria.model.entidades.Pessoa;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class ClienteController implements Serializable {
	private Cliente cliente;

	private List<Cliente> clientes;

	private List<Pessoa> pessoas;

	public Cliente getCliente() {

		return cliente;

	}

	public void setCliente(Cliente cliente) {

		this.cliente = cliente;

	}

	public List<Cliente> getClientes() {

		return clientes;

	}

	public void setClientes(List<Cliente> clientes) {

		this.clientes = clientes;

	}

	public List<Pessoa> getPessoas() {

		return pessoas;

	}

	public void setPessoas(List<Pessoa> pessoas) {

		this.pessoas = pessoas;

	}

	@PostConstruct

	public void listar() {

		try {

			ClienteDAO clienteDAO = new ClienteDAO();

			List<Object> clientes = (List<Object>) clienteDAO.listar("dataCadastro", Cliente.class);

			List<Cliente> client = getListCliente(clientes);			
			this.setClientes(client);

		} catch (RuntimeException erro) {

			Messages.addGlobalError("Ocorreu um erro ao tentar listar os clientes");

			erro.printStackTrace();

		}

	}

	public void novo() {

		try {

			cliente = new Cliente();

			PessoaDAO pessoaDAO = new PessoaDAO();

			List<Object> pessoas = (List<Object>) pessoaDAO.listar("nome", Pessoa.class);

			List<Pessoa> people = getListPessoa(pessoas);			
			this.setPessoas(people);

		} catch (RuntimeException erro) {

			Messages.addGlobalError("Ocorreu um erro ao tentar criar um novo cliente");

			erro.printStackTrace();

		}

	}

	public void salvar() {

		try {

			ClienteDAO clienteDAO = new ClienteDAO();

			clienteDAO.merge(cliente);

			cliente = new Cliente();

			List<Object> clientes = (List<Object>) clienteDAO.listar("dataCadastro", Cliente.class);
			
			List<Cliente> client = getListCliente(clientes);			
			this.setClientes(client);

			PessoaDAO pessoaDAO = new PessoaDAO();

			List<Object>pessoas = (List<Object>) pessoaDAO.listar("nome", Pessoa.class);

			List<Pessoa> people = getListPessoa(pessoas);			
			this.setPessoas(people);
			
			Messages.addGlobalInfo("Cliente salvo com sucesso");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar o cliente");
			erro.printStackTrace();
		}
	}
	
	private List<Pessoa> getListPessoa(List<Object> pessoas) {
		List<Pessoa> people = new ArrayList<>();
		
		for (Object obj : pessoas) {
			people.add((Pessoa) obj);
		}
		return people;
	}

	
	private List<Cliente> getListCliente(List<Object> clientes) {
		List<Cliente> client = new ArrayList<>();
		
		for (Object obj : clientes) {
			client.add((Cliente) obj);
		}
		return client;
	}

	public void excluir(ActionEvent evento){
		try {
			cliente = (Cliente) evento.getComponent().getAttributes().get("clienteSelecionado");

			ClienteDAO clienteDAO = new ClienteDAO();
			clienteDAO.excluir(cliente);

			List<Object> clientes = (List<Object>) clienteDAO.listar(Cliente.class);

			List<Cliente> client = getListCliente(clientes);			
			this.setClientes(client);
			
			Messages.addGlobalInfo("Cliente excluido com sucesso");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao excluir o cliente");
			erro.printStackTrace();
		}
	}
}
