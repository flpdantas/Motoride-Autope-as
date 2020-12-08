/**
 * 
 */
package br.com.motoride.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.motoride.model.Endereco;

/**
 * @author Filipe Soares Dantas
 *
 */
@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Integer> {

}
