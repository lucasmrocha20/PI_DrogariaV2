package com.pi.drogaria.DAO;

import java.util.List;

public interface DAO <T>{

	public void salvar(T t);
	
	public List<T> listar(Class clazz);
	
	public List<T> listar(String campoOrdenacao, Class clazz);
	
	public T buscar(Long codigo);
	
	public void excluir(T t);
	
	public void editar(T t);
	
	public void merge(T t);
}
