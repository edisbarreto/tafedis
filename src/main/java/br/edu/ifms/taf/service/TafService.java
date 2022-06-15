package br.edu.ifms.taf.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.edu.ifms.taf.dto.TafDTO;
import br.edu.ifms.taf.model.Taf;
import br.edu.ifms.taf.repository.TafRepository;
import br.edu.ifms.taf.service.exception.DataIntegrityException;
import br.edu.ifms.taf.service.exception.ObjectNotFoundException;


@Service
public class TafService {
	
	@Autowired
	private TafRepository repo;
	
	public Taf find(Integer id) {
		Optional<Taf> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException( 
				 "Objeto não encontrado! Id: " + id + ", Tipo: " + Taf.class.getName()));		
	}
	
	public Taf insert (Taf obj) {
		obj.setId(null);
		repo.save(obj);		
		return repo.save(obj);
		
		
	}

	public Taf update(Taf obj) {
		Taf newObj = find(obj.getId());
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

	public List<Taf> findAll() {
		// TODO Auto-generated method stub		
		return repo.findAll();
	}
	
	public Page<Taf> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Taf fromDTO(TafDTO objDto) {
		return new Taf(objDto.getId(), objDto.getTeste(), objDto.getInstante());
	}
	
	private void updateData(Taf newObj, Taf obj) {
		newObj.setTeste(obj.getTeste());
		newObj.setInstante(obj.getInstante());

	}

}
