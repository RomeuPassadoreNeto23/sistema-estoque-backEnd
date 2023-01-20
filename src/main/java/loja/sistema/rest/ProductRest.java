package loja.sistema.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
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
	
	@RequestMapping(value = "",method = RequestMethod.GET)
 public List<Product> lista(){
	 return repository.findAll();
 }
}
