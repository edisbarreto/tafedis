package br.edu.ifms.taf.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.edu.ifms.taf.dto.ExercicioDTO;
import br.edu.ifms.taf.model.Exercicio;
import br.edu.ifms.taf.repository.ExercicioRepository;
import br.edu.ifms.taf.service.exception.DataIntegrityException;
import br.edu.ifms.taf.service.exception.ObjectNotFoundException;


@Service
public class ExercicioService {
	
	@Autowired
	private ExercicioRepository repo;
	
	public Exercicio find(Integer id) {
		Optional<Exercicio> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException( 
				 "Objeto não encontrado! Id: " + id + ", Tipo: " + Exercicio.class.getName()));		
	}
	
	public Exercicio insert (Exercicio obj) {
		obj.setId(null);
		return repo.save(obj);
		
	}

	public Exercicio update(Exercicio obj) {
		Exercicio newObj = find(obj.getId());
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

	public List<Exercicio> findAll() {
		// TODO Auto-generated method stub		
		return repo.findAll();
	}
	
	public Page<Exercicio> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Exercicio fromDTO(ExercicioDTO objDto) {
		return new Exercicio(objDto.getId(), objDto.getNome());
	}
	
	private void updateData(Exercicio newObj, Exercicio obj) {
		newObj.setNome(obj.getNome());
		

	}

}
