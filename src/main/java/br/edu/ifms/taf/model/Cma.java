package br.edu.ifms.taf.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
public class Cma implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="exercito_id")
	private Exercito exercito;
	@OneToMany(mappedBy = "cma")
	private List<Gu> gus = new ArrayList<Gu>();
	
	public Cma() {
		// TODO Auto-generated constructor stub
	}

	public Cma(Integer id, String nome, Exercito exercito) {
		super();
		this.id = id;
		this.nome = nome;
		this.exercito = exercito;
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

	public Exercito getExercito() {
		return exercito;
	}

	public void setExercito(Exercito exercito) {
		this.exercito = exercito;
	}
	
	

	public List<Gu> getGus() {
		return gus;
	}

	public void setGus(List<Gu> gus) {
		this.gus = gus;
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
		Cma other = (Cma) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	

}
