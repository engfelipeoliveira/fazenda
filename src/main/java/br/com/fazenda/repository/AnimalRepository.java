package br.com.fazenda.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fazenda.model.Animal;

/**
 * camada de repositorio para acesso a dados
 * @author Admin
 *
 */
public interface AnimalRepository extends JpaRepository<Animal, Long> {
	
	List<Animal> findAllByTipoAnimalId(Long idTipoAnimal);

}