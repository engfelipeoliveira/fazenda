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

/**
 * camada de servico
 * @author Admin
 *
 */
@Service
public class AnimalServiceImpl implements AnimalService {
	
	/**
	 * injecao de dependencia animal repository
	 *
	 */
	private final AnimalRepository animalRepository;

	/**
	 * metodo construtor
	 *
	 */
	public AnimalServiceImpl(AnimalRepository animalRepository) {
		this.animalRepository = animalRepository;
	}

	/**
	 * adicionar novo animal no banco de dados
	 */
	@Override
	public void adicionar(Double peso, Long idTipoAnimal) {
		Animal animal = Animal.builder().peso(peso).tipoAnimal(TipoAnimal.builder().id(idTipoAnimal).build()).build(); 
		animalRepository.save(animal);
	}
	
	/**
	 * listar os animais no banco de dados e calcular o peso medio por tipo de animal
	 */
	@Override
	public List<PesoMedioDto> calcularPesoMedio() {
		
		Double mediaVacas = calcularPesoMedioPorTipoAnimal(1L);
		Double mediaPorcos = calcularPesoMedioPorTipoAnimal(2L);
		Double mediaGalinhas = calcularPesoMedioPorTipoAnimal(3L);
		
		return Arrays.asList(PesoMedioDto.builder().tipoAnimal("Vacas").peso(mediaVacas).build(),
				PesoMedioDto.builder().tipoAnimal("Porcos").peso(mediaPorcos).build(),
				PesoMedioDto.builder().tipoAnimal("Galinhas").peso(mediaGalinhas).build());
	}

	/**
	 * consultar a quantidade de animais a venda no banco de dados considerando o peso de venda
	 */
	@Override
	public List<QuantidadeVendaDto> listarAnimaisVenda() {
		Integer qtdVacas = quantidadeVendaPorTipoAnimal(1L);
		Integer qtdPorcos = quantidadeVendaPorTipoAnimal(2L);
		Integer qtdGalinhas = quantidadeVendaPorTipoAnimal(3L);
		
		return Arrays.asList(QuantidadeVendaDto.builder().tipoAnimal("Vacas").quantidade(qtdVacas).build(),
				QuantidadeVendaDto.builder().tipoAnimal("Porcos").quantidade(qtdPorcos).build(),
				QuantidadeVendaDto.builder().tipoAnimal("Galinhas").quantidade(qtdGalinhas).build());
	}
	
	/**
	 * calcular o valor total da fazenda considerando a quantidade de animais no banco de dados e o valor de venda passado por parametro
	 */
	@Override
	public Double consultarValorFazendaPorParametro(Double vaca, Double porco, Double galinha) {
		Integer qtdVacas = quantidadePorTipoAnimal(1L);
		Integer qtdPorcos = quantidadePorTipoAnimal(2L);
		Integer qtdGalinhas = quantidadePorTipoAnimal(3L);
		
		return format((qtdVacas * vaca) + (qtdPorcos * porco) + (qtdGalinhas * galinha));
		
	}
	
	/**
	 * consultar o valor total do estoque no banco de dados considerando o valor e peso de venda de cada tipo de animal
	 */
	@Override
	public Double consultarValorEstoque() {
		List<Animal> listaAnimalPorTipo = animalRepository.findAll().stream().filter(animal -> animal.getPeso() >= animal.getTipoAnimal().getPesoVenda()).collect(Collectors.toList());
		
		Double valorEstoque = 0D;
		if(!listaAnimalPorTipo.isEmpty()) {
			for(Animal animal : listaAnimalPorTipo) {
				valorEstoque += animal.getTipoAnimal().getValorMercado();
			}
			
			return format(valorEstoque);
		}
		
		return 0D;
	}

	/**
	 * metodo acessorio para apoio no calculo de peso medio de cada tipo de animal
	 * @param idTipoAnimal
	 * @return
	 */
	private Double calcularPesoMedioPorTipoAnimal(Long idTipoAnimal) {
		List<Animal> listaAnimalPorTipo = animalRepository.findAll().stream().filter(animal -> animal.getTipoAnimal().getId() == idTipoAnimal).collect(Collectors.toList());
		Double peso = 0D;
		
		if(!listaAnimalPorTipo.isEmpty()) {
			int qtd = listaAnimalPorTipo.size();
			for(Animal animal : listaAnimalPorTipo) {
				peso += animal.getPeso();
			}
			
			return format(peso / qtd);
		}
		
		
		return 0D;
	}
	
	/**
	 * metodo acessorio para calcular a quantidade de animais por tipo
	 * @param idTipoAnimal
	 * @return
	 */
	private Integer quantidadePorTipoAnimal(Long idTipoAnimal) {
		List<Animal> listaAnimalPorTipo = animalRepository.findAll().stream().filter(animal -> animal.getTipoAnimal().getId() == idTipoAnimal).collect(Collectors.toList());
		return listaAnimalPorTipo.size();
	}
	
	/**
	 * metodo acessorio para calcular a quantidade de animais a venda por tipo
	 * @param idTipoAnimal
	 * @return
	 */
	private Integer quantidadeVendaPorTipoAnimal(Long idTipoAnimal) {
		List<Animal> listaAnimalPorTipo = animalRepository.findAll().stream().filter(animal -> animal.getTipoAnimal().getId() == idTipoAnimal && animal.getPeso() >= animal.getTipoAnimal().getPesoVenda()).collect(Collectors.toList());
		return listaAnimalPorTipo.size();
	}
	
	/**
	 * formatar o valor em duas casas decimais para exibicao
	 * @param valor
	 * @return
	 */
	private Double format(Double valor) {
		DecimalFormat df = new DecimalFormat("#.##");      
		return Double.valueOf(df.format(valor));
	}

}
