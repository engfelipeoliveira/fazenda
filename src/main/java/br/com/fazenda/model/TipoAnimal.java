package br.com.fazenda.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.pojomatic.annotations.AutoProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AutoProperty
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Entity(name = "TIPO_ANIMAL")
public class TipoAnimal implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID_TIPO_ANIMAL", nullable = false, unique = true)
	private Long id;
	
	@Column(name = "TX_TIPO_ANIMAL", nullable = false, unique = true)
	private String descricao;
	
	@Column(name = "VL_PESO_VENDA", nullable = false)
	private Double pesoVenda;
	
	@Column(name = "VL_MERCADO", nullable = false)
	private String valorMercado;

}
