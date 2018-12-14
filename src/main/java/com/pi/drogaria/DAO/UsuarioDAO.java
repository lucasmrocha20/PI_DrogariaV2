package com.pi.drogaria.DAO;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;

import com.pi.drogaria.model.entidades.Usuario;
import com.pi.drogaria.util.HibernateUtil;

public class UsuarioDAO extends DAOGenerico {


	// Lista para ordernar no cadastro por ordem alfabética.

	@SuppressWarnings("unchecked")
	public List<Usuario> listarOrdenado() {

		Session sessao = HibernateUtil.getInstance().getSessionFactory().openSession();

		try {
			Criteria consulta = sessao.createCriteria(Usuario.class);

			consulta.createAlias("pessoa", "p");

			consulta.addOrder(Order.asc("p.nome"));

			List<Usuario> resultado = consulta.list();

			return resultado;

		} catch (RuntimeException erro) {

			throw erro;

		} finally {
			sessao.close();
		}
	}
}