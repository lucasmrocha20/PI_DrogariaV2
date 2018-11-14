package com.pi.drogaria.DAO;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.pi.drogaria.model.entidades.ItemVenda;
import com.pi.drogaria.model.entidades.Venda;
import com.pi.drogaria.util.HibernateUtil;

public class VendaDAO extends DAOGenerico {

	public void salvar(Venda venda, List<ItemVenda> itensVenda) {

		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();

		Transaction transacao = null;

		try {

			transacao = sessao.beginTransaction();

			sessao.save(venda);

			for (int posicao = 0; posicao < itensVenda.size(); posicao++) {

				ItemVenda itemVenda = itensVenda.get(posicao);

				itemVenda.setVenda(venda);

				sessao.save(itemVenda);

			}

			transacao.commit();

		} catch (HibernateException erro) {
			if (transacao != null) {
				transacao.rollback();
			}
			throw erro;
		} finally {
			sessao.close();
		}
	}
}
