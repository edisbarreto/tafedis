package br.edu.ifms.taf.dto;

import java.io.Serializable;

public class CmaNewDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	
	private String nome;
	private String nomeGu;
	
	public CmaNewDTO() {
		// TODO Auto-generated constructor stub
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNomeGu() {
		return nomeGu;
	}

	public void setNomeGu(String nomeGu) {
		this.nomeGu = nomeGu;
	}
	
	

}
