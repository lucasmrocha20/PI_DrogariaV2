package com.pi.drogaria.DAO;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.pi.drogaria.model.entidades.Estado;
import com.pi.drogaria.util.HibernateUtil;

public class DAOGenerico implements DAO<Object> {
	public DAOGenerico() {
	}

	public void salvar(Object Object) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction transacao = null;
		try {
			transacao = sessao.beginTransaction();
			sessao.save(Object);
			transacao.commit();
		} catch (RuntimeException erro) {
			if (transacao != null) {
				transacao.rollback();
			}
			throw erro;
		} finally {
			sessao.close();
		}
	}

	public List<Object> listar(Class clazz) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			CriteriaQuery<Object> query = sessao.getCriteriaBuilder().createQuery(clazz);

			Root<Object> root =  query.from(clazz);


			List<Object> resultado = sessao.createQuery(query.select(root)).getResultList();
			return resultado;
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}

	// Lista para ordernar no cadastro por ordem alfabética.
	public List<Object> listar(String campoOrdenacao, Class clazz) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {

			CriteriaQuery<Object> query = sessao.getCriteriaBuilder().createQuery(clazz);

			Root<Object> root = query.from(clazz);

			List<Object> resultado = sessao.createQuery(query.select(root)).getResultList();

			return resultado;
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}

	public Object buscar(Long codigo) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			CriteriaBuilder builder = sessao.getCriteriaBuilder();

			CriteriaQuery<Long> criteriaQuery = builder.createQuery(Long.class);
			Root<Object> root = criteriaQuery.from(Object.class);
			criteriaQuery.select(builder.count(root));
			Query<Long> query = sessao.createQuery(criteriaQuery);
			long resultado = query.getSingleResult();
			return resultado;
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}

	public void excluir(Object Object) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction transacao = null;
		try {
			transacao = sessao.beginTransaction();
			sessao.delete(Object);
			transacao.commit();
		} catch (RuntimeException erro) {
			if (transacao != null) {
				transacao.rollback();
			}
			throw erro;
		} finally {
			sessao.close();
		}
	}

	public void editar(Object Object) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction transacao = null;
		try {
			transacao = sessao.beginTransaction();
			sessao.update(Object);
			transacao.commit();
		} catch (RuntimeException erro) {
			if (transacao != null) {
				transacao.rollback();
			}
			throw erro;
		} finally {
			sessao.close();
		}
	}

	// MERGE serve para tanto para salvar quanto para editar.
	public void merge(Object Object) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction transacao = null;
		try {
			transacao = sessao.beginTransaction();
			sessao.merge(Object);
			transacao.commit();
		} catch (RuntimeException erro) {
			if (transacao != null) {
				transacao.rollback();
			}
			throw erro;
		} finally {
			sessao.close();
		}
	}
}