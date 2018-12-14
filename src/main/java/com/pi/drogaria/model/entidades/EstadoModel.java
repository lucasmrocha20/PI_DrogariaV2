package com.pi.drogaria.model.entidades;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.pi.drogaria.DAO.EstadoDAO;

public class EstadoModel {
	public List<Estado> listar() {

			EstadoDAO estadoDAO = new EstadoDAO();

			List<Object> estados = estadoDAO.listar(Estado.class);
			List<Estado> states = getListEstado(estados);
		
			return states;
	}


	public List<Estado> salvar(Estado estado) {

		EstadoDAO estadoDAO = new EstadoDAO();
		estadoDAO.merge(estado);

		estado = new Estado();
		List<Object> estados = estadoDAO.listar(Estado.class);

		List<Estado> states = getListEstado(estados);

		return states;
	}

	
	//criar interator dos metodos em todos os model.
	private List<Estado> getListEstado(List<Object> estados) {
		List<Estado> states = new ArrayList<>();

		for (Object obj : estados) {
			states.add((Estado) obj);
		}
		return states;
	}

	public List<Estado> excluir(Estado estado) {

		EstadoDAO estadoDAO = new EstadoDAO();
		estadoDAO.excluir(estado);

		List<Object> estados = estadoDAO.listar(Estado.class);
		List<Estado> states = getListEstado(estados);

		return states;
	}
}
