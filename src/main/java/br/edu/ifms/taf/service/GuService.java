package br.edu.ifms.taf.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.edu.ifms.taf.dto.GuDTO;
import br.edu.ifms.taf.model.Gu;
import br.edu.ifms.taf.repository.GuRepository;
import br.edu.ifms.taf.repository.OmdsRepository;
import br.edu.ifms.taf.service.exception.DataIntegrityException;
import br.edu.ifms.taf.service.exception.ObjectNotFoundException;

@Service
public class GuService {
	@Autowired
	private GuRepository repo;
	@Autowired
	private OmdsRepository omds;
	
	public Gu find(Integer id) {
		Optional<Gu> obj = repo.findById(id); 
		return obj.orElseThrow(() -> new ObjectNotFoundException( 
				 "Objeto não encontrado! Id: " + id + ", Tipo: " + Gu.class.getName()));		
	}
	
	@Transactional
	public Gu insert (Gu obj) {
		obj.setId(null);
		repo.save(obj);
		omds.saveAll(obj.getOmdss());
		return obj;
		
	}
	
	public Gu update(Gu obj) {
		Gu newObj = find(obj.getId());
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

	public List<Gu> findAll() {
		// TODO Auto-generated method stub		
		return repo.findAll();
	}
	

	
	public Gu fromDTO(GuDTO objDto) {
		return new Gu(objDto.getId(), objDto.getNome(),null);
	}
	

	
	private void updateData(Gu newObj, Gu obj) {
		newObj.setNome(obj.getNome());
	
	}

}
