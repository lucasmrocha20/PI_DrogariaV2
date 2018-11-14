package com.pi.drogaria.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import com.pi.drogaria.DAO.EstadoDAO;
import com.pi.drogaria.model.entidades.Cidade;
import com.pi.drogaria.model.entidades.CidadeModel;
import com.pi.drogaria.model.entidades.Estado;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class CidadeController implements Serializable {
	private Cidade cidade = new Cidade();
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
			CidadeModel cidadeModel = new CidadeModel();

			this.setCidades(cidadeModel.listar());

		} catch (Exception ex) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar listar as cidades");
			ex.printStackTrace();
		}
	}

	public void novo() {
		try {
			EstadoDAO estadoDAO = new EstadoDAO();
			List<Object> estados = estadoDAO.listar("nome", Estado.class);

			List<Estado> states = getListEstado(estados);
			this.setEstados(states);

		} catch (Exception ex) {
			Messages.addFlashGlobalError("Ocorreu um erro ao gerar uma nova cidade");
			ex.printStackTrace();
		}
	}

	public void salvar() {
		try {
			CidadeModel cidadeModel = new CidadeModel();
			
			this.setCidades(cidadeModel.salvar(cidade));

			EstadoDAO estadoDAO = new EstadoDAO();
			List<Object> estados = estadoDAO.listar(Estado.class);
			
			List<Estado> states = getListEstado(estados);
			this.setEstados(states);
			
			cidade = new Cidade();
			Messages.addGlobalInfo("Cidade salva com sucesso");
		} catch (Exception ex) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar salvar uma nova cidade");
			ex.printStackTrace();
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

			CidadeModel cidadeModel = new CidadeModel();
			this.setCidades(cidadeModel.excluir(cidade));

			Messages.addGlobalInfo("Cidade removida com sucesso");
		} catch (Exception ex) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar remover a cidade");
			ex.printStackTrace();
		}
	}

	public void editar(ActionEvent evento) {
		try {
			cidade = (Cidade) evento.getComponent().getAttributes().get("cidadeSelecionada");

			CidadeModel cidadeModel = new CidadeModel();
			this.setCidades(cidadeModel.editar(cidade));
			
		} catch (Exception ex) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar selecionar uma cidade");
			ex.printStackTrace();
		}

	}
}
