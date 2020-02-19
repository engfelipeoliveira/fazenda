package br.com.fazenda.service;

import java.util.List;

import br.com.fazenda.dto.PesoMedioDto;
import br.com.fazenda.dto.QuantidadeVendaDto;

public interface AnimalService {
	
	void adicionar(Double peso, Long idTipoAnimal);
	
	List<PesoMedioDto> calcularPesoMedio();
	
	List<QuantidadeVendaDto> listarAnimaisVenda();
	
	Double consultarValorEstoque();

}
