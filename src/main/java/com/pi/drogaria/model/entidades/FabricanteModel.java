package com.pi.drogaria.model.entidades;

import java.util.ArrayList;
import java.util.List;

import com.pi.drogaria.DAO.FabricanteDAO;

public class FabricanteModel {

	private List<Fabricante> getListFabricante(List<Object> fabricantes) {
		List<Fabricante> fabri = new ArrayList<>();

		for (Object obj : fabricantes) {
			fabri.add((Fabricante) obj);
		}
		return fabri;
	}

	public List<Fabricante> listar() {

		FabricanteDAO fabricanteDAO = new FabricanteDAO();
		List<Object> fabricantes = fabricanteDAO.listar("descricao", Fabricante.class);

		List<Fabricante> fabri = getListFabricante(fabricantes);

		return fabri;
	}

	public List<Fabricante> salvar(Fabricante fabricante){
		
		FabricanteDAO fabricanteDAO = new FabricanteDAO();
		fabricanteDAO.merge(fabricante);
		
		fabricante = new Fabricante();
		List<Object> fabricantes = fabricanteDAO.listar(Fabricante.class);
		
		List<Fabricante> fabri = getListFabricante(fabricantes);			
		return fabri;
		
	}
	
	public List<Fabricante> excluir(Fabricante fabricante){

		FabricanteDAO fabricanteDAO = new FabricanteDAO();
		fabricanteDAO.excluir(fabricante);
		
		List<Object> fabricantes = fabricanteDAO.listar(Fabricante.class);
		
		List<Fabricante> fabri = getListFabricante(fabricantes);			
		return fabri;
	}
	
}
