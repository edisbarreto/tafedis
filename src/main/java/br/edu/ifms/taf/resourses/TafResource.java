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

import br.edu.ifms.taf.dto.TafDTO;
import br.edu.ifms.taf.model.Taf;
import br.edu.ifms.taf.service.TafService;

@RestController
@RequestMapping(value = "/taf")
public class TafResource {
	@Autowired
	private TafService taf;
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public ResponseEntity<Taf> find(@PathVariable Integer id) {		
		Taf obj = taf.find(id);
		return ResponseEntity.ok().body(obj);
	}
		
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody TafDTO objDto) {
		Taf obj = taf.fromDTO(objDto);
		obj = taf.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
			.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody TafDTO objDto, @PathVariable Integer id) {
		Taf obj = taf.fromDTO(objDto);
		obj.setId(id);
		obj = taf.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	
	@RequestMapping(value="/{id}", method= RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@RequestBody Taf obj,@PathVariable Integer id){
		taf.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<TafDTO>> findAll() {		
		List<Taf> list = taf.findAll();
		List<TafDTO> listDto = list.stream().map(obj -> new TafDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}
	
	@RequestMapping(value="/page", method=RequestMethod.GET)
	public ResponseEntity<Page<TafDTO>> findPage(
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="nome") String orderBy, 
			@RequestParam(value="direction", defaultValue="ASC") String direction) {
		Page<Taf> list = taf.findPage(page, linesPerPage, orderBy, direction);
		Page<TafDTO> listDto = list.map(obj -> new TafDTO(obj));  
		return ResponseEntity.ok().body(listDto);
	}


}
