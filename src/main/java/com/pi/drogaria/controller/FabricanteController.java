package com.pi.drogaria.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import com.pi.drogaria.model.entidades.Fabricante;
import com.pi.drogaria.model.entidades.FabricanteModel;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class FabricanteController implements Serializable{

	private Fabricante fabricante;
	private List<Fabricante> fabricantes;
	
	public Fabricante getFabricante() {
		return fabricante;
	}
	public void setFabricante(Fabricante fabricante) {
		this.fabricante = fabricante;
	}
	public List<Fabricante> getFabricantes() {
		return fabricantes;
	}
	public void setFabricantes(List<Fabricante> fabricantes) {
		this.fabricantes = fabricantes;
	}
	
	@PostConstruct
	public void listar(){
		try{
			
			FabricanteModel fabricanteModel = new FabricanteModel();

			this.setFabricantes(fabricanteModel.listar());
			
		}catch(Exception erro){
			Messages.addGlobalError("Erro ao listar fabricantes");
			erro.printStackTrace();
		}
	}
	
	public void novo(){
		fabricante = new Fabricante();
		fabricantes = new ArrayList<>();
	}
	
	public void salvar(){
		try{
			FabricanteModel fabricanteModel = new FabricanteModel();

			this.setFabricantes(fabricanteModel.salvar(fabricante));
			
			fabricante = new Fabricante();
			
			Messages.addGlobalInfo("Fabricante salvo com sucesso");
		}catch(Exception erro){
			Messages.addGlobalError("Erro ao salvar um fabricante");
			erro.printStackTrace();
		}
	}

	private List<Fabricante> getListFabricante(List<Object> fabricantes) {
		List<Fabricante> fabri = new ArrayList<>();
		
		for (Object obj : fabricantes) {
			fabri.add((Fabricante) obj);
		}
		return fabri;
	}

	
	public void exclui(ActionEvent evento){
		try{
			fabricante = (Fabricante) evento.getComponent().getAttributes().get("fabricanteSelecionado");
			
			FabricanteModel fabricanteModel = new FabricanteModel();

			this.setFabricantes(fabricanteModel.excluir(fabricante));
			
			Messages.addGlobalInfo("Fabricante excluido com sucesso");
	
		}catch(Exception erro){
			Messages.addGlobalError("Erro ao excluir um fabricante");
			erro.printStackTrace();
		}
	}
	
	public void editar(ActionEvent evento){
		fabricante = (Fabricante) evento.getComponent().getAttributes().get("fabricanteSelecionado");
		
		Messages.addGlobalInfo("Fabricante alterado com sucesso");
	}
	
}
