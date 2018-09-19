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
import com.pi.drogaria.model.entidades.Cidade;
import com.pi.drogaria.model.entidades.Estado;

@SuppressWarnings("serial")
@ViewScoped
@ManagedBean
public class CidadeController implements Serializable {
	private Cidade cidade;
	private List<Cidade> cidades;
	private List<Estado> estados;

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public List<Cidade> getCidades() {
		return cidades;
	}

	public void setCidades(List<Cidade> cidades) {
		this.cidades = cidades;
	}

	public List<Estado> getEstados() {
		return estados;
	}

	public void setEstados(List<Estado> estados) {
		this.estados = estados;
	}

	@PostConstruct
	public void listar() {
		try {
			CidadeDAO cidadeDAO = new CidadeDAO();

			List<Object> cidades = (List<Object>) cidadeDAO.listar("nome",Cidade.class);
			

			List<Cidade> city = getListCidade(cidades);			
			this.setCidades(city);

		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar listar as cidades");
			erro.printStackTrace();
		}
	}

	public void novo() {
		try {
			cidade = new Cidade();

			EstadoDAO estadoDAO = new EstadoDAO();
			List<Object> estados = (List<Object>) estadoDAO.listar("nome", Estado.class);
			
			List<Estado> states = getListEstado(estados);			
			this.setEstados(states);


		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao gerar uma nova cidade");
			erro.printStackTrace();
		}
	}

	public void salvar() {
		try {
			CidadeDAO cidadeDAO = new CidadeDAO();
			cidadeDAO.merge(cidade);

			cidade = new Cidade();

			EstadoDAO estadoDAO = new EstadoDAO();
			List<Object> estados = (List<Object>) estadoDAO.listar(Estado.class);

			List<Object> cidades = (List<Object>) cidadeDAO.listar(Cidade.class);
			
			List<Cidade> city = getListCidade(cidades);			
			this.setCidades(city);
			
			List<Estado> states = getListEstado(estados);			
			this.setEstados(states);



			Messages.addGlobalInfo("Cidade salva com sucesso");
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar salvar uma nova cidade");
			erro.printStackTrace();
		}
	}

	private List<Estado> getListEstado(List<Object> estados) {
		List<Estado> states = new ArrayList<>();
		
		for (Object obj : estados) {
			states.add((Estado) obj);
		}
		return states;
	}

	private List<Cidade> getListCidade(List<Object> cidades) {
		List<Cidade> city = new ArrayList<>();
		
		for (Object obj : cidades) {
			city.add((Cidade) obj);
		}
		return city;
	}


	public void excluir(ActionEvent evento) {
		try {
			cidade = (Cidade) evento.getComponent().getAttributes().get("cidadeSelecionada");

			CidadeDAO cidadeDAO = new CidadeDAO();
			cidadeDAO.excluir(cidade);

			List<Object> cidades = (List<Object>) cidadeDAO.listar(Cidade.class);
			

			List<Cidade> city = getListCidade(cidades);			
			this.setCidades(city);

			Messages.addGlobalInfo("Cidade removida com sucesso");
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar remover a cidade");
			erro.printStackTrace();
		}
	}

	public void editar(ActionEvent evento){
		try {
			cidade = (Cidade) evento.getComponent().getAttributes().get("cidadeSelecionada");

			EstadoDAO estadoDAO = new EstadoDAO();
			List<Object> estados = (List<Object>) estadoDAO.listar(Estado.class);
			

			List<Estado> states = getListEstado(estados);			
			this.setEstados(states);
			
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar selecionar uma cidade");
			erro.printStackTrace();
		}	

	}
}
