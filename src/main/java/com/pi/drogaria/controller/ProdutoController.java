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
import com.pi.drogaria.DAO.ProdutoDAO;
import com.pi.drogaria.model.entidades.Fabricante;
import com.pi.drogaria.model.entidades.Produto;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class ProdutoController implements Serializable{
	private Produto  produto;
	private List<Produto> produtos;
	private List<Fabricante> fabricantes;

	public Produto getProduto() {

		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public List<Fabricante> getFabricantes() {
		return fabricantes;
	}

	public void setFabricantes(List<Fabricante> fabricantes) {
		this.fabricantes = fabricantes;
	}

	@PostConstruct
	public void listar() {
		try {
			ProdutoDAO produtoDAO = new ProdutoDAO();
			List<Object> produtos = (List<Object>) produtoDAO.listar("descricao", Produto.class);

			List<Produto> prod = getListProduto(produtos);			
			this.setProdutos(prod);
			
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar os produtos");
			erro.printStackTrace();
		}
	}


	public void novo() {
		try {
			produto = new Produto();
			FabricanteDAO fabricanteDAO = new FabricanteDAO();
			List<Object>fabricantes = (List<Object>) fabricanteDAO.listar(Fabricante.class);

			List<Fabricante> fabri = getListFabricante(fabricantes);			
			this.setFabricantes(fabri);
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar gerar um novo produto");
			erro.printStackTrace();
		}
	}

	public void editar(ActionEvent evento){
		try {
			produto = (Produto) evento.getComponent().getAttributes().get("produtoSelecionado");
			FabricanteDAO fabricanteDAO = new FabricanteDAO();
			List<Object> fabricantes = (List<Object>)fabricanteDAO.listar(Fabricante.class);

			List<Fabricante> fabri = getListFabricante(fabricantes);			
			this.setFabricantes(fabri);
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar selecionar um produto");
			erro.printStackTrace();
		}	
	}

	public void salvar() {
		try {
			ProdutoDAO produtoDAO = new ProdutoDAO();
			produtoDAO.merge(produto);
			produto = new Produto();
			FabricanteDAO fabricanteDAO = new FabricanteDAO();
			List<Object> fabricantes = (List<Object>) fabricanteDAO.listar(Fabricante.class);

			List<Fabricante> fabri = getListFabricante(fabricantes);			
			this.setFabricantes(fabri);
			
			List<Object> produtos = (List<Object>) produtoDAO.listar(Produto.class);

			List<Produto> prod = getListProduto(produtos);			
			this.setProdutos(prod);
			
			Messages.addGlobalInfo("Produto salvo com sucesso");
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar salvar o produto");
			erro.printStackTrace();
		}
	}
	
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

	public void excluir(ActionEvent evento) {
		try {
			produto = (Produto) evento.getComponent().getAttributes().get("produtoSelecionado");
			ProdutoDAO produtoDAO = new ProdutoDAO();
			produtoDAO.excluir(produto);
			List<Object> produtos = (List<Object>) produtoDAO.listar(Produto.class);

			List<Produto> prod = getListProduto(produtos);			
			this.setProdutos(prod);
			
			Messages.addGlobalInfo("Produto removido com sucesso");
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar remover o produto");
			erro.printStackTrace();
		}
	}
}