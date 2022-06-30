package br.edu.ifms.taf.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.edu.ifms.taf.dto.OmdsDTO;
import br.edu.ifms.taf.model.Omds;
import br.edu.ifms.taf.repository.CompanhiaRepository;
import br.edu.ifms.taf.repository.OmdsRepository;
import br.edu.ifms.taf.service.exception.DataIntegrityException;
import br.edu.ifms.taf.service.exception.ObjectNotFoundException;

@Service
public class OmdsService {
	@Autowired
	private OmdsRepository repo;
	@Autowired
	private CompanhiaRepository companhia;
	
	public Omds find(Integer id) {
		Optional<Omds> obj = repo.findById(id); 
		return obj.orElseThrow(() -> new ObjectNotFoundException( 
				 "Objeto não encontrado! Id: " + id + ", Tipo: " + Omds.class.getName()));		
	}
	
	@Transactional
	public Omds insert (Omds obj) {
		obj.setId(null);
		repo.save(obj);
		companhia.saveAll(obj.getCompanhias());
		return obj;
		
	}
	
	public Omds update(Omds obj) {
		Omds newObj = find(obj.getId());
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

	public List<Omds> findAll() {
		// TODO Auto-generated method stub		
		return repo.findAll();
	}
	

	
	public Omds fromDTO(OmdsDTO objDto) {
		return new Omds(objDto.getId(), objDto.getNome(),null);
	}
	

	
	private void updateData(Omds newObj, Omds obj) {
		newObj.setNome(obj.getNome());
	
	}

}
