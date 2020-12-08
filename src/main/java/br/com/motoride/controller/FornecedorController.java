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

import br.com.motoride.model.Fornecedor;
import br.com.motoride.repository.FornecedorRepository;
import io.swagger.annotations.ApiOperation;

/**
 * @author Filipe Soares Dantas
 *
 */
@RestController
@RequestMapping("/fornecedor/api/")
public class FornecedorController {
	@Autowired
	private FornecedorRepository fornecedorRepository;
	
	@PostMapping("/v1")
	public Fornecedor salvarv1(@RequestBody Fornecedor fornecedor) {
		return this.fornecedorRepository.save(fornecedor);
	}
	
	@ApiOperation(
			value="Cadastrar um Fornecedor novo", 
			response=ResponseEntity.class, 
			notes="Essa operação salva um novo registro com as informações do Fornecedor.")
	
	@PostMapping("/v2")
	@ResponseStatus(value = HttpStatus.CREATED)
	public ResponseEntity<Fornecedor> salvarV2(@RequestBody Fornecedor fornecedor){
		return ResponseEntity.ok().body(this.fornecedorRepository.save(fornecedor));
	}


	
	@GetMapping("/v1")
	public List<Fornecedor> listarv1() {
		return this.fornecedorRepository.findAll();
	}
	
	@GetMapping("/v2")
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<List<Fornecedor>> listarV2(){
		return ResponseEntity.ok().body(this.fornecedorRepository.findAll());
	}
	
	
	@PutMapping("/v1/{id}")
	public Fornecedor editar(@PathVariable("id") Integer id, @RequestBody Fornecedor fornecedor) {
		Fornecedor fornecedorBD = this.fornecedorRepository.findById(id).get();
		BeanUtils.copyProperties(fornecedor, fornecedorBD, "id");
		this.fornecedorRepository.save(fornecedorBD);
		return fornecedorBD;
	}
	
	@PutMapping("/v2/{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<Fornecedor> editarFornecedorV2(@PathVariable("id") int id, @RequestBody Fornecedor fornecedor){
		Fornecedor fornecedorBD = this.fornecedorRepository.findById(id).get();
		BeanUtils.copyProperties(fornecedor, fornecedorBD, "id");
		return ResponseEntity.ok().body(this.fornecedorRepository.save(fornecedorBD));
	}
	
	@DeleteMapping("/v1/{id}")
	public void deletarv1(@PathVariable("id") Integer id) {
		this.fornecedorRepository.deleteById(id);
	}
	
}
	
