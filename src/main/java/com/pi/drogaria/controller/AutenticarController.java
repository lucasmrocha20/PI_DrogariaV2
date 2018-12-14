package com.pi.drogaria.controller;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.pi.drogaria.model.entidades.Usuario;
import com.pi.drogaria.model.entidades.UsuarioModel;

@ManagedBean
@SessionScoped
public class AutenticarController{

	
	public AutenticarController() {
		
	}
	
	public Usuario getUsuarioLogado() {
		return (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuarioLogado");
	}
	
	public void setUsuarioLogado(Usuario usuarioLogado) {
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuarioLogado", usuarioLogado);
	}
	
	public String realizarLoginusuario(String cpf, String senha) {
		String ret = "";
		List<Usuario> usuario = new UsuarioModel().listar();
		
		for( Usuario u : usuario) {
			if(u.getCpf().equals(cpf)) {
				if(u.getSenha().equals(senha)) {
					this.setUsuarioLogado(u);
					ret = "template.xhtml";
				}
				break;
			}
		}
		return ret;
		
	}
}
