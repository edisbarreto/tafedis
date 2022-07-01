package br.edu.ifms.taf.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.edu.ifms.taf.dto.CmaDTO;
import br.edu.ifms.taf.model.Cma;
import br.edu.ifms.taf.repository.CmaRepository;
import br.edu.ifms.taf.repository.GuRepository;
import br.edu.ifms.taf.service.exception.DataIntegrityException;
import br.edu.ifms.taf.service.exception.ObjectNotFoundException;


@Service
public class CmaService {
	
	@Autowired
	private CmaRepository repo;
	@Autowired
	private GuRepository gu;
	
	public Cma find(Integer id) {
		Optional<Cma> obj = repo.findById(id); 
		return obj.orElseThrow(() -> new ObjectNotFoundException( 
				 "Objeto não encontrado! Id: " + id + ", Tipo: " + Cma.class.getName()));		
	}
	
	@Transactional
	public Cma insert (Cma obj) {
		obj.setId(null);
		repo.save(obj);
		gu.saveAll(obj.getGus());
		return obj;
		
	}
	
	public Cma update(Cma obj) {
		Cma newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}

	public void delete(Integer id) {
		// TODO Auto-generated method stub
		find(id);
		try {
			repo.deleteById(id);	
		}
		catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível remover. Verifique a integridade referencial.");
		}
	}

	public List<Cma> findAll() {
		// TODO Auto-generated method stub		
		return repo.findAll();
	}
	
	
	
	public Cma fromDTO(CmaDTO objDto) {
		return new Cma(objDto.getId(), objDto.getNome(),null);
	}
	

	
	private void updateData(Cma newObj, Cma obj) {
		newObj.setNome(obj.getNome());	
	}

}
