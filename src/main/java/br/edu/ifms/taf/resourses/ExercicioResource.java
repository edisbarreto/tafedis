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

import br.edu.ifms.taf.dto.ExercicioDTO;
import br.edu.ifms.taf.model.Exercicio;
import br.edu.ifms.taf.service.ExercicioService;

@RestController
@RequestMapping(value = "/exercicio")
public class ExercicioResource {
	@Autowired
	private ExercicioService exercicio;
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public ResponseEntity<Exercicio> find(@PathVariable Integer id) {		
		Exercicio obj = exercicio.find(id);
		return ResponseEntity.ok().body(obj);
	}
		
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody ExercicioDTO objDto) {
		Exercicio obj = exercicio.fromDTO(objDto);
		obj = exercicio.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
			.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody ExercicioDTO objDto, @PathVariable Integer id) {
		Exercicio obj = exercicio.fromDTO(objDto);
		obj.setId(id);
		obj = exercicio.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	
	@RequestMapping(value="/{id}", method= RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@RequestBody Exercicio obj,@PathVariable Integer id){
		exercicio.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<ExercicioDTO>> findAll() {		
		List<Exercicio> list = exercicio.findAll();
		List<ExercicioDTO> listDto = list.stream().map(obj -> new ExercicioDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}
	
	@RequestMapping(value="/page", method=RequestMethod.GET)
	public ResponseEntity<Page<ExercicioDTO>> findPage(
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="nome") String orderBy, 
			@RequestParam(value="direction", defaultValue="ASC") String direction) {
		Page<Exercicio> list = exercicio.findPage(page, linesPerPage, orderBy, direction);
		Page<ExercicioDTO> listDto = list.map(obj -> new ExercicioDTO(obj));  
		return ResponseEntity.ok().body(listDto);
	}


}
