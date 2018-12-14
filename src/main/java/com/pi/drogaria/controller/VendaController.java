package com.pi.drogaria.controller;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import com.pi.drogaria.DAO.ProdutoDAO;
import com.pi.drogaria.DAO.VendaDAO;
import com.pi.drogaria.model.entidades.Cliente;
import com.pi.drogaria.model.entidades.ClienteModel;
import com.pi.drogaria.model.entidades.ItemVenda;
import com.pi.drogaria.model.entidades.Pessoa;
import com.pi.drogaria.model.entidades.Produto;
import com.pi.drogaria.model.entidades.Usuario;
import com.pi.drogaria.model.entidades.UsuarioModel;
import com.pi.drogaria.model.entidades.Venda;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class VendaController implements Serializable {
	
	private Venda venda = new Venda();
	
	private Pessoa selectedPessoa = new Pessoa();
	
	private Cliente cliente = new Cliente();
	private Usuario usuario = new Usuario();
	
	ClienteModel clienteModel = new ClienteModel();
	UsuarioModel usuarioModel = new UsuarioModel();
	
	private List<Produto> produtos;

	private List<ItemVenda> itensVenda;

	private List<Cliente> clientes;

	private List<Usuario> usuarios;

	public Venda getVenda() {
		return venda;
	}

	public void setVenda(Venda venda) {
		this.venda = venda;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public List<ItemVenda> getItensVenda() {
		return itensVenda;
	}

	public void setItensVenda(List<ItemVenda> itensVenda) {
		this.itensVenda = itensVenda;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	@PostConstruct
	public void novo() {
		try {

			venda.setPrecoTotal(new BigDecimal("0.00"));

			ProdutoDAO produtoDAO = new ProdutoDAO();

			List<Object> produtos = produtoDAO.listar("descricao", Produto.class);

			List<Produto> prod = getListProduto(produtos);
			this.setProdutos(prod);

			itensVenda = new ArrayList<>();
		} catch (Exception ex) {
			Messages.addGlobalError("Ocorreu um erro ao tentar carregar a tela de vendas");
			ex.printStackTrace();
		}
	}

	public void adicionar(ActionEvent evento) {
		Produto produto = (Produto) evento.getComponent().getAttributes().get("produtoSelecionado");

		int achou = -1;

		for (int posicao = 0; posicao < itensVenda.size(); posicao++) {

			if (itensVenda.get(posicao).getProduto().equals(produto)) {

				achou = posicao;
			}
		}
		if (achou < 0) {

			ItemVenda itemVenda = new ItemVenda();

			itemVenda.setPrecoParcial(produto.getPreco());

			itemVenda.setProduto(produto);

			itemVenda.setQuantidade(new Short("1"));

			itensVenda.add(itemVenda);
		} else {
			ItemVenda itemVenda = itensVenda.get(achou);

			itemVenda.setQuantidade(new Short(itemVenda.getQuantidade() + 1 + ""));

			itemVenda.setPrecoParcial(produto.getPreco().multiply(new BigDecimal(itemVenda.getQuantidade())));
		}
		calcular();
	}

	public void remover(ActionEvent evento) {

		ItemVenda itemVenda = (ItemVenda) evento.getComponent().getAttributes().get("itemSelecionado");

		int achou = -1;

		for (int posicao = 0; posicao < itensVenda.size(); posicao++) {

			if (itensVenda.get(posicao).getProduto().equals(itemVenda.getProduto())) {

				achou = posicao;
			}
		}
		if (achou > -1) {

			itensVenda.remove(achou);
		}
		calcular();
	}

	public void calcular() {

		venda.setPrecoTotal(new BigDecimal("0.00"));

		for (int posicao = 0; posicao < itensVenda.size(); posicao++) {

			ItemVenda itemVenda = itensVenda.get(posicao);

			venda.setPrecoTotal(venda.getPrecoTotal().add(itemVenda.getPrecoParcial()));
		}
	}

	public void finalizar() {
		try {
			usuario.copiaDados(this.selectedPessoa);
			cliente.copiaDados(this.selectedPessoa);
			
			this.setClientes(clienteModel.listar());
			this.setUsuarios(usuarioModel.listar());
			
			venda.setHorario(new Date());

			venda.setCliente(null);

			venda.setUsuario(null);

			
						
			Messages.addGlobalInfo("Venda finalizada com sucesso!");
		} catch (Exception erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar finalizar a venda");
			erro.printStackTrace();
		}
	}
	
	
	private List<Pessoa> getListPessoa(List<Pessoa> pessoas) {
		List<Pessoa> people = new ArrayList<>();
		
		for (Object obj : pessoas) {
			people.add((Pessoa) obj);
		}
		return people;
	}


	public void salvar() {
		try {

			if (venda.getPrecoTotal().signum() == 0) {

				Messages.addGlobalError("Informe pelo menos um item para a venda");

				return;
			}
			VendaDAO vendaDAO = new VendaDAO();

			vendaDAO.salvar(venda, itensVenda);

			venda = new Venda();

			venda.setPrecoTotal(new BigDecimal("0.00"));

			ProdutoDAO produtoDAO = new ProdutoDAO();

			List<Object> produtos = produtoDAO.listar("descricao", Produto.class);

			List<Produto> prod = getListProduto(produtos);
			this.setProdutos(prod);

			itensVenda = new ArrayList<>();

			Messages.addGlobalInfo("Venda realizada com sucesso");

		} catch (Exception erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar a venda");
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

}
