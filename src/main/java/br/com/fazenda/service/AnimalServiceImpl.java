package br.com.fazenda.service;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import br.com.fazenda.dto.PesoMedioDto;
import br.com.fazenda.dto.QuantidadeVendaDto;
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
	
	@Override
	public List<PesoMedioDto> calcularPesoMedio() {
		
		Double mediaVacas = calcularPesoMedioPorTipoAnimal(1L);
		Double mediaPorcos = calcularPesoMedioPorTipoAnimal(2L);
		Double mediaGalinhas = calcularPesoMedioPorTipoAnimal(3L);
		
		return Arrays.asList(PesoMedioDto.builder().tipoAnimal("Vacas").peso(mediaVacas).build(),
				PesoMedioDto.builder().tipoAnimal("Porcos").peso(mediaPorcos).build(),
				PesoMedioDto.builder().tipoAnimal("Galinhas").peso(mediaGalinhas).build());
	}

	@Override
	public List<QuantidadeVendaDto> listarAnimaisVenda() {
		Integer qtdVacas = quantidadeVendaPorTipoAnimal(1L);
		Integer qtdPorcos = quantidadeVendaPorTipoAnimal(2L);
		Integer qtdGalinhas = quantidadeVendaPorTipoAnimal(3L);
		
		return Arrays.asList(QuantidadeVendaDto.builder().tipoAnimal("Vacas").quantidade(qtdVacas).build(),
				QuantidadeVendaDto.builder().tipoAnimal("Porcos").quantidade(qtdPorcos).build(),
				QuantidadeVendaDto.builder().tipoAnimal("Galinhas").quantidade(qtdGalinhas).build());
	}

	private Double calcularPesoMedioPorTipoAnimal(Long idTipoAnimal) {
		List<Animal> listaAnimalPorTipo = animalRepository.findAll().stream().filter(animal -> animal.getTipoAnimal().getId() == idTipoAnimal).collect(Collectors.toList());
		Double peso = 0D;
		
		if(!listaAnimalPorTipo.isEmpty()) {
			int qtd = listaAnimalPorTipo.size();
			for(Animal animal : listaAnimalPorTipo) {
				peso += animal.getPeso();
			}
			
			DecimalFormat df = new DecimalFormat("#.##");      
			return Double.valueOf(df.format(peso / qtd));
		}
		
		
		return 0D;
	}
	
	private Integer quantidadeVendaPorTipoAnimal(Long idTipoAnimal) {
		List<Animal> listaAnimalPorTipo = animalRepository.findAll().stream().filter(animal -> animal.getTipoAnimal().getId() == idTipoAnimal && animal.getPeso() >= animal.getTipoAnimal().getPesoVenda()).collect(Collectors.toList());
		return listaAnimalPorTipo.size();
	}

}
