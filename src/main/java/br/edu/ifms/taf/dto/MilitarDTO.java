package br.edu.ifms.taf.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import br.edu.ifms.taf.model.Militar;

public class MilitarDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	private Integer id;
	
	@NotEmpty(message="Preenchimento obrigatório")
	@Length(min=5, max=80, message="O tamanho deve ser entre 5 e 80 caracteres")
	private String nome;
	private String identidade;
	private String cpf;
	
	public MilitarDTO() {
		// TODO Auto-generated constructor stub
	}
	
	

	public MilitarDTO(Militar obj) {		
		super();
		this.id = obj.getId();
		this.nome = obj.getNome();
		this.identidade = obj.getIdentidade();
		this.cpf = obj.getCpf();
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getIdentidade() {
		return identidade;
	}


	public void setIdentidade(String identidade) {
		this.identidade = identidade;
	}


	public String getCpf() {
		return cpf;
	}


	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	

}
