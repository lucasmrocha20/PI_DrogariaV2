package com.pi.drogaria.DAO;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;

import com.pi.drogaria.model.entidades.Funcionario;
import com.pi.drogaria.util.HibernateUtil;

public class FuncionarioDAO extends DAOGenerico {
	
	// Lista para ordernar no cadastro por ordem alfabética.
	@SuppressWarnings("unchecked")
	public List<Funcionario> listarOrdenado() {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			
			Criteria consulta = sessao.createCriteria(Funcionario.class);
			consulta.createAlias("pessoa", "p");
			consulta.addOrder(Order.asc("p.nome"));
			List<Funcionario> resultado = consulta.list();
			return resultado;
		} catch (Exception erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}
}
