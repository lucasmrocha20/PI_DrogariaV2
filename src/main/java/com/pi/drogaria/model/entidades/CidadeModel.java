package com.pi.drogaria.model.entidades;

import java.util.ArrayList;
import java.util.List;

import com.pi.drogaria.DAO.CidadeDAO;

public class CidadeModel {

	public List<Cidade> listar() {
		CidadeDAO cidadeDAO = new CidadeDAO();

		List<Object> cidades = cidadeDAO.listar("nome", Cidade.class);

		List<Cidade> city = getListCidade(cidades);
		return city;
	}

	public List<Cidade> salvar(Cidade cidade) {

		CidadeDAO cidadeDAO = new CidadeDAO();
		cidadeDAO.merge(cidade);

		List<Object> cidades = cidadeDAO.listar(Cidade.class);

		List<Cidade> city = getListCidade(cidades);


		return city;

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

	public List<Cidade> excluir(Cidade cidade) {

		CidadeDAO cidadeDAO = new CidadeDAO();
		cidadeDAO.excluir(cidade);

		List<Object> cidades = cidadeDAO.listar(Cidade.class);

		List<Cidade> city = getListCidade(cidades);
		return city;
	}

	public List<Cidade> editar(Cidade cidade) {
		CidadeDAO cidadeDAO = new CidadeDAO();
		cidadeDAO.editar(cidade);

		List<Object> cidades = cidadeDAO.listar(Cidade.class);

		List<Cidade> city = getListCidade(cidades);
		return city;	
		
		
	}
}
