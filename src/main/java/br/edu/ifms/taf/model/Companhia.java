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
public class Companhia implements Serializable{


	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="omds_id")
	private Omds omds;
	
	@OneToMany(mappedBy = "companhia")
	private List<Pelotao> pelotoes = new ArrayList<Pelotao>();
	
	public Companhia() {
		// TODO Auto-generated constructor stub
	}

	

	public Companhia(Integer id, String nome, Omds omds) {
		super();
		this.id = id;
		this.nome = nome;
		this.omds = omds;
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

	

	public Omds getOmds() {
		return omds;
	}



	public void setOmds(Omds omds) {
		this.omds = omds;
	}
	
	



	public List<Pelotao> getPelotoes() {
		return pelotoes;
	}



	public void setPelotoes(List<Pelotao> pelotoes) {
		this.pelotoes = pelotoes;
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
		Companhia other = (Companhia) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}



	
	

}
