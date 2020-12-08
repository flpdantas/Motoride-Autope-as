/**
 * 
 */
package br.com.motoride.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.motoride.model.Endereco;
import br.com.motoride.repository.EnderecoRepository;
import io.swagger.annotations.ApiOperation;

/**
 * @author Filipe Soares Dantas
 *
 */
@RestController
@RequestMapping("/endereco/api/")
public class EnderecoController {
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@GetMapping("/v1")
	public List<Endereco> listar() {
		return this.enderecoRepository.findAll();
	}
	
	@GetMapping("/v2") 
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<List<Endereco>> listarV2() {
		return ResponseEntity.ok().body(this.enderecoRepository.findAll());
	}
	
	@PutMapping("/v1/{id}")
	public Endereco editar(@PathVariable("id") Integer id, @RequestBody Endereco endereco) {
		Endereco enderecoBD = this.enderecoRepository.findById(id).get();
		BeanUtils.copyProperties(endereco, enderecoBD, "id");
		this.enderecoRepository.save(enderecoBD);
		return enderecoBD;
	}
	
	@PutMapping("/v2/{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<Endereco> editarEnderecoV2(@PathVariable("id") int id, @RequestBody Endereco endereco){
		Endereco enderecoBD = this.enderecoRepository.findById(id).get();
		BeanUtils.copyProperties(endereco, enderecoBD, "id");
		return ResponseEntity.ok().body(this.enderecoRepository.save(enderecoBD));
	}
	
	@DeleteMapping("/v1/{id}")
	public void deletarv1(@PathVariable("id") Integer id) {
		this.enderecoRepository.deleteById(id);
	}
	
	@DeleteMapping("/v2/{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public String deletarV2(@PathVariable("id") int id) {
		this.enderecoRepository.deleteById(id);
		return "Endereço foi excluído com sucesso!";
	}
	
	@PostMapping("/v1")
	public Endereco salvarV1(@RequestBody Endereco endereco) {
		return this.enderecoRepository.save(endereco);
	}
	
	@ApiOperation(
			value="Cadastrar um Endereço novo", 
			response=ResponseEntity.class, 
			notes="Essa operação salva um novo registro com as informações do Endereço.")
	
	@PostMapping("/v2")
	@ResponseStatus(value = HttpStatus.CREATED)
	public ResponseEntity<Endereco> salvarV2(@RequestBody Endereco endereco){
		return ResponseEntity.ok().body(this.enderecoRepository.save(endereco));
	}

}
