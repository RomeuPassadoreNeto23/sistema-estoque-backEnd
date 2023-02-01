package loja.sistema.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private Integer quantidadeEmEstoque;
	@Column(columnDefinition = "VARCHAR(512)")
	private String descricaoDoProduct;
	private Double precoDoProduct;
	@Column(columnDefinition = "LONGTEXT")
	private String img;
	private String codProduct;
	
	

	

}
