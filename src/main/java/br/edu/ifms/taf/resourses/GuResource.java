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

import br.edu.ifms.taf.dto.GuDTO;
import br.edu.ifms.taf.model.Gu;
import br.edu.ifms.taf.service.GuService;

@RestController
@RequestMapping(value = "/gu")
public class GuResource {
	
	@Autowired
	private GuService gu;
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public ResponseEntity<Gu> find(@PathVariable Integer id) {		
		Gu obj = gu.find(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody GuDTO objDto) {
		Gu obj = gu.fromDTO(objDto);
		obj = gu.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
			.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody GuDTO objDto, @PathVariable Integer id) {
		Gu obj = gu.fromDTO(objDto);
		obj.setId(id);
		obj = gu.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	
	@RequestMapping(value="/{id}", method= RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@RequestBody Gu obj,@PathVariable Integer id){
		gu.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<GuDTO>> findAll() {		
		List<Gu> list = gu.findAll();
		List<GuDTO> listDto = list.stream().map(obj -> new GuDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}
	

}
