package com.pi.drogaria.model.entidades;

import java.util.ArrayList;
import java.util.List;

import com.pi.drogaria.DAO.PessoaDAO;

public class PessoaModel {

	private List<Pessoa> getListPessoa(List<Object> pessoas) {
		List<Pessoa> people = new ArrayList<>();

		for (Object obj : pessoas) {
			people.add((Pessoa) obj);
		}
		return people;
	}
	private List<Estado> getListEstado(List<Object> estados) {
		List<Estado> states = new ArrayList<>();
		
		for (Object obj : estados) {
			states.add((Estado) obj);
		}
		return states;
	}

	public List<Pessoa> listar() {
		PessoaDAO pessoaDAO = new PessoaDAO();
		List<Object> pessoas = pessoaDAO.listar("nome", Pessoa.class);

		List<Pessoa> people = getListPessoa(pessoas);
		return people;
	}

	public List<Pessoa> editar(Pessoa pessoa){

		PessoaDAO pessoaDAO = new PessoaDAO();
		List<Object> pessoas = pessoaDAO.listar(Pessoa.class);

		List<Pessoa> people = getListPessoa(pessoas);
		return people;
	}

	public List<Pessoa> salvar(Pessoa pessoa) {
		
		PessoaDAO pessoaDAO = new PessoaDAO();
		pessoaDAO.merge(pessoa);
		
		List<Object> pessoas = pessoaDAO.listar(Pessoa.class);
		
		List<Pessoa> people = getListPessoa(pessoas);
		
		return people;
	}

	public List<Pessoa> excluir(Pessoa pessoa){

		PessoaDAO pessoaDAO = new PessoaDAO();
		pessoaDAO.excluir(pessoa);
		
		List<Object> pessoas = pessoaDAO.listar(Pessoa.class);
		
		List<Pessoa> people = getListPessoa(pessoas);
		
		return people;
		
	}
}