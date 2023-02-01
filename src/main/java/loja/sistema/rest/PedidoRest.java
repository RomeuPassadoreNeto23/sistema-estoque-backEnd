package loja.sistema.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import loja.sistema.model.Pedido;
import loja.sistema.repository.PedidoRepository;
@RestController
@RequestMapping("api/pedido")
@CrossOrigin
public class PedidoRest {
	@Autowired
	private PedidoRepository repository;
	@RequestMapping(value = "", method = RequestMethod.GET)
	public List<Pedido> lista(){
		return repository.findAll();
		
	}

}
