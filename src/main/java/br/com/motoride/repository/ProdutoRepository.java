/**
 * 
 */
package br.com.motoride.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.motoride.model.Produto;

/**
 * @author Filipe Soares Dantas
 *
 */
@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

}
