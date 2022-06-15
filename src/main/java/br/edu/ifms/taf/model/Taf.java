package br.edu.ifms.taf.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonFormat;
@Entity
public class Taf implements Serializable{


	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String teste;
	
	@JsonFormat (pattern = "dd/MM/yyyy hh:mm")
	private Date instante;
	
	@ManyToMany
	@JoinTable(
			name="TAF_EXERCICIO",
			joinColumns = @JoinColumn(name="taf_id"),
			inverseJoinColumns = @JoinColumn(name="exercicio_id"))
	private List<Exercicio> exercicios = new ArrayList<Exercicio>();
	
	public Taf() {
		// TODO Auto-generated constructor stub
	}

	public Taf(Integer id, String teste, Date instante) {
		super();
		this.id = id;
		this.teste = teste;
		this.instante = instante;
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
	
	

	public List<Exercicio> getExercicios() {
		return exercicios;
	}

	public void setExercicios(List<Exercicio> exercicios) {
		this.exercicios = exercicios;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Taf other = (Taf) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	

}
