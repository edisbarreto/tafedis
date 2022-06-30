package br.edu.ifms.taf.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.edu.ifms.taf.dto.MilitarDTO;
import br.edu.ifms.taf.model.Militar;
import br.edu.ifms.taf.repository.MilitarExercicioRepository;
import br.edu.ifms.taf.repository.MilitarRepository;
import br.edu.ifms.taf.service.exception.DataIntegrityException;
import br.edu.ifms.taf.service.exception.ObjectNotFoundException;

@Service
public class MilitarService {
	@Autowired
	private MilitarRepository repo;
	@Autowired
	private MilitarExercicioRepository me;
	
	public Militar find(Integer id) {
		Optional<Militar> obj = repo.findById(id); 
		return obj.orElseThrow(() -> new ObjectNotFoundException( 
				 "Objeto não encontrado! Id: " + id + ", Tipo: " + Militar.class.getName()));		
	}
	
	@Transactional
	public Militar insert (Militar obj) {
		obj.setId(null);
		repo.save(obj);
		me.saveAll(obj.getMilitarExercicios());
		return obj;
		
	}
	
	public Militar update(Militar obj) {
		Militar newObj = find(obj.getId());
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

	public List<Militar> findAll() {
		// TODO Auto-generated method stub		
		return repo.findAll();
	}
	

	
	public Militar fromDTO(MilitarDTO objDto) {
		return new Militar(objDto.getId(), objDto.getNome(),objDto.getIdentidade(),objDto.getCpf(),null);
	}
	

	
	private void updateData(Militar newObj, Militar obj) {
		newObj.setNome(obj.getNome());
		newObj.setNome(obj.getIdentidade());
		newObj.setNome(obj.getCpf());
	
	}

}
