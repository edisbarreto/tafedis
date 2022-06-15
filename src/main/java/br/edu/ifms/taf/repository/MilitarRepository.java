package br.edu.ifms.taf.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ifms.taf.model.Militar;

@Repository
public interface MilitarRepository extends JpaRepository<Militar, Integer>{

}
