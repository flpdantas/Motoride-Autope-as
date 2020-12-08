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

import br.com.motoride.model.Produto;
import br.com.motoride.service.ProdutoService;
import io.swagger.annotations.ApiOperation;
import lombok.Data;

/**
 * @author Filipe Soares Dantas
 *
 */
@Data
@RestController
@RequestMapping("/produto/api")
public class ProdutoController {
	
	@Autowired
	private ProdutoService produtoService;
	
	@PostMapping("/v1")
	public Produto Salvarv1(@RequestBody Produto produto) {
		this.produtoService.salvar(produto);
		return produto;
	}
	
	@ApiOperation(
			value="Cadastrar um Produto novo", 
			response=ResponseEntity.class, 
			notes="Essa operação salva um novo registro com as informações do Produto.")
	
	@PostMapping("/v2")
	@ResponseStatus(value = HttpStatus.CREATED)
	public ResponseEntity<Produto> salvarV2(@RequestBody Produto produto){
		return ResponseEntity.ok().body(this.produtoService.salvar(produto));
	}
	
	@GetMapping("/v1")
	public List<Produto> Listarv1(){
		return this.produtoService.listarProduto();
	}
	
	@GetMapping("/v2")
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<List<Produto>> listarV2(){
		return ResponseEntity.ok().body(this.produtoService.listarProduto());
	}
	
	

 	@PutMapping("/v1/{id}")
	public Produto buscarProdutoId(@PathVariable("id") Integer id, @RequestBody Produto produto) {
		Produto produtoBD = this.produtoService.buscarPorId(id);
		BeanUtils.copyProperties(produto, produtoBD, "id");
		this.produtoService.salvar(produtoBD);
		return produtoBD;
	}
 	
 	@PutMapping("/v2/{id}")
	@ResponseStatus(value = HttpStatus.CREATED)
	public ResponseEntity<Produto> editarProdutoId2(@PathVariable("id") int id, @RequestBody Produto produto){
		Produto produtoBD = this.produtoService.buscarPorId(id);
		BeanUtils.copyProperties(produto, produtoBD, "id");
		return ResponseEntity.ok().body(this.produtoService.salvar(produtoBD));
	}
 	
 	@DeleteMapping("/v1/{id}")
	public String Remover(@PathVariable("id") Integer id) {
		this.produtoService.removerProduto(this.produtoService.buscarPorId(id));
		return "Produto informado deletado com sucesso!";
	}
 	
 	@DeleteMapping("/v2/{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public String deletar2(@PathVariable("id") int id) {
		this.produtoService.removerProduto(this.produtoService.buscarPorId(id));
		return "Produto foi deletado!";
	}


}