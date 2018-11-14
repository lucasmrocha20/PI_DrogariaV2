package com.pi.drogaria.DAO;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.pi.drogaria.model.entidades.Cidade;
import com.pi.drogaria.util.HibernateUtil;

public class CidadeDAO extends DAOGenerico {
	// Para quando fizer cadastro da pessoa selecionando estado, aparecer a
	// cidade relacionado ao estado.
	@SuppressWarnings("unchecked")
	public List<Cidade> buscarPorEstado() {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			CriteriaBuilder builder = sessao.getCriteriaBuilder();

			CriteriaQuery<Cidade> criteriaQuery = builder.createQuery(Cidade.class);
			Root<Cidade> root = criteriaQuery.from(Cidade.class);
			criteriaQuery.multiselect(builder.count(root));
			Query<Cidade> query = sessao.createQuery(criteriaQuery);
			List<Cidade> resultado = (List<Cidade>) query.getSingleResult();
			return resultado;
		} catch (Exception erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}
}
