package com.pi.drogaria.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import com.pi.drogaria.model.entidades.Pessoa;
import com.pi.drogaria.model.entidades.PessoaModel;
import com.pi.drogaria.model.entidades.Usuario;
import com.pi.drogaria.model.entidades.UsuarioModel;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class UsuarioController implements Serializable {

	private Usuario usuario = new Usuario();

	private Pessoa selectedPessoa = new Pessoa();

	private List<Usuario> usuarios;
	

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Pessoa getSelectedPessoa() {
		return selectedPessoa;
	}

	public void setSelectedPessoa(Pessoa selectedPessoa) {
		this.selectedPessoa = selectedPessoa;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	@PostConstruct
	public void listar() {
		try {
			UsuarioModel usuarioModel = new UsuarioModel();
			this.setUsuarios(usuarioModel.listar());

		} catch (Exception ex) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar os usuários");
			ex.printStackTrace();
		}
	}

	public void novo() {
		try {
			PessoaModel pessoaModel = new PessoaModel();

			List<Pessoa> pessoas = pessoaModel.listar();

			List<Pessoa> people = getListPessoa(pessoas);			
//			this.setSelectedPessoa((Pessoa) people);
			
		} catch (Exception erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar criar um novo usuário");
			erro.printStackTrace();
		}
	}

	public void salvar() {
		try {
			UsuarioModel usuarioModel = new UsuarioModel();
			
			usuario.copiaDados(this.selectedPessoa);
			
			this.setUsuarios(usuarioModel.salvar(usuario));

			PessoaModel pessoaModel = new PessoaModel();
			pessoaModel.excluir(selectedPessoa);

			usuario = new Usuario();
			Messages.addGlobalInfo("Usuário salvo com sucesso");
		} catch (Exception erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar o usuário");
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

	private List<Usuario> getListUsuario(List<Object> usuarios) {
		List<Usuario> user = new ArrayList<>();

		for (Object obj : usuarios) {
			user.add((Usuario) obj);
		}
		return user;
	}

	public void excluir(ActionEvent evento) {
		try {
			usuario = (Usuario) evento.getComponent().getAttributes().get("usuarioSelecionado");

			UsuarioModel usuarioModel = new UsuarioModel();
			this.setUsuarios(usuarioModel.excluir(usuario));

			Messages.addGlobalInfo("Usuario excluido com sucesso");
		} catch (Exception erro) {
			Messages.addGlobalError("Erro ao excluir o usuário");
			erro.printStackTrace();
		}
	}
}