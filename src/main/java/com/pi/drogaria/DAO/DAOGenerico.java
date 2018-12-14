package com.pi.drogaria.DAO;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.pi.drogaria.util.HibernateUtil;

public class DAOGenerico implements DAO<Object> {
	
	private SessionFactory sessionFactory;

	public DAOGenerico() {
		this.sessionFactory = HibernateUtil.getInstance().getSessionFactory();
	}

	@Override
	public void salvar(Object Object) {
		Session sessao = this.sessionFactory.openSession();

		Transaction transacao = sessao.beginTransaction();
		try {
			sessao.save(Object);
			transacao.commit();
		} catch (Exception erro) {
			if (transacao != null) {
				transacao.rollback();
			}
			throw erro;
		} finally {
			sessao.close();
		}
	}

	@Override
	public List<Object> listar(Class clazz) {
		Session sessao = this.sessionFactory.openSession();
		try {
			CriteriaQuery<Object> query = sessao.getCriteriaBuilder().createQuery(clazz);

			Root<Object> root = query.from(clazz);

			List<Object> resultado = sessao.createQuery(query.select(root)).getResultList();
			return resultado;
		} catch (Exception erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}

	// Lista para ordernar no cadastro por ordem alfabética.
	@Override
	public List<Object> listar(String campoOrdenacao, Class clazz) {
		Session sessao = this.sessionFactory.openSession();
		try {

			CriteriaQuery<Object> query = sessao.getCriteriaBuilder().createQuery(clazz);

			Root<Object> root = query.from(clazz);

			List<Object> resultado = sessao.createQuery(query.select(root)).getResultList();

			return resultado;
		} catch (Exception erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}

	@Override
	public Object buscar(Long codigo) {
		Session sessao = this.sessionFactory.openSession();
		try {
			CriteriaBuilder builder = sessao.getCriteriaBuilder();

			CriteriaQuery<Long> criteriaQuery = builder.createQuery(Long.class);
			Root<Object> root = criteriaQuery.from(Object.class);
			criteriaQuery.select(builder.count(root));
			Query<Long> query = sessao.createQuery(criteriaQuery);
			long resultado = query.getSingleResult();
			return resultado;
		} catch (Exception erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}

	@Override
	public void excluir(Object Object) {
		Session sessao = this.sessionFactory.openSession();

		Transaction transacao = sessao.beginTransaction();
		try {
			sessao.delete(Object);
			transacao.commit();
		} catch (Exception erro) {
			if (transacao != null) {
				transacao.rollback();
			}
			throw erro;
		} finally {
			sessao.close();
		}
	}

	@Override
	public void editar(Object Object) {
		Session sessao = this.sessionFactory.openSession();

		Transaction transacao = sessao.beginTransaction();
		try {
			sessao.update(Object);
			transacao.commit();
		} catch (Exception erro) {
			if (transacao != null) {
				transacao.rollback();
			}
			throw erro;
		} finally {
			sessao.close();
		}
	}

	// MERGE serve para tanto para salvar quanto para editar.
	@Override
	public void merge(Object object) {

		Session sessao = this.sessionFactory.openSession();

		Transaction transacao = sessao.beginTransaction();
		try {
			sessao.merge(object);
			transacao.commit();
		} catch (Exception erro) {
			if (transacao != null) {
				transacao.rollback();
			}
			throw erro;
		} finally {
			sessao.close();
		}
	}
}