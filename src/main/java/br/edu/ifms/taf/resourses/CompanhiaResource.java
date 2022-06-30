package br.edu.ifms.taf.resourses;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.edu.ifms.taf.dto.CompanhiaDTO;
import br.edu.ifms.taf.model.Companhia;
import br.edu.ifms.taf.service.CompanhiaService;

@RestController
@RequestMapping(value = "/companhia")
public class CompanhiaResource {
	
	@Autowired
	private CompanhiaService companhia;
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public ResponseEntity<Companhia> find(@PathVariable Integer id) {		
		Companhia obj = companhia.find(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody CompanhiaDTO objDto) {
		Companhia obj = companhia.fromDTO(objDto);
		obj = companhia.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
			.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody CompanhiaDTO objDto, @PathVariable Integer id) {
		Companhia obj = companhia.fromDTO(objDto);
		obj.setId(id);
		obj = companhia.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	
	@RequestMapping(value="/{id}", method= RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@RequestBody Companhia obj,@PathVariable Integer id){
		companhia.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<CompanhiaDTO>> findAll() {		
		List<Companhia> list = companhia.findAll();
		List<CompanhiaDTO> listDto = list.stream().map(obj -> new CompanhiaDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}
	

}
