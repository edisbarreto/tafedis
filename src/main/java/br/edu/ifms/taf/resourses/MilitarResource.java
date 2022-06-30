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

import br.edu.ifms.taf.dto.MilitarDTO;
import br.edu.ifms.taf.model.Militar;
import br.edu.ifms.taf.service.MilitarService;

@RestController
@RequestMapping(value = "/militar")
public class MilitarResource {
	
	@Autowired
	private MilitarService militar;
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public ResponseEntity<Militar> find(@PathVariable Integer id) {		
		Militar obj = militar.find(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody MilitarDTO objDto) {
		Militar obj = militar.fromDTO(objDto);
		obj = militar.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
			.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody MilitarDTO objDto, @PathVariable Integer id) {
		Militar obj = militar.fromDTO(objDto);
		obj.setId(id);
		obj = militar.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	
	@RequestMapping(value="/{id}", method= RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@RequestBody Militar obj,@PathVariable Integer id){
		militar.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<MilitarDTO>> findAll() {		
		List<Militar> list = militar.findAll();
		List<MilitarDTO> listDto = list.stream().map(obj -> new MilitarDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}
	

}
