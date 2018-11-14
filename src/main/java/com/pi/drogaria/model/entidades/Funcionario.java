package com.pi.drogaria.model.entidades;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Funcionario extends Pessoa {

	@OneToOne
	@JoinColumn(nullable = false)
	private Pessoa pessoa;



	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
}