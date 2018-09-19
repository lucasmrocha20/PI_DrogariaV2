package com.pi.drogaria.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import com.pi.drogaria.DAO.FabricanteDAO;
import com.pi.drogaria.model.entidades.Fabricante;

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
			
			FabricanteDAO fabricanteDAO = new FabricanteDAO();
			List<Object>fabricantes = (List<Object>) fabricanteDAO.listar("descricao", Fabricante.class);

			List<Fabricante> fabri = getListFabricante(fabricantes);			
			this.setFabricantes(fabri);
			
		}catch(RuntimeException erro){
			Messages.addGlobalError("Erro ao listar");
			erro.printStackTrace();
		}
	}
	
	public void novo(){
		fabricante = new Fabricante();
		fabricantes = new ArrayList<>();
	}
	
	public void salvar(){
		try{
			FabricanteDAO fabricanteDAO = new FabricanteDAO();
			fabricanteDAO.merge(fabricante);
			
			fabricante = new Fabricante();
			List<Object> fabricantes = (List<Object>) fabricanteDAO.listar(Fabricante.class);
			
			List<Fabricante> fabri = getListFabricante(fabricantes);			
			this.setFabricantes(fabri);
			
			Messages.addGlobalInfo("Salvo com sucesso");
			
		}catch(RuntimeException erro){
			Messages.addGlobalError("Erro ao salvar");
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
			
			FabricanteDAO fabricanteDAO = new FabricanteDAO();
			fabricanteDAO.excluir(fabricante);
			
			List<Object> fabricantes = (List<Object>) fabricanteDAO.listar(Fabricante.class);
			
			List<Fabricante> fabri = getListFabricante(fabricantes);			
			this.setFabricantes(fabri);
			

			Messages.addGlobalInfo("Excluido com sucesso");
	
		}catch(RuntimeException erro){
			Messages.addGlobalError("Erro ao excluir");
			erro.printStackTrace();
		}
	}
	
	public void editar(ActionEvent evento){
		fabricante = (Fabricante) evento.getComponent().getAttributes().get("fabricanteSelecionado");
	}
	
}
