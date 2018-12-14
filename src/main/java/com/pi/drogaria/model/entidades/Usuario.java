package com.pi.drogaria.model.entidades;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;


@Entity
@DiscriminatorValue(value = "U")
public class Usuario extends Pessoa{

	
	@Column(length = 32)
	private String senha;

	@Column
	private Character tipo;

	@Column
	private Boolean ativo;

	
	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Character getTipo() {
		return tipo;
	}

	// Converter o tipo caracter para tipo texto.
	@Transient
	public String getTipoFormatado() {
		String tipoFormatado;

		if (tipo == 'A') {
			tipoFormatado = "Administrador";
		} else if (tipo == 'B') {
			tipoFormatado = "Balconista";
		} else {
			tipoFormatado = "Gerente";
		}

		return tipoFormatado;
	}

	public void setTipo(Character tipo) {
		this.tipo = tipo;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	// Converter o inglês (true ou false) para (sim ou não)
	@Transient
	public String getAtivoFormatado() {
		String ativoFormatado;

		if (ativo) {
			ativoFormatado = "Sim";
		} else {
			ativoFormatado = "Não";
		}
		return ativoFormatado;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
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