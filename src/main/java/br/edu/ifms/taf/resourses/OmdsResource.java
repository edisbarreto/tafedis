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

import br.edu.ifms.taf.dto.OmdsDTO;
import br.edu.ifms.taf.model.Omds;
import br.edu.ifms.taf.service.OmdsService;

@RestController
@RequestMapping(value = "/omds")
public class OmdsResource {
	
	@Autowired
	private OmdsService omds;
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public ResponseEntity<Omds> find(@PathVariable Integer id) {		
		Omds obj = omds.find(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody OmdsDTO objDto) {
		Omds obj = omds.fromDTO(objDto);
		obj = omds.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
			.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody OmdsDTO objDto, @PathVariable Integer id) {
		Omds obj = omds.fromDTO(objDto);
		obj.setId(id);
		obj = omds.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	
	@RequestMapping(value="/{id}", method= RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@RequestBody Omds obj,@PathVariable Integer id){
		omds.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<OmdsDTO>> findAll() {		
		List<Omds> list = omds.findAll();
		List<OmdsDTO> listDto = list.stream().map(obj -> new OmdsDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}
	

}
