package br.edu.ifms.taf.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.edu.ifms.taf.dto.CompanhiaDTO;
import br.edu.ifms.taf.model.Companhia;
import br.edu.ifms.taf.repository.CompanhiaRepository;
import br.edu.ifms.taf.repository.PelotaoRepository;
import br.edu.ifms.taf.service.exception.DataIntegrityException;
import br.edu.ifms.taf.service.exception.ObjectNotFoundException;

@Service
public class CompanhiaService {
	@Autowired
	private CompanhiaRepository repo;
	@Autowired
	private PelotaoRepository pelotao;
	
	public Companhia find(Integer id) {
		Optional<Companhia> obj = repo.findById(id); 
		return obj.orElseThrow(() -> new ObjectNotFoundException( 
				 "Objeto não encontrado! Id: " + id + ", Tipo: " + Companhia.class.getName()));		
	}
	
	@Transactional
	public Companhia insert (Companhia obj) {
		obj.setId(null);
		repo.save(obj);
		pelotao.saveAll(obj.getPelotoes());
		return obj;
		
	}
	
	public Companhia update(Companhia obj) {
		Companhia newObj = find(obj.getId());
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

	public List<Companhia> findAll() {
		// TODO Auto-generated method stub		
		return repo.findAll();
	}
	

	
	public Companhia fromDTO(CompanhiaDTO objDto) {
		return new Companhia(objDto.getId(), objDto.getNome(),null);
	}
	

	
	private void updateData(Companhia newObj, Companhia obj) {
		newObj.setNome(obj.getNome());
	
	}

}
