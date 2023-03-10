package loja.sistema.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import org.jasypt.util.text.BasicTextEncryptor;

import loja.sistema.Enum.Status;
import lombok.Data;

@Data
@Entity

public class Pedido {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nomeCompleto;

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
	// Definindo uma relação um para muitos
	@OneToMany
	@JoinColumn(name="pididos_id")
	private List<Product> product;
	@Enumerated(EnumType.STRING)
	private Status status;

	private Double precoTotal;

	private Integer quantidadeDeProduct;

	/*
	 * public Pedido(String nomeCompleto, String codPedido, String telefone, String
	 * endereco, String numero, String complemento, String bairro, String cidade,
	 * String cep, String estado, String cpf, Set<Product> set) { BasicTextEncryptor
	 * textEncryptor = new BasicTextEncryptor();
	 * textEncryptor.setPassword("opensezame"); String TelefoneCrip =
	 * textEncryptor.encrypt(telefone); String enderecoCrip =
	 * textEncryptor.encrypt(endereco); String numeroCrip =
	 * textEncryptor.encrypt(numero); String complementoCrip =
	 * textEncryptor.encrypt(complemento); String bairroCrip =
	 * textEncryptor.encrypt(bairro); String cidadeCrip =
	 * textEncryptor.encrypt(cidade); String cepCrip = textEncryptor.encrypt(cep);
	 * String estadoCrip = textEncryptor.encrypt(estado); String cpfCrip =
	 * textEncryptor.encrypt(cpf);
	 * 
	 * this.nomeCompleto = nomeCompleto; this.codPedido = codPedido; this.telefone =
	 * TelefoneCrip ; this.endereco = enderecoCrip; this.numero = numeroCrip;
	 * this.complemento = complementoCrip; this.bairro = bairroCrip; this.cidade =
	 * cidadeCrip ; this.cep = cepCrip; this.estado = estadoCrip ; this.cpf =
	 * cpfCrip; this.product.addAll(set);
	 * set.stream().findAny().get().getPedidos().add(this);
	 * 
	 * 
	 * }
	 */

	// metodo que descriptografia o telefone ,utilizando o proprio getTelefone
	public String getTelefone() {

		BasicTextEncryptor textEncryptor = new BasicTextEncryptor();

		textEncryptor.setPassword("opensezame");

		String telefoneDesc = textEncryptor.decrypt(this.telefone);

		return telefoneDesc;

	}

	// metodo que criptografia o telefone ,utilizando o proprio setTelefone
	public void setTelefone(String telefone) {
		BasicTextEncryptor textEncryptor = new BasicTextEncryptor();

		textEncryptor.setPassword("opensezame");

		String TelefoneCrip = textEncryptor.encrypt(telefone);
		this.telefone = TelefoneCrip;
	}

	// metodo que descriptografia o endereco ,utilizando o proprio getendereco
	public String getendereco() {

		BasicTextEncryptor textEncryptor = new BasicTextEncryptor();

		textEncryptor.setPassword("opensezame");

		String enderecoDesc = textEncryptor.decrypt(this.endereco);

		return enderecoDesc;

	}

	// metodo que criptografia o endereço,utilizando o proprio setendereco
	public void setendereco(String endereco) {
		BasicTextEncryptor textEncryptor = new BasicTextEncryptor();

		textEncryptor.setPassword("opensezame");

		String enderecoCrip = textEncryptor.encrypt(endereco);

		this.endereco = enderecoCrip;

	}

	// metodo que descriptografia o numero ,utilizando o proprio getnumero
	public String getnumero() {

		BasicTextEncryptor textEncryptor = new BasicTextEncryptor();

		textEncryptor.setPassword("opensezame");

		String numeroDesc = textEncryptor.decrypt(this.numero);

		return numeroDesc;

	}

	// metodo que criptografia o numero ,utilizando o proprio setnumero
	public void setnumero(String numero) {
		BasicTextEncryptor textEncryptor = new BasicTextEncryptor();

		textEncryptor.setPassword("opensezame");

		String numeroCrip = textEncryptor.encrypt(numero);

		this.numero = numeroCrip;

	}

