package com.pi.drogaria.model.entidades;

import java.util.ArrayList;
import java.util.List;

import com.pi.drogaria.DAO.UsuarioDAO;

public class UsuarioModel {

	private List<Usuario> getListUsuario(List<Object> usuarios) {
		List<Usuario> user = new ArrayList<>();

		for (Object obj : usuarios) {
			user.add((Usuario) obj);
		}
		return user;
	}

	public List<Usuario> listar() {

		UsuarioDAO usuarioDAO = new UsuarioDAO();
		
		List<Object> usuarios = usuarioDAO.listar("tipo", Usuario.class);

		List<Usuario> user = getListUsuario(usuarios);

		return user;
	}

	public List<Usuario> salvar(Usuario usuario) {

		UsuarioDAO usuarioDAO = new UsuarioDAO();
		usuarioDAO.merge(usuario);

		usuario = new Usuario();
		List<Object> usuarios = usuarioDAO.listar("tipo", Usuario.class);

		List<Usuario> user = getListUsuario(usuarios);
		return user;

	}

	public List<Usuario> excluir(Usuario usuario) {
		
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			usuarioDAO.excluir(usuario);

			List<Object> usuarios = usuarioDAO.listar(Usuario.class);

			List<Usuario> user = getListUsuario (usuarios);	
			return user;
		}
}
