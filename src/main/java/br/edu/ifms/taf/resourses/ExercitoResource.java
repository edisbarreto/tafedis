package br.edu.ifms.taf.resourses;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.edu.ifms.taf.dto.ExercitoDTO;
import br.edu.ifms.taf.model.Exercito;
import br.edu.ifms.taf.service.ExercitoService;

@RestController
@RequestMapping(value = "/exercito")
public class ExercitoResource {
	@Autowired
	private ExercitoService exercito;
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public ResponseEntity<Exercito> find(@PathVariable Integer id) {		
		Exercito obj = exercito.find(id);
		return ResponseEntity.ok().body(obj);
	}
		
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody ExercitoDTO objDto) {
		Exercito obj = exercito.fromDTO(objDto);
		obj = exercito.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
			.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody ExercitoDTO objDto, @PathVariable Integer id) {
		Exercito obj = exercito.fromDTO(objDto);
		obj.setId(id);
		obj = exercito.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	
	@RequestMapping(value="/{id}", method= RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@RequestBody Exercito obj,@PathVariable Integer id){
		exercito.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<ExercitoDTO>> findAll() {		
		List<Exercito> list = exercito.findAll();
		List<ExercitoDTO> listDto = list.stream().map(obj -> new ExercitoDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}
	
	@RequestMapping(value="/page", method=RequestMethod.GET)
	public ResponseEntity<Page<ExercitoDTO>> findPage(
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="nome") String orderBy, 
			@RequestParam(value="direction", defaultValue="ASC") String direction) {
		Page<Exercito> list = exercito.findPage(page, linesPerPage, orderBy, direction);
		Page<ExercitoDTO> listDto = list.map(obj -> new ExercitoDTO(obj));  
		return ResponseEntity.ok().body(listDto);
	}


}
