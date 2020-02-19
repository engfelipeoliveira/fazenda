package br.com.fazenda.service;

import java.util.List;

import br.com.fazenda.dto.PesoMedioDto;

public interface AnimalService {
	
	void adicionar(Double peso, Long idTipoAnimal);
	
	List<PesoMedioDto> calcularPesoMedio();

}
