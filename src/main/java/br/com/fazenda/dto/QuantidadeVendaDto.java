package br.com.fazenda.dto;

import java.io.Serializable;

import org.pojomatic.annotations.AutoProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * classe pojo de transferencia de dados contendo a quantidade por tipo de animal
 * @author Admin
 *
 */
@AutoProperty
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class QuantidadeVendaDto implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String tipoAnimal;
	private Integer quantidade;

}
