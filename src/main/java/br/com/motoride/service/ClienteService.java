/**
 * 
 */
package br.com.motoride.service;

import java.util.List;

import br.com.motoride.model.Cliente;

/**
 * @author Filipe Soares Dantas
 *
 */
public interface ClienteService {
	
	Cliente Salvar(Cliente cliente);
	List<Cliente> listaClientes();
	void remover(Cliente cliente);
	Cliente buscarPorId(int idCliente);


}

