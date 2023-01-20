package loja.sistema.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import loja.sistema.model.Pedido;

public interface PedidoRepository extends PagingAndSortingRepository<Pedido, Long> {
	@Query("SELECT p FROM Pedido p ORDER BY p.id desc")
      List<Pedido> findAll();
}
