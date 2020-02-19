package br.com.fazenda.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fazenda.dto.PesoMedioDto;
import br.com.fazenda.service.AnimalService;

@RestController
@RequestMapping("/animal")
public class AnimalController {

	private final AnimalService animalService;
	
	public AnimalController(AnimalService animalService) {
		this.animalService = animalService;
	}

	/**
	 * 
	 * @param peso
	 * @param idTipoAnimal
	 * 
	 * Servico rest POST para adicionar novos animais
	 * Exemplo http://localhost:8082/fazenda/animal/adicionar/0.2/1
	 */
	@PostMapping(value = "/adicionar/{peso}/{idTipoAnimal}")
	public void adicionar(@PathVariable("peso") Double peso, @PathVariable("idTipoAnimal") Long idTipoAnimal) {
		animalService.adicionar(peso, idTipoAnimal);
	}
	
	@GetMapping(value = "/calcularPesoMedio")
	public List<PesoMedioDto> calcularPesoMedio() {
		return animalService.calcularPesoMedio();
		
	}

}