package com.pi.drogaria.model.entidades;

import java.util.ArrayList;
import java.util.List;

import com.pi.drogaria.DAO.ProdutoDAO;

public class ProdutoModel {

	private List<Produto> getListProduto(List<Object> produtos) {
		List<Produto> prod = new ArrayList<>();

		for (Object obj : produtos) {
			prod.add((Produto) obj);
		}
		return prod;
	}

	private List<Fabricante> getListFabricante(List<Object> fabricantes) {
		List<Fabricante> fabri = new ArrayList<>();

		for (Object obj : fabricantes) {
			fabri.add((Fabricante) obj);
		}
		return fabri;
	}

	public List<Produto> listar() {
		ProdutoDAO produtoDAO = new ProdutoDAO();
		List<Object> produtos = produtoDAO.listar("descricao", Produto.class);

		List<Produto> prod = getListProduto(produtos);
		return prod;
	}
	public List<Produto> salvar(Produto produto) {

		ProdutoDAO produtoDAO = new ProdutoDAO();
		produtoDAO.merge(produto);

		List<Object> produtos = produtoDAO.listar(Produto.class);

		List<Produto> prod = getListProduto(produtos);
		
		return prod;

	}
	public List<Produto> excluir(Produto produto) {

		ProdutoDAO produtoDAO = new ProdutoDAO();
		produtoDAO.excluir(produto);

		List<Object> produtos = produtoDAO.listar(Produto.class);

		List<Produto> prod = getListProduto(produtos);
		return prod;	
		
	}

	public List<Produto> editar(Produto produto) {
		ProdutoDAO produtoDAO = new ProdutoDAO();
		produtoDAO.editar(produto);

		List<Object> produtos = produtoDAO.listar(Produto.class);

		List<Produto> prod = getListProduto(produtos);
		return prod;	
		
		
	}
}