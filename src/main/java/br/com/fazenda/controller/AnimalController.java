package br.com.fazenda.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fazenda.dto.PesoMedioDto;
import br.com.fazenda.dto.QuantidadeVendaDto;
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
	 * @param peso - peso do animal
	 * @param idTipoAnimal - id tipo do animal - ver tabela TIPO_ANIMAL
	 * 
	 * Servico rest http POST para adicionar novos animais
	 * Exemplo http://localhost:8082/fazenda/animal/adicionar/peso/0.2/idTipoAnimal/1
	 */
	@PostMapping(value = "/adicionar/peso/{peso}/idTipoAnimal/{idTipoAnimal}")
	public void adicionar(@PathVariable("peso") Double peso, @PathVariable("idTipoAnimal") Long idTipoAnimal) {
		animalService.adicionar(peso, idTipoAnimal);
	}
	
	/**
	 * Servico rest http GET para listar os tipos de animais com o peso medio
	 * @return
	 * Exemplo http://localhost:8082/fazenda/animal/calcularPesoMedio
	 */
	@GetMapping(value = "/calcularPesoMedio")
	public List<PesoMedioDto> calcularPesoMedio() {
		return animalService.calcularPesoMedio();
		
	}
	
	/**
	 * Servico rest http GET para consultar o peso medio de cada tipo de animal
	 * @return
	 * Exemplo http://localhost:8082/fazenda/animal/listarAnimaisAVenda
	 */
	@GetMapping(value = "/listarAnimaisAVenda")
	public List<QuantidadeVendaDto> listarAnimaisAVenda() {
		return animalService.listarAnimaisVenda();
		
	}

}