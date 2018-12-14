package com.pi.drogaria.model.entidades;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@DiscriminatorValue(value = "C")
public class Cliente extends Pessoa{

	@Column
	@Temporal(TemporalType.DATE)
	private Date dataCadastro;

	@Column
	private Boolean liberado;

	
	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Boolean getLiberado() {
		return liberado;
	}

	public void setLiberado(Boolean liberado) {
		this.liberado = liberado;
	}

	public void copiaDados(Pessoa pessoa) {
		this.setNome(pessoa.getNome());
		this.setCidade(pessoa.getCidade());
		this.setCpf(pessoa.getCpf());
		this.setRg(pessoa.getRg());
		this.setRua(pessoa.getRua());
		this.setNumero(pessoa.getNumero());
		this.setBairro(pessoa.getBairro());
		this.setCep(pessoa.getCep());
		this.setComplemento(pessoa.getComplemento());
		this.setTelefone(pessoa.getTelefone());
		this.setCelular(pessoa.getCelular());
		this.setEmail(pessoa.getEmail());
	}
}