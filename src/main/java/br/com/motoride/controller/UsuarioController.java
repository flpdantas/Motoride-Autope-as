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
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.motoride.model.Usuario;
import br.com.motoride.repository.UsuarioRepository;
import io.swagger.annotations.ApiOperation;

/**
 * @author Filipe Soares Dantas
 *
 */
@RestController
@RequestMapping("/usuario/api/")
public class UsuarioController {
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@GetMapping("/v1")
	public List<Usuario> listarv1(){
		return this.usuarioRepository.findAll();
	}
	
	@GetMapping("/v2")
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<List<Usuario>> listarV2(){
		return ResponseEntity.ok().body(this.usuarioRepository.findAll());
	}
	
	@PutMapping("/v1/{id}")
	public Usuario editar(@PathVariable("id") Integer id,@RequestBody Usuario usuario) {
		Usuario usuarioBD =  this.usuarioRepository.findById(id).get();
		BeanUtils.copyProperties(usuario, usuarioBD, "id");
		this.usuarioRepository.save(usuarioBD);
		return usuarioBD;
	}
	
	@PatchMapping("/v2/{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<Usuario> editarUsuarioV2(@PathVariable("id") int id, @RequestBody Usuario usuario){
		Usuario usuarioBD =  this.usuarioRepository.findById(id).get();
		BeanUtils.copyProperties(usuario, usuarioBD, "id");
		return ResponseEntity.ok().body(this.usuarioRepository.save(usuario));
	}
	
	@DeleteMapping("/v1/{id}")
	public void deletarv1(@PathVariable("id") Integer id) {
		this.usuarioRepository.deleteById(id);
	}
	
	@DeleteMapping("/v2/{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public String deletarV2(@PathVariable("id") int id) {
		this.usuarioRepository.deleteById(id);
		return "Usuario deletado!";
	}
	
	@PostMapping("/v1")
	public Usuario salvarv1(@RequestBody Usuario usuario) {
		return this.usuarioRepository.save(usuario);
	}
	
	@ApiOperation(
			value="Cadastrar um Usuario novo", 
			response=ResponseEntity.class, 
			notes="Essa operação salva um novo registro com as informações do Usuario.")
	
	@PostMapping("/v2")
	@ResponseStatus(value = HttpStatus.CREATED)
	public ResponseEntity<Usuario> salvarV2(@RequestBody Usuario usuario){
		return ResponseEntity.ok().body(this.usuarioRepository.save(usuario));
	}

}
