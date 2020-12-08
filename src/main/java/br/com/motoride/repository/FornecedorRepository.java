/**
 * 
 */
package br.com.motoride.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.motoride.model.Fornecedor;

/**
 * @author Filipe Soares Dantas
 *
 */
@Repository
public interface FornecedorRepository extends JpaRepository<Fornecedor, Integer>{

}
