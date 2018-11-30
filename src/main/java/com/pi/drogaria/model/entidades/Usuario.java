package com.pi.drogaria.model.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
public class Usuario extends Pessoa{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int codigoUsuario;
	
	@Column(length = 32, nullable = false)
	private String senha;

	@Column(nullable = false)
	private Character tipo;

	@Column(nullable = false)
	private Boolean ativo;

	public int getCodigoUsuario() {
		return codigoUsuario;
	}
	
	public void setCodigoUsuario(int codigoUsuario) {
		this.codigoUsuario = codigoUsuario;
	}
	
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