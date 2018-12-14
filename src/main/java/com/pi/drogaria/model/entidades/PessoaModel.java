package com.pi.drogaria.model.entidades;

import java.util.ArrayList;
import java.util.List;

import com.pi.drogaria.DAO.DAO;
import com.pi.drogaria.DAO.DAOGenerico;
import com.pi.drogaria.DAO.PessoaDAO;
import com.pi.drogaria.excecoes.VerificacaoCelularException;
import com.pi.drogaria.excecoes.VerificacaoCpfException;
import com.pi.drogaria.excecoes.VerificacaoEmailException;
import com.pi.drogaria.excecoes.VerificacaoNomeException;
import com.pi.drogaria.excecoes.VerificacaoObjectExistenteException;
import com.pi.drogaria.excecoes.VerificacaoObjectNullException;
import com.pi.drogaria.excecoes.VerificacaoRGgException;
import com.pi.drogaria.excecoes.VerificacaoTelefoneException;
import com.pi.drogaria.util.MetodosVerificadores;

public class PessoaModel {

	private List<Pessoa> getListPessoa(List<Object> pessoas) {
		List<Pessoa> people = new ArrayList<>();

		for (Object obj : pessoas) {
			people.add((Pessoa) obj);
		}
		return people;
	}

	private List<Estado> getListEstado(List<Object> estados) {
		List<Estado> states = new ArrayList<>();

		for (Object obj : estados) {
			states.add((Estado) obj);
		}
		return states;
	}

	public List<Pessoa> listar() {
		PessoaDAO pessoaDAO = new PessoaDAO();
		List<Object> pessoas = pessoaDAO.listar("nome", Pessoa.class);

		List<Pessoa> people = getListPessoa(pessoas);
		return people;
	}

	public List<Pessoa> editar(Pessoa pessoa){
//			throws VerificacaoNomeException, VerificacaoCpfException,
//			VerificacaoRGgException, VerificacaoTelefoneException, VerificacaoCelularException,
//			VerificacaoEmailException, VerificacaoObjectNullException {
//		if (pessoa != null) {
//			if (MetodosVerificadores.verificacaoNome(pessoa.getNome())) {
//				if (MetodosVerificadores.verificacaoCpf(pessoa.getCpf())) {
//					if (MetodosVerificadores.verificacaoRg(pessoa.getRg())) {
//						if (MetodosVerificadores.verificacaoTelefone(pessoa.getTelefone())) {
//							if (MetodosVerificadores.verificacaoCelular(pessoa.getCelular())) {
//								if (MetodosVerificadores.verificacaoEmail(pessoa.getEmail())) {
//									DAOGenerico daoGenerico = new DAOGenerico();
//									daoGenerico.editar(pessoa);
//								} else {
//									throw new VerificacaoNomeException(
//											"Insira um Nome que contenha apenas Caracteres e não deixe campos em Branco.");
//								}
//							}else {
//								throw new VerificacaoCpfException("Inserir CPF válido");
//							}
//						}else {
//							throw new VerificacaoRGgException("Inserir Rg válido");
//						}
//					}else {
//						throw new VerificacaoTelefoneException("Inserir apenas números");
//					}
//				}else {
//					throw new VerificacaoCelularException("Inserir apenas números.");
//				}
//			}else {
//				throw new VerificacaoObjectNullException("Todos os campos devem estar preenchidos.");
//			}
//		}

		PessoaDAO pessoaDAO = new PessoaDAO();
		List<Object> pessoas = pessoaDAO.listar(Pessoa.class);

		List<Pessoa> people = getListPessoa(pessoas);
		return people;
	}

	public List<Pessoa> salvar(Pessoa pessoa) {
//			throws VerificacaoCpfException, VerificacaoNomeException,
//			VerificacaoRGgException, VerificacaoTelefoneException, VerificacaoCelularException,
//			VerificacaoEmailException, VerificacaoObjectNullException {
//		if (pessoa != null) {
//			if (jaExiste(pessoa) == false) {
//				if (MetodosVerificadores.verificacaoCpf(pessoa.getCpf())) {
//					if (MetodosVerificadores.verificacaoNome(pessoa.getNome())) {
//						if (MetodosVerificadores.verificacaoRg(pessoa.getRg())) {
//							if (MetodosVerificadores.verificacaoTelefone(pessoa.getTelefone())) {
//								if (MetodosVerificadores.verificacaoCelular(pessoa.getCelular())) {
//									if (MetodosVerificadores.verificacaoEmail(pessoa.getEmail())) {
//										DAOGenerico daoGenerico = new DAOGenerico();
//										daoGenerico.salvar(pessoa);
//									} else {
//										// verif. cpf.
//										throw new VerificacaoCpfException("Inserir CPF válido.");
//									}
//								} else {
//									// verif. nome.
//									throw new VerificacaoNomeException(
//											"Insira um Nome que contenha apenas Letras e não deixe campos em Branco.");
//								}
//							} else {
//								// verif. rg.
//								throw new VerificacaoRGgException("Inserir RG válido.");
//							}
//						} else {
//							// verif. telefone.
//							throw new VerificacaoTelefoneException("Iserir apenas números.");
//						}
//					} else {
//						// verif. celular.
//						throw new VerificacaoCelularException("Inserir apenas números.");
//					}
//				} else {
//					// verif. email.
//					throw new VerificacaoEmailException("Insira um Email corretamente e Válido.");
//				}
//			} else {
//				throw new VerificacaoObjectNullException("Este Cliente já estar Cadastrado.");
//			}
//		}

		PessoaDAO pessoaDAO = new PessoaDAO();
		pessoaDAO.merge(pessoa);

		List<Object> pessoas = pessoaDAO.listar(Pessoa.class);

		List<Pessoa> people = getListPessoa(pessoas);

		return people;
	}

	
	
//	public boolean jaExiste(Pessoa pessoa) {
//		
//		PessoaDAO pessoaDAO = new PessoaDAO();
//
//		List<Object> pessoas = pessoaDAO.listar(Pessoa.class);
//		
//		boolean existe = false;
//		
//		for(int i = 1; i < pessoas.size(); i++) {
//			if(pessoas.get(i).equals(pessoa.getCpf())) {
//				existe = true;
//			}
//		}
//
//		return existe;
//
//	}

	public List<Pessoa> excluir(Pessoa pessoa) {

		PessoaDAO pessoaDAO = new PessoaDAO();
		pessoaDAO.excluir(pessoa);

		List<Object> pessoas = pessoaDAO.listar(Pessoa.class);

		List<Pessoa> people = getListPessoa(pessoas);

		return people;

	}
}