package loja.sistema.rest;

import java.net.URI;
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

import loja.sistema.model.Product;
import loja.sistema.repository.ProductRepository;

@RestController
@RequestMapping("api/product")
@CrossOrigin
public class ProductRest {
	@Autowired
	private ProductRepository repository;

	// api para listar Produto
	@RequestMapping(value = "", method = RequestMethod.GET)
	public List<Product> lista() {
		return repository.findAll();
	}

	// api para criar Produto
	@RequestMapping(value = "", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> criandoProduct(@RequestBody Product product) {
		// faz a verificação de campos vazio
		if (product.getName().equals("") || product.getPrecoDoProduct() == null
				|| product.getQuantidadeEmEstoque() == null || product.getImg().equals("")) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();

		}

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
			char randomChar = numerico.charAt(index);

			// append the character to string builder
			sb.append(randomChar);
		}

		String randomString = sb.toString();
		String codProduto = product.getName() + ":" + randomString;
		product.setCodProduct(codProduto);
		repository.save(product);
		return ResponseEntity.created(URI.create("/" + product.getId())).body(product);

	}

	// método pra excluir algum Produto pelo id
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> excluirProduct(@PathVariable("id") Long idProduct) {
		try {
			repository.deleteById(idProduct);

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();

		}
		return ResponseEntity.noContent().build();

	}
     // api alterar Product
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> alterarProduct(@RequestBody Product product, @PathVariable("id") Long idproduto) {
		if (idproduto.longValue() != product.getId().longValue()) {
			throw new RuntimeException("id inválidado");

		}
		repository.save(product);
		HttpHeaders headers = new HttpHeaders();

		headers.setLocation(URI.create("/api/Product/"));
		return new ResponseEntity<Void>(headers, HttpStatus.OK);

	}

	// api para buscar produto
	@RequestMapping(value = "/buscarProduct/", method = RequestMethod.POST)
	public ResponseEntity<?> busacrProduct(@RequestBody String parametro) {
		List<Product> products = repository.buscarPorduct(parametro.replace("\"", ""));
		return ResponseEntity.ok(products);

	}

}
