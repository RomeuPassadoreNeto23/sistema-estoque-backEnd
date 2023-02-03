package loja.sistema.rest;

import java.net.URI;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import loja.sistema.model.Pedido;
import loja.sistema.repository.PedidoRepository;
import loja.sistema.repository.ProductRepository;

@RestController
@RequestMapping("api/pedido")
@CrossOrigin
public class PedidoRest {
	@Autowired
	private PedidoRepository repository;

	@Autowired
	public ProductRepository productRepository;

	// api para listar pedido
	@RequestMapping(value = "", method = RequestMethod.GET)
	public List<Pedido> lista() {
		return repository.findAll();

	}

	// api para criar pedido
	@RequestMapping(value = "", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> criandoPedido(@RequestBody Pedido pedido) {
		System.out.println(pedido);

		if (pedido.getProduct() == null || pedido.getbairro().equals("") || pedido.getNomeDoCliente().equals("")
				|| pedido.getSobrenomeDoCliente().equals("") || pedido.getTelefone().equals("")
				|| pedido.getnumero().equals("") || pedido.getendereco().equals("") || pedido.getbairro().equals("")
				|| pedido.getcep().equals("") || pedido.getcidade().equals("") || pedido.getestado().equals("")
				|| pedido.getcpf().equals("")) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();

		}

		if (pedido.getProduct().getQuantidadeEmEstoque() <= 0 || pedido.getQuantidadeDeProduct() <= 0) {
			return ResponseEntity.status(HttpStatus.CHECKPOINT).build();
		}
		Integer valorFifinal = pedido.getProduct().getQuantidadeEmEstoque() - pedido.getQuantidadeDeProduct();
		pedido.getProduct().setQuantidadeEmEstoque(valorFifinal);
		productRepository.save(pedido.getProduct());
		String numerico = "0123456789";
		// create random string builder
		StringBuilder sb = new StringBuilder();

		// create an object of Random class
		Random random = new Random();

		// specify length of random string
		int length = 4;

		for (int i = 0; i < length; i++) {

			// generate random index number
			int index = random.nextInt(numerico.length());

			// get character specified by index
			// from the string
			char randomnumero = numerico.charAt(index);

			// append the character to string builder
			sb.append(randomnumero);
		}

		String randomrandomNumero = sb.toString();
		String codigoPedido = pedido.getNomeDoCliente() + ":" + randomrandomNumero;
		pedido.setCodPedido(codigoPedido);

		repository.save(pedido);
		return ResponseEntity.created(URI.create("/" + pedido.getId())).body(pedido);

	}

	// api para excluir pediudo
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> excluirPedido(@PathVariable("id") Long idPedido) {
		List<Pedido> lista = repository.findAll();
		try {
			for (Pedido pedido : lista) {
				if (pedido.getId().equals(idPedido)) {
					System.out.println("pedi: " + pedido);

					if (pedido.getStatus().equals(pedido.getStatus().CANCELADA)) {
						System.out.println("oi1");
						Integer valorFifinal = pedido.getProduct().getQuantidadeEmEstoque()
								+ pedido.getQuantidadeDeProduct();
						pedido.getProduct().setQuantidadeEmEstoque(valorFifinal);
						productRepository.save(pedido.getProduct());
						repository.deleteById(idPedido);
						return ResponseEntity.noContent().build();

					} else if (pedido.getStatus().equals(pedido.getStatus().FECHADA)) {

						repository.deleteById(idPedido);
						return ResponseEntity.noContent().build();

					} else {
						return ResponseEntity.status(HttpStatus.CONFLICT).build();
					}

				}

			}

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();

		}
		return ResponseEntity.noContent().build();

	}

	// api para alterar pedido
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> alterarPedido(@RequestBody Pedido pedido, @PathVariable("id") Long idpedido) {
		System.out.println(pedido);
		List<Pedido> lista = repository.findAll();
		for (Pedido pedido2 : lista) {
			if (pedido.getCodPedido().equals(pedido2.getCodPedido())) {
				if (pedido.getQuantidadeDeProduct() == pedido2.getQuantidadeDeProduct()) {
					repository.save(pedido);
				} else if (pedido.getQuantidadeDeProduct() > pedido2.getQuantidadeDeProduct()) {
					Integer valorFifinal = pedido.getProduct().getQuantidadeEmEstoque()
							- pedido.getQuantidadeDeProduct();
					pedido.getProduct().setQuantidadeEmEstoque(valorFifinal);
					productRepository.save(pedido.getProduct());
					repository.save(pedido);

				} else {
					Integer valorFifinal = pedido.getProduct().getQuantidadeEmEstoque()
							+ pedido.getQuantidadeDeProduct();
					pedido.getProduct().setQuantidadeEmEstoque(valorFifinal);
					productRepository.save(pedido.getProduct());
					repository.save(pedido);

				}
			}

		}
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(URI.create("/api/pedido/"));
		return new ResponseEntity<Void>(headers, HttpStatus.OK);

	}

	// api para bucar pedido
	@RequestMapping(value = "/buscar/", method = RequestMethod.POST)
	public ResponseEntity<?> buscarPedido(@RequestBody String parametro) {
		List<Pedido> list = repository.findAll();
		for (Pedido pedido : list) {
			if (pedido.getTelefone().contains(parametro.replace("\"", ""))
					|| pedido.getendereco().contains(parametro.replace("\"", ""))
					|| pedido.getnumero().contains(parametro.replace("\"", ""))
					|| pedido.getbairro().contains(parametro.replace("\"", ""))
					|| pedido.getcidade().contains(parametro.replace("\"", ""))
					|| pedido.getcep().contains(parametro.replace("\"", ""))
					|| pedido.getestado().contains(parametro.replace("\"", ""))
					|| pedido.getcpf().contains(parametro.replace("\"", ""))) {

				List lista = new ArrayList();
				lista.add(pedido);
				return ResponseEntity.ok().body(lista);

			}
		}
		List<Pedido> pedidos = repository.buscarPedido(parametro.replace("\"", ""));
		return ResponseEntity.ok(pedidos);

	}
}
