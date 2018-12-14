package com.pi.drogaria.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import com.pi.drogaria.model.entidades.Cliente;
import com.pi.drogaria.model.entidades.ClienteModel;
import com.pi.drogaria.model.entidades.Pessoa;
import com.pi.drogaria.model.entidades.PessoaModel;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class ClienteController implements Serializable {
	
	private Cliente cliente = new Cliente();
	
	private List<Cliente> clientes;
	
	private Pessoa selectedPessoa = new Pessoa();

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

	public Pessoa getSelectedPessoa() {
		return selectedPessoa;
	}
	public void setSelectedPessoa(Pessoa selectedPessoa) {
		this.selectedPessoa = selectedPessoa;
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

			PessoaModel pessoaModel = new PessoaModel();

			List<Pessoa> pessoas = pessoaModel.listar();

			List<Pessoa> people = getListPessoa(pessoas);			
//			this.setSelectedPessoa((Pessoa)people);
			
		} catch (Exception ex) {
			Messages.addGlobalError("Ocorreu um erro ao tentar criar um novo cliente");
			ex.printStackTrace();
		}
	}

	public void salvar() {
		try {

			ClienteModel clienteModel = new ClienteModel();
			
			cliente.copiaDados(this.selectedPessoa);
			
			this.setClientes(clienteModel.salvar(cliente));
			
			PessoaModel pm = new PessoaModel();
			pm.excluir(selectedPessoa);
			
			cliente = new Cliente();			
			Messages.addGlobalInfo("Cliente salvo com sucesso");
		} catch (Exception erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar o cliente");
			erro.printStackTrace();
		}
	}
	
	private List<Pessoa> getListPessoa(List<Pessoa> pessoas) {
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
