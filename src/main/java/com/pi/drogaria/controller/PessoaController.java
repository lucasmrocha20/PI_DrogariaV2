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
import com.pi.drogaria.model.entidades.PessoaModel;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class PessoaController implements Serializable {

	private Pessoa pessoa = new Pessoa();
	private List<Pessoa> pessoas;
	private Estado estado;
	private List<Estado> estados;
	private List<Cidade> cidades;

	PessoaModel pessoaModel = new PessoaModel();

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
	public void listar() {
		try {

			PessoaModel pessoaModel = new PessoaModel();

			this.setPessoas(pessoaModel.listar());

		} catch (Exception ex) {
			Messages.addGlobalError("Erro ao listar pessoas");
			ex.printStackTrace();
		}
	}

	public void novo() {
		try {
			
			
			EstadoDAO estadoDAO = new EstadoDAO();
			List<Object> estados = estadoDAO.listar("nome", Estado.class);

			List<Estado> states = getListEstado(estados);
			this.setEstados(states);
			
			pessoa = new Pessoa();
			estado = new Estado();
			cidades = new ArrayList<Cidade>();
			
		} catch (Exception ex) {
			Messages.addGlobalError("Ocorreu erro ao gerar nova Pessoa.");
			ex.printStackTrace();
		}
	}

	public void editar(ActionEvent evento){

		try {
			pessoa = (Pessoa) evento.getComponent().getAttributes().get("pessoaSelecionada");

			PessoaModel pessoaModel = new PessoaModel();

			this.setPessoas(pessoaModel.editar(pessoa));
			
			Messages.addGlobalInfo("Pessoa alterada com sucesso");
		} catch (Exception ex) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar selecionar uma pessoa.");
			ex.printStackTrace();
		}
	}

	public void salvar() {
		try {

			PessoaModel pessoaModel = new PessoaModel();

			this.setPessoas(pessoaModel.salvar(pessoa));
			
			Messages.addGlobalInfo("Pessoa salvo com sucesso.");
		} catch (Exception ex) {
			Messages.addGlobalError("Ocorreu erro ao salvar.");
			ex.printStackTrace();
		}

	}

	public void excluir(ActionEvent evento) {
		try {
			pessoa = (Pessoa) evento.getComponent().getAttributes().get("pessoaSelecionada");

			PessoaDAO pessoaDAO = new PessoaDAO();
			this.setPessoas(pessoaModel.excluir(pessoa));

			List<Object> pessoas = pessoaDAO.listar(Pessoa.class);

			List<Pessoa> people = getListPessoa(pessoas);
			this.setPessoas(people);

			Messages.addGlobalInfo("Pessoa excluída com sucesso");
		} catch (Exception erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar excluir a pessoa");
			erro.printStackTrace();
		}
	}

	private List<Pessoa> getListPessoa(List<Object> pessoas) {
		List<Pessoa> people = new ArrayList<>();

		for (Object obj : cidades) {
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

	// serve para mostrar as cidades de acordo com estado.
	public void popular() {
		try {
			if (estado != null) {
				CidadeDAO cidadeDAO = new CidadeDAO();
				cidades = cidadeDAO.buscarPorEstado(estado);
			} else {
				cidades = new ArrayList<>();
			}
		} catch (Exception erro) {
			Messages.addGlobalError("erro ao filtrar cidades.");
			erro.printStackTrace();
		}
	}

}
