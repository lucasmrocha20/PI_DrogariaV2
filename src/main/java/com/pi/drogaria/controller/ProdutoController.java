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
import com.pi.drogaria.model.entidades.Produto;
import com.pi.drogaria.model.entidades.ProdutoModel;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class ProdutoController implements Serializable{
	private Produto  produto = new Produto();;
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
			ProdutoModel produtoModel = new ProdutoModel();
			
			this.setProdutos(produtoModel.listar());
			
		} catch (Exception ex) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar os produtos");
			ex.printStackTrace();
		}
	}


	public void novo() {
		try {
			FabricanteDAO fabricanteDAO = new FabricanteDAO();
			List<Object>fabricantes = fabricanteDAO.listar(Fabricante.class);

			List<Fabricante> fabri = getListFabricante(fabricantes);
			
			
			this.setFabricantes(fabri);
		} catch (Exception ex) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar gerar um novo produto");
			ex.printStackTrace();
		}
	}

	public void editar(ActionEvent evento){
		try {
			produto = (Produto) evento.getComponent().getAttributes().get("produtoSelecionado");
			
			ProdutoModel produtoModel = new ProdutoModel();
			this.setProdutos(produtoModel.editar(produto));
			
			Messages.addGlobalInfo("Produto alterado com sucesso");
		} catch (Exception ex) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar selecionar um produto");
			ex.printStackTrace();
		}	
	}

	public void salvar() {
		try {
			ProdutoModel produtoModel = new ProdutoModel();
			
			this.setProdutos(produtoModel.salvar(produto));
			
			FabricanteDAO fabricanteDAO = new FabricanteDAO();
			List<Object> fabricantes = fabricanteDAO.listar(Fabricante.class);

			List<Fabricante> fabri = getListFabricante(fabricantes);			
			this.setFabricantes(fabri);
	
			produto = new Produto();
			
			Messages.addGlobalInfo("Produto salvo com sucesso");
		} catch (Exception ex) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar salvar o produto");
			ex.printStackTrace();
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
			ProdutoModel produtoModel = new ProdutoModel();			
			this.setProdutos(produtoModel.excluir(produto));
			
			Messages.addGlobalInfo("Produto removido com sucesso");
		} catch (Exception ex) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar remover o produto");
			ex.printStackTrace();
		}
	}
}