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

import br.edu.ifms.taf.dto.CmaDTO;
import br.edu.ifms.taf.dto.CmaNewDTO;
import br.edu.ifms.taf.model.Cma;
import br.edu.ifms.taf.service.CmaService;

@RestController
@RequestMapping(value = "/cma")
public class CmaResource {
	
	@Autowired
	private CmaService cma;
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public ResponseEntity<Cma> find(@PathVariable Integer id) {		
		Cma obj = cma.find(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody CmaNewDTO objDto) {
		Cma obj = cma.fromDTO(objDto);
		obj = cma.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
			.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody CmaDTO objDto, @PathVariable Integer id) {
		Cma obj = cma.fromDTO(objDto);
		obj.setId(id);
		obj = cma.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	
	@RequestMapping(value="/{id}", method= RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@RequestBody Cma obj,@PathVariable Integer id){
		cma.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<CmaDTO>> findAll() {		
		List<Cma> list = cma.findAll();
		List<CmaDTO> listDto = list.stream().map(obj -> new CmaDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}
	

	@RequestMapping(value="/page", method=RequestMethod.GET)
	public ResponseEntity<Page<CmaDTO>> findPage(
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="nome") String orderBy, 
			@RequestParam(value="direction", defaultValue="ASC") String direction) {
		Page<Cma> list = cma.findPage(page, linesPerPage, orderBy, direction);
		Page<CmaDTO> listDto = list.map(obj -> new CmaDTO(obj));  
		return ResponseEntity.ok().body(listDto);
	}


}
