/**
 * 
 */
package br.com.motoride.service;

import java.util.List;

import br.com.motoride.model.Produto;

/**
 * @author Filipe Soares Dantas
 *
 */
public interface ProdutoService {
	
	Produto salvar(Produto produto);
	List<Produto> listarProduto();
	void removerProduto(Produto produto);
	void removerPorId(int idProduto);
	Produto buscarPorId(int idProduto);

}
