package com.pi.drogaria.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import com.pi.drogaria.DAO.PessoaDAO;
import com.pi.drogaria.model.entidades.Cliente;
import com.pi.drogaria.model.entidades.ClienteModel;
import com.pi.drogaria.model.entidades.Pessoa;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class ClienteController implements Serializable {
	private Cliente cliente = new Cliente();
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
			ClienteModel clienteModel = new ClienteModel();			
			this.setClientes(clienteModel.listar());

		} catch (Exception ex) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar os clientes");
			ex.printStackTrace();
		}
	}

	public void novo() {
		try {

			PessoaDAO pessoaDAO = new PessoaDAO();

			List<Object> pessoas = pessoaDAO.listar("nome", Pessoa.class);

			List<Pessoa> people = getListPessoa(pessoas);			
			this.setPessoas(people);

		} catch (Exception ex) {
			Messages.addGlobalError("Ocorreu um erro ao tentar criar um novo cliente");
			ex.printStackTrace();
		}
	}

	public void salvar() {
		try {

			ClienteModel clienteModel = new ClienteModel();
			
			cliente = new Cliente();
			this.setClientes(clienteModel.salvar(cliente));

			PessoaDAO pessoaDAO = new PessoaDAO();

			List<Object>pessoas = pessoaDAO.listar("nome", Pessoa.class);

			List<Pessoa> people = getListPessoa(pessoas);			
			this.setPessoas(people);
			
			Messages.addGlobalInfo("Cliente salvo com sucesso");
		} catch (Exception erro) {
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

			ClienteModel clienteModel = new ClienteModel();
			this.setClientes(clienteModel.excluir(cliente));
			
			Messages.addGlobalInfo("Cliente excluido com sucesso");
		} catch (Exception erro) {
			Messages.addGlobalError("Erro ao excluir o cliente");
			erro.printStackTrace();
		}
	}
}
