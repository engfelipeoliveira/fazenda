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

/**
 * camada rest
 * @author Admin
 *
 */
@RestController
@RequestMapping("/animal")
public class AnimalController {

	/**
	 * injecao de dependecia do servico animalService
	 */
	private final AnimalService animalService;
	
	/**
	 * metodo construtor
	 * @param animalService
	 */
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
	 * Servico rest http GET para consultar a quantidade de animais a venda por tipo considerando o peso de venda
	 * @return
	 * Exemplo http://localhost:8082/fazenda/animal/listarAnimaisAVenda
	 */
	@GetMapping(value = "/listarAnimaisAVenda")
	public List<QuantidadeVendaDto> listarAnimaisAVenda() {
		return animalService.listarAnimaisVenda();
		
	}
	
	
	/**
	 * Servico rest http GET para consultar o valor total do estoque considerando o peso e o preco de venda
	 * @return
	 * Exemplo http://localhost:8082/fazenda/animal/consultarValorEstoque
	 */
	@GetMapping(value = "/consultarValorEstoque")
	public Double consultarValorEstoque() {
		return animalService.consultarValorEstoque();
		
	}
	
	/**
	 * 
	 * @param vaca
	 * @param porco
	 * @param galinha
	 * Servico rest http GET para consultar o valor total da fazenda segundo valores passados por parametro para cada tipo de animal
	 * @return
	 * Exemplo http://localhost:8082/fazenda/animal/consultarValorFazendaPorParametro/vaca/15/porco/10/galinha/5
	 */
	@GetMapping(value = "/consultarValorFazendaPorParametro/vaca/{vaca}/porco/{porco}/galinha/{galinha}")
	public Double consultarValorFazendaPorParametro(@PathVariable("vaca") Double vaca, @PathVariable("porco") Double porco, @PathVariable("galinha") Double galinha) {
		return animalService.consultarValorFazendaPorParametro(vaca, porco, galinha);
		
	}

}