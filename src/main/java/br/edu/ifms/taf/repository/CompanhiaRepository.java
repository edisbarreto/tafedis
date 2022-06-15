package br.edu.ifms.taf.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ifms.taf.model.Companhia;

@Repository
public interface CompanhiaRepository extends JpaRepository<Companhia, Integer>{

}
