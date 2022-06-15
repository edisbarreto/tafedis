package br.edu.ifms.taf.dto;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.edu.ifms.taf.model.Taf;

public class TafDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	
	@NotEmpty(message="Preenchimento obrigatório")
	@Length(min=5, max=80, message="O tamanho deve ser entre 5 e 80 caracteres")
	private String teste;
	@JsonFormat (pattern = "dd/MM/yyyy hh:mm")
	private Date instante;
	
	public TafDTO() {
		// TODO Auto-generated constructor stub
	}

	public TafDTO(Taf obj) {
		this.id = obj.getId();
		this.teste = obj.getTeste();
		this.instante = obj.getInstante();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTeste() {
		return teste;
	}

	public void setTeste(String teste) {
		this.teste = teste;
	}

	public Date getInstante() {
		return instante;
	}

	public void setInstante(Date instante) {
		this.instante = instante;
	}
	

}
