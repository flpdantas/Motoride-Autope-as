/**
 * 
 */
package br.com.motoride.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.motoride.exception.EntidadeComDependenciasException;
import br.com.motoride.model.Cliente;
import br.com.motoride.repository.ClienteRepository;

/**
 * @author Filipe Soares Dantas
 *
 */
@Service
public class ClienteServiceImpl implements ClienteService{

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Override
	@Transactional
	public Cliente Salvar(Cliente cliente) {
		return this.clienteRepository.save(cliente);
	}

	@Override
	public List<Cliente> listaClientes() {
		return this.clienteRepository.findAll();
	}

	@Override
	@Transactional
	public void remover(Cliente cliente) {
		if(!cliente.getProdutos().isEmpty()) {
			throw new EntidadeComDependenciasException("Cliente não pode ser removido, existem produtos vinculados!");
		} else
		this.clienteRepository.delete(cliente);
	
	}

	@Override
	public Cliente buscarPorId(int idCliente) {
		return this.clienteRepository.findById(idCliente).get();
	}


}
