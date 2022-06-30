package br.edu.ifms.taf.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.edu.ifms.taf.dto.PelotaoDTO;
import br.edu.ifms.taf.model.Pelotao;
import br.edu.ifms.taf.repository.MilitarRepository;
import br.edu.ifms.taf.repository.PelotaoRepository;
import br.edu.ifms.taf.service.exception.DataIntegrityException;
import br.edu.ifms.taf.service.exception.ObjectNotFoundException;

@Service
public class PelotaoService {
	@Autowired
	private PelotaoRepository repo;
	@Autowired
	private MilitarRepository militar;
	
	public Pelotao find(Integer id) {
		Optional<Pelotao> obj = repo.findById(id); 
		return obj.orElseThrow(() -> new ObjectNotFoundException( 
				 "Objeto não encontrado! Id: " + id + ", Tipo: " + Pelotao.class.getName()));		
	}
	
	@Transactional
	public Pelotao insert (Pelotao obj) {
		obj.setId(null);
		repo.save(obj);
		militar.saveAll(obj.getMilitares());
		return obj;
		
	}
	
	public Pelotao update(Pelotao obj) {
		Pelotao newObj = find(obj.getId());
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

	public List<Pelotao> findAll() {
		// TODO Auto-generated method stub		
		return repo.findAll();
	}
	

	
	public Pelotao fromDTO(PelotaoDTO objDto) {
		return new Pelotao(objDto.getId(), objDto.getNome(),null);
	}
	

	
	private void updateData(Pelotao newObj, Pelotao obj) {
		newObj.setNome(obj.getNome());
	
	}

}
