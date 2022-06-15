package br.edu.ifms.taf.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ifms.taf.model.Gu;

@Repository
public interface GuRepository extends JpaRepository<Gu, Integer>{

}
