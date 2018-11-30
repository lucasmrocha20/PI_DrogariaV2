package com.pi.drogaria.DAO;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.pi.drogaria.model.entidades.Cidade;
import com.pi.drogaria.model.entidades.Estado;
import com.pi.drogaria.util.HibernateUtil;

public class CidadeDAO extends DAOGenerico {
	// Para quando fizer cadastro da pessoa selecionando estado, aparecer a
	// cidade relacionado ao estado.
	
	public List<Cidade> buscarPorEstado(Estado estado2) {

		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			CriteriaBuilder builder = sessao.getCriteriaBuilder();
			CriteriaQuery<Cidade> criteriaQuery = builder.createQuery(Cidade.class);
			Root<Cidade> root = criteriaQuery.from(Cidade.class);
			criteriaQuery = criteriaQuery.select(root).where(builder.equal(root.get("estado").get("nome"), estado2.getNome()));
				
			Query<Cidade> query = (Query<Cidade>) sessao.createQuery(criteriaQuery);
			List<Cidade> resultado = (List<Cidade>) query.getResultList();
			return resultado;
		} catch (Exception erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}
}
