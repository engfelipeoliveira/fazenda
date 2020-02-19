package br.com.fazenda.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import br.com.fazenda.dto.PesoMedioDto;
import br.com.fazenda.model.Animal;
import br.com.fazenda.model.TipoAnimal;
import br.com.fazenda.repository.AnimalRepository;

@Service
public class AnimalServiceImpl implements AnimalService {
	
	private final AnimalRepository animalRepository;

	public AnimalServiceImpl(AnimalRepository animalRepository) {
		this.animalRepository = animalRepository;
	}

	@Override
	public void adicionar(Double peso, Long idTipoAnimal) {
		Animal animal = Animal.builder().peso(peso).tipoAnimal(TipoAnimal.builder().id(idTipoAnimal).build()).build(); 
		animalRepository.save(animal);
	}
	
	private Double calcularPesoMedioPorTipoAnimal(Long idTipoAnimal) {
		Stream<Animal> listaAnimalPorTipo = animalRepository.findAll().stream().filter(animal -> animal.getTipoAnimal().getId() == idTipoAnimal);
		Double somaPeso = listaAnimalPorTipo.mapToDouble(Animal::getPeso).sum();
		Long qtd = listaAnimalPorTipo.count();
		Double mediaPeso = somaPeso/qtd; 
		
		return mediaPeso;
	}

	@Override
	public List<PesoMedioDto> calcularPesoMedio() {
		
		Double mediaVacas = calcularPesoMedioPorTipoAnimal(1L);
		Double mediaPorcos = calcularPesoMedioPorTipoAnimal(2L);
		Double mediaGalinhas = calcularPesoMedioPorTipoAnimal(3L);
		
		return Arrays.asList(PesoMedioDto.builder().tipoAnimal("Vacas").peso(mediaVacas).build(),
				PesoMedioDto.builder().tipoAnimal("Porcos").peso(mediaPorcos).build(),
				PesoMedioDto.builder().tipoAnimal("Galinhas").peso(mediaGalinhas).build());
	}

}
