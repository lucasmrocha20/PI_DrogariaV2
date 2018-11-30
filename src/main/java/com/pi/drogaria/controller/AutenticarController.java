package com.pi.drogaria.controller;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import com.pi.drogaria.DAO.UsuarioDAO;
import com.pi.drogaria.model.entidades.Pessoa;
import com.pi.drogaria.model.entidades.Usuario;
import com.pi.drogaria.model.entidades.UsuarioModel;

@ManagedBean
@SessionScoped
public class AutenticarController {

	private Usuario usuario;
	private Usuario usuarioLogado;
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}

	public void setUsuaripoLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}

	@PostConstruct
	public void iniciar() {
		usuario = new Usuario();
//		usuario.setCodigoPessoa(new Pessoa(){
//		});
	}

	public void autenticar(){
		try{
			UsuarioModel usuarioModel = new UsuarioModel();
//			usuarioLogado = usuarioModel.autenticar(usuario.getCodigoPessoa().getClass(), usuario.getSenha());
			
			usuarioLogado = usuarioModel.autenticar(usuario.getCpf(), usuario.getSenha());
			if(usuarioLogado == null){
				Messages.addGlobalError("CPF ou SENHA incorreto");
				return;
			}
		Faces.redirect("./pages/templates/template.xhtml");
	}catch(IOException erro){
		Messages.addGlobalError(erro.getMessage());
	 }
	}
}
