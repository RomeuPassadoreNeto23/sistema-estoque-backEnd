package loja.sistema.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;

@Data
@Entity
public class Pedido {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nomeDoCliente;
	private String sobrenomeDoCliente;
	private String codPedido;
	private String telefone;
	private String endereco;
	private String numero;
	@Column(columnDefinition = "VARCHAR(512)")
	private String complemento;
	private String bairro;
	private String cidade;
	private String cep;
	private String estado;
	private String cpf;
	@ManyToOne
	private Product product;
    private  int quantidadeDeProduct;

}
