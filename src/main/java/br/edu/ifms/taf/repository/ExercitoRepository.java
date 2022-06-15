package br.edu.ifms.taf.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ifms.taf.model.Exercito;

@Repository
public interface ExercitoRepository extends JpaRepository<Exercito, Integer>{

}
