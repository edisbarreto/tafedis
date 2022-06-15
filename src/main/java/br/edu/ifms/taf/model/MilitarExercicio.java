package br.edu.ifms.taf.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
public class MilitarExercicio implements Serializable{
	

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String conceito;
	//@JsonIgnore
	@ManyToOne
	@JoinColumn(name="militar_id")
	private Militar militar;
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="exercicio_id")
	private Exercicio exercicio;
	
	public MilitarExercicio() {
		// TODO Auto-generated constructor stub
	}



	public MilitarExercicio(Integer id, String conceito, Militar militar, Exercicio exercicio) {
		super();
		this.id = id;
		this.conceito = conceito;
		this.militar = militar;
		this.exercicio = exercicio;
	}



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getConceito() {
		return conceito;
	}

	public void setConceito(String conceito) {
		this.conceito = conceito;
	}

	public Militar getMilitar() {
		return militar;
	}

	public void setMilitar(Militar militar) {
		this.militar = militar;
	}
	
	

	public Exercicio getExercicio() {
		return exercicio;
	}



	public void setExercicio(Exercicio exercicio) {
		this.exercicio = exercicio;
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
		MilitarExercicio other = (MilitarExercicio) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	

}
