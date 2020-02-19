package br.com.fazenda.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fazenda.model.Animal;

public interface AnimalRepository extends JpaRepository<Animal, Long> {
	
	List<Animal> findAllByTipoAnimalId(Long idTipoAnimal);

}