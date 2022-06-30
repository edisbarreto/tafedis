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

import br.edu.ifms.taf.dto.PelotaoDTO;
import br.edu.ifms.taf.model.Pelotao;
import br.edu.ifms.taf.service.PelotaoService;

@RestController
@RequestMapping(value = "/pelotao")
public class PelotaoResource {
	
	@Autowired
	private PelotaoService pelotao;
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public ResponseEntity<Pelotao> find(@PathVariable Integer id) {		
		Pelotao obj = pelotao.find(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody PelotaoDTO objDto) {
		Pelotao obj = pelotao.fromDTO(objDto);
		obj = pelotao.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
			.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody PelotaoDTO objDto, @PathVariable Integer id) {
		Pelotao obj = pelotao.fromDTO(objDto);
		obj.setId(id);
		obj = pelotao.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	
	@RequestMapping(value="/{id}", method= RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@RequestBody Pelotao obj,@PathVariable Integer id){
		pelotao.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<PelotaoDTO>> findAll() {		
		List<Pelotao> list = pelotao.findAll();
		List<PelotaoDTO> listDto = list.stream().map(obj -> new PelotaoDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}
	

}