	// metodo que descriptografia o complemento ,utilizando o proprio getcomplemento
	public String getcomplemento() {

		BasicTextEncryptor textEncryptor = new BasicTextEncryptor();

		textEncryptor.setPassword("opensezame");

		String complementoDesc = textEncryptor.decrypt(this.complemento);

		return complementoDesc;

	}

	// metodo que criptografia o complemento, utilizando o proprio setcomplemento
	public void setcomplemento(String complemento) {
		BasicTextEncryptor textEncryptor = new BasicTextEncryptor();

		textEncryptor.setPassword("opensezame");

		String complementoCrip = textEncryptor.encrypt(complemento);

		this.complemento = complementoCrip;

	}

	// metodo que descriptografia o bairro ,utilizando o proprio getbairro
	public String getbairro() {

		BasicTextEncryptor textEncryptor = new BasicTextEncryptor();

		textEncryptor.setPassword("opensezame");

		String bairroDesc = textEncryptor.decrypt(this.bairro);

		return bairroDesc;

	}

	// metodo que criptografia o bairro, utilizando o proprio setbairro
	public void setbairro(String bairro) {
		BasicTextEncryptor textEncryptor = new BasicTextEncryptor();

		textEncryptor.setPassword("opensezame");

		String bairroCrip = textEncryptor.encrypt(bairro);

		this.bairro = bairroCrip;

	}

	// metodo que descriptografia o cidade ,utilizando o proprio getcidade
	public String getcidade() {

		BasicTextEncryptor textEncryptor = new BasicTextEncryptor();

		textEncryptor.setPassword("opensezame");

		String cidadeDesc = textEncryptor.decrypt(this.cidade);

		return cidadeDesc;

	}

	// metodo que criptografia o cicade, utilizando o proprio setcidade
	public void setcidade(String cidade) {
		BasicTextEncryptor textEncryptor = new BasicTextEncryptor();

		textEncryptor.setPassword("opensezame");

		String cidadeCrip = textEncryptor.encrypt(cidade);

		this.cidade = cidadeCrip;

	}

	// metodo que descriptografia o cep ,utilizando o proprio getcep
	public String getcep() {

		BasicTextEncryptor textEncryptor = new BasicTextEncryptor();

		textEncryptor.setPassword("opensezame");

		String cepDesc = textEncryptor.decrypt(this.cep);

		return cepDesc;

	}

	// metodo que criptografia o cep, utilizando o proprio setcep
	public void setcep(String cep) {
		BasicTextEncryptor textEncryptor = new BasicTextEncryptor();

		textEncryptor.setPassword("opensezame");

		String cepCrip = textEncryptor.encrypt(cep);

		this.cep = cepCrip;

	}

	// metodo que descriptografia o estado ,utilizando o proprio getestado
	public String getestado() {

		BasicTextEncryptor textEncryptor = new BasicTextEncryptor();

		textEncryptor.setPassword("opensezame");

		String estadoDesc = textEncryptor.decrypt(this.estado);

		return estadoDesc;

	}

	// metodo que criptografia o estado, utilizando o proprio setcep
	public void setestado(String estado) {
		BasicTextEncryptor textEncryptor = new BasicTextEncryptor();

		textEncryptor.setPassword("opensezame");

		String estadoCrip = textEncryptor.encrypt(estado);

		this.estado = estadoCrip;

	}

	// metodo que descriptografia o cpf ,utilizando o proprio getcpf
	public String getcpf() {

		BasicTextEncryptor textEncryptor = new BasicTextEncryptor();

		textEncryptor.setPassword("opensezame");

		String cpfDesc = textEncryptor.decrypt(this.cpf);

		return cpfDesc;

	}

	// metodo que criptografia o cpf, utilizando o proprio setcpf
	public void setcpf(String cpf) {
		BasicTextEncryptor textEncryptor = new BasicTextEncryptor();

		textEncryptor.setPassword("opensezame");

		String cpfCrip = textEncryptor.encrypt(cpf);

		this.cpf = cpfCrip;

	}

}
