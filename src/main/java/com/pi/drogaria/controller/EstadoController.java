package com.pi.drogaria.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import com.pi.drogaria.model.entidades.Estado;
import com.pi.drogaria.model.entidades.EstadoModel;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class EstadoController implements Serializable {

	private Estado estado;
	private List<Estado> estados;

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
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

			EstadoModel estadoModel = new EstadoModel();
			
			this.setEstados(estadoModel.listar());
			
		} catch (Exception erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar os estados");
			erro.printStackTrace();
		}
	}

	public void novo() {

		estado = new Estado();
		estados = new ArrayList<>();
	}

	public void salvar() {

		try {
			
			EstadoModel estadoModel = new EstadoModel();
			
			this.setEstados(estadoModel.salvar(estado));
			
			estado = new Estado();
			
			Messages.addGlobalInfo("Estado salvo com sucesso.");
		} catch (Exception erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar o estado");
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

	public void excluir(ActionEvent evento) {
		try {
			
			estado = (Estado) evento.getComponent().getAttributes().get("estadoSelecionado");

			EstadoModel estadoModel = new EstadoModel();
			this.setEstados(estadoModel.excluir(estado));
			
			Messages.addGlobalInfo("Estado removido com sucesso");
		} catch (Exception erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar remover o estado");
			erro.printStackTrace();
		}
	}

	public void editar(ActionEvent evento) {

		estado = (Estado) evento.getComponent().getAttributes().get("estadoSelecionado");
		Messages.addGlobalInfo("Estado alterado com sucesso");
	}
}