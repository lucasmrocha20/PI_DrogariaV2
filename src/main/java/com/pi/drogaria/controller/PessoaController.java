package com.pi.drogaria.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import com.pi.drogaria.DAO.CidadeDAO;
import com.pi.drogaria.DAO.EstadoDAO;
import com.pi.drogaria.DAO.PessoaDAO;
import com.pi.drogaria.model.entidades.Cidade;
import com.pi.drogaria.model.entidades.Estado;
import com.pi.drogaria.model.entidades.Pessoa;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class PessoaController implements Serializable {

	private Pessoa pessoa;
	private List<Pessoa> pessoas;

	private Estado estado;
	private List<Estado> estados;
	private List<Cidade> cidades;

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public List<Pessoa> getPessoas() {
		return pessoas;
	}

	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}

	public List<Estado> getEstados() {
		return estados;
	}

	public void setEstados(List<Estado> estados) {
		this.estados = estados;
	}

	public List<Cidade> getCidades() {
		return cidades;
	}

	public void setCidades(List<Cidade> cidades) {
		this.cidades = cidades;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	@PostConstruct
	public void Listar() {
		try {
			PessoaDAO pessoaDAO = new PessoaDAO();
			List<Object> pessoas = (List<Object>) pessoaDAO.listar("nome", Pessoa.class);

			List<Pessoa> people = getListPessoa(pessoas);			
			this.setPessoas(people);
			
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao listar pessoas");
			erro.printStackTrace();
		}
	}

	public void novo() {
		try {
			pessoa = new Pessoa();

			estado = new Estado();

			EstadoDAO estadoDAO = new EstadoDAO();
			List<Object> estados = (List<Object>) estadoDAO.listar(Estado.class);

			List<Estado> states = getListEstado(estados);			
			this.setEstados(states);

			cidades = new ArrayList<Cidade>();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu erro ao gerar nova Pessoa.");
			erro.printStackTrace();
		}
	}

	public void editar(ActionEvent evento) {
		try {
			pessoa = (Pessoa) evento.getComponent().getAttributes().get("pessoaSelecionada");

			estado = pessoa.getCidade().getEstado();

			EstadoDAO estadoDAO = new EstadoDAO();
			List<Object> estados = (List<Object>)estadoDAO.listar("nome", Estado.class);
			
			List<Estado> states = getListEstado(estados);			
			this.setEstados(states);

			CidadeDAO cidadeDAO = new CidadeDAO();
			cidades = cidadeDAO.buscarPorEstado(estado.getCodigo());

			Messages.addGlobalInfo("Pessoa alterada com sucesso");
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar selecionar uma pessoa.");
			erro.printStackTrace();
		}
	}

	public void salvar() {
		try {
			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoaDAO.merge(pessoa);

			List<Object> pessoas = (List<Object>)pessoaDAO.listar("nome", Pessoa.class);
			
			List<Pessoa> people = getListPessoa(pessoas);			
			this.setPessoas(people);


			pessoa = new Pessoa();

			estado = new Estado();

			EstadoDAO estadoDAO = new EstadoDAO();
			List<Object> estados = (List<Object>)estadoDAO.listar(Estado.class);

			List<Estado> states = getListEstado(estados);			
			this.setEstados(states);

			cidades = new ArrayList<Cidade>();
			
			Messages.addGlobalInfo("Salvo com sucesso.");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu erro ao salvar.");
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
	

	private List<Estado> getListEstado(List<Object> estados) {
		List<Estado> states = new ArrayList<>();
		
		for (Object obj : estados) {
			states.add((Estado) obj);
		}
		return states;
	}

	public void excluir(ActionEvent evento) {
		try {
			pessoa = (Pessoa) evento.getComponent().getAttributes().get("pessoaSelecionada");

			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoaDAO.excluir(pessoa);

			List<Object> pessoas = (List<Object>) pessoaDAO.listar(Pessoa.class);

			List<Pessoa> people = getListPessoa(pessoas);			
			this.setPessoas(people);

			Messages.addGlobalInfo("Pessoa excluida com sucesso");
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar excluir a pessoa");
			erro.printStackTrace();
		}
	}

	// serve para mostrar as cidades de acordo com estado.
	public void popular() {
		try {
			if (estado != null) {
				CidadeDAO cidadeDAO = new CidadeDAO();
				cidades = cidadeDAO.buscarPorEstado(estado.getCodigo());
			} else {
				cidades = new ArrayList<>();
			}
		} catch (RuntimeException erro) {
			Messages.addGlobalError("erro ao filtrar cidades.");
			erro.printStackTrace();
		}
	}

}
